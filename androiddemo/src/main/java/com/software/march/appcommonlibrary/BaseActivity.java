package com.software.march.appcommonlibrary;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.software.march.R;
import com.software.march.utils.SPUtils;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description Activity 基类, 继承AppCompatActivity
 * @date 2017/3/7
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * 上下文
     */
    protected Context mContext;

    /**
     * 根布局
     */
    protected View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        mContext = this;
        rootView = View.inflate(mContext, getLayoutId(), null);
        setContentView(rootView);
        setToolbar();
        afterCreate(savedInstanceState);
    }

    /**
     * 强制子类重写,返回布局Id
     *
     * @return layout Id
     */
    protected abstract int getLayoutId();

    protected abstract void afterCreate(Bundle savedInstanceState);

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar == null)
            return;
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}