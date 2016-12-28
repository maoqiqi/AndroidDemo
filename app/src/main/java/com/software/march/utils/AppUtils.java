package com.software.march.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.telephony.SmsManager;

import com.software.march.bean.AppInfoBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description Helper class for App
 * @date 2016/11/11
 */
public class AppUtils {

    private AppUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * Gets the current version code of the application
     *
     * @param context
     * @return version code
     */
    public static int getVersionCode(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Gets the current version name of the application
     *
     * @param context
     * @return version name
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets the current name for the application
     *
     * @param context
     * @return app name
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 1.创建一个Intent(隐式)
    // 2.携带数据
    // 3.startActivity(intent)

    /**
     * install app
     *
     * @param context
     * @param file
     */
    public static void installApp(Context context, File file) {
        // Intent intent = new Intent(Intent.ACTION_VIEW);
        // //09-05 12:59:20.553: I/ActivityManager(1179): Displayed com.android.packageinstaller/.PackageInstallerActivity: +282ms
        Intent intent = new Intent(Intent.ACTION_INSTALL_PACKAGE);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /**
     * 调起系统所有的播放 - 隐式意图
     *
     * @param context
     * @param path
     */
    public static void playVideo(Context context, String path) {
        Intent intent = new Intent();
        intent.setDataAndType(Uri.parse(path), "video/*");
        context.startActivity(intent);
    }

    /**
     * 进入拨号界面
     *
     * @param context
     */
    public static void dial(Context context, String number) {
        // 08-28 03:27:09.976: I/ActivityManager(1222): Displayed com.android.dialer/.DialtactsActivity: +535ms
        String action = Intent.ACTION_DIAL;// android.intent.action.DIAL
        Intent intent = new Intent(action);
        intent.setData(Uri.parse("tel:" + number)); // <data android:scheme="tel" />
        context.startActivity(intent);
    }

    /**
     * 直接拨打电话
     * <uses-permission android:name="android.permission.CALL_PHONE"/>
     *
     * @param context
     */
    private void call(Context context, String number) {
        // 08-28 03:38:59.717: I/ActivityManager(1222): Displayed com.android.phone/.PrivilegedOutgoingCallBroadcaster: +570ms
        Intent intent = new Intent(Intent.ACTION_CALL);// android.intent.action.CALL
        intent.setData(Uri.parse("tel:" + number));
        // context.startActivity(intent);
    }

    /**
     * 进入编辑短信界面
     *
     * @param context
     */
    private void sendto(Context context, String number, String sms) {
        // 08-28 04:00:02.420: I/ActivityManager(1222): Displayed com.android.mms/.ui.ComposeMessageActivity: +132ms
        Intent intent = new Intent(Intent.ACTION_SENDTO);// android.intent.action.SENDTO
        intent.setData(Uri.parse("smsto:" + number));
        // 携带额外数据
        intent.putExtra("sms_body", sms);
        context.startActivity(intent);
    }

    /**
     * 直接将短信发送出去
     */
    private void sendMessage(String number, String sms) {
        // 1.得到SmsManager的对象
        SmsManager smsManager = SmsManager.getDefault();
        // 2.发送文本信息(短信)
        smsManager.sendTextMessage(number, null, sms, null, null);
    }


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