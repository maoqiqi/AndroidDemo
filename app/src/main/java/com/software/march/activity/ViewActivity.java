package com.software.march.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.software.march.R;
import com.software.march.utils.SPUtils;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description View和ViewGroup
 * @date 2017/1/17
 */
public class ViewActivity extends AppCompatActivity {

    // View是什么?
    // 1.View类是所有用来构建用户界面的组件的基类
    // 2.一个View对象占用屏幕上的一个矩形区域, 它负责界面的绘制和事件处理
    // 3.手机屏幕上所有看得见摸得着的都是View

    // ViewGroup是什么?
    // 1.ViewGroup是View的一个子类, 是各种布局的基类
    // 2.一个ViewGroup可以包含多个子View(ViewGroup)
    // 3.作用: 控制子View的布局
    // ViewManager接口:
    // public void addView(View view, ViewGroup.LayoutParams params);添加子View
    // public void updateViewLayout(View view, ViewGroup.LayoutParams params);更新子View的布局
    // public void removeView(View view);删除子View

    // 区别View与Activity
    // 1.Activity是四大组件中唯一能与用户进行直接交互的应用组件
    // 2.Activity只是控制和管理View, 真正显示和处理事件的是View本身来完成

    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        // 设置id为content的布局的子View
        setContentView(R.layout.activity_view);
        // 显示ContentView的 3 种方式
        // 1.public void setContentView(int layoutResID)
        // 2.public void setContentView(View view)
        //   1)动态加载布局文件得到视图对象
        //     View view = View.inflate(this, R.layout.activity_view, null);
        //   2)动态创建视图对象
        //     TextView view  = new TextView(this);
        // 3.public void setContentView(View view, ViewGroup.LayoutParams params)

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 得到根布局
        Window window = getWindow();
        View decorView = window.getDecorView();

        Log.e(TAG, decorView.toString());
        Log.e(TAG, decorView.getClass().getSuperclass().getName());
    }
}