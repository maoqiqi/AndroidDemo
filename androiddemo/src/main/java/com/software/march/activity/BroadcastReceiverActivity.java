package com.software.march.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.software.march.R;
import com.software.march.receiver.NormalDynamicBroadcastReceiver;
import com.software.march.receiver.OrderBroadcastReceiver;
import com.software.march.receiver.OrderBroadcastReceiver1;
import com.software.march.utils.SPUtils;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 广播接收器
 * @date 2016/12/29
 */
public class BroadcastReceiverActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnRegisterNormalDynamicBroadcast;
    private Button btnUnregisterNormalDynamicBroadcast;
    private Button btnRegisterBroadcast;
    private Button btnUnregisterBroadcast;

    private NormalDynamicBroadcastReceiver receiver;

    private OrderBroadcastReceiver orderBroadcastReceiver;
    private OrderBroadcastReceiver1 orderBroadcastReceiver1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_broadcast_receiver);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnRegisterNormalDynamicBroadcast = (Button) findViewById(R.id.btn_register_normal_dynamic_broadcast);
        btnUnregisterNormalDynamicBroadcast = (Button) findViewById(R.id.btn_unregister_normal_dynamic_broadcast);
        btnRegisterBroadcast = (Button) findViewById(R.id.btn_register_broadcast);
        btnUnregisterBroadcast = (Button) findViewById(R.id.btn_unregister_broadcast);

        btnRegisterNormalDynamicBroadcast.setOnClickListener(this);
        btnUnregisterNormalDynamicBroadcast.setOnClickListener(this);
        btnRegisterBroadcast.setOnClickListener(this);
        btnUnregisterBroadcast.setOnClickListener(this);
    }

    // 1.广播事件处理属于系统级的事件处理。
    // 2.一个应用可以在发生特定事件时发送Broadcast, 系统中任何应用只要注册了对应Receiver就会接收到此Broadcast。
    // 3.一个应用如果对某个广播感兴趣, 就可以注册对应的Receiver来接收广播。
    // 4.广播事件机制是应用程序(进程间)之间通信的一种手段。

    // 常见系统广播
    // 收到短信广播:android.provider.Telephony.SMS_RECEIVED
    // 开机完成广播:Intent.ACTION_BOOT_COMPLETED
    // 应用被卸载广播:Intent.ACTION_PACKAGE_REMOVED
    // 手机锁屏:Intent.ACTION_SCREEN_OFF
    // 手机开屏:Intent.ACTION_SCREEN_ON

    // 区别静态注册与动态注册
    // 静态注册：
    // 1.注册方式:配置文件
    // 2.注册的时间:应用安装成功/手机开机完成,注册时不会创建对象,接收到广播才创建
    // 3.生命结束的时间(解注册):应用卸载对象执行onReceive()后就回收了
    // 4.应用情景:需要监听的时间为应用的整个生命过程中
    // 动态注册：
    // 1.注册方式:Java代码
    // 2.注册的时间:执行registerReceiver(receiver, intentFilter),注册就创建对象
    // 3.生命结束的时间(解注册):执行unregisterReceiver(),(activity退出必须解注册)解注册后对象被回收
    // 4.应用情景:只服务于某个Activity/Service

    // 区别两种广播
    // 一般广播:
    // 1.多人接收时是否有序:无序,都会同时执行
    // 2.是否可以中断:不可以
    // 有序广播
    // 1.多人接收时是否有序:有序,根据优先级和注册先后依次执行(优先级大的先执行,先注册的先执行)
    // 2.是否可以中断:可以,通过br.abortBroadcast()中断后,后面的接收器不能接收到此广播了

    // 1.发送一般广播(动态注册)
    // registerReceiver()方法调用时实例化。
    // NormalDynamicBroadcastReceiver: NormalDynamicBroadcastReceiver()
    // 第一次发送广播：
    // NormalDynamicBroadcastReceiver: NormalDynamicBroadcastReceiver onReceive() com.software.march.NORMAL_DYNAMIC_BROADCAST_RECEIVER
    // 第二次发送广播：
    // NormalDynamicBroadcastReceiver onReceive() com.software.march.NORMAL_DYNAMIC_BROADCAST_RECEIVER

    // 2.发送一般广播(静态注册)
    // 发送广播时才实例化对象。
    // 第一发送广播：
    // NormalStaticBroadcastReceiver: NormalStaticBroadcastReceiver()
    // NormalStaticBroadcastReceiver: NormalStaticBroadcastReceiver onReceive() com.software.march.NORMAL_STATIC_BROADCAST_RECEIVER
    // 第二次发送广播：
    // NormalStaticBroadcastReceiver: NormalStaticBroadcastReceiver()
    // NormalStaticBroadcastReceiver: NormalStaticBroadcastReceiver onReceive() com.software.march.NORMAL_STATIC_BROADCAST_RECEIVER

    // 3.发送一般广播(动态/静态)
    // 全部是动态注册广播：
    // registerReceiver()方法调用时实例化。
    // OrderBroadcastReceiver: OrderBroadcastReceiver()
    // OrderBroadcastReceiver1: OrderBroadcastReceiver1()
    // 发送广播：优先级高的先调用。
    // OrderBroadcastReceiver1: OrderBroadcastReceiver1 onReceive() com.software.march.ORDER_BROADCAST_RECEIVER
    // OrderBroadcastReceiver: OrderBroadcastReceiver onReceive() com.software.march.ORDER_BROADCAST_RECEIVER

    // 全部是静态注册广播：
    // 发送广播：优先级高的先调用。
    // OrderBroadcastReceiver3: OrderBroadcastReceiver3()
    // OrderBroadcastReceiver3: OrderBroadcastReceiver3 onReceive() com.software.march.ORDER_BROADCAST_RECEIVER
    // OrderBroadcastReceiver2: OrderBroadcastReceiver2()
    // OrderBroadcastReceiver2: OrderBroadcastReceiver2 onReceive() com.software.march.ORDER_BROADCAST_RECEIVER

    // 动态/静态注册广播都有：
    // registerReceiver()方法调用时实例化。
    // OrderBroadcastReceiver: OrderBroadcastReceiver()
    // OrderBroadcastReceiver1: OrderBroadcastReceiver1()
    // 发送广播：同时存在动态/静态注册广播，先调用动态注册广播，后调用静态注册(不论优先级别)【根据注册先后依次执行】，同是动态或者静态，优先级高的先调用。(无序)
    // OrderBroadcastReceiver1: OrderBroadcastReceiver1 onReceive() com.software.march.ORDER_BROADCAST_RECEIVER
    // OrderBroadcastReceiver: OrderBroadcastReceiver onReceive() com.software.march.ORDER_BROADCAST_RECEIVER
    // OrderBroadcastReceiver3: OrderBroadcastReceiver3()
    // OrderBroadcastReceiver3: OrderBroadcastReceiver3 onReceive() com.software.march.ORDER_BROADCAST_RECEIVER
    // OrderBroadcastReceiver2: OrderBroadcastReceiver2()
    // OrderBroadcastReceiver2: OrderBroadcastReceiver2 onReceive() com.software.march.ORDER_BROADCAST_RECEIVER

    // 4.发送有序广播(动态注册)
    // registerReceiver()方法调用时实例化。
    // OrderBroadcastReceiver: OrderBroadcastReceiver()
    // OrderBroadcastReceiver1: OrderBroadcastReceiver1()
    // 发送广播：优先级高的先调用。
    // OrderBroadcastReceiver1: OrderBroadcastReceiver1 onReceive() com.software.march.ORDER_BROADCAST_RECEIVER
    // OrderBroadcastReceiver: OrderBroadcastReceiver onReceive() com.software.march.ORDER_BROADCAST_RECEIVER

    // 5.发送有序广播(静态注册)
    // 发送广播：优先级高的先调用。
    // OrderBroadcastReceiver3: OrderBroadcastReceiver3()
    // OrderBroadcastReceiver3: OrderBroadcastReceiver3 onReceive() com.software.march.ORDER_BROADCAST_RECEIVER
    // OrderBroadcastReceiver2: OrderBroadcastReceiver2()
    // OrderBroadcastReceiver2: OrderBroadcastReceiver2 onReceive() com.software.march.ORDER_BROADCAST_RECEIVER

    // 6.发送有序广播(动态/静态)
    // 动态/静态注册广播都有：
    // registerReceiver()方法调用时实例化。
    // OrderBroadcastReceiver: OrderBroadcastReceiver()
    // OrderBroadcastReceiver1: OrderBroadcastReceiver1()
    // 发送广播：同时存在动态/静态注册广播，优先级高的先调用,同样的优先级,先调用动态注册广播【根据优先级和注册先后依次执行】。(有序)
    // OrderBroadcastReceiver1: OrderBroadcastReceiver1 onReceive() com.software.march.ORDER_BROADCAST_RECEIVER
    // OrderBroadcastReceiver3: OrderBroadcastReceiver3()
    // OrderBroadcastReceiver3: OrderBroadcastReceiver3 onReceive() com.software.march.ORDER_BROADCAST_RECEIVER
    // OrderBroadcastReceiver: OrderBroadcastReceiver onReceive() com.software.march.ORDER_BROADCAST_RECEIVER
    // OrderBroadcastReceiver2: OrderBroadcastReceiver2()
    // OrderBroadcastReceiver2: OrderBroadcastReceiver2 onReceive() com.software.march.ORDER_BROADCAST_RECEIVER

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register_normal_dynamic_broadcast:
                // 发送一般广播(动态注册)--注册
                registerNormalDynamicBroadcast();
                break;
            case R.id.btn_unregister_normal_dynamic_broadcast:
                // 发送一般广播(动态注册)--解注册
                unregisterNormalDynamicBroadcast();
                break;
            case R.id.btn_register_broadcast:
                // 多个一般、有序广播(动态/静态)--注册
                registerBroadcast();
                break;
            case R.id.btn_unregister_broadcast:
                // 多个一般、有序广播(动态/静态)--解注册
                unregisterBroadcast();
                break;
        }
    }

    // 发送一般广播(动态注册)--注册
    private void registerNormalDynamicBroadcast() {
        if (receiver == null) {
            // 创建receiver对象
            receiver = new NormalDynamicBroadcastReceiver();
            // 创建过滤器对象
            IntentFilter filter = new IntentFilter(NormalDynamicBroadcastReceiver.ACTION_NORMAL_DYNAMIC_BROADCAST_RECEIVER);
            // 注册广播接收器
            registerReceiver(receiver, filter);
            Toast.makeText(this, "发送一般广播(动态注册)--注册", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "已经注册了", Toast.LENGTH_SHORT).show();
        }
    }

    // 发送一般广播(动态注册)--解注册
    public void unregisterNormalDynamicBroadcast() {
        if (receiver != null) {
            // 解注册广播接收器
            unregisterReceiver(receiver);
            receiver = null;
            Toast.makeText(this, "发送一般广播(动态注册)--解注册", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "还没有注册", Toast.LENGTH_SHORT).show();
        }
    }

    // 多个一般、有序广播(动态/静态)--注册
    public void registerBroadcast() {
        if (orderBroadcastReceiver == null) {
            orderBroadcastReceiver = new OrderBroadcastReceiver();
            orderBroadcastReceiver1 = new OrderBroadcastReceiver1();

            IntentFilter filter = new IntentFilter(OrderBroadcastReceiver.ACTION_ORDER_BROADCAST_RECEIVER);
            IntentFilter filter1 = new IntentFilter(OrderBroadcastReceiver.ACTION_ORDER_BROADCAST_RECEIVER);
            filter1.setPriority(2147483647);

            registerReceiver(orderBroadcastReceiver, filter);
            registerReceiver(orderBroadcastReceiver1, filter1);
            Toast.makeText(this, "多个一般、有序广播(动态/静态)--注册", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "已经注册了", Toast.LENGTH_SHORT).show();
        }
    }

    // 多个一般、有序广播(动态/静态)--解注册
    public void unregisterBroadcast() {
        if (orderBroadcastReceiver != null) {
            unregisterReceiver(orderBroadcastReceiver);
            unregisterReceiver(orderBroadcastReceiver1);
            orderBroadcastReceiver = null;
            orderBroadcastReceiver1 = null;
            Toast.makeText(this, "多个一般、有序广播(动态/静态)--解注册", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "还没有注册", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_broadcast_receiver, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.send_broadcast:
                startActivity(new Intent(this, SendBroadcastActivity.class));
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receiver != null) {
            // 解注册广播接收器
            unregisterReceiver(receiver);
            receiver = null;
        }
        if (orderBroadcastReceiver != null) {
            unregisterReceiver(orderBroadcastReceiver);
            unregisterReceiver(orderBroadcastReceiver1);
            orderBroadcastReceiver = null;
            orderBroadcastReceiver1 = null;
        }
    }
}