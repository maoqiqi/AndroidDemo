package com.software.march.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.software.march.R;
import com.software.march.adapter.BasicUseAdapter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 基本使用的Fragment
 * @date 2016/11/5
 */
public class BasicUseFragment extends BaseFragment {

    private View rootView;
    private ListView listView;
    private String[] data;
    private BasicUseAdapter adapter;

    /**
     * Use this factory method to create a new instance of this fragment using the provided parameters.
     *
     * @return A new instance of fragment BasicUseFragment.
     */
    public static BasicUseFragment newInstance() {
        BasicUseFragment fragment = new BasicUseFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_basic_use, container, false);
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
                "Activity", "Service", "BroadcastReceiver", "ContentProvider",
                "SharedPreferences", "文件存储", "SQLite", "ContentProvider", "网络存储",
                "消息机制与异步任务",
                "事件机制",
                "动画"
        };
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "Android 四大应用组件");
        map.put(4, "Android 五种数据存储方式");
        map.put(9, "Android 消息机制与异步任务");
        map.put(10, "Android 事件机制");
        map.put(11, "Android 动画");
        // 设置适配器
        adapter = new BasicUseAdapter(getActivity(), Arrays.asList(data), map);
        listView.setAdapter(adapter);
    }
}