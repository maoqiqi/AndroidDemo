package com.software.march.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 静态注册的广播接收器
 * @date 2016/12/29
 */
public class NormalStaticBroadcastReceiver extends BroadcastReceiver {

    public static final String ACTION_NORMAL_STATIC_BROADCAST_RECEIVER = "com.software.march.NORMAL_STATIC_BROADCAST_RECEIVER";

    private final String TAG = getClass().getSimpleName();

    public NormalStaticBroadcastReceiver() {
        Log.e(TAG, TAG + "()");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.e(TAG, TAG + " onReceive() " + action);
        Toast.makeText(context, TAG + " onReceive() " + action, Toast.LENGTH_SHORT).show();
    }
}