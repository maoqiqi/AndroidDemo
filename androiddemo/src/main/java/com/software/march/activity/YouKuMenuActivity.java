package com.software.march.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.software.march.R;
import com.software.march.utils.SPUtils;
import com.software.march.view.utils.YouKuMenuTool;

/**
 * @author Doc.March
 * @version V 1.Toast.LENGTH_SHORT
 * @Description 优酷菜单
 * @date 2017/1/18
 */
public class YouKuMenuActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView icon_home;
    private ImageView icon_menu;
    private RelativeLayout level1, level2, level3;

    // 是否显示第三个圆环 true显示 false 隐藏
    private boolean isShowLevel3 = true;
    // 是否显示第二个圆环 true显示 false 隐藏
    private boolean isShowLevel2 = true;
    // 是否显示第一个圆环 true显示 false 隐藏
    private boolean isShowLevel1 = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_you_ku_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        icon_home = (ImageView) findViewById(R.id.icon_home);
        icon_menu = (ImageView) findViewById(R.id.icon_menu);
        level1 = (RelativeLayout) findViewById(R.id.level1);
        level2 = (RelativeLayout) findViewById(R.id.level2);
        level3 = (RelativeLayout) findViewById(R.id.level3);

        // 设置点击事件
        icon_home.setOnClickListener(this);
        icon_menu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_home:
                // 如果三级和二级都是显示的, 都设置隐藏
                if (isShowLevel2) {
                    isShowLevel2 = false;
                    YouKuMenuTool.hideView(level2);

                    if (isShowLevel3) {
                        isShowLevel3 = false;
                        YouKuMenuTool.hideView(level3, 200);
                    }
                } else {
                    isShowLevel2 = true;
                    YouKuMenuTool.showView(level2);
                }
                // 如果都是隐藏的,显示二级菜单
                break;
            case R.id.icon_menu:
                if (isShowLevel3) {
                    // 隐藏
                    isShowLevel3 = false;
                    YouKuMenuTool.hideView(level3);
                } else {
                    // 显示
                    isShowLevel3 = true;
                    YouKuMenuTool.showView(level3);
                }
                break;
        }
    }
}