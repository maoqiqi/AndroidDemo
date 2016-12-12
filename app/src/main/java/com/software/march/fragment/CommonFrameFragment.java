package com.software.march.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 常用框架的Fragment
 * @date 2016/11/5
 */
public class CommonFrameFragment extends BaseFragment {

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
        TextView textView = new TextView(getActivity());
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(20);
        textView.setTextColor(Color.RED);
        textView.setText("常用框架页面");
        return textView;
    }
}