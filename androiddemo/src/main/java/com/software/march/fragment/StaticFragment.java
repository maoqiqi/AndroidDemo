package com.software.march.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 静态创建Fragment生命周期测试
 * @date 2017/1/16
 */
public class StaticFragment extends Fragment {

    private final String TAG = getClass().getSimpleName();

    public StaticFragment() {
        Log.e(TAG, TAG + "()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView()");

        TextView tv = new TextView(getActivity());
        tv.setText("静态创建Fragment生命周期测试");
        tv.setBackgroundColor(Color.RED);

        return tv;
    }
}