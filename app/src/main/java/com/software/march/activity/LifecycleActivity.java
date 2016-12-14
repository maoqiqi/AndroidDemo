package com.software.march.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.software.march.R;
import com.software.march.utils.SPUtils;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description Activity生命周期
 * @date 2016/12/14
 */
public class LifecycleActivity extends AppCompatActivity {

    private final String NUMBER = "NUMBER";

    private final String TAG = getClass().getSimpleName();

    private int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // The activity is being created.
        number = getIntent().getIntExtra(NUMBER, 1);
        Log.i(TAG, TAG + number + ",onCreate()");
        // 首次创建 Activity 时调用。 您应该在此方法中执行所有正常的静态设置 — 创建视图、将数据绑定到列表等等。
        // 始终后接 onStart()。
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_lifecycle);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView tvPagerNumber = (TextView) findViewById(R.id.tv_pager_number);
        tvPagerNumber.setText("这是第" + number + "个页面");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, TAG + number + ",onRestart()");
        // 在 Activity 已停止并即将再次启动前调用。
        // 始终后接 onStart()。
    }

    @Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible.
        Log.i(TAG, TAG + number + ",onStart()");
        // 在 Activity 即将对用户可见之前调用。
        // 如果 Activity 转入前台，则后接 onResume()，如果 Activity 转入隐藏状态，则后接 onStop()。
    }

    @Override
    protected void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed").
        Log.i(TAG, TAG + number + ",onResume()");
        // 在 Activity 即将开始与用户进行交互之前调用。 此时，Activity 处于 Activity 堆栈的顶层，并具有用户输入焦点。
        // 始终后接 onPause()。
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_lifecycle, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_activity:
                Intent intent = new Intent(this, LifecycleActivity.class);
                intent.putExtra(NUMBER, ++number);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Another activity is taking focus (this activity is about to be "paused").
        Log.i(TAG, TAG + number + ",onPause()");
        // 当系统即将开始继续另一个 Activity 时调用。
        // 此方法通常用于确认对持久性数据的未保存更改、停止动画以及其他可能消耗 CPU 的内容，诸如此类。
        // 它应该非常迅速地执行所需操作，因为它返回后，下一个 Activity 才能继续执行。
    }

    @Override
    protected void onStop() {
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
        Log.i(TAG, TAG + number + ",onStop()");
        // 在 Activity 对用户不再可见时调用。
        // 如果 Activity 被销毁，或另一个 Activity（一个现有 Activity 或新 Activity）继续执行并将其覆盖，就可能发生这种情况。
        // 如果 Activity 恢复与用户的交互，则后接 onRestart()，如果 Activity 被销毁，则后接 onDestroy()。
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // The activity is about to be destroyed.
        Log.i(TAG, TAG + number + ",onDestroy()");
        // 在 Activity 被销毁前调用。这是 Activity 将收到的最后调用。
        // 当 Activity 结束（有人对 Activity 调用了 finish()），或系统为节省空间而暂时销毁该 Activity 实例时，可能会调用它。
        // 您可以通过 isFinishing() 方法区分这两种情形。
    }
}