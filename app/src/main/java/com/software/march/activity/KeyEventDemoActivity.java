package com.software.march.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.widget.Toast;

import com.software.march.R;
import com.software.march.utils.SPUtils;

public class KeyEventDemoActivity extends AppCompatActivity {

    // 两次连续按返回键的间隔时间
    private long waitTime = 2000;
    // 按返回键的时间
    private long touchTime = 0;

    // 标识是否可以退出
    private boolean isExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_key_event_demo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_UP && KeyEvent.KEYCODE_BACK == keyCode) {
            /*if (back1()) {
                return true;
            }*/
            if (back2()) {
                return true;
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    private boolean back1() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - touchTime) >= waitTime) {
            Toast.makeText(this, "再按一次返回键退出程序", Toast.LENGTH_SHORT).show();
            touchTime = currentTime;
            return true;
        }
        return false;
    }

    private boolean back2() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(this, "再按一次返回键退出程序", Toast.LENGTH_SHORT).show();
            // 发消息延迟2s将exit=false
            handler.sendEmptyMessageDelayed(1, waitTime);
            return true;
        }
        return false;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                isExit = false;
            }
        }
    };
}