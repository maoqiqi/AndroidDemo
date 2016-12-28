package com.software.march.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.software.march.R;
import com.software.march.utils.SPUtils;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description Application installation location
 * @date 2016/12/28
 */
public class ApplicationInstallationLocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_application_installation_location);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 从API级别8开始，您可以允许您的应用程序安装在外部存储（例如，设备的SD卡）。
        // 这是一个可选的功能，你可以声明为Android应用程序：installlocation清单属性。
        // 如果不声明此属性，则应用程序将仅安装在内部存储区，无法将其移动到外部存储区.。
        // To allow the system to install your application on the external storage,
        // modify your manifest file to include the android:installLocation attribute in the <manifest> element,
        // with a value of either "preferExternal" or "auto". For example:
        // <manifest xmlns:android="http://schemas.android.com/apk/res/android"
        //      android:installLocation="preferExternal">
        // 如果你声明“preferexternal”，你要求你的应用程序被安装在外部存储器上，但系统不保证你的应用程序将被安装在外部存储器。
        // 如果外部存储已满，系统将将其安装在内部存储器上。用户还可以在两个位置之间移动应用程序.。
        // 如果您声明“自动”，则指示您的应用程序可能安装在外部存储区，但您不具有安装位置的首选项。
        // 系统将根据几个因素决定在哪里安装应用程序.。用户还可以在两个位置之间移动应用程序.。
        // 当应用程序被安装在一个API级别低于8，设备安卓：installlocation属性被忽略，应用程序安装在内部存储。
    }
}