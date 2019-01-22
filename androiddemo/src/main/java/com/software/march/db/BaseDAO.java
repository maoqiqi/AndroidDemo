package com.software.march.db;

import android.content.Context;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 数据库操作基类
 * @date 2016/12/27
 */
public class BaseDAO {

    protected Context mContext;

    protected DBHelper helper;

    public BaseDAO(Context context) {
        this.mContext = context;
        helper = new DBHelper(context);
    }
}