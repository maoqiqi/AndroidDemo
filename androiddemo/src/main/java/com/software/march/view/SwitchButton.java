package com.software.march.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;

import com.software.march.R;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 自定义开关按钮
 * @date 2017/1/22
 */
public class SwitchButton extends CompoundButton implements View.OnClickListener {

    private Paint paint;
    private Bitmap backgroundNormalBitmap;
    private Bitmap backgroundCheckedBitmap;
    private Bitmap slidingBitmap;

    /**
     * 距离左边最大距离
     */
    private int slidLeftMax;
    private int slideLeft;

    // true:点击事件生效，滑动事件不生效
    // false:点击事件不生效，滑动事件生效
    private boolean isEnableClick = true;

    private boolean isOpen = false;

    private float startX;
    private float lastX;

    public SwitchButton(Context context) {
        this(context, null);
    }

    public SwitchButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwitchButton(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SwitchButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundNormalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.switch_track_white);
        backgroundCheckedBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.switch_track_green);
        slidingBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.switch_thumb_normal);
        slidLeftMax = backgroundNormalBitmap.getWidth() - slidingBitmap.getWidth();

        setOnClickListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(backgroundNormalBitmap.getWidth(), backgroundNormalBitmap.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isOpen) {
            canvas.drawBitmap(backgroundCheckedBitmap, 0, 0, paint);
        } else {
            canvas.drawBitmap(backgroundNormalBitmap, 0, 0, paint);
        }
        canvas.drawBitmap(slidingBitmap, slideLeft, 0, paint);
    }

    @Override
    public void onClick(View v) {
        if (isEnableClick) {
            isOpen = !isOpen;
            flushView();
        }
    }

    private void flushView() {
        if (isOpen) {
            slideLeft = slidLeftMax;
        } else {
            slideLeft = 0;
        }
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);// 执行父类的方法
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = startX = event.getX();
                isEnableClick = true;
                break;
            case MotionEvent.ACTION_MOVE:
                float endX = event.getX();
                float distanceX = endX - startX;
                slideLeft += distanceX;
                if (slideLeft < 0) {
                    slideLeft = 0;
                } else if (slideLeft > slidLeftMax) {
                    slideLeft = slidLeftMax;
                }
                // 刷新
                invalidate();
                // 数据还原
                startX = event.getX();
                if (Math.abs(endX - lastX) > 5) {
                    // 滑动
                    isEnableClick = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (!isEnableClick) {
                    if (slideLeft > slidLeftMax / 2) {
                        // 显示按钮开
                        isOpen = true;
                    } else {
                        isOpen = false;
                    }
                    flushView();
                }
                break;
        }
        return true;
    }
}