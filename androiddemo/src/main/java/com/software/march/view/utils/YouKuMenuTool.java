package com.software.march.view.utils;

import android.animation.ObjectAnimator;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;

public class YouKuMenuTool {

    public static void hideView(ViewGroup view) {
        hideView(view, 0);
    }

    public static void hideView_(ViewGroup view, int startOffset) {
        RotateAnimation animation = new RotateAnimation(0, 180, view.getWidth() / 2, view.getHeight());
        // 设置动画持续播放时间
        animation.setDuration(500);
        // 设置动画停留在播放完成的状态
        animation.setFillAfter(true);
        // 延迟多久后播放动画
        animation.setStartOffset(startOffset);

        view.startAnimation(animation);

        //利用 ViewGroup 得到每个孩子设置不可以点击解决 bug
        //设置不可以点击
        for (int i = 0; i < view.getChildCount(); i++) {
            view.getChildAt(i).setEnabled(false);
        }
    }

    public static void hideView(ViewGroup view, int startOffset) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "rotation", 0, 180);
        objectAnimator.setDuration(500);
        objectAnimator.setStartDelay(startOffset);
        objectAnimator.start();

        view.setPivotX(view.getWidth() / 2);
        view.setPivotY(view.getHeight());
    }

    public static void showView(ViewGroup view) {
        showView(view, 0);
    }

    public static void showView_(ViewGroup view, int startOffset) {
        RotateAnimation animation = new RotateAnimation(180, 360, view.getWidth() / 2, view.getHeight());
        animation.setDuration(500);
        animation.setFillAfter(true);
        animation.setStartOffset(startOffset);

        view.startAnimation(animation);

        //设置可以点击
        for (int i = 0; i < view.getChildCount(); i++) {
            view.getChildAt(i).setEnabled(true);
        }
    }

    public static void showView(ViewGroup view, int startOffset) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "rotation", 180, 360);
        objectAnimator.setDuration(500);
        objectAnimator.setStartDelay(startOffset);
        objectAnimator.start();

        view.setPivotX(view.getWidth() / 2);
        view.setPivotY(view.getHeight());
    }
}