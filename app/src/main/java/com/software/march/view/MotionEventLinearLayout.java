package com.software.march.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 自定义LinearLayout
 * @date 2017/1/6
 */
public class MotionEventLinearLayout extends LinearLayout {

    private final String TAG = getClass().getSimpleName();

    public MotionEventLinearLayout(Context context) {
        super(context);
        Log.e(TAG, TAG + ":MotionEventLinearLayout(Context context)");
    }

    public MotionEventLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.e(TAG, TAG + ":MotionEventLinearLayout(Context context, AttributeSet attrs)");
    }

    public MotionEventLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.e(TAG, TAG + ":MotionEventLinearLayout(Context context, AttributeSet attrs, int defStyleAttr)");
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, TAG + ":dispatchTouchEvent(MotionEvent ev)" + ",action:" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e(TAG, TAG + ":onInterceptTouchEvent(MotionEvent ev)" + ",action:" + ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.e(TAG, TAG + ":onTouchEvent(MotionEvent ev)" + ",action:" + ev.getAction());
        return super.onTouchEvent(ev);
    }
}