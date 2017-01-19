package com.software.march.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.software.march.R;
import com.software.march.utils.SPUtils;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description View动画
 * @date 2017/1/19
 */
public class ViewAnimationActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = getClass().getSimpleName();

    private Button btnCodeScale;
    private Button btnXmlScale;
    private Button btnCodeRotate;
    private Button btnXmlRotate;
    private Button btnCodeAlpha;
    private Button btnXmlAlpha;
    private Button btnCodeTranslate;
    private Button btnXmlTranslate;
    private Button btnCodeAnimationSet;
    private Button btnXmlAnimationSet;
    private Button btnAnimationListener;
    private ImageView ivAnimation;
    private TextView tvAnimationMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_view_animation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnCodeScale = (Button) findViewById(R.id.btn_code_scale);
        btnXmlScale = (Button) findViewById(R.id.btn_xml_scale);
        btnCodeRotate = (Button) findViewById(R.id.btn_code_rotate);
        btnXmlRotate = (Button) findViewById(R.id.btn_xml_rotate);
        btnCodeAlpha = (Button) findViewById(R.id.btn_code_alpha);
        btnXmlAlpha = (Button) findViewById(R.id.btn_xml_alpha);
        btnCodeTranslate = (Button) findViewById(R.id.btn_code_translate);
        btnXmlTranslate = (Button) findViewById(R.id.btn_xml_translate);
        btnCodeAnimationSet = (Button) findViewById(R.id.btn_code_animation_set);
        btnXmlAnimationSet = (Button) findViewById(R.id.btn_xml_animation_set);
        btnAnimationListener = (Button) findViewById(R.id.btn_animation_listener);
        ivAnimation = (ImageView) findViewById(R.id.iv_animation);
        tvAnimationMsg = (TextView) findViewById(R.id.tv_animation_msg);

        btnCodeScale.setOnClickListener(this);
        btnXmlScale.setOnClickListener(this);
        btnCodeRotate.setOnClickListener(this);
        btnXmlRotate.setOnClickListener(this);
        btnCodeAlpha.setOnClickListener(this);
        btnXmlAlpha.setOnClickListener(this);
        btnCodeTranslate.setOnClickListener(this);
        btnXmlTranslate.setOnClickListener(this);
        btnCodeAnimationSet.setOnClickListener(this);
        btnXmlAnimationSet.setOnClickListener(this);
        btnAnimationListener.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_code_scale:
                codeScale();
                break;
            case R.id.btn_xml_scale:
                xmlScale();
                break;
            case R.id.btn_code_rotate:
                codeRotate();
                break;
            case R.id.btn_xml_rotate:
                xmlRotate();
                break;
            case R.id.btn_code_alpha:
                codeAlpha();
                break;
            case R.id.btn_xml_alpha:
                xmlAlpha();
                break;
            case R.id.btn_code_translate:
                codeTranslate();
                break;
            case R.id.btn_xml_translate:
                xmlTranslate();
                break;
            case R.id.btn_code_animation_set:
                codeAnimationSet();
                break;
            case R.id.btn_xml_animation_set:
                xmlAnimationSet();
                break;
            case R.id.btn_animation_listener:
                break;
            case R.id.iv_animation:
                animationListener();
                break;
        }
    }

    // setDuration(long durationMillis):设置持续时间(单位ms)
    // setStartOffset(long startOffset):设置开始的延迟的时间(单位ms)
    // setFillBefore(boolean fillBefore):设置最终是否固定在起始状态
    // setFillAfter(boolean fillAfter):设置最终是否固定在最后的状态
    // setAnimationListener(AnimationListener listener):设置动画监听

    // 坐标类型:
    // Animation.ABSOLUTE
    // Animation.RELATIVE_TO_SELF
    // Animation.RELATIVE_TO_PARENT

    // 启动动画:view.startAnimation(animation);
    // 结束动画:view.clearAnimation()

    // 动画监听器:AnimationListener
    // onAnimationStart(Animation animation):动画开始的回调
    // onAnimationEnd(Animation animation):动画结束的回调
    // onAnimationRepeat(Animation animation):动画重复执行

    // 编码实现:缩放动画
    private void codeScale() {
        tvAnimationMsg.setText("Code缩放动画:宽度从0.5到1.5, 高度从0.0到1.0, 缩放的圆心为顶部中心点,延迟1s开始,持续2s,最终还原");
        // 1.创建动画对象
        // public ScaleAnimation(float fromX, float toX, float fromY, float toY,int pivotXType, float pivotXValue, int pivotYType, float pivotYValue)
        ScaleAnimation animation = new ScaleAnimation(0.5f, 1.5f, 1, 1, Animation.ABSOLUTE, ivAnimation.getWidth() / 2, Animation.ABSOLUTE, 0);
        animation = new ScaleAnimation(0.5f, 1.5f, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0);
        // 2.设置
        animation.setStartOffset(1000);
        animation.setDuration(2000);
        animation.setFillBefore(true);
        // 3.启动动画
        ivAnimation.startAnimation(animation);
    }

    // xml实现:缩放动画
    private void xmlScale() {
        tvAnimationMsg.setText("Xml缩放动画:Xml缩放动画:宽度从0.0到1.5, 高度从0.0到1.0, 延迟1s开始,持续3s,圆心为右下角, 最终固定");
        // 1.定义动画文件
        // 2.加载动画文件得到动画对象
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale);
        // 3.启动动画
        ivAnimation.startAnimation(animation);
    }

    // 编码实现:旋转动画
    private void codeRotate() {
        tvAnimationMsg.setText("Code旋转动画:以图片中心点为中心, 从负90度到正90度, 持续5s");
        // public RotateAnimation(float fromDegrees, float toDegrees, int pivotXType, float pivotXValue,int pivotYType, float pivotYValue)
        RotateAnimation animation = new RotateAnimation(-90f, 90f,
                Animation.ABSOLUTE, ivAnimation.getWidth() / 2, Animation.ABSOLUTE, ivAnimation.getHeight() / 2);
        animation = new RotateAnimation(-90f, 90f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(5000);
        ivAnimation.startAnimation(animation);
    }

    // xml实现:旋转动画
    private void xmlRotate() {
        tvAnimationMsg.setText("Xml旋转动画:以左顶点为坐标, 从正90度到负90度, 持续5s");
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale);
        ivAnimation.startAnimation(animation);
    }

    // 编码实现:透明度动画
    private void codeAlpha() {
        tvAnimationMsg.setText("Code透明度动画:从完全透明到完全不透明, 持续2s");
        // public AlphaAnimation(float fromAlpha, float toAlpha)
        AlphaAnimation animation = new AlphaAnimation(0, 1);
        animation.setDuration(2000);
        ivAnimation.startAnimation(animation);
    }

    // xml实现:透明度动画
    private void xmlAlpha() {
        tvAnimationMsg.setText("Xml透明度动画:从完全不透明到完全透明, 持续4s");
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        ivAnimation.startAnimation(animation);
    }

    // 编码实现:平移动画
    private void codeTranslate() {
        tvAnimationMsg.setText("Code移动动画:向右移动一个自己的宽度, 向下移动一个自己的高度, 持续2s");
        // public TranslateAnimation(int fromXType, float fromXValue, int toXType, float toXValue,
        // int fromYType, float fromYValue, int toYType, float toYValue)
        TranslateAnimation animation = new TranslateAnimation(Animation.ABSOLUTE, 0, Animation.ABSOLUTE, ivAnimation.getWidth(),
                Animation.ABSOLUTE, 0, Animation.ABSOLUTE, ivAnimation.getHeight());
        animation = new TranslateAnimation(Animation.ABSOLUTE, 0, Animation.RELATIVE_TO_SELF, 1, Animation.ABSOLUTE, 0, Animation.RELATIVE_TO_SELF, 1);
        animation.setDuration(2000);
        ivAnimation.startAnimation(animation);
    }

    // xml实现:平移动画
    private void xmlTranslate() {
        tvAnimationMsg.setText("xml移动动画:从屏幕的右边逐渐回到原来的位置, 持续2s"); // 此效果用于界面切换的动画效果
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate);
        ivAnimation.startAnimation(animation);
    }

    // 编码实现:复合动画
    private void codeAnimationSet() {
        tvAnimationMsg.setText("Code复合动画:透明度从透明到不透明, 持续2s, 接着进行旋转360度的动画, 持续1s");

        // 创建透明动画并设置
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(2000);

        // 创建旋转动画并设置
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setStartOffset(2000);

        // 创建复合动画对象
        AnimationSet animationSet = new AnimationSet(true);
        // 添加两个动画
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(rotateAnimation);

        // 启动复合动画对象
        ivAnimation.startAnimation(animationSet);
    }

    // xml实现:复合动画
    private void xmlAnimationSet() {
        tvAnimationMsg.setText("Xml复合动画:透明度从透明到不透明, 持续2s, 接着进行旋转360度的动画, 持续2s");
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.set);
        ivAnimation.startAnimation(animation);
    }

    // 测试动画监听
    private void animationListener() {
        tvAnimationMsg.setText("测试动画监听");
        Animation animation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(1000);
        // 设置线性变化
        animation.setInterpolator(new LinearInterpolator());
        // 设置动画重复次数
        animation.setRepeatCount(Animation.INFINITE);
        // 设置动画监听
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.e(TAG, "动画开始");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.e(TAG, "动画结束");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.e(TAG, "动画重复");
            }
        });
    }
}