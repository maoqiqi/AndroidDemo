package com.software.march.activity;

import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.software.march.R;
import com.software.march.utils.SPUtils;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description Matrix，中文里叫矩阵，高等数学里有介绍，在图像处理方面，主要是用于平面的缩放、平移、旋转等操作。
 * @date 2017/1/20
 */
public class MatrixActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etMatrixScale;
    private EditText etMatrixRotate;
    private EditText etMatrixTranslateX;
    private EditText etMatrixTranslateY;
    private Button btnMatrixScale;
    private Button btnMatrixRotate;
    private Button btnMatrixTranslate;
    private Button btnMatrixClear;
    private ImageView ivMatrix;

    private Matrix matrix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_matrix);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etMatrixScale = (EditText) findViewById(R.id.et_matrix_scale);
        etMatrixRotate = (EditText) findViewById(R.id.et_matrix_rotate);
        etMatrixTranslateX = (EditText) findViewById(R.id.et_matrix_translateX);
        etMatrixTranslateY = (EditText) findViewById(R.id.et_matrix_translateY);
        btnMatrixScale = (Button) findViewById(R.id.btn_matrix_scale);
        btnMatrixRotate = (Button) findViewById(R.id.btn_matrix_rotate);
        btnMatrixTranslate = (Button) findViewById(R.id.btn_matrix_translate);
        btnMatrixClear = (Button) findViewById(R.id.btn_matrix_clear);
        ivMatrix = (ImageView) findViewById(R.id.iv_matrix);

        btnMatrixScale.setOnClickListener(this);
        btnMatrixRotate.setOnClickListener(this);
        btnMatrixTranslate.setOnClickListener(this);
        btnMatrixClear.setOnClickListener(this);

        matrix = new Matrix();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_matrix_scale:
                matrixScale();
                break;
            case R.id.btn_matrix_rotate:
                matrixRotate();
                break;
            case R.id.btn_matrix_translate:
                matrixTranslate();
                break;
            case R.id.btn_matrix_clear:
                matrixClear();
                break;
        }
    }

    private void matrixScale() {
        float scale = Float.parseFloat(etMatrixScale.getText().toString());
        // 保存缩放比例
        matrix.postScale(scale, scale);
        // 将matrix设置到ImageView
        // android:scaleType="matrix"
        ivMatrix.setImageMatrix(matrix);
    }

    private void matrixRotate() {
        float degree = Float.parseFloat(etMatrixRotate.getText().toString());
        matrix.postRotate(degree);
        ivMatrix.setImageMatrix(matrix);
    }

    private void matrixTranslate() {
        float dx = Float.parseFloat(etMatrixTranslateX.getText().toString());
        float dy = Float.parseFloat(etMatrixTranslateY.getText().toString());
        matrix.postTranslate(dx, dy);
        ivMatrix.setImageMatrix(matrix);
    }

    private void matrixClear() {
        matrix.reset();
        ivMatrix.setImageMatrix(matrix);
    }
}