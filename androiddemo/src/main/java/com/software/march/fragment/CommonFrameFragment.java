package com.software.march.fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.software.march.R;
import com.software.march.adapter.FragmentAdapter;
import com.software.march.commonframe.network.OkhttpActivity;
import com.software.march.commonframe.network.OkhttputilsActivity;
import com.software.march.commonframe.network.RetrofitActivity;
import com.software.march.commonframe.rxjava2.RxJava2Activity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 常用框架的Fragment
 * @date 2016/11/5
 */
public class CommonFrameFragment extends BaseFragment {

    private View rootView;
    private ListView listView;
    private String[] data;
    private FragmentAdapter adapter;

    /**
     * Use this factory method to create a new instance of this fragment using the provided parameters.
     *
     * @return A new instance of fragment CommonFrameFragment.
     */
    public static CommonFrameFragment newInstance() {
        CommonFrameFragment fragment = new CommonFrameFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
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
                "okhttp", "okhttp + retrofit", "okhttp + okhttputils",
                "rxjava2"
        };
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "网络框架");
        map.put(3, "RxJava");
        Class<? extends AppCompatActivity>[] clazzs = new Class[data.length];
        clazzs[0] = OkhttpActivity.class;
        clazzs[1] = RetrofitActivity.class;
        clazzs[2] = OkhttputilsActivity.class;
        clazzs[3] = RxJava2Activity.class;
        adapter = new FragmentAdapter(getActivity(), Arrays.asList(data), map, clazzs);
        listView.setAdapter(adapter);
    }
}