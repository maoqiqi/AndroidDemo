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
 * @Description 动画
 * @date 2017/1/19
 */
public class AnimationActivity extends AppCompatActivity implements View.OnClickListener {

    // 补间动画
    // 视图(View)动画：同一个图形通过视图在界面上进行透明度,缩放,旋转,平移的变化
    // 图片(Drawable)动画：在界面的同一个位置上不断切换显示不同的图片

    private Button btnViewAnimation;
    private Button btnDrawableAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_animation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnViewAnimation = (Button) findViewById(R.id.btn_view_animation);
        btnDrawableAnimation = (Button) findViewById(R.id.btn_drawable_animation);

        btnViewAnimation.setOnClickListener(this);
        btnDrawableAnimation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_view_animation:
                startActivity(new Intent(this, ViewAnimationActivity.class));
                break;
            case R.id.btn_drawable_animation:
                startActivity(new Intent(this, DrawableAnimationActivity.class));
                break;
        }
    }
}