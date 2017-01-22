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
 * @Description 图片
 * @date 2017/1/20
 */
public class TuPianActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnTestBitmap;
    private Button btnTestMatrix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_tu_pian);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnTestBitmap = (Button) findViewById(R.id.btn_test_bitmap);
        btnTestMatrix = (Button) findViewById(R.id.btn_test_matrix);

        btnTestBitmap.setOnClickListener(this);
        btnTestMatrix.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test_bitmap:
                startActivity(new Intent(this, BitmapActivity.class));
                break;
            case R.id.btn_test_matrix:
                startActivity(new Intent(this, MatrixActivity.class));
                break;
        }
    }
}