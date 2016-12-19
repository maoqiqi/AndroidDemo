package com.software.march.activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.software.march.R;
import com.software.march.utils.SPUtils;

import java.util.Calendar;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description Dialog组件
 * @date 2016/12/16
 */
public class DialogComponentActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAd1;
    private Button btnAd2;
    private Button btnAd3;
    private Button btnPd1;
    private Button btnPd2;
    private Button btnDpd;
    private Button btnTpd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_dialog_component);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnAd1 = (Button) findViewById(R.id.btn_ad_1);
        btnAd2 = (Button) findViewById(R.id.btn_ad_2);
        btnAd3 = (Button) findViewById(R.id.btn_ad_3);
        btnPd1 = (Button) findViewById(R.id.btn_pd_1);
        btnPd2 = (Button) findViewById(R.id.btn_pd_2);
        btnDpd = (Button) findViewById(R.id.btn_dpd);
        btnTpd = (Button) findViewById(R.id.btn_tpd);

        btnAd1.setOnClickListener(this);
        btnAd2.setOnClickListener(this);
        btnAd3.setOnClickListener(this);
        btnPd1.setOnClickListener(this);
        btnPd2.setOnClickListener(this);
        btnDpd.setOnClickListener(this);
        btnTpd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ad_1:
                showAD1();
                break;
            case R.id.btn_ad_2:
                showAD2();
                break;
            case R.id.btn_ad_3:
                showAD3();
                break;
            case R.id.btn_pd_1:
                showPD1();
                break;
            case R.id.btn_pd_2:
                showPD2();
                break;
            case R.id.btn_dpd:
                showDPD();
                break;
            case R.id.btn_tpd:
                showTPD();
                break;
        }
    }

    private void showAD1() {
        new AlertDialog.Builder(this)
                .setTitle("删除数据")
                .setMessage("你确定删除数据吗")
                .setPositiveButton("删除", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogComponentActivity.this, "删除数据", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogComponentActivity.this, "取消删除数据", Toast.LENGTH_SHORT).show();
                    }
                }).show();// 方法链调用
    }

    private void showAD2() {
        final String[] items = {"红", "蓝", "绿", "灰"}; // final的变量在方法执行完后还存在
        new AlertDialog.Builder(this)
                .setTitle("指定背景颜色")
                .setSingleChoiceItems(items, 2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {// which就是选中的position
                        // 提示颜色
                        Toast.makeText(DialogComponentActivity.this, items[which], Toast.LENGTH_SHORT).show();
                        // 移除dialog
                        dialog.dismiss();
                    }
                }).show();
    }

    private void showAD3() {
        // 动态加载布局文件, 得到对应的View对象
        View view = View.inflate(this, R.layout.dialog_view, null);
        final EditText nameET = (EditText) view.findViewById(R.id.et_dialog_name);
        final EditText pwdET = (EditText) view.findViewById(R.id.et_dialog_pwd);

        new AlertDialog.Builder(this)
                .setView(view)
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 读取用户名和密码
                        String username = nameET.getText().toString();
                        String pwd = pwdET.getText().toString();
                        // 提示
                        Toast.makeText(DialogComponentActivity.this, username + " : " + pwd, Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    private void showPD1() {
        final ProgressDialog dialog = ProgressDialog.show(this, "数据加载", "数据加载中...");
        // 模拟做一个长时间的工作
        // 长时间的工作不能在主线程做, 得启动分线程完成
        new Thread() {
            public void run() {// 分线程
                for (int i = 0; i < 20; i++) {
                    // 休息一会
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 移除dialog
                dialog.dismiss();// 方法在分线程执行,但内部使用Handler实现主线程移除dialog

                // 不能在分线程直接更新UI
                // runOnUiThread()在分线程执行
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {// 在主线程执行
                        Toast.makeText(DialogComponentActivity.this, "加载完成了!!!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }.start();
    }

    private void showPD2() {
        // 1.创建dialog对象
        final ProgressDialog pd = new ProgressDialog(this);
        // 2.设置样式
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // 3.显示
        pd.show();

        // 4.启动分线程, 加载数据, 并显示进度, 当加载完成移除dilaog
        new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 20;
                // 设置最大进度
                pd.setMax(count);
                for (int i = 0; i < count; i++) {
                    // 休息一会
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pd.setProgress(pd.getProgress() + 1);
                }
                // 移除dialog
                pd.dismiss();
            }
        }).start();
    }

    private void showDPD() {
        // 创建日历对象
        Calendar calendar = Calendar.getInstance();
        // 得到当前的年月日
        int year = calendar.get(Calendar.YEAR);// 得到年份
        int monthOfYear = calendar.get(Calendar.MONTH);// 得到月
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);// 得到日
        Log.i("TAG", year + "-" + monthOfYear + "-" + dayOfMonth);

        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Log.i("TAG", year + "--" + (monthOfYear + 1) + "--" + dayOfMonth);
            }
        }, year, monthOfYear, dayOfMonth).show();
    }

    private void showTPD() {
        Calendar c = Calendar.getInstance();
        int hourOfDay = c.get(Calendar.HOUR_OF_DAY); // 得到小时
        int minute = c.get(Calendar.MINUTE); // 得到分钟
        Log.i("TAG", hourOfDay + ":" + minute);
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Log.i("TAG", hourOfDay + ":" + minute);
            }
        }, hourOfDay, minute, true).show();
    }
}