package com.software.march.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.software.march.R;
import com.software.march.utils.SPUtils;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 事件机制
 * @date 2017/1/5
 */
public class EventActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnMotionEvent;
    private Button btnKeyEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_event);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnMotionEvent = (Button) findViewById(R.id.btn_motion_event);
        btnKeyEvent = (Button) findViewById(R.id.btn_key_event);

        btnMotionEvent.setOnClickListener(this);
        btnKeyEvent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_motion_event:
                startActivity(new Intent(this, MotionEventActivity.class));
                break;
            case R.id.btn_key_event:
                startActivity(new Intent(this, KeyEventActivity.class));
                break;
        }
    }
}