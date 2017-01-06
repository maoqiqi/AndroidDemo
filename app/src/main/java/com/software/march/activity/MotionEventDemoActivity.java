package com.software.march.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.software.march.R;
import com.software.march.utils.SPUtils;

public class MotionEventDemoActivity extends AppCompatActivity implements View.OnTouchListener {

    private ImageView iv;
    private LinearLayout parentView;

    private int minLeft;
    private int minTop;
    private int maxRight;
    private int maxBottom;

    private int lastX;
    private int lastY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_motion_event_demo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        iv = (ImageView) findViewById(R.id.iv);
        iv.setOnTouchListener(this);

        parentView = (LinearLayout) iv.getParent();
        // minLeft = parentView.getLeft();// 0
        // minTop = parentView.getTop();// 0
        // maxRight = parentView.getRight();// 0
        // maxBottom = parentView.getBottom();// 0
    }

    @Override
    public boolean onTouch(View view, MotionEvent ev) {
        int action = ev.getAction();
        // 得到事件的坐标
        // getX():是获取以widget左上角为坐标原点计算的Ｘ轴坐标值
        // getRawX():获取的是以屏幕左上角为坐标原点计算的Ｘ轴坐标值
        int eventX = (int) ev.getRawX();
        int eventY = (int) ev.getRawY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // 得到父视图的left/top/right/bottom
                minLeft = parentView.getLeft();
                minTop = parentView.getTop() + getSupportActionBar().getHeight();
                maxRight = parentView.getRight();
                maxBottom = parentView.getBottom();
                Toast.makeText(this, minLeft + "-" + minTop + "-" + maxRight + "-" + maxBottom, Toast.LENGTH_SHORT).show();

                lastX = eventX;
                lastY = eventY;
                break;
            case MotionEvent.ACTION_MOVE:
                // 计算移动的距离
                int distanceX = eventX - lastX;
                int distanceY = eventY - lastY;

                // 根据事件的偏移来移动ImageView
                int left = iv.getLeft() + distanceX;
                int top = iv.getTop() + distanceY;
                int right = iv.getRight() + distanceX;
                int bottom = iv.getBottom() + distanceY;

                // 限制left>=minLeft
                if (left < minLeft) {
                    right += minLeft - left;
                    left = minLeft;
                }
                // 限制top>=minTop
                if (top < minTop) {
                    bottom += minTop - top;
                    top = minTop;
                }

                // 限制right<=maxRight
                if (right > maxRight) {
                    left += maxRight - right;
                    right = maxRight;
                }
                // 限制bottom<=maxBottom
                if (bottom > maxBottom) {
                    top += maxBottom - bottom;
                    bottom = maxBottom;
                }

                iv.layout(left, top, right, bottom);

                // 再次记录lastX/lastY
                lastX = eventX;
                lastY = eventY;
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;// 所有的MotionEvent都交给ImageView处理
    }
}