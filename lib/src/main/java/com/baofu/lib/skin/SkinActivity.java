package com.baofu.lib.skin;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.LayoutInflaterCompat;
import androidx.fragment.app.FragmentActivity;


public abstract class SkinActivity extends AppCompatActivity implements SkinManager
        .SkinUpdateListener {

    public String TAG = getClass().getSimpleName();
    private SkinFactory mSkinFactory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mSkinFactory = new SkinFactory(this);

//        getLayoutInflater().setFactory2(mSkinFactory);
        LayoutInflaterCompat.setFactory2(getLayoutInflater(), mSkinFactory);
        SkinManager.getInstance().addSkinUpdateListener(TAG, this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SkinManager.getInstance().removeSkinUpdateListener(TAG);
    }

    @Override
    public void onSkinUpdate() {
        if (mSkinFactory != null) {
            mSkinFactory.apply();
            changeStatusBar();
        }
    }
    public abstract void changeStatusBar();

}
