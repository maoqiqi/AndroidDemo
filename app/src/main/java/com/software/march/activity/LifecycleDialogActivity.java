package com.software.march.activity;

import android.app.Activity;
import android.os.Bundle;

import com.software.march.R;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 在Activity上显示
 * @date 2016/12/14
 */
public class LifecycleDialogActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle_dialog);
        setFinishOnTouchOutside(false);
    }
}