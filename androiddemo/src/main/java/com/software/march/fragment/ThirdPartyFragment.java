package com.software.march.fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.software.march.R;
import com.software.march.adapter.FragmentAdapter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 第三方类库Fragment
 * @date 2016/11/5
 */
public class ThirdPartyFragment extends BaseFragment {

    private View rootView;
    private ListView listView;
    private String[] data;
    private FragmentAdapter adapter;

    /**
     * Use this factory method to create a new instance of this fragment using the provided parameters.
     *
     * @return A new instance of fragment ThirdPartyFragment.
     */
    public static ThirdPartyFragment newInstance() {
        ThirdPartyFragment fragment = new ThirdPartyFragment();
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
                "第三方类库"
        };
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "第三方类库");
        Class<? extends AppCompatActivity>[] clazzs = new Class[data.length];
        adapter = new FragmentAdapter(getActivity(), Arrays.asList(data), map, clazzs);
        listView.setAdapter(adapter);
    }
}