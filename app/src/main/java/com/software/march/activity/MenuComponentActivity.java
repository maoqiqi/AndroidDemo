package com.software.march.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.software.march.R;
import com.software.march.utils.SPUtils;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description Menu组件
 * @date 2016/12/16
 */
public class MenuComponentActivity extends AppCompatActivity {

    private Button btnContextMenu;
    private Button btnActionMode;
    private Button btnPopupMenu;

    private ActionMode mActionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_menu_component);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnContextMenu = (Button) findViewById(R.id.btn_context_menu);
        btnActionMode = (Button) findViewById(R.id.btn_action_mode);
        btnPopupMenu = (Button) findViewById(R.id.btn_popup_menu);

        // 设置创建上下文菜单的监听
        // btnContextMenu.setOnCreateContextMenuListener(this);
        // 注册监听
        registerForContextMenu(btnContextMenu);

        // 响应长按操作
        btnActionMode.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (mActionMode != null) {
                    return false;
                }

                // 当您调用 startActionMode() 时，系统将返回已创建的 ActionMode。
                // 通过将其保存在成员变量中，您可以更改上下文操作栏来响应其他事件。
                // 在上述示例中， ActionMode 用于在启动操作模式之前检查成员是否为空，以确保当 ActionMode 实例已激活时不再重建该实例。

                // Start the CAB using the ActionMode.Callback defined above
                mActionMode = startActionMode(mActionModeCallback);
                view.setSelected(true);
                return true;
            }
        });

        btnPopupMenu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PopupMenu pop = new PopupMenu(MenuComponentActivity.this, v);
                pop.setOnMenuItemClickListener(new MyMenuItemClickListener());
                MenuInflater inflater = pop.getMenuInflater();
                inflater.inflate(R.menu.menu_actions, pop.getMenu());
                // 在 API 级别 14 及更高版本中，您可以将两行合并在一起，使用 PopupMenu.inflate() 扩充菜单。
                pop.show();
                // 当用户选择项目或触摸菜单以外的区域时，系统即会清除此菜单。 您可使用 PopupMenu.OnDismissListener 侦听清除事件。
            }
        });

        // 创建弹出菜单
        // PopupMenu 是锚定到 View 的模态菜单。如果空间足够，它将显示在定位视图下方，否则显示在其上方。它适用于：
        // 为与特定内容确切相关的操作提供溢出样式菜单
        // 注：这与上下文菜单不同，后者通常用于影响所选内容的操作。 对于影响所选内容的操作，请使用上下文操作模式或浮动上下文菜单。
        // 注：PopupMenu 在 API 级别 11 及更高版本中可用。
        // 如果使用 XML 定义菜单，则显示弹出菜单的方法如下：
        // 1) 实例化 PopupMenu 及其构造函数，该函数将提取当前应用的 Context 以及菜单应锚定到的 View。
        // 2) 使用 MenuInflater 将菜单资源扩充到 PopupMenu.getMenu() 返回的 Menu 对象中。
        // 3) 调用 PopupMenu.show()。

        // 处理点击事件
        // 要在用户选择菜单项时执行操作，您必须实现 PopupMenu.OnMenuItemClickListener 接口，
        // 并通过调用 setOnMenuItemclickListener() 将其注册到 PopupMenu。
        // 用户选择项目时，系统会在接口中调用 onMenuItemClick() 回调。
    }

    private class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            if (menuItem.isChecked())
                menuItem.setChecked(false);
            else
                menuItem.setChecked(true);
            switch (menuItem.getItemId()) {
                case R.id.add:
                    Toast.makeText(MenuComponentActivity.this, "添加", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.delete:
                    Toast.makeText(MenuComponentActivity.this, "删除", Toast.LENGTH_SHORT).show();
                    return true;
                default:
                    return false;
            }
        }
    }

    // 创建上下文菜单
    // 1. 创建浮动上下文菜单和上下文操作模式
    // 1.1 使用浮动上下文菜单。用户长按（按住）一个声明支持上下文菜单的视图时，菜单显示为菜单项的浮动列表（类似于对话框）。 用户一次可对一个项目执行上下文操作。
    // 1.2 使用上下文操作模式。此模式是 ActionMode 的系统实现，它将在屏幕顶部显示上下文操作栏，其中包括影响所选项的操作项目。
    // 当此模式处于活动状态时，用户可以同时对多项执行操作（如果应用允许）。
    // 注：上下文操作模式可用于 Android 3.0（API 级别 11）及更高版本，是显示上下文操作（如果可用）的首选方法。
    // 如果应用支持低于 3.0 版本的系统，则应在这些设备上回退到浮动上下文菜单。
    // 2. 创建浮动上下文菜单
    // 2.1 在 Activity 或 Fragment 中实现 onCreateContextMenu() 方法。
    // 当注册后的视图收到长按事件时，系统将调用您的 onCreateContextMenu() 方法。在此方法中，您通常可通过扩充菜单资源来定义菜单项。
    // 2.2 通过调用 registerForContextMenu()，注册应与上下文菜单关联的 View 并将其传递给 View。
    // 如果 Activity 使用 ListView 或 GridView 且您希望每个项目均提供相同的上下文菜单，
    // 请通过将 ListView 或 GridView 传递给 registerForContextMenu()，为上下文菜单注册所有项目。

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        // 1.得到菜单加载器对象
        MenuInflater inflater = getMenuInflater();
        // 2.加载菜单文件
        inflater.inflate(R.menu.menu_options, menu);
    }

    // 成功处理菜单项后，系统将返回 true。
    // 如果未处理菜单项，则应将菜单项传递给超类实现。
    // 如果 Activity 包括片段，则 Activity 将先收到此回调。
    // 通过在未处理的情况下调用超类，系统会将事件逐一传递给每个片段中相应的回调方法（按照每个片段的添加顺序），直到返回 true 或 false 为止。
    // （Activity 和 android.app.Fragment 的默认实现返回 false，因此您始终应在未处理的情况下调用超类。）

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.add:
                Toast.makeText(this, "添加", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "删除", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }

    /*@Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        // 添加菜单项
        menu.add(0, 1, 0, "添加");
        menu.add(0, 4, 0, "删除");
    }*/

    /*@Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Toast.makeText(this, "添加", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(this, "删除", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }*/

    // 3. 使用上下文操作模式
    // 上下文操作模式是 ActionMode 的一种系统实现，它将用户交互的重点转到执行上下文操作上。
    // 用户通过选择项目启用此模式时，屏幕顶部将出现一个“上下文操作栏”，显示用户可对当前所选项执行的操作。
    // 启用此模式后，用户可以选择多个项目（若您允许）、取消选择项目以及继续在 Activity 内导航（在您允许的最大范围内）。
    // 当用户取消选择所有项目、按“返回”按钮或选择操作栏左侧的“完成”操作时，该操作模式将会停用，且上下文操作栏将会消失。
    // 注：上下文操作栏不一定与应用栏相关联。 尽管表面上看来上下文操作栏取代了应用栏的位置，但事实上二者独立运行。
    // 3.1 对于提供上下文操作的视图，当出现以下两个事件（或之一）时，您通常应调用上下文操作模式：
    // 3.1.1 用户长按视图。
    // 3.1.2 用户选中复选框或视图内的类似 UI 组件。
    // 3.2 应用如何调用上下文操作模式以及如何定义每个操作的行为，具体取决于您的设计。 设计基本上分为两种：
    // 3.2.1 针对单个任意视图的上下文操作。
    // 3.2.2 针对 ListView 或 GridView 中项目组的批处理上下文操作（允许用户选择多个项目并针对所有项目执行操作）。
    // 4. 为单个视图启用上下文操作模式
    // 如果希望仅当用户选择特定视图时才调用上下文操作模式，则应：
    // 4.1 实现 ActionMode.Callback 接口。在其回调方法中，您既可以为上下文操作栏指定操作，又可以响应操作项目的点击事件，还可以处理操作模式的其他生命周期事件。
    // 4.2 当需要显示操作栏时（例如，用户长按视图），请调用 startActionMode()。
    // 5. 在 ListView 或 GridView 中启用批处理上下文操作
    // 如果您在 ListView 或 GridView 中有一组项目（或 AbsListView 的其他扩展），且需要允许用户执行批处理操作，则应：
    // 5.1 实现 AbsListView.MultiChoiceModeListener 接口，并使用 setMultiChoiceModeListener() 为视图组设置该接口。
    // 在侦听器的回调方法中，您既可以为上下文操作栏指定操作，也可以响应操作项目的点击事件，还可以处理从 ActionMode.Callback 接口继承的其他回调。
    // 5.2 使用 CHOICE_MODE_MULTIPLE_MODAL 参数调用 setChoiceMode()。
    // 例如：
    /*
    ListView listView = getListView();
    listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
    listView.setMultiChoiceModeListener(new MultiChoiceModeListener() {

        @Override
        public void onItemCheckedStateChanged(ActionMode mode, int position,
        long id, boolean checked) {
            // Here you can do something when items are selected/de-selected,
            // such as update the title in the CAB
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            // Respond to clicks on the actions in the CAB
            switch (item.getItemId()) {
                case R.id.menu_delete:
                    deleteSelectedItems();
                    mode.finish(); // Action picked, so close the CAB
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate the menu for the CAB
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.context, menu);
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            // Here you can make any necessary updates to the activity when
            // the CAB is removed. By default, selected items are deselected/unchecked.
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            // Here you can perform updates to the CAB due to
            // an invalidate() request
            return false;
        }
    });
    */
    // 就这么简单。现在，当用户通过长按选择项目时，系统即会调用 onCreateActionMode() 方法，并显示包含指定操作的上下文操作栏。
    // 当上下文操作栏可见时，用户可以选择其他项目。
    // 在某些情况下，如果上下文操作提供常用的操作项目，则您可能需要添加一个复选框或类似的 UI 元素来支持用户选择项目，这是因为他们可能没有发现长按行为。
    // 用户选中该复选框时，您可以通过使用 setItemChecked() 将相应的列表项设置为选中状态，以此调用上下文操作模式。


    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        // Called when the action mode is created; startActionMode() was called
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate a menu resource providing context menu items
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.menu_options, menu);
            return true;
        }

        // Called each time the action mode is shown. Always called after onCreateActionMode, but
        // may be called multiple times if the mode is invalidated.
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false; // Return false if nothing is done
        }

        // Called when the user selects a contextual menu item
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.share:
                    Toast.makeText(MenuComponentActivity.this, "分享", Toast.LENGTH_SHORT).show();
                    mode.finish(); // Action picked, so close the CAB
                    return true;
                case R.id.add:
                    Toast.makeText(MenuComponentActivity.this, "添加", Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return true;
                case R.id.delete:
                    Toast.makeText(MenuComponentActivity.this, "删除", Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return true;
                default:
                    return false;
            }
        }

        // Called when the user exits the action mode
        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
        }
    };

    // 创建选项菜单
    // 1. 选项菜单中的项目在屏幕上的显示位置取决于您开发的应用所适用的 Android 版本：
    // 1.1 如果您开发的应用适用于 Android 2.3.x（API 级别 10）或更低版本，则当用户按“菜单”按钮时，选项菜单的内容会出现在屏幕底部。
    // 1.2 如果您开发的应用适用于 Android 3.0（API 级别 11）及更高版本，则选项菜单中的项目将出现在应用栏中。 默认情况下，系统会将所有项目均放入操作溢出菜单中。
    // 2. 从 Android 3.0（API 级别 11）开始，采用 Android 技术的设备不必再提供一个专用“菜单”按钮。
    // 2.1 如果您开发的应用适用于 Android 2.3.x 及更低版本，则当用户首次打开选项菜单时，系统会调用 onCreateOptionsMenu() 来创建该菜单。
    // 2.2 如果您开发的应用适用于 Android 3.0 及更高版本，则系统将在启动 Activity 时调用 onCreateOptionsMenu()，以便向应用栏显示项目。
    // 3. 处理点击事件
    // 用户从选项菜单中选择项目（包括应用栏中的操作项目）时，系统将调用 Activity 的 onOptionsItemSelected() 方法。
    // 此方法将传递所选的 MenuItem。您可以通过调用 getItemId() 方法来识别项目，该方法将返回菜单项的唯一 ID（由菜单资源中的 android:id 属性定义，
    // 或通过提供给 add() 方法的整型数定义）。 您可以将此 ID 与已知的菜单项匹配，以执行适当的操作。
    // 成功处理菜单项后，系统将返回 true。如果未处理菜单项，则应调用 onOptionsItemSelected() 的超类实现（默认实现将返回 false）。
    // 如果 Activity 包括片段，则系统将依次为 Activity 和每个片段（按照每个片段的添加顺序）调用 onOptionsItemSelected()，
    // 直到有一个返回结果为 true 或所有片段均调用完毕为止。
    // 提示：
    // Android 3.0 新增了一项功能，支持您在 XML 中使用 android:onClick 属性为菜单项定义点击行为。
    // 该属性的值必须是 Activity 使用菜单定义的方法的名称。
    // 该方法必须是公用的，且接受单个 MenuItem 参数；当系统调用此方法时，它会传递所选的菜单项。
    // 4. 在运行时更改菜单项
    // 系统调用 onCreateOptionsMenu() 后，将保留您填充的 Menu 实例。
    // 除非菜单由于某些原因而失效，否则不会再次调用 onCreateOptionsMenu()。
    // 但是，您仅应使用 onCreateOptionsMenu() 来创建初始菜单状态，而不应使用它在 Activity 生命周期中执行任何更改。
    // 如需根据在 Activity 生命周期中发生的事件修改选项菜单，则可通过 onPrepareOptionsMenu() 方法执行此操作。
    // 此方法向您传递 Menu 对象（因为该对象目前存在），以便您能够对其进行修改，如添加、移除或禁用项目。
    // （此外，片段还提供 onPrepareOptionsMenu() 回调。）
    // 在 Android 2.3.x 及更低版本中，每当用户打开选项菜单时（按“菜单”按钮），系统均会调用 onPrepareOptionsMenu()。
    // 在 Android 3.0 及更高版本中，当菜单项显示在应用栏中时，选项菜单被视为始终处于打开状态。
    // 发生事件时，如果您要执行菜单更新，则必须调用 invalidateOptionsMenu() 来请求系统调用 onPrepareOptionsMenu()。
    // 注：切勿根据目前处于焦点的 View 更改选项菜单中的项目。
    // 处于触摸模式（用户未使用轨迹球或方向键）时，视图无法形成焦点，因此切勿根据焦点修改选项菜单中的项目。
    // 若要为 View 提供上下文相关的菜单项，请使用上下文菜单。

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 一、菜单文件方式
        // 1.得到菜单加载器对象
        MenuInflater menuInflater = getMenuInflater();
        // 2.加载菜单文件
        menuInflater.inflate(R.menu.menu_options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.add:
                Toast.makeText(this, "添加", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "删除", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 二、纯编码方式
        menu.add(0, 2, 0, "添加");
        menu.add(0, 3, 0, "删除");
        return super.onCreateOptionsMenu(menu);
    }*/

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 2:
                Toast.makeText(this, "添加", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(this, "删除", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }*/

    // 创建菜单组
    // 菜单组是指一系列具有某些共同特征的菜单项。通过菜单组，您可以：
    // 1) 使用 setGroupVisible() 显示或隐藏所有项目
    // 2) 使用 setGroupEnabled() 启用或禁用所有项目
    // 3) 使用 setGroupCheckable() 指定所有项目是否可选中
        /*
        <?xml version="1.0" encoding="utf-8"?>
        <menu xmlns:android="http://schemas.android.com/apk/res/android">
        <item android:id="@+id/menu_save"
        android:icon="@drawable/menu_save"
        android:title="@string/menu_save" />
        <!-- menu group -->
        <group android:id="@+id/group_delete">
        <item android:id="@+id/menu_archive"
        android:title="@string/menu_archive" />
        <item android:id="@+id/menu_delete"
        android:title="@string/menu_delete" />
        </group>
        </menu>
        */
    // 组中的项目出现在与第一项相同的级别，即：菜单中的所有三项均为同级。
    // 但是，您可以通过引用组 ID 并使用上面列出的方法，修改组中两项的特征。此外，系统也绝不会分离已分组的项目。
    // 例如，如果为每个项目声明 android:showAsAction="ifRoom"，则它们会同时显示在操作栏或操作溢出菜单中。

    // 使用可选中的菜单项
    // 注：“图标菜单”（在选项菜单中）的菜单项无法显示复选框或单选按钮。
    // 如果您选择使“图标菜单”中的项目可选中，则必须在选中状态每次发生变化时交换图标和/或文本，手动指出该状态。

    // 您可以使用 <item> 元素中的 android:checkable 属性为各个菜单项定义可选中的行为，
    // 或者使用 <group> 元素中的 android:checkableBehavior 属性为整个组定义可选中的行为。
    // android:checkableBehavior 属性接受以下任一选项：
    // single 组中只有一个项目可以选中（单选按钮）
    // all 所有项目均可选中（复选框）
    // none 所有项目均无法选中

    // 选择可选中项目后，系统将调用所选项目的相应回调方法（例如，onOptionsItemSelected()）。
    // 此时，您必须设置复选框的状态，因为复选框或单选按钮不会自动更改其状态。
    // 您可以使用 isChecked() 查询项目的当前状态（正如用户选择该项目之前一样），然后使用 setChecked() 设置选中状态。

    // 如果未通过这种方式设置选中状态，则项目的可见状态（复选框或单选按钮）不会因为用户选择它而发生变化。
    // 如果已设置该状态，则 Activity 会保留项目的选中状态。这样一来，当用户稍后打开菜单时，您设置的选中状态将会可见。
    // 注：可选中菜单项的使用往往因会话而异，且在应用销毁后不予保存。 如果您想为用户保存某些应用设置，则应使用共享首选项存储数据。
}