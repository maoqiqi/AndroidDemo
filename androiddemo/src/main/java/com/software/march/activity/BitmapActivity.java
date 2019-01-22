package com.software.march.activity;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.software.march.R;
import com.software.march.utils.SPUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description Bitmap
 * @date 2017/1/20
 */
public class BitmapActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv;
    private Button btnRead;
    private Button btnSave;

    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_bitmap);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        iv = (ImageView) findViewById(R.id.iv);
        btnRead = (Button) findViewById(R.id.btn_read);
        btnSave = (Button) findViewById(R.id.btn_save);

        btnRead.setOnClickListener(this);
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_read:
                read();
                break;
            case R.id.btn_save:
                save();
                break;
        }
    }

    private void read() {
        try {
            AssetManager manager = getAssets();
            InputStream is = manager.open("logo.png");
            bitmap = BitmapFactory.decodeStream(is);
            iv.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void save() {
        try {
            FileOutputStream fis = openFileOutput("logo.png", Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fis);
            Toast.makeText(this, "保存完成", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}