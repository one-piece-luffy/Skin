package com.baofu.lib.skin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;

import androidx.core.content.res.ResourcesCompat;

import java.util.HashMap;
import java.util.Map;

public class SkinManager {

    @SuppressLint("StaticFieldLeak")
    private static SkinManager mInstance;
    private Resources mSkinResources;
    private Context context;
    private String skinPackageName;
    public Map<String, SkinUpdateListener> mListeners = new HashMap<>();

    //皮肤后缀
    public String mSkinSuffix;

    private SkinManager() {
    }

    public static SkinManager getInstance() {
        if (mInstance == null) {
            mInstance = new SkinManager();
        }
        return mInstance;
    }

    private boolean judge() {
        if (context == null) {
            Log.e("SkinManager", "context is null");
            return true;
        }
        return false;
    }


    public interface SkinUpdateListener {
        void onSkinUpdate();
    }

    private void notifySkinUpdate() {
        for (SkinUpdateListener listener : mListeners.values()) {
            listener.onSkinUpdate();
        }
    }

    /**
     * API
     */
    public void addSkinUpdateListener(String key, SkinUpdateListener listener) {
        if (listener == null || judge() || mListeners == null)
            return;

        mListeners.put(key, listener);
    }

    public void removeSkinUpdateListener(String key) {
        if (mListeners == null) {
            return;
        }
        mListeners.remove(key);
    }

    public void init(Context context) {
        this.context = context.getApplicationContext();
        mSkinSuffix = SkinSharePreference.getSkin(context);
        if (mSkinSuffix == null) {
            mSkinSuffix = "";
        }
        if (TextUtils.isEmpty(mSkinSuffix)) {
            skinPackageName = context.getPackageName();
            mSkinResources = context.getResources();
            return;
        }
        loadSkin(mSkinSuffix);
    }

    public void loadSkin(String skin) {
        if (judge()) {
            return;
        }
//        if (!TextUtils.isEmpty(mSkinSuffix) && mSkinSuffix.equals(skin)) {
//            return;
//        }
        SkinSharePreference.saveSkin(context, skin);
        mSkinSuffix = skin;
        skinPackageName = context.getPackageName();
        mSkinResources = context.getResources();
        notifySkinUpdate();
    }

    public int getColor(String resName, int resId) {
        if (judge()) {
            return 0;
        }
        int originColor = context.getResources().getColor(resId);
        if (TextUtils.isEmpty(mSkinSuffix)) {
            return originColor;
        } else {
            int newResId = context.getResources().getIdentifier(resName + mSkinSuffix, "color", skinPackageName);
            int newColor;
            try {
                newColor = mSkinResources.getColor(newResId);
            } catch (Resources.NotFoundException e) {
                e.printStackTrace();
                return originColor;
            }
            return newColor;
        }

    }

    public Drawable getDrawable(String resName, int resId) {
        if (judge()) {
            return null;
        }
        Drawable originDrawable = ResourcesCompat.getDrawable(context.getResources(), resId, null);
        if (mSkinResources == null) {
            return originDrawable;
        }
        int newResId = mSkinResources.getIdentifier(resName + mSkinSuffix, "drawable", skinPackageName);
        Drawable newDrawable;
        try {
            newDrawable = ResourcesCompat.getDrawable(mSkinResources, newResId, null);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return originDrawable;
        }
        return newDrawable;
    }

    public void restoreDefaultTheme() {
        if (judge()) {
            return;
        }
        SkinSharePreference.saveSkin(context, "");
        mSkinSuffix = "";
        mSkinResources = null;
        notifySkinUpdate();
    }

}
