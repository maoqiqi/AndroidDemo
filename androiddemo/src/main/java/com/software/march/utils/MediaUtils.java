package com.software.march.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.software.march.bean.MediaItemBean;

import java.util.ArrayList;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 媒体资源帮助类
 * @date 2017/1/4
 */
public class MediaUtils {

    /**
     * 从Cursor读取数据,得到数据列表
     *
     * @param cursor 光标
     * @return
     */
    private static ArrayList<MediaItemBean> getVideosForCursor(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        try {
            if ((cursor != null) && (cursor.moveToFirst())) {
                int _id = cursor.getColumnIndex(MediaStore.Video.Media._ID);
                int title = cursor.getColumnIndex(MediaStore.Video.Media.TITLE);
                int artist = cursor.getColumnIndex(MediaStore.Video.Media.ARTIST);
                int displayName = cursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME);
                int data = cursor.getColumnIndex(MediaStore.Video.Media.DATA);
                int duration = cursor.getColumnIndex(MediaStore.Video.Media.DURATION);
                int size = cursor.getColumnIndex(MediaStore.Video.Media.SIZE);

                do {
                    MediaItemBean bean = new MediaItemBean();

                    bean.set_id(cursor.getLong(_id));
                    bean.setTitle(cursor.getString(title));
                    bean.setArtist(cursor.getString(artist));
                    bean.setDisplayName(cursor.getString(displayName));
                    bean.setData(cursor.getString(data));
                    bean.setDuration(cursor.getLong(duration));
                    bean.setSize(cursor.getLong(size));

                    arrayList.add(bean);
                }
                while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return arrayList;
    }

    /**
     * 根据uri和条件查询得到Cursor
     *
     * @param context       上下文
     * @param uri           地址
     * @param projection    查询字段
     * @param selection     筛选条件
     * @param selectionArgs 筛选条件参数
     * @param sortOrder     排序
     * @return Cursor
     */
    private static Cursor makeCursor(Context context, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (context == null)
            return null;

        ContentResolver resolver = context.getContentResolver();

        Cursor cursor = resolver.query(uri, projection, selection, selectionArgs, sortOrder);

        return cursor;
    }

    /**
     * 得到所有视频
     *
     * @param context 上下文
     * @return 视频列表
     */
    public static ArrayList<MediaItemBean> getAllVideos(Context context) {
        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.Video.Media._ID,// 视频的ID
                MediaStore.Video.Media.TITLE,// 视频的标题
                MediaStore.Video.Media.ARTIST,// 歌曲的演唱者
                MediaStore.Video.Media.DISPLAY_NAME,// 视频文件在SDCard的名称
                MediaStore.Video.Media.DATA,// 视频的绝对地址
                MediaStore.Video.Media.DURATION,// 视频总时长
                MediaStore.Video.Media.SIZE// 视频文件大小
        };
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = MediaStore.Video.Media.DEFAULT_SORT_ORDER;

        return getVideosForCursor(makeCursor(context, uri, projection, selection, selectionArgs, sortOrder));
    }
}