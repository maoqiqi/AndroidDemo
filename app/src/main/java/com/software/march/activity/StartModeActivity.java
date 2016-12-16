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

/**
 * @author Doc.March
 * @version V 1.0
 * @Description Activity启动方式
 * @date 2016/12/16
 */
public class StartModeActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etMessage;
    private Button btnStart;
    private Button btnStartForResult;
    private TextView tvResult;

    public static final String MESSAGE = "message";
    public static final String RESULT = "result";

    private final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_start_mode);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 初始化视图对象
        etMessage = (EditText) findViewById(R.id.et_message);
        btnStart = (Button) findViewById(R.id.btn_start);
        btnStartForResult = (Button) findViewById(R.id.btn_start_for_result);
        tvResult = (TextView) findViewById(R.id.tv_result);

        // 设置点击监听
        btnStart.setOnClickListener(this);
        btnStartForResult.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_start:
                // 1.创建Intent对象(显式)
                intent = new Intent(this, ResultActivity.class);
                // 2.通过intent携带额外数据
                intent.putExtra(MESSAGE, etMessage.getText().toString());
                // 3.启动Activity
                startActivity(intent);
                break;
            case R.id.btn_start_for_result:
                // 1.创建Intent对象(显式)
                intent = new Intent(this, ResultActivity.class);
                // 2.通过intent携带额外数据
                intent.putExtra(MESSAGE, etMessage.getText().toString());
                // 3.带回调启动Activity
                startActivityForResult(intent, REQUEST_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (REQUEST_CODE == requestCode) {
                String result = data.getStringExtra(RESULT);
                tvResult.setText(result);
            }
        }
    }
}