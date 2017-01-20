package com.software.march.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.software.march.appcommonlibrary.DensityUtils;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 自定义联系人快速索引
 * @date 2017/1/20
 */
public class IndexView extends View {

    private final String[] words = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    private Context mContext;

    private int itemWidth;
    private int itemHeight;

    private Paint paint;

    // 默认字体颜色
    private int defaultColor = Color.parseColor("#FF0000");
    // 选中字体颜色
    private int checkColor = Color.parseColor("#000000");
    // 选中背景颜色
    private int checkBackground = Color.GRAY;

    // 选中字母下标索引
    private int touchIndex = -1;
    private OnIndexChangeListener onIndexChangeListener;

    public IndexView(Context context) {
        super(context);
        init(context);
    }

    public IndexView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public IndexView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public IndexView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        // 抗锯齿
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // paint.setAntiAlias(true);// 设置抗锯齿
        paint.setTextSize(DensityUtils.sp2px(mContext, 16));
        paint.setColor(defaultColor);
        // paint.setTextAlign(Paint.Align.CENTER);// 居中
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        itemWidth = getMeasuredWidth();
        itemHeight = getMeasuredHeight() / words.length;

        for (int i = 0; i < words.length; i++) {
            if (touchIndex == i) {
                paint.setColor(checkColor);
            } else {
                paint.setColor(defaultColor);
            }

            // float baseX = itemWidth / 2;
            // BaseLine
            // float baseY = itemHeight * i + itemHeight / 2;

            // Paint.FontMetrics fontMetrics = paint.getFontMetrics();
            // TopLine
            // float topY = baseY + fontMetrics.top;
            // AscentLine
            // float ascentY = baseY + fontMetrics.ascent;
            // DescentLine
            // float descentY = baseY + fontMetrics.descent;
            // BottomLine
            // float bottomY = baseY + fontMetrics.bottom;

            // 得到
            Rect rect = new Rect();
            paint.getTextBounds(words[i], 0, 1, rect);

            // 字母的宽和高
            int wordWidth = rect.width();
            int wordHeight = rect.height();

            // drawText画字符串是baseline对齐的
            canvas.drawText(words[i], itemWidth / 2 - wordWidth / 2, itemHeight * i + itemHeight / 2 + wordHeight / 2, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float y = event.getY();
                int index = (int) (y / itemHeight);// 字母索引
                if (index != touchIndex) {
                    touchIndex = index;
                    invalidate();// 强制绘制

                    if (onIndexChangeListener != null && touchIndex < words.length) {
                        onIndexChangeListener.onIndexChange(words[touchIndex]);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (onIndexChangeListener != null) {
                    onIndexChangeListener.onEnd();
                }
                break;
        }
        return true;
    }

    /**
     * 字母下标索引变化的监听器
     */
    public interface OnIndexChangeListener {

        /**
         * 当触摸屏幕,字母下标位置发生变化的时候回调
         *
         * @param word
         */
        void onIndexChange(String word);

        /**
         * 当手指离开时调用
         */
        void onEnd();
    }

    /**
     * 设置字母下标索引变化的监听
     *
     * @param onIndexChangeListener
     */
    public void setOnIndexChangeListener(OnIndexChangeListener onIndexChangeListener) {
        this.onIndexChangeListener = onIndexChangeListener;
    }
}