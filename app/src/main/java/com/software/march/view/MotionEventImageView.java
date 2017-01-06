package com.software.march.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 自定义ImageView
 * @date 2017/1/6
 */
public class MotionEventImageView extends ImageView {

    private final String TAG = getClass().getSimpleName();

    public MotionEventImageView(Context context) {
        super(context);
        Log.e(TAG, TAG + ":MotionEventImageView(Context context)");
    }

    public MotionEventImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.e(TAG, TAG + ":MotionEventImageView(Context context, AttributeSet attrs)");
    }

    public MotionEventImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.e(TAG, TAG + ":MotionEventImageView(Context context, AttributeSet attrs, int defStyleAttr)");
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, TAG + ":dispatchTouchEvent(MotionEvent ev)" + ",action:" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.e(TAG, TAG + ":onTouchEvent(MotionEvent ev)" + ",action:" + ev.getAction());
        return super.onTouchEvent(ev);
    }
}