package com.software.march.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.software.march.R;
import com.software.march.fragment.DynamicFragment1;
import com.software.march.fragment.DynamicFragment2;
import com.software.march.utils.SPUtils;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 动态加载Fragment
 * @date 2016/12/14
 * @update 2017/1/9
 */
public class DynamicFragmentActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnShowDynamicFragment2;
    private Button btnDeleteDynamicFragment2;

    private DynamicFragment2 fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_dynamic_fragment);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnShowDynamicFragment2 = (Button) findViewById(R.id.btn_show_dynamic_fragment_2);
        btnDeleteDynamicFragment2 = (Button) findViewById(R.id.btn_delete_dynamic_fragment_2);

        btnShowDynamicFragment2.setOnClickListener(this);
        btnDeleteDynamicFragment2.setOnClickListener(this);

        // Fragment(动态使用)
        // 1.使用FragmentManager和FragmentTransaction动态使用一个Fragment
        // 2.方式:
        // add(viewId, fragment):将fragment的视图添加为指定视图的子视图(加在原有子视图的后面)
        // replace(viewId, fragment):将fragment的视图添加为指定视图的子视图(先remove原有的子视图)
        // 3.remove(fragment):将fragment的视图移除

        // 1.创建Fragment对象
        DynamicFragment1 fragment1 = new DynamicFragment1();
        // 2.得到FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        // 3.得到fragmentTransaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // 4.添加Fragment对象并提交
        fragmentTransaction.add(R.id.dynamic_fragment_1, fragment1).commit();
    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;
        switch (v.getId()) {
            case R.id.btn_show_dynamic_fragment_2:
                fragment2 = new DynamicFragment2();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                // 将当前操作添加到回退栈, 这样点击back回到上一个状态
                fragmentTransaction.addToBackStack(null);
                // 替换Fragment对象并提交
                fragmentTransaction.replace(R.id.dynamic_fragment_2, fragment2).commit();
                break;
            case R.id.btn_delete_dynamic_fragment_2:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                // 移除Fragment对象并提交
                fragmentTransaction.remove(fragment2).commit();
                break;
        }
    }
}