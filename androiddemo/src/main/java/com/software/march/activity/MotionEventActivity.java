package com.software.march.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.software.march.R;
import com.software.march.utils.SPUtils;
import com.software.march.view.MotionEventImageView;
import com.software.march.view.MotionEventLinearLayout;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description MotionEvent事件
 * @date 2017/1/5
 */
public class MotionEventActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    private MotionEventLinearLayout layout;
    private MotionEventImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_motion_event);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        layout = (MotionEventLinearLayout) findViewById(R.id.layout);
        iv = (MotionEventImageView) findViewById(R.id.iv);

        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent ev) {
                Log.e(TAG, "MotionEventLinearLayout" + ":onTouch(View view, MotionEvent ev)" + ",action:" + ev.getAction());
                return false;
            }
        });
        iv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent ev) {
                Log.e(TAG, "MotionEventImageView" + ":onTouch(View view, MotionEvent ev)" + ",action:" + ev.getAction());
                return false;
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, TAG + ":dispatchTouchEvent(MotionEvent ev)" + ",action:" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.e(TAG, TAG + ":onTouchEvent(MotionEvent ev)" + ",action:" + ev.getAction());
        return super.onTouchEvent(ev);
    }
}