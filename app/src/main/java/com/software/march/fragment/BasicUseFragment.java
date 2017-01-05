package com.software.march.fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.software.march.R;
import com.software.march.activity.ActivityActivity;
import com.software.march.activity.ApplicationInstallationLocationActivity;
import com.software.march.activity.BroadcastReceiverActivity;
import com.software.march.activity.ContentProviderActivity;
import com.software.march.activity.DataBackupActivity;
import com.software.march.activity.ServiceActivity;
import com.software.march.activity.StorageOptionsActivity;
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
                "存储选项", "数据备份", "应用安装位置",
                "消息机制与异步任务",
                "事件机制",
                "动画"
        };
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "Android 四大应用组件");
        map.put(4, "数据存储");
        map.put(7, "Android 消息机制与异步任务");
        map.put(8, "Android 事件机制");
        map.put(9, "Android 动画");
        Class<? extends AppCompatActivity>[] clazzs = new Class[data.length];
        clazzs[0] = ActivityActivity.class;
        clazzs[1] = ServiceActivity.class;
        clazzs[2] = BroadcastReceiverActivity.class;
        clazzs[3] = ContentProviderActivity.class;
        clazzs[4] = StorageOptionsActivity.class;
        clazzs[5] = DataBackupActivity.class;
        clazzs[6] = ApplicationInstallationLocationActivity.class;
        // 设置适配器
        adapter = new BasicUseAdapter(getActivity(), Arrays.asList(data), map, clazzs);
        listView.setAdapter(adapter);
    }
}