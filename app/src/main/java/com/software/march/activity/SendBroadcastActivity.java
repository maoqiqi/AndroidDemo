package com.software.march.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.software.march.R;
import com.software.march.receiver.NormalDynamicBroadcastReceiver;
import com.software.march.receiver.NormalStaticBroadcastReceiver;
import com.software.march.receiver.OrderBroadcastReceiver;
import com.software.march.utils.SPUtils;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 发送广播
 * @date 2016/12/29
 */
public class SendBroadcastActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSendNormalDynamicBroadcast;
    private Button btnSendNormalStaticBroadcast;
    private Button btnSendNormalBroadcast;
    private Button btnSendOrderDynamicBroadcast;
    private Button btnSendOrderStaticBroadcast;
    private Button btnSendOrderBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_send_broadcast);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnSendNormalDynamicBroadcast = (Button) findViewById(R.id.btn_send_normal_dynamic_broadcast);
        btnSendNormalStaticBroadcast = (Button) findViewById(R.id.btn_send_normal_static_broadcast);
        btnSendNormalBroadcast = (Button) findViewById(R.id.btn_send_normal_broadcast);
        btnSendOrderDynamicBroadcast = (Button) findViewById(R.id.btn_send_order_dynamic_broadcast);
        btnSendOrderStaticBroadcast = (Button) findViewById(R.id.btn_send_order_static_broadcast);
        btnSendOrderBroadcast = (Button) findViewById(R.id.btn_send_order_broadcast);

        btnSendNormalDynamicBroadcast.setOnClickListener(this);
        btnSendNormalStaticBroadcast.setOnClickListener(this);
        btnSendNormalBroadcast.setOnClickListener(this);
        btnSendOrderDynamicBroadcast.setOnClickListener(this);
        btnSendOrderStaticBroadcast.setOnClickListener(this);
        btnSendOrderBroadcast.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send_normal_dynamic_broadcast:
                // 发送一般广播(动态注册)
                sendNormalDynamicBroadcast();
                break;
            case R.id.btn_send_normal_static_broadcast:
                // 发送一般广播(静态注册)
                sendNormalStaticBroadcast();
                break;
            case R.id.btn_send_normal_broadcast:
                // 发送一般广播(动态/静态)
                sendNormalBroadcast();
                break;
            case R.id.btn_send_order_dynamic_broadcast:
                // 发送有序广播(动态注册)
                sendOrderDynamicBroadcast();
                break;
            case R.id.btn_send_order_static_broadcast:
                // 发送有序广播(静态注册)
                sendOrderStaticBroadcast();
                break;
            case R.id.btn_send_order_broadcast:
                // 发送有序广播(动态/静态)
                sendOrderBroadcast();
                break;
        }
    }

    // 发送一般广播(动态注册)
    private void sendNormalDynamicBroadcast() {
        Intent intent = new Intent(NormalDynamicBroadcastReceiver.ACTION_NORMAL_DYNAMIC_BROADCAST_RECEIVER);
        // 发送一般广播
        sendBroadcast(intent);
        Toast.makeText(this, "发送一般广播(动态注册)", Toast.LENGTH_SHORT).show();
    }

    // 发送一般广播(静态注册)
    private void sendNormalStaticBroadcast() {
        Intent intent = new Intent(NormalStaticBroadcastReceiver.ACTION_NORMAL_STATIC_BROADCAST_RECEIVER);
        sendBroadcast(intent);
        Toast.makeText(this, "发送一般广播(静态注册)", Toast.LENGTH_SHORT).show();
    }

    // 发送一般广播(动态/静态)
    private void sendNormalBroadcast() {
        Intent intent = new Intent(OrderBroadcastReceiver.ACTION_ORDER_BROADCAST_RECEIVER);
        sendBroadcast(intent);
        Toast.makeText(this, "发送一般广播(动态/静态)", Toast.LENGTH_SHORT).show();
    }

    // 发送有序广播(动态注册)
    private void sendOrderDynamicBroadcast() {
        Intent intent = new Intent(OrderBroadcastReceiver.ACTION_ORDER_BROADCAST_RECEIVER);
        // 发送有序广播
        sendOrderedBroadcast(intent, null);
        Toast.makeText(this, "发送有序广播(动态注册)", Toast.LENGTH_SHORT).show();
    }

    // 发送有序广播(静态注册)
    private void sendOrderStaticBroadcast() {
        Intent intent = new Intent(OrderBroadcastReceiver.ACTION_ORDER_BROADCAST_RECEIVER);
        sendOrderedBroadcast(intent, null);
        Toast.makeText(this, "发送有序广播(静态注册)", Toast.LENGTH_SHORT).show();
    }

    // 发送有序广播(动态/静态)
    private void sendOrderBroadcast() {
        Intent intent = new Intent(OrderBroadcastReceiver.ACTION_ORDER_BROADCAST_RECEIVER);
        sendOrderedBroadcast(intent, null);
        Toast.makeText(this, "发送有序广播(动态/静态)", Toast.LENGTH_SHORT).show();
    }
}