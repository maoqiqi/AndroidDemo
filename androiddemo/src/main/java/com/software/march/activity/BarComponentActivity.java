package com.software.march.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import com.software.march.R;
import com.software.march.utils.SPUtils;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description ProgressBar、SeekBar组件
 * @date 2016/12/16
 */
public class BarComponentActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    private LinearLayout llLoading;
    private ProgressBar pbLoading;
    private SeekBar sbLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_bar_component);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        llLoading = (LinearLayout) findViewById(R.id.ll_loading);
        pbLoading = (ProgressBar) findViewById(R.id.pb_loading);
        sbLoading = (SeekBar) findViewById(R.id.sb_loading);

        // 给SeekBar设置监听
        sbLoading.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i(TAG, "滑杆移动");

                // 1.得到SeekBar的进度
                int progress = seekBar.getProgress();
                // 2.设置为ProgressBar的进度
                pbLoading.setProgress(progress);

                // 3.判断是否达到最大值
                if (progress == seekBar.getMax()) {
                    // 如果达到了,设置ll_loading不可见
                    llLoading.setVisibility(View.GONE);
                } else {
                    // 如果没有达到,设置ll_loading显示
                    llLoading.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.i(TAG, "按下滑杆");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.e(TAG, "离开滑杆");
            }
        });
    }
}