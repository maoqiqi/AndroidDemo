package com.software.march.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.software.march.R;
import com.software.march.utils.RPUtils;
import com.software.march.utils.SPUtils;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description intent filter
 * @date 2016/12/23
 */
public class IntentFilterActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    private static final int REQUEST_CALL_PHONE = 0;
    private static final int REQUEST_SEND_SMS = 1;

    private EditText etPhone;
    private Button btnCall;
    private Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_intent_filter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 初始化视图对象
        etPhone = (EditText) findViewById(R.id.et_phone);
        btnCall = (Button) findViewById(R.id.btn_call);
        btnSend = (Button) findViewById(R.id.btn_send);

        // 给视图对象设置点击监听
        btnCall.setOnClickListener(this);
        btnSend.setOnClickListener(this);

        // 给视图对象设置长按监听
        btnCall.setOnLongClickListener(this);
        btnSend.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_call: {
                // 1) 创建一个Intent(隐式)
                // ActivityManager(1222): Displayed com.android.dialer/.DialtactsActivity: +535ms
                Intent intent = new Intent(Intent.ACTION_DIAL);
                // 2) 携带数据
                String number = etPhone.getText().toString();
                intent.setData(Uri.parse("tel:" + number)); // <data android:scheme="tel" />
                // 3) startActivity(intent);
                startActivity(intent);
                break;
            }
            case R.id.btn_send: {
                // 1) 创建一个Intent(隐式)
                // ActivityManager(1222): Displayed com.android.mms/.ui.ComposeMessageActivity: +132ms
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                // 2) 携带数据(号码/内容)
                String number = etPhone.getText().toString();
                intent.setData(Uri.parse("smsto:" + number));
                // 携带额外数据
                intent.putExtra("sms_body", "短信内容");
                // 3) startActivity(intent);
                startActivity(intent);
                break;
            }
        }
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.btn_call: {
                if (!RPUtils.requestPermissions(this, Manifest.permission.CALL_PHONE, REQUEST_CALL_PHONE)) {
                    return true;
                }
                call();
                return true;// 不会再触发点击事件(表示此事件已经被消费了)
            }
            case R.id.btn_send: {
                if (!RPUtils.requestPermissions(this, Manifest.permission.SEND_SMS, REQUEST_SEND_SMS)) {
                    return true;
                }
                send();
                return true;
            }
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_CALL_PHONE) {
            if (grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                call();
            }
        }
        if (requestCode == REQUEST_SEND_SMS) {
            if (grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                send();
            }
        }
    }

    private void call() {
        // 1) 创建一个Intent(隐式)
        // ActivityManager(1222): Displayed com.android.phone/.PrivilegedOutgoingCallBroadcaster: +570ms
        Intent intent = new Intent("android.intent.action.CALL");// Intent.ACTION_CALL
        // 2) 携带数据
        String number = etPhone.getText().toString();
        intent.setData(Uri.parse("tel:" + number));
        // 3) startActivity(intent);
        startActivity(intent);
    }

    private void send() {
        // 1) 得到SmsManager的对象
        SmsManager smsManager = SmsManager.getDefault();
        // 2) 发送文本信息(短信)
        String number = etPhone.getText().toString();
        String sms = "短信内容";
        smsManager.sendTextMessage(number, null, sms, null, null);
    }
}