package com.software.march.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.software.march.R;
import com.software.march.utils.SPUtils;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 自定义View
 * @date 2017/1/18
 */
public class CustomViewActivity extends AppCompatActivity {

    // 1.什么是自定义控件?
    // Android 系统中，继承 Android 系统自带的 View 或者 ViewGroup 控件或者系统自带的控件，
    // 并在这基础上增加或者重新组合成我们想要的效果。

    // 2.为什么用自定义控件?
    // 系统控件无法满足需求时，需要自定义控件。
    // 1).系统的控件在不同手机长得不一样，我们希望在不同手机实现相同的效果
    // 2).有些手机上的控件长得不好看，希望好看一些
    // 3).系统控件的功能有限，需要在基础上增加功能

    // 3.怎么用自定义控件-三种方式
    // 1.使用系统控件，重新组合，实现自定义的效果，案例有:优酷环形菜单、广告条(ViewPager)、下拉菜单(spinner)
    // 2.自己定义一个类继承 View ，实现特定的效果，案例有：自定义开关按钮、水波纹效果、自定义属性
    // 3.自己定义一个类继承 ViewGroup，实现特定的效果，案例有：仿 ViewPager 的效果实现、仿网易侧滑菜
    // 自定义属性：给自己的控件，添加自己的属性

    // 使用原则：尽量使用系统的控件，在系统控件没法达到我们的需求的时候才需要自定义控件。再定义控件会带来工作量，例如修改bug.

    // 文本控件
    // TextView 和 EditText
    // TextView 控件继承自 View 类。TextView 控件的功能是向用户显示文本内容，TextView 不 允许编辑。
    // EditText 控件继承自 TextView。EditText 与 TextView 最大的不同是 EditText 是可以编辑的。

    // 图片控件 ImageView
    // ImageView 控件继承自 View 类。控件负责显示图片,其图片来源既可以是资源文件的 id,也可以是 Drawable 对象或 Bitmap 对象,还可以是 内容提供者（Content Provider）的 Uri。

    // 按钮控件 Button 和 ImageButton
    // Button 控件继承自 TextView 类，Button 的用法比较简单，主要是为 Button 设置一个点击事件监听器，并在编写按钮点击事件的处理代码。
    // ImageButton 控件 继承自 ImageView。
    // ImageButton 与 Button 相同之处：都用于响应按钮的点击事件。
    // 不同之处：ImageButton 只能显示图片，Button 用于显示文字

    // 进度条 ProgressBar
    // ProgressBar 继承自 View，用于显示正在运行的状态。
    // 有两种显示形式：一种是 环形显示只用于显示状态，没有具体的进度。第二种是水平显示，可以显示具体的进度。
    // 通过设置不同的 Style 显示不同的样式：
    // style="?android:attr/progressBarStyleLarge"环形样式
    // style="?android:attr/progressBarStyleHorizontal"水平样式

    // 单选按钮 RadioButton 和复选按钮 CheckBox
    // CheckBox 和 RadioButton 都继承自 CompoundButton，都只有选中和未选中两种 状态,可以通过 checked 属性来设置。
    // 不同的是在一个RadioGroup 中只能有一个 RadioButton 按钮处于选中状态;CheckBox 则可以有多个按钮被选中。

    // 状态开关按钮 ToggleButton
    // ToggleButton 控件是继承自 CompoundButton。ToggleButton 的状态只能是选中和未选中, 并且需要为不同的状态设置不同的显示文本。
    // 除了继承自父类的一些属性和方法之 外,ToggleButton 也具有一些自己的属性。

    // 时钟控件 AnalogClock 和 DigitalClock
    // AnalogClock 继承自 View，用于显示模拟时钟只显示时针和分针。
    // DigitalClock 继承自 TextView。用于显示数字时钟可精确到秒。时钟控件比较简单，只需要在布局文件中声明控件即可。

    // 日期选择器 DatePicker 和时间选择器 TimePicker
    // DatePicker 继承自 FrameLayout 类，日期选择控件的主要功能是向用户提供包含年、月、日 的日期数据，并允许用户对其修改。
    // 如果要捕获这个修改,可以为 DatePicker 添加 onDateChangedListener 监听器。
    // TimePicker 同样继承自 FrameLayout 类。时间选择控件向用户显示一天中的时间，可以为 24 小时制，可以为 AM/PM 制，
    // 并允许用户进行修改。如果要捕获用户的修改事件,需要为 TimePicker 添加 OnTimeChangedListener 监听器。

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_custom_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}