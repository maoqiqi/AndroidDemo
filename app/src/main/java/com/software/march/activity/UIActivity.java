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
 * @Description 用户界面
 * @date 2016/12/16
 */
public class UIActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLayout;
    private Button btnSimpleComponent;
    private Button btnBar;
    private Button btnDialog;
    private Button btnMenu;
    private Button btnListView;
    private Button btnGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_ui);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 初始化视图对象
        btnLayout = (Button) findViewById(R.id.btn_layout);
        btnSimpleComponent = (Button) findViewById(R.id.btn_simple_component);
        btnBar = (Button) findViewById(R.id.btn_bar);
        btnDialog = (Button) findViewById(R.id.btn_dialog);
        btnMenu = (Button) findViewById(R.id.btn_menu);
        btnListView = (Button) findViewById(R.id.btn_list_view);
        btnGridView = (Button) findViewById(R.id.btn_grid_view);

        // 设置点击监听
        btnLayout.setOnClickListener(this);
        btnSimpleComponent.setOnClickListener(this);
        btnBar.setOnClickListener(this);
        btnDialog.setOnClickListener(this);
        btnMenu.setOnClickListener(this);
        btnListView.setOnClickListener(this);
        btnGridView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_layout:
                startActivity(new Intent(this, LayoutActivity.class));
                break;
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
            case R.id.btn_list_view:
                startActivity(new Intent(this, ListViewActivity.class));
                break;
            case R.id.btn_grid_view:
                startActivity(new Intent(this, GridViewActivity.class));
                break;
        }
    }
}