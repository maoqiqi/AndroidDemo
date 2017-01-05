package com.software.march.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.software.march.db.DBHelper;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 操作 person 表的 provider 类
 * @date 2017/1/4
 */
public class PersonProvider extends ContentProvider {

    private final String TAG = getClass().getSimpleName();

    private static final String AUTHORITY = "com.software.march.provider";

    /**
     * 用来存放所有合法的 Uri 的容器
     */
    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    // 保存一些合法的 Uri
    // content://com.software.march.provider/person 不根据 id 操作
    // content://com.software.march.provider/person/3 根据 id 操作
    static {
        URI_MATCHER.addURI(AUTHORITY, "/person", 1);
        URI_MATCHER.addURI(AUTHORITY, "/person/#", 2);// # 匹配任意数字
    }

    protected DBHelper dbHelper;

    public PersonProvider() {
        Log.e(TAG, TAG + "()");
    }

    // 创建数据时调用的回调函数
    @Override
    public boolean onCreate() {
        Log.e(TAG, TAG + "()");
        dbHelper = new DBHelper(getContext());
        return false;
    }

    // 查询
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.e(TAG, "query()");

        // 得到连接对象
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        // 匹配 uri ,返回 code
        int code = URI_MATCHER.match(uri);
        // 如果合法, 进行查询
        if (code == 1) {
            // 不根据 id 查询
            return database.query("person", projection, selection, selectionArgs, null, null, sortOrder);
        } else if (code == 2) {
            // 根据 id 查询
            // 得到id
            long id = ContentUris.parseId(uri);
            return database.query("person", projection, "_id=?", new String[]{id + ""}, null, null, sortOrder);
        } else {
            // 不合法, 抛出异常
            throw new RuntimeException("查询的uri不合法");
        }
    }

    // 返回数据的 MIME 类型
    @Override
    public String getType(Uri uri) {
        Log.e(TAG, "getType()");
        return null;
    }

    // 插入
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        Log.e(TAG, "insert()");

        SQLiteDatabase database = dbHelper.getReadableDatabase();

        int code = URI_MATCHER.match(uri);
        // 如果合法, 进行插入
        if (code == 1) {
            long id = database.insert("person", null, contentValues);
            // 将 id 添加到 uri 中
            uri = ContentUris.withAppendedId(uri, id);
            database.close();
            return uri;
        } else {
            // 不合法, 抛出异常
            database.close();
            throw new RuntimeException("插入的uri不合法");
        }
    }

    // 删除
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.e(TAG, "delete()");

        SQLiteDatabase database = dbHelper.getReadableDatabase();

        int code = URI_MATCHER.match(uri);
        int deleteCount = -1;
        // 如果合法, 进行删除
        if (code == 1) {
            deleteCount = database.delete("person", selection, selectionArgs);
        } else if (code == 2) {
            long id = ContentUris.parseId(uri);
            deleteCount = database.delete("person", "_id=?", new String[]{id + ""});
        } else {
            // 不合法, 抛出异常
            database.close();
            throw new RuntimeException("删除的uri不合法");
        }
        database.close();
        return deleteCount;
    }

    // 更新
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Log.e(TAG, "update()");

        SQLiteDatabase database = dbHelper.getReadableDatabase();

        int code = URI_MATCHER.match(uri);
        int updateCount = -1;
        // 如果合法, 进行更新
        if (code == 1) {
            updateCount = database.update("person", values, selection, selectionArgs);
        } else if (code == 2) {
            long id = ContentUris.parseId(uri);
            updateCount = database.update("person", values, "_id=?", new String[]{id + ""});
        } else {
            // 不合法, 抛出异常
            database.close();
            throw new RuntimeException("更新的uri不合法");
        }
        database.close();
        return updateCount;
    }
}