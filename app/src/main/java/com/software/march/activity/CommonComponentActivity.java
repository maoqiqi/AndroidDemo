package com.software.march.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.software.march.R;
import com.software.march.utils.SPUtils;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 常用组件
 * @date 2016/12/16
 */
public class CommonComponentActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSimpleComponent;
    private Button btnMenu;
    private Button btnBar;
    private Button btnDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_common_component);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 初始化视图对象
        btnSimpleComponent = (Button) findViewById(R.id.btn_simple_component);
        btnBar = (Button) findViewById(R.id.btn_bar);
        btnDialog = (Button) findViewById(R.id.btn_dialog);
        btnMenu = (Button) findViewById(R.id.btn_menu);

        // 设置点击监听
        btnSimpleComponent.setOnClickListener(this);
        btnBar.setOnClickListener(this);
        btnDialog.setOnClickListener(this);
        btnMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_simple_component:
                startActivity(new Intent(this, SimpleComponentActivity.class));
                break;
            case R.id.btn_bar:
                startActivity(new Intent(this, BarComponentActivity.class));
                break;
            case R.id.btn_dialog:
                startActivity(new Intent(this, DialogComponentActivity.class));
                break;
            case R.id.btn_menu:
                startActivity(new Intent(this, MenuComponentActivity.class));
                break;
        }
    }
}