package com.software.march.activity;

import android.Manifest;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.software.march.R;
import com.software.march.utils.RPUtils;
import com.software.march.utils.SPUtils;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description SimpleCursorAdapter
 * @date 2016/12/21
 */
public class SimpleCursorAdapterActivity extends AppCompatActivity {

    private static final String[] PROJECTION = new String[]{ContactsContract.Data._ID, ContactsContract.Data.DISPLAY_NAME};
    private static final String SELECTION = "((" + ContactsContract.Data.DISPLAY_NAME + " NOTNULL) AND (" + ContactsContract.Data.DISPLAY_NAME + " != '' ))";

    // For the cursor adapter, specify which columns go into which views
    String[] fromColumns = {ContactsContract.Data.DISPLAY_NAME};
    int[] toViews = {android.R.id.text1}; // The TextView in simple_list_item_1

    private static final int REQUEST_READ_CONTACTS = 0;
    private static final int CURSOR_LOADER_ID = 1;

    private int type;

    private ListView listView;
    private GridView gridView;

    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.layout_adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        type = getIntent().getIntExtra(UIActivity.TYPE, UIActivity.TYPE_LIST_VIEW);

        listView = (ListView) findViewById(R.id.list_view);
        gridView = (GridView) findViewById(R.id.grid_view);

        if (type == UIActivity.TYPE_LIST_VIEW) {
            listView.setVisibility(View.VISIBLE);
            gridView.setVisibility(View.GONE);

            if (!RPUtils.requestPermissions(this, Manifest.permission.READ_CONTACTS, REQUEST_READ_CONTACTS)) {
                return;
            }
            setListViewAdapter();
        } else {
            listView.setVisibility(View.GONE);
            gridView.setVisibility(View.VISIBLE);

            if (!RPUtils.requestPermissions(this, Manifest.permission.READ_CONTACTS, REQUEST_READ_CONTACTS)) {
                return;
            }
            setGridViewAdapter();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (type == UIActivity.TYPE_LIST_VIEW) {
                    setListViewAdapter();
                } else {
                    setGridViewAdapter();
                }
            }
        }
    }

    private void setListViewAdapter() {
        // 准备SimpleCursorAdapter对象
        // ① new SimpleCursorAdapter(context, layout, c, from, to)
        // ② new SimpleCursorAdapter(context, layout, c, from, to, flags);
        // 最后一个参数flags，它有两个值可以选择分别为：CursorAdapter.FLAG_AUTO_REQUERY和CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER，
        // 另外一个构造方法就是少了最后一个参数，默认被设置为CursorAdapter.FLAG_AUTO_REQUERY。

        // 1) 首先我们来看没有flag参数或者参数为CursorAdapter.FLAG_AUTO_REQUERY 的情况，
        // 这种是在 android3.0 以下版本中才使用的，在分为 android3.0 及以上的版本中，已经被废弃了，是不建议使用的。
        // 2) 正如字面意思，FLAG_AUTO_REQUERY会自动查询如果数据库中的数据发生了变化以实时反映到界面上来，
        // 这样使用有一个问题，查询数据是发生在UI线程中，会造成卡顿的现象，甚至出现ANR异常，
        // 取决于手机的配置及数据的大小，这也是android3.0及以上放弃的原因
        // 3) 一般来说我们使用Cursor需要close，但是在这里我们并没close，那么是怎么处理的呢?
        // 原理是Activity自己帮我处理的，大家看我是使用managedQuery来获取Cursor对象的，在这个方法中
        /*
        @Deprecated
        public final Cursor managedQuery(Uri uri, String[] projection, String selection,
                String[] selectionArgs, String sortOrder) {
            Cursor c = getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
            if (c != null) {
                startManagingCursor(c);// 把获取的Cursor对象交给Activity管理
            }
            return c;
        }
        */
        // 4) 用getContentResolver().query()获取到Cursor对象以后，使用startManagingCursor()把Cursor对象加入到Activity中管理。
        /*Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, PROJECTION, SELECTION, null, null);
        if (cursor != null) {
            startManagingCursor(cursor);
        }*/
        /*Cursor cursor = managedQuery(ContactsContract.Contacts.CONTENT_URI, PROJECTION, SELECTION, null, null);
        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, fromColumns, toViews);
        listView.setAdapter(adapter);*/

        // 1) CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER，这是android3.0及以上的做法，我们需要使用到CursorLoader来加载数据了。
        // Create an empty adapter we will use to display the loaded data.
        // We pass null for the cursor, then update it in onLoadFinished()
        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1,
                null, fromColumns, toViews, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        // 设置Adapter显示列表
        listView.setAdapter(adapter);

        // Prepare the loader.  Either re-connect with an existing one,
        // or start a new one.
        getLoaderManager().initLoader(CURSOR_LOADER_ID, null, cursorLoaderCallbacks);
        // getSupportLoaderManager().initLoader(0, null, cursorLoaderCallbacks);// 向下兼容

        // 2) 使用 CursorLoader 是以异步任务形式查询 Cursor 的标准方式，可避免查询阻塞应用的主线程。
        // 当 CursorLoader 接收到 Cursor 结果时，LoaderCallbacks 会收到对 onLoadFinished() 的回调，
        // 从中您可以使用新的 Cursor 更新 Adapter，然后列表视图会显示结果。

        // 3) 当数据库的数据变化的时候会自动回调onLoadFinished()来更新界面，同样这个方式也是由Activity来管理Cursor的，
        // adapter.swapCursor()会把之前的Cursor给close掉，并使用新的Cursor对象，
        // onLoaderReset()就是由Activity中的performDestroy()来间接调用的，
        // adapter.swapCursor(null);被调用后就关闭了Cursor，
        // 这个方式获取Cursor是在子线程中，加载完成后会回调onLoadFinished()方法，不会阻塞UI线程，提高了流畅度，
        // 另外当Configuration发生变化是可以重用Cursor，不需要去重新获取Cursor，提高了效率，
        // 强烈的推荐大家在今后的开发中，使用这种方式。
    }

    private LoaderManager.LoaderCallbacks<Cursor> cursorLoaderCallbacks = new LoaderManager.LoaderCallbacks<Cursor>() {

        // Called when a new Loader needs to be created
        // 在创建activity时跟着onCreate会调用一次
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            // Now create and return a CursorLoader that will take care of
            // creating a Cursor for the data being displayed.
            return new CursorLoader(SimpleCursorAdapterActivity.this,
                    ContactsContract.Data.CONTENT_URI, PROJECTION, SELECTION, null, null);
        }

        // Called when a previously created loader has finished loading
        // 每次改变和Loader相关的数据库记录后会调用一次
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            // Swap the new cursor in.  (The framework will take care of closing the
            // old cursor once we return.)
            adapter.swapCursor(data);
        }

        // Called when a previously created loader is reset, making the data unavailable
        // 在关闭Activity时调用，释放资源
        public void onLoaderReset(Loader<Cursor> loader) {
            // This is called when the last Cursor provided to onLoadFinished()
            // above is about to be closed.  We need to make sure we are no
            // longer using it.
            adapter.swapCursor(null);
        }
    };

    private void setGridViewAdapter() {
        Cursor cursor = getContentResolver().query(ContactsContract.Data.CONTENT_URI, PROJECTION, SELECTION, null, null);
        if (cursor != null) {
            startManagingCursor(cursor);// 把获取的Cursor对象交给Activity管理
        }
        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, fromColumns, toViews);
        gridView.setAdapter(adapter);
        stopManagingCursor(cursor);
    }
}