package com.software.march.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.software.march.R;
import com.software.march.utils.SPUtils;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 简单组件
 * @date 2016/12/16
 */
public class SimpleComponentActivity extends AppCompatActivity {

    private TextView tvSimpleMessage;
    private EditText etSimpleNumber;
    private Button btnSimpleSubmit;
    private ImageView ivSimpleIcon;
    private CheckBox cbSimpleBasket;
    private CheckBox cbSimpleFoot;
    private CheckBox cbSimpleTennis;
    private RadioGroup rgSimpleSex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_simple_component);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 1.TextView
        tvSimpleMessage = (TextView) findViewById(R.id.tv_simple_message);
        tvSimpleMessage.setText("TextView");

        // 2.EditText
        etSimpleNumber = (EditText) findViewById(R.id.et_simple_number);

        // 3.Button
        btnSimpleSubmit = (Button) findViewById(R.id.btn_simple_submit);
        btnSimpleSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 得到内容
                String number = etSimpleNumber.getText().toString();
                // 提示
                Toast.makeText(SimpleComponentActivity.this, number, Toast.LENGTH_SHORT).show();
            }
        });

        // 4.ImageView
        ivSimpleIcon = (ImageView) findViewById(R.id.iv_simple_icon);
        ivSimpleIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 设置背景图片
                ivSimpleIcon.setBackgroundResource(android.R.drawable.alert_light_frame);
                // 设置前景图片
                ivSimpleIcon.setImageResource(android.R.drawable.ic_media_pause);
            }
        });

        // 5.CheckBox
        cbSimpleBasket = (CheckBox) findViewById(R.id.cb_simple_basket);
        cbSimpleFoot = (CheckBox) findViewById(R.id.cb_simple_foot);
        cbSimpleTennis = (CheckBox) findViewById(R.id.cb_simple_tennis);
        // 设置选中状态改变的监听
        cbSimpleBasket.setOnCheckedChangeListener(new MyCheckedChangeListener());
        cbSimpleFoot.setOnCheckedChangeListener(new MyCheckedChangeListener());
        cbSimpleTennis.setOnCheckedChangeListener(new MyCheckedChangeListener());

        // 6.RadioGroup/RadioButton
        rgSimpleSex = (RadioGroup) findViewById(R.id.rg_simple_sex);
        rgSimpleSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // 找到选中的RadioButton
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                // 得到文本
                String sex = radioButton.getText().toString();
                // 提示
                Toast.makeText(SimpleComponentActivity.this, sex, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class MyCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            StringBuffer sb = new StringBuffer();
            if (cbSimpleBasket.isChecked()) {
                sb.append(cbSimpleBasket.getText().toString()).append(" ");
            }
            if (cbSimpleFoot.isChecked()) {
                sb.append(cbSimpleFoot.getText().toString()).append(" ");
            }
            if (cbSimpleTennis.isChecked()) {
                sb.append(cbSimpleTennis.getText().toString()).append(" ");
            }
            sb.deleteCharAt(sb.length() - 1);
            // 提示
            Toast.makeText(SimpleComponentActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}