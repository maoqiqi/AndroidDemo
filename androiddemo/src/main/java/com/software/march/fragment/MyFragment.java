package com.software.march.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 替换显示Fragment生命周期测试
 * @date 2017/1/16
 */
public class MyFragment extends Fragment {

    private final String TAG = getClass().getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView()");

        TextView tv = new TextView(getActivity());
        tv.setText("替换显示Fragment生命周期测试");
        tv.setBackgroundColor(Color.YELLOW);

        return tv;
    }
}