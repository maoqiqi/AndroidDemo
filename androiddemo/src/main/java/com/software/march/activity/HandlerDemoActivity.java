package com.software.march.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.software.march.R;
import com.software.march.utils.SPUtils;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description Handler 使用 demo
 * @date 2017/1/5
 */
public class HandlerDemoActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int WHAT_INCREASE = 1;
    private static final int WHAT_DECREASE = 2;

    private TextView tvNumber;
    private Button btnIncrease;
    private Button btnDecrease;
    private Button btnPause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_handler_demo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvNumber = (TextView) findViewById(R.id.tv_number);
        btnIncrease = (Button) findViewById(R.id.btn_increase);
        btnDecrease = (Button) findViewById(R.id.btn_decrease);
        btnPause = (Button) findViewById(R.id.btn_pause);

        btnIncrease.setOnClickListener(this);
        btnDecrease.setOnClickListener(this);
        btnPause.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_increase:// 自动增加
                // 限制Button可操作性
                btnIncrease.setEnabled(false);
                btnDecrease.setEnabled(true);
                btnPause.setEnabled(true);

                // 停止减少(移除未处理的减少的消息)
                handler.removeMessages(WHAT_DECREASE);
                // 发消息(增加)
                handler.sendEmptyMessage(WHAT_INCREASE);
                break;
            case R.id.btn_decrease:// 自动减少
                // 限制Button可操作性
                btnIncrease.setEnabled(true);
                btnDecrease.setEnabled(false);
                btnPause.setEnabled(true);

                // 停止增加(移除未处理的增加的消息)
                handler.removeMessages(WHAT_INCREASE);

                // 发消息(减少)
                handler.sendEmptyMessage(WHAT_DECREASE);
                break;
            case R.id.btn_pause:// 暂停
                // 限制Button可操作性
                btnIncrease.setEnabled(true);
                btnDecrease.setEnabled(true);
                btnPause.setEnabled(false);

                // 停止增加/减少(移除未处理的减少/增加的消息)
                handler.removeMessages(WHAT_INCREASE);
                handler.removeMessages(WHAT_DECREASE);
                break;
        }
    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // 得到当前显示的数值
            int number = Integer.parseInt(tvNumber.getText().toString());
            switch (msg.what) {
                case WHAT_INCREASE:
                    // 限制number<=20
                    if (number == 20) {
                        // 设置暂停不能操作
                        btnPause.setEnabled(false);
                        Toast.makeText(HandlerDemoActivity.this, "已经达到最大值", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    number++;
                    tvNumber.setText(number + "");
                    // 发送增加的延迟消息
                    handler.sendEmptyMessageDelayed(WHAT_INCREASE, 1000);
                    break;
                case WHAT_DECREASE:
                    // 限制number>=1
                    if (number == 1) {
                        // 设置暂停不能操作
                        btnPause.setEnabled(false);
                        Toast.makeText(HandlerDemoActivity.this, "已经达到最小值", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    number--;
                    tvNumber.setText(number + "");
                    // 发送减少的延迟消息
                    handler.sendEmptyMessageDelayed(WHAT_DECREASE, 1000);
                    break;
                default:
                    break;
            }
        }
    };
}