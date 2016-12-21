package com.software.march.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;

import com.software.march.bean.AppInfoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description
 * @date 2016/12/21
 */
public class AppUtils {

    /**
     * 判断某个应用程序是不是三方的应用程序
     *
     * @param info
     * @return
     */
    public boolean filterApp(ApplicationInfo info) {
        if ((info.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
            return true;
        } else if ((info.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
            return true;
        }
        return false;
    }

    /**
     * 得到手机中所有应用信息的列表
     *
     * @param context 上下文
     * @return 应用信息的列表对象
     */
    public static List<AppInfoBean> getAllAppInfoBeans(Context context) {
        List<AppInfoBean> list = new ArrayList<AppInfoBean>();
        // 得到应用的packgeManager
        PackageManager packageManager = context.getPackageManager();
        // 创建一个主界面的intent
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        // 得到包含应用信息的列表
        List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(intent, 0);
        if (resolveInfos != null) {
            // 遍历
            for (ResolveInfo ri : resolveInfos) {
                // 得到图标
                Drawable icon = ri.loadIcon(packageManager);
                // 得到应用名称
                String appName = ri.loadLabel(packageManager).toString();
                // 得到包名
                String packageName = ri.activityInfo.packageName;

                // 封装应用信息对象
                AppInfoBean bean = new AppInfoBean();
                bean.setIcon(icon);
                bean.setAppName(appName);
                bean.setPackageName(packageName);

                // 添加到list
                list.add(bean);
            }
        }
        return list;
    }
}