package com.software.march.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.software.march.R;
import com.software.march.adapter.ContentProviderAdapter;
import com.software.march.bean.MediaItemBean;
import com.software.march.utils.MediaUtils;
import com.software.march.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description Content Provider
 * @date 2017/1/4
 */
public class ContentProviderActivity extends AppCompatActivity {

    private ListView listView;
    private List<MediaItemBean> list;
    private ArrayAdapter<MediaItemBean> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_content_provider);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        list = new ArrayList<>();

        listView = (ListView) findViewById(R.id.list_view);
        adapter = new ContentProviderAdapter(this, R.layout.item_content_provider, list);
        listView.setAdapter(adapter);

        new Thread(new Runnable() {
            @Override
            public void run() {
                list.addAll(MediaUtils.getAllVideos(ContentProviderActivity.this));
            }
        }).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_provider, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.person_provider:
                startActivity(new Intent(this, PersonProviderActivity.class));
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}