package com.software.march.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.software.march.R;
import com.software.march.utils.AppUtils;
import com.software.march.utils.SPUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetWorkActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnDownloadApk;
    private File apkFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_net_work);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnDownloadApk = (Button) findViewById(R.id.btn_download_apk);
        btnDownloadApk.setOnClickListener(this);

        // 准备用于保存APK文件的File对象:/storage/sdcard/Android/package_name/files/xxx.apk
        apkFile = new File(getExternalFilesDir(null), "update.apk");
    }

    @Override
    public void onClick(View view) {
        downloadApk();
    }

    private void downloadApk() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.show();

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    String path = "http://112.65.235.163/imtt.dd.qq.com/16891/6A4A3337FC49B1162C4CD0D239AD7C7D.apk?mkey=586341779ebbd0cf&f=d388&c=0&fsname=com.vteam.cook_3.1.1_3110.apk&csr=4d5s&p=.apk";
                    // 创建URL对象
                    URL url = new URL(path);
                    // 打开连接,得到HttpURLConnection对象
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    // 设置请求方式,连接超时,读取数据超时
                    conn.setRequestMethod("POST");
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(6000);
                    // 连接服务器
                    conn.connect();
                    // 发请求,得到响应数据
                    // 得到响应码,必须是200才读取
                    if (conn.getResponseCode() == 200) {
                        // 设置dialog的最大进度
                        dialog.setMax(conn.getContentLength());

                        // 得到InputStream,并读取成String
                        InputStream is = conn.getInputStream();
                        // 创建指向apkFile的FileOutputStream
                        FileOutputStream fos = new FileOutputStream(apkFile);
                        byte[] buffer = new byte[1024];
                        int len = -1;
                        while ((len = is.read(buffer)) != -1) {
                            fos.write(buffer, 0, len);
                            // 显示下载进度
                            dialog.incrementProgressBy(len);

                            // 休息一会(模拟网速慢)
                            // Thread.sleep(50);
                            // SystemClock.sleep(50);
                        }

                        fos.close();
                        is.close();
                    }
                    // 断开连接
                    conn.disconnect();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismiss();
                            AppUtils.installApp(NetWorkActivity.this, apkFile);
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}