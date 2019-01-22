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
 * @Description 图像处理
 * @date 2017/1/22
 */
public class GraphicsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnTuPian;
    private Button btnDraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_graphics);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnTuPian = (Button) findViewById(R.id.btn_tu_pian);
        btnDraw = (Button) findViewById(R.id.btn_draw);

        btnTuPian.setOnClickListener(this);
        btnDraw.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_tu_pian:
                startActivity(new Intent(this, TuPianActivity.class));
                break;
            case R.id.btn_draw:
                startActivity(new Intent(this, DrawActivity.class));
                break;
        }
    }
}