package com.software.march.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;

import com.software.march.R;
import com.software.march.utils.SPUtils;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 常用的五种布局
 * @date 2016/12/19
 */
public class LayoutActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnFrameLayout;
    private Button btnLinearLayout;
    private Button btnRelativeLayout;
    private Button btnAbsoluteLayout;
    private Button btnTableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnFrameLayout = (Button) findViewById(R.id.btn_frame_layout);
        btnLinearLayout = (Button) findViewById(R.id.btn_linear_layout);
        btnRelativeLayout = (Button) findViewById(R.id.btn_relative_layout);
        btnAbsoluteLayout = (Button) findViewById(R.id.btn_absolute_layout);
        btnTableLayout = (Button) findViewById(R.id.btn_table_layout);

        btnFrameLayout.setOnClickListener(this);
        btnLinearLayout.setOnClickListener(this);
        btnRelativeLayout.setOnClickListener(this);
        btnAbsoluteLayout.setOnClickListener(this);
        btnTableLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_frame_layout:
                startActivity(new Intent(this, FrameLayoutActivity.class));
                break;
            case R.id.btn_linear_layout:
                startActivity(new Intent(this, LinearLayoutActivity.class));
                break;
            case R.id.btn_relative_layout:
                startActivity(new Intent(this, RelativeLayoutActivity.class));
                break;
            case R.id.btn_absolute_layout:
                startActivity(new Intent(this, AbsoluteLayoutActivity.class));
                break;
            case R.id.btn_table_layout:
                startActivity(new Intent(this, TableLayoutActivity.class));
                break;
        }
    }
}