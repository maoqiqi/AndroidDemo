package com.software.march.fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.software.march.R;
import com.software.march.activity.CustomViewActivity;
import com.software.march.activity.ViewActivity;
import com.software.march.activity.ViewLifecycleActivity;
import com.software.march.activity.YouKuMenuActivity;
import com.software.march.adapter.BaseAdapter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 自定义控件Fragment
 * @date 2016/11/5
 */
public class CustomFragment extends BaseFragment {

    private View rootView;
    private ListView listView;
    private String[] data;
    private BaseAdapter adapter;

    /**
     * Use this factory method to create a new instance of this fragment using the provided parameters.
     *
     * @return A new instance of fragment CustomFragment.
     */
    public static CustomFragment newInstance() {
        CustomFragment fragment = new CustomFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_base, container, false);
        listView = (ListView) rootView.findViewById(R.id.list_view);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected void initData() {
        data = new String[]{
                "View和ViewGroup", "View的生命周期",
                "自定义View", "优酷菜单"
        };
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "View基础");
        map.put(2, "自定义View");
        Class<? extends AppCompatActivity>[] clazzs = new Class[data.length];
        clazzs[0] = ViewActivity.class;
        clazzs[1] = ViewLifecycleActivity.class;
        clazzs[2] = CustomViewActivity.class;
        clazzs[3] = YouKuMenuActivity.class;
        adapter = new BaseAdapter(getActivity(), Arrays.asList(data), map, clazzs);
        listView.setAdapter(adapter);
    }
}