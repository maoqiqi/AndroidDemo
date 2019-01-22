package com.software.march.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.software.march.R;
import com.software.march.appcommonlibrary.BaseActivity;
import com.software.march.utils.NotificationsUtils;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description Notification
 * @date 2017/3/26
 */
public class NotificationActivity extends BaseActivity implements View.OnClickListener {

    private Button btnNotification;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notification;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        btnNotification = (Button) findViewById(R.id.btn_notification);

        btnNotification.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (NotificationsUtils.isNotificationEnabled(this)) {
            Toast.makeText(mContext, "true", Toast.LENGTH_SHORT).show();
            createNotification();
        } else {
            Toast.makeText(mContext, "false", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivity(intent);
        }
    }

    private void createNotification() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        // FLAG_CANCEL_CURRENT：如果构建的PendingIntent已经存在,则取消前一个,重新构建一个。
        // FLAG_NO_CREATE：如果前一个PendingIntent已经不存在了,将不再构建它。
        // FLAG_ONE_SHOT：表明这里构建的PendingIntent只能使用一次。
        // FLAG_UPDATE_CURRENT：如果构建的PendingIntent已经存在,则替换它,常用。
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // 得到NotificationManager
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // ## 低于API Level 11版本,也就是Android 2.3.3 以下的系统中,setLatestEventInfo()函数是唯一的实现方法。
        // 创建Notification实例
        // 第一个参数是传入通知的图片,第二个是传入通知的标题
        // Notification notification = new Notification(R.drawable.ic_avatar, "测试", System.currentTimeMillis());

        // 调用setLatestEvent(),里面有四个参数,分别是context,标题,内容,和pendingIntent4个参数
        // notification.setLatestEventInfo(mContext, "安卓案例", "不同版本下Notification创建方法", pendingIntent);
        // manager.notify(Notification.FLAG_AUTO_CANCEL, notification);

        Notification notification;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            // ## 高于API Level 11,低于API Level 16 (Android 4.1.2)版本的系统中,可使用Notification.Builder来构造函数。
            // 但要使用getNotification()来使notification实现。此时,前面版本在notification中设置的Flags,icon等属性都已经无效,要在builder里面设置。
            notification = new Notification.Builder(getApplicationContext())
                    .setTicker("您收到一条新信息") // 设置在status bar上显示的提示文字
                    .setSmallIcon(R.drawable.ic_avatar)
                    .setContentTitle("安卓案例")
                    .setContentText("不同版本下Notification创建方法")
                    .setNumber(1) // 在TextView的右方显示的数字。这个number同时也起到一个序列号的左右,如果多个触发多个通知（同一ID）,可以指定显示哪一个。
                    .setContentIntent(pendingIntent)
                    .setWhen(System.currentTimeMillis()) // 设置发出通知的时间为发出通知时的系统时间
                    .setAutoCancel(true) // 设为true,notification将无法通过左右滑动的方式清除,可用于添加常驻通知,必须调用cancel方法来清除
                    .setOngoing(true) // 设置点击后通知消失
                    // 用于向通知添加声音、闪灯和振动效果的
                    // 设置notification的默认效果,有以下几种
                    // Notification.DEFAULT_ALL：铃声、闪光、震动均系统默认。
                    // Notification.DEFAULT_SOUND：系统默认铃声。
                    // Notification.DEFAULT_VIBRATE：系统默认震动。
                    // Notification.DEFAULT_LIGHTS：系统默认闪光。
                    .setDefaults(Notification.DEFAULT_ALL)
                    .getNotification();
        } else {
            // ## 高于API Level 16 的版本,就可以用Builder 和build()函数来配套的方便使用notification了。
            notification = new Notification.Builder(getApplicationContext())
                    .setTicker("您收到一条新信息")
                    .setSmallIcon(R.drawable.ic_avatar)
                    .setContentTitle("安卓案例")
                    .setContentText("不同版本下Notification创建方法")
                    .setContentIntent(pendingIntent)
                    .setWhen(System.currentTimeMillis())
                    .setOngoing(true)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .build();
        }
        // Notification.FLAG_NO_CLEAR; // 点击清除按钮时就会清除消息通知,但是点击通知栏的通知时不会消失
        // Notification.FLAG_ONGOING_EVENT; // 点击清除按钮不会清除消息通知,可以用来表示在正在运行
        // Notification.FLAG_AUTO_CANCEL; // 点击清除按钮或点击通知后会自动消失
        // Notification.FLAG_INSISTENT; // 一直进行,比如音乐一直播放,直到用户响应
        // 通过通知管理器来发起通知。如果id 不同,则每通知,在status 那里增加一个提示
        manager.notify(Notification.FLAG_INSISTENT, notification);
    }
}