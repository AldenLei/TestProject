package com.qf.administrator.baozou.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 登录的SharedPrefence储存
 * Created by Alden on 2017/5/9.
 */

public class SharedPreferencesUtils {
    //jsoninfo ,isLogin
    public static void saveLoginInfo(Context context,String key,String vlaue){
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginInfo",Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key,vlaue).commit();

    }
    public static String getLoginInfo(Context context,String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginInfo",Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,"");

    }
    public static boolean isLogin(Context context){
        boolean b = "1".equals(SharedPreferencesUtils.getLoginInfo(context,"isLogin"));
        return b;
    }

    /**
     * 保存当前主题
     * @param context
     * @param key
     * @param vlaue
     */
    public static void saveTheme(Context context,String key,String vlaue){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Theme",Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key,vlaue).commit();
    }

    /**
     * 获取主题
     * @param context
     * @param key
     * @return 默认0为白天，1为晚上
     */
    public static String getTheme(Context context,String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Theme",Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,"0");
    }
    public static boolean isDayTheme(Context context){
        boolean b = "0".equals(SharedPreferencesUtils.getTheme(context,"isDayTheme"));
        return b;
    }
}
