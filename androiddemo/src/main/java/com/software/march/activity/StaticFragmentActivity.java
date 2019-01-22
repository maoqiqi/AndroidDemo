package com.software.march.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.software.march.R;
import com.software.march.utils.SPUtils;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 静态加载Fragment
 * @date 2016/12/14
 * @update 2017/1/9
 */
public class StaticFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_static_fragment);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Fragment(静态加载)
        // 1.定义Fragment的子类, 并加载一个布局文件
        // 2.在布局文件中通过<fragment>指定指定自定义Fragment
        // 3.我们的Activity必须继承于FragmentActivity
        // 每个Fragment本质上都会生成一个FrameLayout, 它加载的布局为其子布局
    }
}