package com.software.march.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.software.march.R;
import com.software.march.fragment.DynamicFragment;
import com.software.march.fragment.MyFragment;
import com.software.march.fragment.StaticFragment;
import com.software.march.utils.SPUtils;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description Fragment生命周期
 * @date 2017/1/16
 */
public class FragmentLifecycleActivity extends AppCompatActivity implements View.OnClickListener {

    private StaticFragment staticFragment;
    private Button btnReplaceDynamicFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_fragment_lifecycle);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        staticFragment = (StaticFragment) getSupportFragmentManager().findFragmentById(R.id.static_fragment);
        btnReplaceDynamicFragment = (Button) findViewById(R.id.btn_replace_dynamic_fragment);

        btnReplaceDynamicFragment.setOnClickListener(this);

        DynamicFragment fragment = new DynamicFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.dynamic_fragment, fragment).commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_replace_dynamic_fragment:
                MyFragment fragment = new MyFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.add(R.id.dynamic_fragment, fragment).commit();
                break;
        }
    }
}