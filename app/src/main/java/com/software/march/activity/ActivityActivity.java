package com.software.march.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.software.march.R;
import com.software.march.utils.SPUtils;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description Activity相关内容
 * @date 2016/12/14
 * @update 2017/1/9
 */
public class ActivityActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = getClass().getSimpleName();

    private Button btnLifecycle;
    private Button btnRestoreInstance;
    private Button btnStartMode;
    private Button btnLaunchMode;
    private Button btnIntentFilter;
    private Button btnCommonComponent;
    private Button btnFragment;

    /**
     * 重写的方法：onCreate(Bundle savedInstanceState)
     * 在当前类(activity)对象创建的时候自动调用。
     * 回调方法:不是我们调的,是系统在一定条件下自动调用的,基本都以on开头onXXX,这些方法我们不需要调用,一般只是去重写此类方法.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 调用父类处理做一些默认的初始化工作
        super.onCreate(savedInstanceState);
        // 动态设置主题
        setTheme(SPUtils.getThemeRes(this));
        // 加载布局文件,并在内存中生成对应的视图对象
        setContentView(R.layout.activity_activity);

        // 根据id在内存查找得到对应的视图对象
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 初始化视图对象
        btnLifecycle = (Button) findViewById(R.id.btn_lifecycle);
        btnRestoreInstance = (Button) findViewById(R.id.btn_restore_instance);
        btnStartMode = (Button) findViewById(R.id.btn_start_mode);
        btnLaunchMode = (Button) findViewById(R.id.btn_launch_mode);
        btnIntentFilter = (Button) findViewById(R.id.btn_intent_filter);
        btnCommonComponent = (Button) findViewById(R.id.btn_common_component);
        btnFragment = (Button) findViewById(R.id.btn_fragment);

        // 设置点击监听
        btnLifecycle.setOnClickListener(this);
        btnRestoreInstance.setOnClickListener(this);
        btnStartMode.setOnClickListener(this);
        btnLaunchMode.setOnClickListener(this);
        btnIntentFilter.setOnClickListener(this);
        btnCommonComponent.setOnClickListener(this);
        btnFragment.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_lifecycle:
                startActivity(new Intent(this, LifecycleActivity.class));
                break;
            case R.id.btn_restore_instance:
                startActivity(new Intent(this, RestoreInstanceActivity.class));
                break;
            case R.id.btn_start_mode:
                startActivity(new Intent(this, StartModeActivity.class));
                break;
            case R.id.btn_launch_mode:
                startActivity(new Intent(this, LaunchModeActivity.class));
                break;
            case R.id.btn_intent_filter:
                startActivity(new Intent(this, IntentFilterActivity.class));
                break;
            case R.id.btn_common_component:
                startActivity(new Intent(this, UIActivity.class));
                break;
            case R.id.btn_fragment:
                startActivity(new Intent(this, FragmentActivity.class));
                break;
        }
    }
}