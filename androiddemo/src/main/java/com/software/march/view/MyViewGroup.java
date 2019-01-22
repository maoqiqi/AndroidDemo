package com.software.march.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 自定义View实现ViewGroup测试生命周期
 * @date 2017/1/18
 */
public class MyViewGroup extends ViewGroup {

    private final String TAG = getClass().getSimpleName();

    public MyViewGroup(Context context) {
        super(context);
        Log.e(TAG, TAG + "(Context context)");
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.e(TAG, TAG + "(Context context, AttributeSet attrs)");
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.e(TAG, TAG + "(Context context, AttributeSet attrs, int defStyleAttr)");
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Log.e(TAG, TAG + "(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)");
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.e(TAG, "onFinishInflate()");
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.e(TAG, "onAttachedToWindow()");
    }

    // 此方法不能重写
    // 系统在此方法中测量计算出当前视图的宽高
    // public final void measure(int widthMeasureSpec, int heightMeasureSpec)

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = this.getMeasuredWidth();
        int measuredHeight = this.getMeasuredHeight();
        Log.e(TAG, "onMeasure(), measuredWidth = " + measuredWidth + ", measuredHeight = " + measuredHeight);
    }

    // 此方法不能重写
    // public final void layout(int l, int t, int r, int b)

    // 必须重写该方法
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.e(TAG, "onLayout(),changed = " + changed);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Log.e(TAG, "draw()");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG, "onDraw()");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.e(TAG, "onDetachedFromWindow()");
    }
}