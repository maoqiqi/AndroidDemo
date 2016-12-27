package com.software.march.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description SQLite数据库帮助类
 * @date 2016/12/27
 */
public class DBHelper extends SQLiteOpenHelper {

    private final String TAG = getClass().getSimpleName();

    /*
     * 数据库名称
	 */
    public static final String DATABASE_NAME = "march.db";

    /*
     * 数据库版本号(数据库版本升级)
     */
    public static final int DATABASE_VERSION = 1;

    // 上下文对象
    private Context context;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        Log.i(TAG, TAG + " DBHelper()");
    }

    // 什么时候才会创建数据库文件?
    // 1.数据库文件不存在
    // 2.连接数据库

    // 什么时候调用?
    // 当数据库文件创建时调用(1次)

    /**
     * 在此方法中做什么?
     * 建表
     * 插入一些初始化数据
     */

    private final String PERSON = "create table person(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "userName VARCHAR(20),nickName VARCHAR(20),age INTEGER DEFAULT 0);";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i(TAG, TAG + " onCreate()");
        // 向数据中添加表
        sqLiteDatabase.execSQL(PERSON);
    }

    // 当传入的版本号大于数据库的版本号时调用
    // 拿到当前数据库的版本信息 与之前数据库的版本信息 用来更新数据库
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.i(TAG, TAG + " onUpgrade()");
    }

    // 删除数据库
    public boolean deleteDatabase() {
        if (context == null) {
            return false;
        }
        return context.deleteDatabase(DATABASE_NAME);
    }
}