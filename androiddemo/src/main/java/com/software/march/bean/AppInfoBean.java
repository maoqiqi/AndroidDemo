package com.software.march.bean;

import android.graphics.drawable.Drawable;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 应用信息的封装类
 * @date 2016/12/21
 */
public class AppInfoBean {

    // 应用图标
    private Drawable icon;

    // 应用名称
    private String appName;

    // 包名
    private String packageName;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public String toString() {
        return "AppInfoBean{" +
                "appName='" + appName + '\'' +
                ", icon=" + icon +
                ", packageName='" + packageName + '\'' +
                '}';
    }
}