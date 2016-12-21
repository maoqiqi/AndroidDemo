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
 * @Description ListView
 * @date 2016/12/19
 */
public class ListViewActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnArrayAdapter;
    private Button btnSimpleAdapter;
    private Button btnCursorAdapter;
    private Button btnBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_list_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnArrayAdapter = (Button) findViewById(R.id.btn_array_adapter);
        btnSimpleAdapter = (Button) findViewById(R.id.btn_simple_adapter);
        btnCursorAdapter = (Button) findViewById(R.id.btn_cursor_adapter);
        btnBaseAdapter = (Button) findViewById(R.id.btn_base_adapter);

        btnArrayAdapter.setOnClickListener(this);
        btnSimpleAdapter.setOnClickListener(this);
        btnCursorAdapter.setOnClickListener(this);
        btnBaseAdapter.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_array_adapter:
                startActivity(new Intent(this, ArrayAdapterActivity.class));
                break;
            case R.id.btn_simple_adapter:
                startActivity(new Intent(this, SimpleAdapterActivity.class));
                break;
            case R.id.btn_cursor_adapter:
                startActivity(new Intent(this, CursorAdapterActivity.class));
                break;
            case R.id.btn_base_adapter:
                startActivity(new Intent(this, BaseAdapterActivity.class));
                break;
        }
    }
}