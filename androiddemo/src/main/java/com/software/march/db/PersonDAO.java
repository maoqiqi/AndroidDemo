package com.software.march.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import android.widget.Toast;

import com.software.march.bean.PersonBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description person表操作类
 * @date 2016/12/27
 */
public class PersonDAO extends BaseDAO {

    private final String TAG = getClass().getSimpleName();

    public PersonDAO(Context context) {
        super(context);
    }

    // CRUD是指在做计算处理时的增加(Create)、读取查询(Retrieve)、更新(Update)和删除(Delete)几个单词的首字母简写。

    /**
     * 创建数据库
     */
    public void create() {
        // 获取连接
        SQLiteDatabase database = helper.getReadableDatabase();
        Toast.makeText(mContext, "创建数据库", Toast.LENGTH_SHORT).show();
    }

    /**
     * 添加一条记录
     *
     * @param personBean
     */
    public long add1(PersonBean personBean) {
        long id = -1;
        // 1.得到连接
        SQLiteDatabase database = helper.getReadableDatabase();
        // 2.执行insert into person(userName,nickName,age) values (?,?,?);
        ContentValues values = new ContentValues();
        values.put("userName", personBean.getUserName());
        values.put("nickName", personBean.getNickName());
        values.put("age", personBean.getAge());
        id = database.insert("person", null, values);
        // 3.关闭
        database.close();
        Log.i(TAG, "id=" + id);
        Toast.makeText(mContext, "id=" + id, Toast.LENGTH_SHORT).show();
        return id;
    }

    /**
     * 添加一条记录
     *
     * @param personBean
     */
    public void add2(PersonBean personBean) {
        SQLiteDatabase database = helper.getReadableDatabase();
        String sql = "insert into person(userName,nickName,age) values (?,?,?);";
        Object[] bindArgs = new Object[]{personBean.getUserName(), personBean.getNickName(), personBean.getAge()};
        database.execSQL(sql, bindArgs);
        database.close();
    }

    /**
     * 添加一条记录
     *
     * @param personBean
     * @return
     */
    public long add3(PersonBean personBean) {
        long id = -1;
        SQLiteDatabase database = helper.getReadableDatabase();
        String sql = "insert into person(userName,nickName,age) values (?,?,?);";
        SQLiteStatement stat = database.compileStatement(sql);
        database.beginTransaction();
        try {
            stat.bindString(1, personBean.getUserName());
            stat.bindString(2, personBean.getNickName());
            stat.bindLong(3, personBean.getAge());
            id = stat.executeInsert();
            database.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (database != null) {
                database.endTransaction();
                database.close();
            }
        }
        Log.i(TAG, "id=" + id);
        Toast.makeText(mContext, "id=" + id, Toast.LENGTH_SHORT).show();
        return id;
    }

    /**
     * 查询所有记录
     *
     * @return
     */
    public List<PersonBean> getAll1() {
        List<PersonBean> list = new ArrayList<>();
        SQLiteDatabase database = helper.getReadableDatabase();
        // 执行query select * from person
        Cursor cursor = database.query("person", null, null, null, null, null, null);
        // 得到匹配的总记录数
        int count = cursor.getCount();
        // 取出cursor中所有的数据
        while (cursor.moveToNext()) {
            PersonBean bean = new PersonBean();
            bean.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            bean.setUserName(cursor.getString(cursor.getColumnIndex("userName")));
            bean.setNickName(cursor.getString(cursor.getColumnIndex("nickName")));
            bean.setAge(cursor.getInt(cursor.getColumnIndex("age")));
            Log.i(TAG, bean.toString());
            list.add(bean);
        }
        cursor.close();
        database.close();
        Toast.makeText(mContext, "count=" + count, Toast.LENGTH_SHORT).show();
        return list;
    }

