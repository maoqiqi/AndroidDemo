package com.software.march.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.software.march.R;
import com.software.march.appcommonlibrary.BaseActivity;
import com.software.march.fragment.BaseFragment;
import com.software.march.fragment.BasicUseFragment;
import com.software.march.fragment.CommonFrameFragment;
import com.software.march.fragment.CustomFragment;
import com.software.march.fragment.ThirdPartyFragment;
import com.software.march.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private String[] titles = {"基本使用", "自定义控件", "常用框架", "第三方类库"};

    private Toolbar toolbar;
    private RadioGroup radioGroup;
    private List<BaseFragment> fragments;
    // 选中Fragment对应的位置
    private int position;
    // 上次切换的Fragment
    private BaseFragment mFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_avatar);
        toolbar.setTitle(titles[0]);
        setSupportActionBar(toolbar);

        radioGroup = (RadioGroup) findViewById(R.id.radio_group);

        // 初始化数据
        fragments = new ArrayList<>();
        fragments.add(BasicUseFragment.newInstance());
        fragments.add(CustomFragment.newInstance());
        fragments.add(CommonFrameFragment.newInstance());
        fragments.add(ThirdPartyFragment.newInstance());

        // 设置RadioGroup的监听
        radioGroup.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        // 设置默认选中常用框架
        radioGroup.check(R.id.rb_basic_use);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(mContext, "头像", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.switching_theme:
                SPUtils.changeDayNight(this);
                setTheme(SPUtils.getThemeRes(this));
                // recreate();

                Intent intent = getIntent();
                overridePendingTransition(0, 0);// 不设置进入退出动画
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
                overridePendingTransition(0, 0);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private final class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_basic_use:// 基本使用
                    position = 0;
                    break;
                case R.id.rb_custom:// 自定义
                    position = 1;
                    break;
                case R.id.rb_common_frame:// 常用框架
                    position = 2;
                    break;
                case R.id.rb_third_party:// 第三方类库
                    position = 3;
                    break;
                default:
                    position = 0;
                    break;
            }

            // 根据位置得到对应的fragment
            BaseFragment to = getFragment();
            // 替换
            switchFragment(mFragment, to);
        }
    }

    // 根据位置得到对应的fragment
    private BaseFragment getFragment() {
        return fragments.get(position);
    }

    /**
     * 隐藏于添加fragment
     *
     * @param from 刚刚显示的Fragment,马上就要被隐藏了
     * @param to   要切换到的Fragment,一会要显示
     */
    private void switchFragment(BaseFragment from, BaseFragment to) {
        toolbar.setTitle(titles[position]);
        if (from != to) {//from != to才切换
            mFragment = to;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            // 判断有没有被添加
            if (!to.isAdded()) {
                // 没有被添加,from隐藏,添加to
                if (from != null)
                    ft.hide(from);

                if (to != null)
                    ft.add(R.id.fl_content, to).commit();
            } else {
                // 已经被添加,from隐藏,显示to
                if (from != null)
                    ft.hide(from);

                if (to != null)
                    ft.show(to).commit();
            }
        }
    }
}
