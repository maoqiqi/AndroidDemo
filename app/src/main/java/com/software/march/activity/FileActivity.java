package com.software.march.activity;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.software.march.R;
import com.software.march.utils.SPUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class FileActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = getClass().getSimpleName();

    private ImageView iv;
    private Button btnSave1;
    private Button btnRead1;
    private EditText etContent;
    private Button btnSave2;
    private Button btnRead2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_file);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        iv = (ImageView) findViewById(R.id.iv);
        btnSave1 = (Button) findViewById(R.id.btn_save_1);
        btnRead1 = (Button) findViewById(R.id.btn_read_1);
        etContent = (EditText) findViewById(R.id.et_content);
        btnSave2 = (Button) findViewById(R.id.btn_save_2);
        btnRead2 = (Button) findViewById(R.id.btn_read_2);

        btnSave1.setOnClickListener(this);
        btnRead1.setOnClickListener(this);
        btnSave2.setOnClickListener(this);
        btnRead2.setOnClickListener(this);

        Log.i(TAG, "getFilesDir()：" + getFilesDir().getAbsolutePath());
        Log.i(TAG, "getCacheDir()：" + getCacheDir().getAbsolutePath());
        Log.i(TAG, "getExternalCacheDir()：" + getExternalCacheDir().getAbsolutePath());
        Log.i(TAG, "getExternalFilesDir()：" + getExternalFilesDir(null).getAbsolutePath());
        Log.i(TAG, "getRootDirectory()：" + Environment.getRootDirectory().getAbsolutePath());
        Log.i(TAG, "getExternalStorageDirectory()：" + Environment.getExternalStorageDirectory().getAbsolutePath());

        // getFilesDir()：/data/user/0/com.software.march/files
        // getCacheDir()：/data/user/0/com.software.march/cache
        // getExternalCacheDir()：/storage/emulated/0/Android/data/com.software.march/cache
        // getExternalFilesDir()：/storage/emulated/0/Android/data/com.software.march/files
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save_1:
                save1();
                break;
            case R.id.btn_read_1:
                read1();
                break;
            case R.id.btn_save_2:
                // save2();
                save3();
                break;
            case R.id.btn_read_2:
                // read2();
                read3();
                break;
        }
    }

    private void save1() {
        // 读取assets下的logo.png
        try {
            // 1)得到AssetManager
            AssetManager manager = getAssets();
            // 2)得到InputStream
            InputStream is = manager.open("logo.png");
            // 3)得到OutputStream-->/data/data/packageName/files/logo.png
            FileOutputStream fos = openFileOutput("logo.png", Context.MODE_PRIVATE);
            // 4)边读边写
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            is.close();
            // 5)提示
            Toast.makeText(this, "保存完成", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read1() {
        // 1)得到图片文件的路径-->/data/data/packageName/files
        String filesPath = getFilesDir().getAbsolutePath();
        String imagePath = filesPath + "/logo.png";
        // 2)读取加载图片文件得到bitmap对象
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        // 3)将其设置到imageView中显示
        iv.setImageBitmap(bitmap);
    }

    private void save2() {
        // 1)判断sd卡状态, 如果是挂载的状态才继续, 否则提示
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                // 2)读取输入的内容
                String content = etContent.getText().toString();
                // 3)得到指定文件的OutputStream
                // (1)得到sd卡下的files路径
                String filesPath = getExternalFilesDir(null).getAbsolutePath();
                String fileName = "test.txt";
                // (2)组成完整路径
                String filePath = filesPath + "/" + fileName;
                // (3)创建FileOutputStream
                FileOutputStream fos = new FileOutputStream(filePath);
                // 4)写数据
                fos.write(content.getBytes("utf-8"));
                fos.close();
                // 5)提示
                Toast.makeText(this, "保存完成", Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "sd卡没有挂载", Toast.LENGTH_SHORT).show();
        }
    }

    private void read2() {
        // 1)判断sd卡状态, 如果是挂载的状态才继续, 否则提示
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                // 2)得到指定文件的InputStream
                // (1)得到sd卡下的files路径
                String filesPath = getExternalFilesDir(null).getAbsolutePath();
                String fileName = "test.txt";
                // (2)组成完整路径
                String filePath = filesPath + "/" + fileName;
                // (3)创建FileInputStream
                FileInputStream fis = new FileInputStream(filePath);
                // 3)读取数据,成String
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = -1;
                while ((len = fis.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                String content = baos.toString();
                // 4)显示
                etContent.setText(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "sd卡没有挂载", Toast.LENGTH_SHORT).show();
        }
    }

    private void save3() {
        // 1)判断sd卡状态, 如果是挂载的状态才继续, 否则提示
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                // 2)读取输入的内容
                String content = etContent.getText().toString();
                // 3)得到指定文件的OutputStream
                // (1)/storage/sdcard/
                String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
                // (2)/storage/sdcard/march/(创建文件夹)
                File file = new File(sdPath + "/march");
                if (!file.exists()) {
                    file.mkdirs();// 创建文件夹
                }
                // (3)/storage/sdcard/march/test.txt
                String fileName = "test.txt";
                String filePath = sdPath + "/march/" + fileName;
                // (4)创建FileOutputStream
                FileOutputStream fos = new FileOutputStream(filePath);
                // 4)写数据
                fos.write(content.getBytes("utf-8"));
                fos.close();
                // 5)提示
                Toast.makeText(this, "保存完成", Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "sd卡没有挂载", Toast.LENGTH_SHORT).show();
        }
    }

    private void read3() {
        // 1)判断sd卡状态, 如果是挂载的状态才继续, 否则提示
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                // 2)得到指定文件的InputStream
                // (1)/storage/sdcard/
                String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
                String fileName = "test.txt";
                // (2)组成完整路径
                String filePath = sdPath + "/march/" + fileName;
                // (3)创建FileInputStream
                FileInputStream fis = new FileInputStream(filePath);
                // 3)读取数据,成String
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = -1;
                while ((len = fis.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                String content = baos.toString();
                // 4)显示
                etContent.setText(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "sd卡没有挂载", Toast.LENGTH_SHORT).show();
        }
    }
}