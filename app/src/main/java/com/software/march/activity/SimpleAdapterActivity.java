package com.software.march.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.software.march.R;
import com.software.march.bean.AppInfoBean;
import com.software.march.utils.AppUtils;
import com.software.march.utils.SPUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description SimpleAdapter
 * @date 2016/12/21
 */
public class SimpleAdapterActivity extends AppCompatActivity {

    private int type;

    private ListView listView;
    private GridView gridView;

    private List<Map<String, Object>> list;
    private SimpleAdapter adapter;

    // map对象中的key的数组, 用于得到对应的value
    private static final String[] from = {"icon", "label"};
    // Item布局文件中的子view的id的数组
    private static final int[] to = {R.id.iv_icon, R.id.tv_label};

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

            // 准备SimpleAdapter对象
            adapter = new SimpleAdapter(this, list, R.layout.item_list_view, from, to);
            // 设置Adapter显示列表
            listView.setAdapter(adapter);
        } else {
            listView.setVisibility(View.GONE);
            gridView.setVisibility(View.VISIBLE);

            adapter = new SimpleAdapter(this, list, R.layout.item_grid_view, from, to);
            gridView.setAdapter(adapter);
        }

        // 设置图片,默认只能显示文字
        adapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object o, String s) {
                if (view instanceof ImageView && o instanceof Drawable) {
                    ImageView iv = (ImageView) view;
                    Drawable drawable = (Drawable) o;
                    iv.setImageDrawable(drawable);
                    return true;
                }
                return false;
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 得到手机中所有应用信息的列表
                List<AppInfoBean> appInfoBeanList = AppUtils.getAllAppInfoBeans(SimpleAdapterActivity.this);
                for (int i = 0; i < appInfoBeanList.size(); i++) {
                    Map<String, Object> map = new HashMap<>();
                    map.put(from[0], appInfoBeanList.get(i).getIcon());
                    map.put(from[1], appInfoBeanList.get(i).getAppName());
                    list.add(map);
                }
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