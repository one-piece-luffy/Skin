package com.baofu.lib.skin;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.ArrayList;
import java.util.List;

public class SkinFactory implements LayoutInflater.Factory2 {
    private AppCompatActivity mAppCompatActivity;
    private List<SkinItem> skinItems = new ArrayList<>();

    public SkinFactory(AppCompatActivity appCompatActivity) {
        this.mAppCompatActivity = appCompatActivity;
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
//        View view = createView(name, context, attrs);
//        if (view != null) {
//            collectViewAttr(view, context, attrs);
//        }
//
//        return view;
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        AppCompatDelegate delegate = mAppCompatActivity.getDelegate();
        View view = delegate.createView(parent, name, context, attrs);
        try {
            if (view == null) {
                view = ViewProducer.createViewFromTag(context, name, attrs);
            }
            if (view == null) {
                return null;
            }
            collectViewAttr(view, context, attrs);
        } catch (Exception e) {
            e.printStackTrace();
            view = null;
        }

        return view;
    }


    private void collectViewAttr(View view, Context context, AttributeSet attrs) {
        List<SkinAttr> skinAttrs = new ArrayList<>();
        int attCount = attrs.getAttributeCount();
        for (int i = 0; i < attCount; ++i) {
            String attributeName = attrs.getAttributeName(i);
            String attributeValue = attrs.getAttributeValue(i);
            if (isSupportedAttr(attributeName)) {
                if (attributeValue.startsWith("@")) {
                    int resId = Integer.parseInt(attributeValue.substring(1));
                    String resName = context.getResources().getResourceEntryName(resId);
                    //加个前缀，标记要替换的资源
                    if(!TextUtils.isEmpty(resName)&&resName.startsWith("skin")){
                        String attrType = context.getResources().getResourceTypeName(resId);
                        skinAttrs.add(new SkinAttr(attributeName, attrType, resName, resId));
                        SkinItem skinItem = new SkinItem(view, skinAttrs);
                        skinItem.apply();
                        skinItems.add(skinItem);
                    }

                }
            }
        }
    }

    private boolean isSupportedAttr(String attributeName) {
        return "background".equals(attributeName) || "textColor".equals(attributeName)|| "src".equals(attributeName)
        ||"textColorHint".equals(attributeName);
    }

    public void apply() {
        if(skinItems==null||skinItems.isEmpty())
            return;
        for (SkinItem item : skinItems) {
            if(item==null)
                continue;
            item.apply();
        }
    }

}
