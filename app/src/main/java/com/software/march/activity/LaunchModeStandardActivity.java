package com.software.march.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.software.march.R;
import com.software.march.utils.SPUtils;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description Launch Mode
 * @date 2016/12/23
 */
public class LaunchModeStandardActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = getClass().getSimpleName();

    private TextView tvMessage;
    private Button btnLaunchModeStandard;
    private Button btnLaunchModeSingleTop;
    private Button btnLaunchModeSingleTask;
    private Button btnLaunchModeSingleInstance;

    public LaunchModeStandardActivity() {
        Log.e(TAG, "创建：" + TAG);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_launch_mode);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvMessage = (TextView) findViewById(R.id.tv_message);
        btnLaunchModeStandard = (Button) findViewById(R.id.btn_launch_mode_standard);
        btnLaunchModeSingleTop = (Button) findViewById(R.id.btn_launch_mode_single_top);
        btnLaunchModeSingleTask = (Button) findViewById(R.id.btn_launch_mode_single_task);
        btnLaunchModeSingleInstance = (Button) findViewById(R.id.btn_launch_mode_single_instance);

        btnLaunchModeStandard.setOnClickListener(this);
        btnLaunchModeSingleTop.setOnClickListener(this);
        btnLaunchModeSingleTask.setOnClickListener(this);
        btnLaunchModeSingleInstance.setOnClickListener(this);

        tvMessage.setText("Activity standard");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_launch_mode_standard:
                startActivity(new Intent(this, LaunchModeStandardActivity.class));
                break;
            case R.id.btn_launch_mode_single_top:
                startActivity(new Intent(this, LaunchModeSingleTopActivity.class));
                break;
            case R.id.btn_launch_mode_single_task:
                startActivity(new Intent(this, LaunchModeSingleTaskActivity.class));
                break;
            case R.id.btn_launch_mode_single_instance:
                startActivity(new Intent(this, LaunchModeSingleInstanceActivity.class));
                break;
        }
    }
}