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
 * @Description 第三方Fragment
 * @date 2016/11/5
 */
public class ThirdPartyFragment extends BaseFragment {

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
        TextView textView = new TextView(getActivity());
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(20);
        textView.setTextColor(Color.RED);
        textView.setText("第三方页面");
        return textView;
    }
}