    /**
     * 查询所有记录
     *
     * @return
     */
    public List<PersonBean> getAll2() {
        List<PersonBean> list = new ArrayList<>();
        SQLiteDatabase database = helper.getReadableDatabase();
        String sql = "select _id,userName,nickName,age from person";
        Cursor cursor = database.rawQuery(sql, null);
        int count = cursor.getCount();
        while (cursor.moveToNext()) {
            PersonBean bean = new PersonBean();
            bean.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            bean.setUserName(cursor.getString(cursor.getColumnIndex("userName")));
            bean.setNickName(cursor.getString(cursor.getColumnIndex("nickName")));
            bean.setAge(cursor.getInt(cursor.getColumnIndex("age")));
            Log.i(TAG, bean.toString());
            list.add(bean);
        }
        cursor.close();
        database.close();
        Toast.makeText(mContext, "count=" + count, Toast.LENGTH_SHORT).show();
        return list;
    }

    /**
     * 更新一条记录
     *
     * @param personBean
     */
    public void update1(PersonBean personBean) {
        SQLiteDatabase database = helper.getReadableDatabase();
        // 执行update person set userName=?,nickName=?,age=? where _id=?
        ContentValues values = new ContentValues();
        values.put("userName", personBean.getUserName());
        values.put("nickName", personBean.getNickName());
        values.put("age", personBean.getAge());
        int count = database.update("person", values, "_id=?", new String[]{"1"});
        database.close();
        Toast.makeText(mContext, "count=" + count, Toast.LENGTH_SHORT).show();
    }

    /**
     * 更新一条记录
     *
     * @param personBean
     */
    public void update2(PersonBean personBean) {
        SQLiteDatabase database = helper.getWritableDatabase();
        String sql = "update person set userName=?,nickName=?,age=? where _id=?";
        Object[] bindArgs = new Object[]{personBean.getUserName(), personBean.getNickName(), personBean.getAge(), personBean.getId()};
        database.execSQL(sql, bindArgs);
        database.close();
    }

    /**
     * 更新一条记录
     *
     * @param personBean
     */
    public void update3(PersonBean personBean) {
        int count = -1;
        SQLiteDatabase database = helper.getWritableDatabase();
        String sql = "update person set userName=?,nickName=?,age=? where _id=?";
        SQLiteStatement stat = database.compileStatement(sql);
        database.beginTransaction();
        try {
            stat.bindString(1, personBean.getUserName());
            stat.bindString(2, personBean.getNickName());
            stat.bindLong(3, personBean.getAge());
            count = stat.executeUpdateDelete();
            database.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (database != null) {
                database.endTransaction();
                database.close();
            }
        }
        Toast.makeText(mContext, "count=" + count, Toast.LENGTH_SHORT).show();
    }

    /**
     * 根据 id 删除一条记录
     *
     * @param id
     */
    public void deleteById1(int id) {
        SQLiteDatabase database = helper.getReadableDatabase();
        // 执行delete from person where _id=?
        int count = database.delete("person", "_id=?", new String[]{String.valueOf(id)});
        database.close();
        Toast.makeText(mContext, "count=" + count, Toast.LENGTH_SHORT).show();
    }

    /**
     * 根据 id 删除一条记录
     *
     * @param id
     */
    public void deleteById2(int id) {
        SQLiteDatabase database = helper.getReadableDatabase();
        String sql = "delete from person where _id=?";
        Object[] bindArgs = new Object[]{id};
        database.execSQL(sql, bindArgs);
        database.close();
    }

    /**
     * 根据 id 删除一条记录
     *
     * @param id
     */
    public void deleteById3(int id) {
        int count = -1;
        SQLiteDatabase database = helper.getWritableDatabase();
        String sql = "delete from person where _id=?";
        SQLiteStatement stat = database.compileStatement(sql);
        database.beginTransaction();
        try {
            stat.bindLong(1, id);
            count = stat.executeUpdateDelete();
            database.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (database != null) {
                database.endTransaction();
                database.close();
            }
        }
        Toast.makeText(mContext, "count=" + count, Toast.LENGTH_SHORT).show();
    }
}