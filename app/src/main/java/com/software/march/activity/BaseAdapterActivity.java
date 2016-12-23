package com.software.march.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;

import com.software.march.R;
import com.software.march.adapter.MyBaseAdapter;
import com.software.march.bean.AppInfoBean;
import com.software.march.utils.AppUtils;
import com.software.march.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description BaseAdapter
 * @date 2016/12/21
 */
public class BaseAdapterActivity extends AppCompatActivity {

    private int type;

    private ListView listView;
    private GridView gridView;

    private List<AppInfoBean> list;
    private BaseAdapter adapter;

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

            // 准备BaseAdapter对象
            adapter = new MyBaseAdapter(this, R.layout.item_list_view, list);
            // 设置Adapter显示列表
            listView.setAdapter(adapter);
        } else {
            listView.setVisibility(View.GONE);
            gridView.setVisibility(View.VISIBLE);

            adapter = new MyBaseAdapter(this, R.layout.item_grid_view, list);
            gridView.setAdapter(adapter);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 得到手机中所有应用信息的列表
                list.addAll(AppUtils.getAllAppInfoBeans(BaseAdapterActivity.this));
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