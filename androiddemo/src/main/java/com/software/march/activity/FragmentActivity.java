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
 * @Description Fragment使用
 * @date 2016/12/14
 * @update 2017/1/9
 */
public class FragmentActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnStaticFragment;
    private Button btnDynamicFragment;
    private Button btnLifecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_fragment);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnStaticFragment = (Button) findViewById(R.id.btn_static_fragment);
        btnDynamicFragment = (Button) findViewById(R.id.btn_dynamic_fragment);
        btnLifecycle = (Button) findViewById(R.id.btn_lifecycle);

        btnStaticFragment.setOnClickListener(this);
        btnDynamicFragment.setOnClickListener(this);
        btnLifecycle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dynamic_fragment:
                startActivity(new Intent(this, DynamicFragmentActivity.class));
                break;
            case R.id.btn_lifecycle:
                startActivity(new Intent(this, FragmentLifecycleActivity.class));
                break;
        }
    }
}