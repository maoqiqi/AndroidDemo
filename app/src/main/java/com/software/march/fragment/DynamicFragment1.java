package com.software.march.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description
 * @date 2017/1/9
 */
public class DynamicFragment1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //  加载布局得到View对象并返回

        // 创建一个视图对象, 设置数据并返回
        TextView tv = new TextView(getActivity());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT);
        tv.setLayoutParams(params);
        tv.setText("DynamicFragment1");
        tv.setBackgroundColor(Color.RED);

        return tv;
    }
}