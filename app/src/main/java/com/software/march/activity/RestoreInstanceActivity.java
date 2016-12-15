package com.software.march.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.software.march.R;
import com.software.march.utils.SPUtils;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 保存Activity状态
 * @date 2016/12/14
 */
public class RestoreInstanceActivity extends AppCompatActivity {

    // 屏幕旋转
    // RestoreInstanceActivity,onPause()->
    // RestoreInstanceActivity,onSaveInstanceState()->
    // RestoreInstanceActivity,onStop()->
    // RestoreInstanceActivity,onDestroy()->
    // RestoreInstanceActivity,onCreate()->
    // RestoreInstanceActivity,onStart()->
    // RestoreInstanceActivity,onRestoreInstanceState()->
    // RestoreInstanceActivity,onResume()

    // 系统会先调用 onSaveInstanceState()，然后再使 Activity 变得易于销毁。
    // 系统会向该方法传递一个 Bundle，您可以在其中使用 putString() 和 putInt() 等方法以名称-值对形式保存有关 Activity 状态的信息。
    // 然后，如果系统终止您的应用进程，并且用户返回您的 Activity，则系统会重建该 Activity，并将 Bundle 同时传递给 onCreate() 和 onRestoreInstanceState()。
    // 您可以使用上述任一方法从 Bundle 提取您保存的状态并恢复该 Activity 状态。
    // 如果没有状态信息需要恢复，则传递给您的 Bundle 是空值（如果是首次创建该 Activity，就会出现这种情况）。

    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, TAG + ",onCreate()");
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_restore_instance);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, TAG + ",onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, TAG + ",onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, TAG + ",onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, TAG + ",onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, TAG + ",onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, TAG + ",onDestroy()");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, TAG + ",onSaveInstanceState()");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, TAG + ",onRestoreInstanceState()");
    }
}