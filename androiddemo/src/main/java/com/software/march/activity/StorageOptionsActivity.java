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
 * @Description 存储选项
 * @date 2017/1/5
 */
public class StorageOptionsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSharedPreferences;
    private Button btnFile;
    private Button btnSqlite;
    private Button btnContentProvider;
    private Button btnNetWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_storage_options);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnSharedPreferences = (Button) findViewById(R.id.btn_shared_preferences);
        btnFile = (Button) findViewById(R.id.btn_file);
        btnSqlite = (Button) findViewById(R.id.btn_sqlite);
        btnContentProvider = (Button) findViewById(R.id.btn_content_provider);
        btnNetWork = (Button) findViewById(R.id.btn_net_work);

        btnSharedPreferences.setOnClickListener(this);
        btnFile.setOnClickListener(this);
        btnSqlite.setOnClickListener(this);
        btnContentProvider.setOnClickListener(this);
        btnNetWork.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_shared_preferences:
                startActivity(new Intent(this, SharedPreferencesActivity.class));
                break;
            case R.id.btn_file:
                startActivity(new Intent(this, FileActivity.class));
                break;
            case R.id.btn_sqlite:
                startActivity(new Intent(this, SQLiteActivity.class));
                break;
            case R.id.btn_content_provider:
                startActivity(new Intent(this, ContentProviderActivity.class));
                break;
            case R.id.btn_net_work:
                startActivity(new Intent(this, NetWorkActivity.class));
                break;
        }
    }
}