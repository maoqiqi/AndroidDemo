package com.software.march.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import com.software.march.R;
import com.software.march.adapter.MyArrayAdapter;
import com.software.march.bean.AppInfoBean;
import com.software.march.utils.AppUtils;
import com.software.march.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description ArrayAdapter
 * @date 2016/12/21
 */
public class ArrayAdapterActivity extends AppCompatActivity {

    private int type;

    private ListView listView;
    private GridView gridView;

    private List<AppInfoBean> list;
    private ArrayAdapter<AppInfoBean> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.layout_adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        type = getIntent().getIntExtra(UIActivity.TYPE, UIActivity.TYPE_LIST_VIEW);
        list = new ArrayList<>();

        listView = (ListView) findViewById(R.id.list_view);
        gridView = (GridView) findViewById(R.id.grid_view);

        if (type == UIActivity.TYPE_LIST_VIEW) {
            listView.setVisibility(View.VISIBLE);
            gridView.setVisibility(View.GONE);

            // 准备ArrayAdapter对象
            // 您的应用 Context,布局,字符串数组
            // new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
            // adapter = new ArrayAdapter<AppInfoBean>(this, R.layout.item_list_view, R.id.tv_label, list);
            // 重写ArrayAdapter
            adapter = new MyArrayAdapter(this, R.layout.item_list_view, list);
            // 设置Adapter显示列表
            listView.setAdapter(adapter);
        } else {
            listView.setVisibility(View.GONE);
            gridView.setVisibility(View.VISIBLE);

            adapter = new MyArrayAdapter(this, R.layout.item_grid_view, list);
            gridView.setAdapter(adapter);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 得到手机中所有应用信息的列表
                list.addAll(AppUtils.getAllAppInfoBeans(ArrayAdapterActivity.this));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }
}