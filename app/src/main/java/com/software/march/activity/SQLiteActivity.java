package com.software.march.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.software.march.R;
import com.software.march.adapter.SQLiteAdapter;
import com.software.march.bean.PersonBean;
import com.software.march.db.PersonDAO;
import com.software.march.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 数据库
 * @date 2016/12/27
 */
public class SQLiteActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnCreate;
    private Button btnAdd;
    private Button btnUpdate;
    private Button btnDelete;
    private Button btnQuery;

    private ListView listView;
    private SQLiteAdapter adapter;
    private List<PersonBean> list;

    private int position;

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
        listView = (ListView) findViewById(R.id.list_view);

        btnCreate.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnQuery.setOnClickListener(this);

        list = new ArrayList<>();
        adapter = new SQLiteAdapter(this, list);
        listView.setAdapter(adapter);
        // 给listView设置创建contextMenu的监听
        listView.setOnCreateContextMenuListener(this);
        // registerForContextMenu(listView);

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

                updateAdapter();
                break;
            case R.id.btn_update:
                bean = new PersonBean();
                bean.setId(1);
                bean.setUserName("张三1");
                bean.setNickName("张三1");
                bean.setAge(18);
                dao.update1(bean);

                updateAdapter();
                break;
            case R.id.btn_delete:
                dao.deleteById1(1);

                updateAdapter();
                break;
            case R.id.btn_query:
                dao.getAll1();

                updateAdapter();
                break;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        // 添加2个item
        menu.add(0, 1, 0, "更新");
        menu.add(0, 2, 0, "删除");

        // 得到长按的position
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        position = info.position;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // 得到对应的BlackNumber对象
        PersonBean bean = list.get(position);
        switch (item.getItemId()) {
            case 1:// 更新
                showUpdateDialog(bean);
                break;
            case 2:// 删除
                // 1.删除数据表对应的数据
                dao.deleteById1(bean.getId());
                // 2.删除List对应的数据
                list.remove(position);
                // 3.通知更新列表
                adapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }

    // 显示更新的Dialog
    private void showUpdateDialog(final PersonBean bean) {
        new AlertDialog.Builder(this)
                .setTitle("更新用户信息")
                .setPositiveButton("更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 1.更新List对应的数据
                        bean.setUserName("李四");
                        bean.setNickName("李四");
                        bean.setAge(20);
                        // 2.更新数据表对应的数据
                        dao.update1(bean);
                        // 3.通知更新列表
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("取消", null).show();
    }

    private void updateAdapter() {
        list.clear();
        list.addAll(dao.getAll1());
        adapter.notifyDataSetChanged();
    }
}