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
 * @Description Activity生命周期及基本使用
 * @date 2016/12/14
 */
public class ActivityActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = getClass().getSimpleName();

    private Button btnLifecycle;
    private Button btnRestoreInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnLifecycle = (Button) findViewById(R.id.btn_lifecycle);
        btnRestoreInstance = (Button) findViewById(R.id.btn_restore_instance);
        btnLifecycle.setOnClickListener(this);
        btnRestoreInstance.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_lifecycle:
                startActivity(new Intent(this, LifecycleActivity.class));
                break;
            case R.id.btn_restore_instance:
                startActivity(new Intent(this, RestoreInstanceActivity.class));
                break;
        }
    }
}