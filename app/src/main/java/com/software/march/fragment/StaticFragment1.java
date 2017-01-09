package com.software.march.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description
 * @date 2017/1/9
 */
public class StaticFragment1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        TextView tv = new TextView(getActivity());
        tv.setText("StaticFragment1");
        tv.setBackgroundColor(Color.RED);

        return tv;
    }
}