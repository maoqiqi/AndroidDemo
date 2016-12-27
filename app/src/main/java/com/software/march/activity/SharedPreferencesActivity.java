package com.software.march.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.software.march.R;
import com.software.march.utils.SPUtils;

public class SharedPreferencesActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etSpKey;
    private EditText etSpValue;
    private Button btnSpSave;
    private Button btnSpRead;

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_shared_preferences);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etSpKey = (EditText) findViewById(R.id.et_sp_key);
        etSpValue = (EditText) findViewById(R.id.et_sp_value);
        btnSpSave = (Button) findViewById(R.id.btn_sp_save);
        btnSpRead = (Button) findViewById(R.id.btn_sp_read);

        btnSpSave.setOnClickListener(this);
        btnSpRead.setOnClickListener(this);

        // 1).得到SharedPreferences对象
        sp = getSharedPreferences("test_preferences", Context.MODE_PRIVATE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sp_save: {
                // 2).得到Editor对象
                SharedPreferences.Editor edit = sp.edit();
                // 3).得到输入的key、value
                String key = etSpKey.getText().toString();
                String value = etSpValue.getText().toString();
                // 4).使用editor保存key-value
                edit.putString(key, value).commit();
                // 5).提示
                Toast.makeText(this, "保存完成!", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_sp_read:
                // 1).得到输入的key
                String key = etSpKey.getText().toString();
                // 2).根据key读取对应的value
                String value = sp.getString(key, null);
                // 3).显示
                if (value == null) {
                    Toast.makeText(this, "没有找到对应的value", Toast.LENGTH_SHORT).show();
                } else {
                    etSpValue.setText(value);
                }
                break;
        }
    }
}