package com.software.march.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 自定义View实现View测试生命周期
 * @date 2017/1/18
 */
public class MyView extends View {

    // View的生命周期

    private final String TAG = getClass().getSimpleName();

    // 1.创建对象

    // new创建对象
    public MyView(Context context) {
        super(context);
        Log.e(TAG, TAG + "(Context context)");
    }

    // 布局文件创建对象
    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.e(TAG, TAG + "(Context context, AttributeSet attrs)");
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.e(TAG, TAG + "(Context context, AttributeSet attrs, int defStyleAttr)");
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Log.e(TAG, TAG + "(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)");
    }

    // 只有布局的方式才会调用
    // 重写它: 用于得到子View对象
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.e(TAG, "onFinishInflate()");
    }

    // 无论new还是布局都会调用此方法
    // 重写它:用于得到子View对象
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.e(TAG, "onAttachedToWindow()");
    }

    // 2.测量【计算并确定视图的大小(width/height)】

    // 此方法不能重写
    // 系统在此方法中测量计算出当前视图的宽高
    // public final void measure(int widthMeasureSpec, int heightMeasureSpec)

    // 当measure()中计算出的视图的宽高就会调用此方法, 在此方法默认保存的视图宽高
    // protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    //     setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
    //          getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
    // }
    // 重写它, 做我们自己的工作, 比如得到当前视图测量的宽高, 保存我们指定的宽度
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = this.getMeasuredWidth();
        int measuredHeight = this.getMeasuredHeight();
        Log.e(TAG, "onMeasure(), measuredWidth = " + measuredWidth + ", measuredHeight = " + measuredHeight);
    }

    // 3.布局【确定视图显示的坐标(left, top, right, bottom)】

    // 一般不会重写此方法, 只会调用视图对象的此方法, 指定其新的显示位置
    // view.layout(0,0,0,0);
    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
        Log.e(TAG, "layout()");
    }

    // 可以不重写该方法
    // 确定视图显示的坐标
    // 重写它, 在layout()的过程中, 如果视图的位置变化/强制重新布局就会调用此方法
    // if (changed || (mPrivateFlags & PFLAG_LAYOUT_REQUIRED) == PFLAG_LAYOUT_REQUIRED){
    //     onLayout(changed, l, t, r, b);
    // }
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e(TAG, "onLayout(),changed = " + changed);
    }

    // 4.绘制【画出视图的样子】

    // 一般不会重写此方法
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Log.e(TAG, "draw()");
    }

    // 重写此方法,绘制自己需要的样子
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG, "onDraw()");
    }

    // 5.事件处理

    // 6.死亡
    // 什么时候会死亡?
    // 1)Activity死亡之前
    // 2)视图对象被移除

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.e(TAG, "onDetachedFromWindow()");
    }
}