package com.software.march.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.software.march.R;
import com.software.march.bean.PersonBean;
import com.software.march.db.PersonDAO;
import com.software.march.utils.SPUtils;

public class SQLiteActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnCreate;
    private Button btnAdd;
    private Button btnUpdate;
    private Button btnDelete;
    private Button btnQuery;

    private PersonDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_sqlite);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnCreate = (Button) findViewById(R.id.btn_create);
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnUpdate = (Button) findViewById(R.id.btn_update);
        btnDelete = (Button) findViewById(R.id.btn_delete);
        btnQuery = (Button) findViewById(R.id.btn_query);

        btnCreate.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnQuery.setOnClickListener(this);

        dao = new PersonDAO(this);
    }

    @Override
    public void onClick(View view) {
        PersonBean bean;
        switch (view.getId()) {
            case R.id.btn_create:
                dao.create();
                break;
            case R.id.btn_add:
                bean = new PersonBean();
                bean.setUserName("张三");
                bean.setNickName("张三");
                bean.setAge(18);
                dao.add1(bean);
                break;
            case R.id.btn_update:
                bean = new PersonBean();
                bean.setId(1);
                bean.setUserName("张三1");
                bean.setNickName("张三1");
                bean.setAge(18);
                dao.update1(bean);
                break;
            case R.id.btn_delete:
                dao.deleteById1(1);
                break;
            case R.id.btn_query:
                dao.getAll1();
                break;
        }
    }
}