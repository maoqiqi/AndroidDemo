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
 * @Description 自定义控件Fragment
 * @date 2016/11/5
 */
public class CustomFragment extends BaseFragment {

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
        TextView textView = new TextView(getActivity());
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(20);
        textView.setTextColor(Color.RED);
        textView.setText("自定义控件页面");
        return textView;
    }
}