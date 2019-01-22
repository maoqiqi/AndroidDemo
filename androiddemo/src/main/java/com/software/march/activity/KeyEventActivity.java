package com.software.march.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;

import com.software.march.R;
import com.software.march.utils.SPUtils;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description KeyEvent事件
 * @date 2017/1/5
 */
public class KeyEventActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_key_event);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent ev) {
        Log.e(TAG, TAG + ":dispatchKeyEvent(KeyEvent ev)" + ",action:" + ev.getAction() + ",keyCode:" + ev.getKeyCode());
        return super.dispatchKeyEvent(ev);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent ev) {
        Log.e(TAG, TAG + ":onKeyDown(int keyCode, KeyEvent ev)" + ",action:" + ev.getAction() + ",keyCode:" + ev.getKeyCode());
        return super.onKeyDown(keyCode, ev);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent ev) {
        Log.e(TAG, TAG + ":onKeyUp(int keyCode, KeyEvent ev" + ",action:" + ev.getAction() + ",keyCode:" + ev.getKeyCode());
        return super.onKeyUp(keyCode, ev);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent ev) {
        Log.e(TAG, TAG + ":onKeyLongPress(int keyCode, KeyEvent ev)" + ",action:" + ev.getAction() + ",keyCode:" + ev.getKeyCode());
        return super.onKeyLongPress(keyCode, ev);
    }
}