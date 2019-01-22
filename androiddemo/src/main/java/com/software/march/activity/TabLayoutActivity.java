package com.software.march.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.software.march.R;
import com.software.march.appcommonlibrary.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description TabLayout
 * @date 2017/3/4
 */
public class TabLayoutActivity extends BaseActivity {

    private List<String> titles;
    private List<String> contents;

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tab_layout;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        titles = new ArrayList<>();
        contents = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            titles.add("标题 " + (i + 1));
            contents.add("内容 " + (i + 1));
        }

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new TabLayoutFragmentPagerAdapter(getSupportFragmentManager()));

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        // tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private class TabLayoutFragmentPagerAdapter extends FragmentPagerAdapter {

        public TabLayoutFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return TabLayoutFragment.newInstance(contents.get(position));
        }

        @Override
        public int getCount() {
            return titles.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }

    public static class TabLayoutFragment extends Fragment {

        private Context context;

        private TextView tv;

        private String content;

        /**
         * Use this factory method to create a new instance of this fragment using the provided parameters.
         *
         * @return A new instance of fragment TabLayoutFragment.
         */
        public static TabLayoutFragment newInstance(String content) {
            TabLayoutFragment fragment = new TabLayoutFragment();
            Bundle bundle = new Bundle();
            bundle.putString("content", content);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            context = getActivity();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            tv = new TextView(context);
            tv.setBackgroundColor(Color.YELLOW);
            tv.setTextSize(30);
            tv.setTextColor(Color.RED);
            tv.setGravity(Gravity.CENTER);
            return tv;
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            content = getArguments().getString("content", "");
            tv.setText(content);
        }
    }
}