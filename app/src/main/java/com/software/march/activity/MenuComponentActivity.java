package com.software.march.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.software.march.R;
import com.software.march.utils.SPUtils;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description Menu组件
 * @date 2016/12/16
 */
public class MenuComponentActivity extends AppCompatActivity {

    private Button btnContextMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_menu_component);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnContextMenu = (Button) findViewById(R.id.btn_context_menu);

        // 设置创建上下文菜单的监听
        btnContextMenu.setOnCreateContextMenuListener(this);
    }

    /*
    ContextMenu
    1.如何触发Menu的显示?
    长按某个视图
    view.setOnCreateContextMenuListener(this)
    2.如何向Menu中添加MenuItem?
    重写onCreateContextMenu()
    menu.add()
    3.选择某个MenuItem时如何响应?
    重写onContextItemSelected(), 根据itemId做响应
    */

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        // 添加菜单项
        menu.add(0, 1, 0, "添加");
        menu.add(0, 4, 0, "删除");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Toast.makeText(this, "添加", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(this, "删除", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }

    /*
    OptionMenu
    1.如何触发Menu的显示?
    点击menu键
    2.如何向Menu中添加MenuItem?
    重写onCreateOptionMenu()
    menu.add()或者加载菜单文件
    3.选择某个MenuItem时如何响应?
    重写onOptionsItemSelected(),根据itemId做响应
    */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 一、菜单文件方式
        // 1.得到菜单加载器对象
        MenuInflater menuInflater = getMenuInflater();
        // 2.加载菜单文件
        menuInflater.inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 二、纯编码方式
        menu.add(0, 2, 0, "添加");
        menu.add(0, 3, 0, "删除");
        return super.onCreateOptionsMenu(menu);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                Toast.makeText(this, "添加", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "删除", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 2:
                Toast.makeText(this, "添加", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(this, "删除", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }*/
}