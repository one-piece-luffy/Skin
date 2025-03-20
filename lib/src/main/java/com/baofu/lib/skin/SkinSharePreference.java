package com.baofu.lib.skin;

import android.content.Context;
import android.content.SharedPreferences;

public class SkinSharePreference {

    private static void putString(Context context,String key,String value){
        SharedPreferences mSharedPreferences = context.getSharedPreferences("app", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }
    private static String getString(Context context,String key,String defaultValue){
        SharedPreferences sp = context.getSharedPreferences("app", Context.MODE_PRIVATE);
        return sp.getString(key, defaultValue);
    }
    private static void putLong(Context context,String key,long value){
        SharedPreferences mSharedPreferences = context.getSharedPreferences("app", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }
    private static long getLong(Context context,String key,long defaultValue){
        SharedPreferences sp = context.getSharedPreferences("app", Context.MODE_PRIVATE);
        return sp.getLong(key, defaultValue);
    }
    private static void putInt(Context context,String key,int value){
        SharedPreferences mSharedPreferences = context.getSharedPreferences("app", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }
    private static int getInt(Context context,String key,int defaultValue){
        SharedPreferences sp = context.getSharedPreferences("app", Context.MODE_PRIVATE);
        return sp.getInt(key, defaultValue);
    }
    private static void putBoolean(Context context,String key,boolean value){
        SharedPreferences mSharedPreferences = context.getSharedPreferences("app", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }
    private static boolean getBoolean(Context context,String key,boolean defaultValue){
        SharedPreferences sp = context.getSharedPreferences("app", Context.MODE_PRIVATE);
        return sp.getBoolean(key, defaultValue);
    }

    /**
     * 保存皮肤
     */
    public static void saveSkin(Context context,String value) {
        putString(context,"last_skin",value);
    }
    /**
     * 获取皮肤
     */
    public static String getSkin(Context context) {
        return getString(context,"last_skin",null);

    }
}
