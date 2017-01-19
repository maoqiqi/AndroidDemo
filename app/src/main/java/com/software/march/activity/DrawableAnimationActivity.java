package com.software.march.activity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.software.march.R;
import com.software.march.utils.SPUtils;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description Drawable动画
 * @date 2017/1/19
 */
public class DrawableAnimationActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivDrawableAnimation;
    private Button btnStart;
    private Button btnStop;

    private AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_drawable_animation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ivDrawableAnimation = (ImageView) findViewById(R.id.iv_drawable_animation);
        btnStart = (Button) findViewById(R.id.btn_start);
        btnStop = (Button) findViewById(R.id.btn_stop);

        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                if (animationDrawable == null) {
                    // 得到背景动画图片对象
                    animationDrawable = (AnimationDrawable) ivDrawableAnimation.getBackground();
                    // 启动
                    animationDrawable.start();
                }
                break;
            case R.id.btn_stop:
                if (animationDrawable != null) {
                    animationDrawable.stop();
                    animationDrawable = null;
                }
                break;
        }
    }
}