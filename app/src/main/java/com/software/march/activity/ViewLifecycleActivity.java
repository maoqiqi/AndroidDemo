package com.software.march.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.software.march.R;
import com.software.march.utils.SPUtils;
import com.software.march.view.MyView;
import com.software.march.view.MyViewGroup;

/**
 * @author Doc.March
 * @version V 1.Toast.LENGTH_SHORT
 * @Description View生命周期
 * @date 2Toast.LENGTH_SHORT17/1/17
 */
public class ViewLifecycleActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = getClass().getSimpleName();

    private MyViewGroup myViewGroup;
    private MyView myView;
    private Button btnForceLayout;
    private Button btnForceDraw;
    private Button btnRemoveView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate()");
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_view_lifecycle);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myViewGroup = (MyViewGroup) findViewById(R.id.my_view_group);
        myView = (MyView) findViewById(R.id.my_view);
        btnForceLayout = (Button) findViewById(R.id.btn_force_layout);
        btnForceDraw = (Button) findViewById(R.id.btn_force_draw);
        btnRemoveView = (Button) findViewById(R.id.btn_remove_view);

        btnForceLayout.setOnClickListener(this);
        btnForceDraw.setOnClickListener(this);
        btnRemoveView.setOnClickListener(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, TAG + "onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible.
        Log.i(TAG, TAG + "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, TAG + "onResume()");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_force_layout:
                myViewGroup.requestLayout();
                myView.requestLayout();
                
                Toast.makeText(this, "强制重新布局", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_force_draw:
                myViewGroup.invalidate();
                // myView.invalidate();
                // myViewGroup.postInvalidate();
                // myView.postInvalidate();

                new Thread() {
                    public void run() {
                        // 不可以, 只能在主线程执行
                        // myViewGroup.invalidate();
                        // myView.invalidate();

                        // 可以在主线程和分线程执行
                        // myViewGroup.postInvalidate();
                        // myView.postInvalidate();
                    }
                }.start();

                Toast.makeText(this, "强制重新绘", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_remove_view:
                ViewGroup viewGroup = (ViewGroup) myView.getParent();
                viewGroup.removeView(myView);

                viewGroup = (ViewGroup) myViewGroup.getParent();
                viewGroup.removeView(myViewGroup);

                Toast.makeText(this, "移除View", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, TAG + "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, TAG + "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, TAG + "onDestroy()");
    }
}