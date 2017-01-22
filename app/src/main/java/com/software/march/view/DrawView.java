package com.software.march.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.software.march.appcommonlibrary.DensityUtils;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 绘制图形
 * @date 2017/1/22
 */
public class DrawView extends View {

    private Context mContext;
    private ShapeDrawable shapeDrawable;
    private Paint paint;

    public DrawView(Context context) {
        super(context);
        init(context);
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DrawView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        mContext = context;

        shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.getPaint().setColor(Color.RED);
        // 指定位置
        shapeDrawable.setBounds(10, 10, 200, 100);

        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setTextSize(DensityUtils.dp2px(getContext(), 18));
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GREEN);
        // 将自己画到画布上
        shapeDrawable.draw(canvas);
        // 画文本
        canvas.drawText("绘制文本", 10, 150, paint);
    }
}