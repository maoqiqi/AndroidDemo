package com.software.march.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.software.march.R;
import com.software.march.utils.HttpUtils;
import com.software.march.utils.SPUtils;

import java.io.IOException;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 消息机制
 * @date 2017/1/5
 */
public class HandlerActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String URL = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";

    private Button btnGetData1;
    private Button btnGetData2;
    private TextView tvResult;

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_handler);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnGetData1 = (Button) findViewById(R.id.btn_get_data_1);
        btnGetData2 = (Button) findViewById(R.id.btn_get_data_2);
        tvResult = (TextView) findViewById(R.id.tv_result);

        btnGetData1.setOnClickListener(this);
        btnGetData2.setOnClickListener(this);

        dialog = new ProgressDialog(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_data_1:
                getData1();
                break;
            case R.id.btn_get_data_2:
                getData2();
                break;
        }
    }

    private void getData1() {
        tvResult.setText("");
        dialog.show();

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    final String result = HttpUtils.sendGetRequest(URL);
                    // 主线程, 显示数据
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            dialog.dismiss();
                            tvResult.setText(result);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void getData2() {
        tvResult.setText("");
        dialog.show();

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    String result = HttpUtils.sendGetRequest(URL);
                    // 2.在分线程创建Message对象
                    Message message = Message.obtain();
                    message.what = 1;// 标识
                    message.obj = result;
                    // 3.使用handler对象发送Message
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // 1.创建Handler成员变量对象, 并重写其handleMessage()方法
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {// 在主线程执行
            super.handleMessage(msg);
            if (msg.what == 1) {
                // 4.在handleMessage()中处理消息
                String result = (String) msg.obj;
                dialog.dismiss();
                tvResult.setText(result);
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_handler, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.handler_demo:
                startActivity(new Intent(this, HandlerDemoActivity.class));
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}