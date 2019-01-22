package com.software.march.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 申请权限帮助类
 * @date 2016/12/23
 */
public class RPUtils {

    public static final boolean requestPermissions(final Activity activity, final String permission, final int requestCode) {
        // This Build is < 6 , you can Access contacts here.

        // 如果 SDK 版本是否小于 23
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            // 不用做处理
            return true;
        }

        // 检查应用是否拥有该权限，被授权返回值为PERMISSION_GRANTED，否则返回PERMISSION_DENIED
        if (ActivityCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED) {
            // 不用做处理
            return true;
        }

        // 这个API主要用于给用户一个申请权限的解释，该方法只有在用户在上一次已经拒绝过你的这个权限申请。
        // 也就是说，用户已经拒绝一次了，你又弹个授权框，你需要给用户一个解释，为什么要授权，则使用该方法。
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
            /*Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {

                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });*/
        } else {
            // 将弹出请求授权对话框，这个方法在M之前版本调用，OnRequestPermissionsResultCallback 直接被调用，
            // 带着正确的 PERMISSION_GRANTED 或者 PERMISSION_DENIED 。
            ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
        }

        return false;
    }
}