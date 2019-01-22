package com.software.march.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description
 * @date 2016/12/13
 */
public class SPUtils {

    private static final String NAME = "com.software.march_preferences";
    private static final String NIGHT = "night";

    public static void setNight(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        preferences.edit().putBoolean(NIGHT, true).commit();
    }

    public static void setDay(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        preferences.edit().putBoolean(NIGHT, false).commit();
    }

    public static void changeDayNight(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        boolean change = !preferences.getBoolean(NIGHT, false);
        preferences.edit().putBoolean(NIGHT, change).commit();
    }

    public static boolean isNight(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean(NIGHT, false);
    }

    public static int getThemeRes(Context context) {
        if (!isNight(context)) {
            return Constant.RESOURCES_DAYTHEME;
        } else {
            return Constant.RESOURCES_NIGHTTHEME;
        }
    }
}