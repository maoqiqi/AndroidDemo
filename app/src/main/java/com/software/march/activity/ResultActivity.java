package com.software.march.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.software.march.R;
import com.software.march.utils.SPUtils;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvMessage;
    private Button btnBack;
    private Button btnBackForResult;
    private EditText etResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_result);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 初始化视图对象
        tvMessage = (TextView) findViewById(R.id.tv_message);
        btnBack = (Button) findViewById(R.id.btn_back);
        btnBackForResult = (Button) findViewById(R.id.btn_back_for_result);
        etResult = (EditText) findViewById(R.id.et_result);

        // 设置点击监听
        btnBack.setOnClickListener(this);
        btnBackForResult.setOnClickListener(this);

        // 4.得到intent对象
        Intent intent = getIntent();
        // 5.通过intent读取额外数据
        String message = intent.getStringExtra(StartModeActivity.MESSAGE);
        // 6.显示到EditText
        tvMessage.setText(message);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                // 关闭当前界面
                finish();
                break;
            case R.id.btn_back_for_result:
                // 准备一个带额外数据的intent对象
                Intent data = new Intent();
                String result = etResult.getText().toString();
                data.putExtra(StartModeActivity.RESULT, result);
                // 设置结果
                setResult(RESULT_OK, data);
                // 关闭当前界面
                finish();
                break;
        }
    }
}