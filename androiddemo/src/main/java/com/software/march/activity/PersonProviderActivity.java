package com.software.march.activity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.software.march.R;
import com.software.march.adapter.PersonProviderAdapter;
import com.software.march.bean.PersonBean;
import com.software.march.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description Person Content Provider
 * @date 2017/1/5
 */
public class PersonProviderActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = getClass().getSimpleName();

    private Button btnAdd;
    private Button btnQuery;

    private ListView listView;
    private PersonProviderAdapter adapter;
    private List<PersonBean> list;

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_person_provider);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnAdd = (Button) findViewById(R.id.btn_add);
        btnQuery = (Button) findViewById(R.id.btn_query);
        listView = (ListView) findViewById(R.id.list_view);

        btnAdd.setOnClickListener(this);
        btnQuery.setOnClickListener(this);

        list = new ArrayList<>();
        adapter = new PersonProviderAdapter(this, list);
        listView.setAdapter(adapter);
        listView.setOnCreateContextMenuListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                add();
                query();
                break;
            case R.id.btn_query:
                query();
                break;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 1, 0, "更新");
        menu.add(0, 2, 0, "删除");

        // 得到长按的position
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        position = info.position;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:// 更新
                update();
                query();
                break;
            case 2:// 删除
                delete();
                query();
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }

    /**
     * 通过ContentResolver调用ContentProvider插入一条记录
     */
    private void add() {
        // 得到ContentResolver对象
        ContentResolver resolver = getContentResolver();
        // 调用其query, 得到cursor
        Uri uri = Uri.parse("content://com.software.march.provider/person");
        ContentValues values = new ContentValues();
        values.put("userName", "赵六");
        values.put("nickName", "赵六");
        values.put("age", 60);
        uri = resolver.insert(uri, values);

        Log.e(TAG, "uri:" + uri.toString());
    }

    /**
     * 通过ContentResolver调用ContentProvider查询所有记录
     */
    private void query() {
        // 得到ContentResolver对象
        ContentResolver resolver = getContentResolver();
        // 调用其query, 得到cursor
        Uri uri = Uri.parse("content://com.software.march.provider/person");
        String[] projection = null;
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;
        Cursor cursor = resolver.query(uri, projection, selection, selectionArgs, sortOrder);
        // 取出cursor中的数据, 并显示
        list.clear();
        while (cursor.moveToNext()) {
            PersonBean bean = new PersonBean();
            bean.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            bean.setUserName(cursor.getString(cursor.getColumnIndex("userName")));
            bean.setNickName(cursor.getString(cursor.getColumnIndex("nickName")));
            bean.setAge(cursor.getInt(cursor.getColumnIndex("age")));
            Log.i(TAG, bean.toString());
            list.add(bean);
        }
        cursor.close();
        adapter.notifyDataSetChanged();
    }

    /**
     * 通过ContentResolver调用ContentProvider更新一条记录
     */
    private void update() {
        ContentResolver resolver = getContentResolver();
        Uri uri = Uri.parse("content://com.software.march.provider/person/" + list.get(position).getId());
        ContentValues values = new ContentValues();
        values.put("nickName", "小六");
        int updateCount = resolver.update(uri, values, null, null);

        Log.e(TAG, "updateCount:" + updateCount);
    }

    /**
     * 通过ContentResolver调用ContentProvider删除一条记录
     */
    private void delete() {
        ContentResolver resolver = getContentResolver();
        Uri uri = Uri.parse("content://com.software.march.provider/person/" + list.get(position).getId());
        int deleteCount = resolver.delete(uri, null, null);

        Log.e(TAG, "deleteCount:" + deleteCount);
    }
}