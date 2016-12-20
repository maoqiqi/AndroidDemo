package com.software.march.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.software.march.R;
import com.software.march.utils.SPUtils;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 线性布局
 * @date 2016/12/19
 */
public class LinearLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_linear_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 1.布局权重
        // LinearLayout 还支持使用 android:layout_weight 属性为各个子视图分配权重。
        // 此属性根据视图应在屏幕上占据的空间量向视图分配“重要性”值。
        // 权重值更大的视图可以填充父视图中任何剩余的空间。
        // 子视图可以指定权重值，然后系统会按照子视图声明的权重值的比例，将视图组中的任何剩余空间分配给子视图。
        // 默认权重为零。

        // 例如，如果有三个文本字段，其中两个声明权重为 1，另一个没有赋予权重，则没有权重的第三个文本字段将不会扩展，并且仅占据其内容所需的区域。
        // 另外两个文本字段将以同等幅度进行扩展，以填充所有三个字段都测量后还剩余的空间。
        // 如果为第三个字段提供权重 2（而非 0），那么相当于声明现在它比其他两个字段更为重要，因此，它将获得总剩余空间的一半，其他两个均享余下空间。
    }
}