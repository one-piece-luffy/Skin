package com.baofu.skin;

import android.app.Application;

import com.baofu.lib.skin.SkinManager;

public class BaseApplication extends Application {
    private static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        //皮肤切换初始化
        SkinManager.getInstance().init(this);
    }
}
