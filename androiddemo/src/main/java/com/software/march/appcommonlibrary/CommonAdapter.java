package com.software.march.appcommonlibrary;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 基类, 公共BaseAdapter类
 * @date 2016/11/7
 */
public abstract class CommonAdapter<T> extends android.widget.BaseAdapter {

    /**
     * 上下文环境
     */
    protected Context mContext;

    /**
     * 数据
     */
    protected List<T> mData;

    public CommonAdapter(Context context, List<T> data) {
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, getLayoutId(), null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        convert(holder, position, mData.get(position));
        return convertView;
    }

    /**
     * 强制子类重写,返回布局Id
     *
     * @return layout Id
     */
    protected abstract int getLayoutId();

    /**
     * 强制子类重写,得到View或者设置数据
     *
     * @param holder
     * @param position
     * @param item
     */
    public abstract void convert(ViewHolder holder, int position, T item);

    /**
     * ListView、GridView通用的ViewHolder
     */
    public static class ViewHolder {

        // 布局文件
        public final View mConvertView;

        // 和Map类似,但是比Map效率高,不过键只能为Integer.用于存储控件.
        private final SparseArray<View> mViews;

        public ViewHolder(View itemView) {
            if (itemView == null) {
                throw new IllegalArgumentException("itemView may not be null");
            }
            mConvertView = itemView;
            mViews = new SparseArray<>();
        }

        /**
         * 通过viewId获取view,如果没有则加入views
         *
         * @param viewId
         * @return
         */
        public final View findViewById(int viewId) {
            // 根据viewId在mViews中得到View
            View view = mViews.get(viewId);
            if (view == null) {
                view = mConvertView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return view;
        }

        /**
         * 为TextView设置字符串
         *
         * @param viewId
         * @param textId
         * @return
         */
        public final ViewHolder setText(int viewId, int textId) {
            TextView view = (TextView) findViewById(viewId);
            view.setText(textId);
            return this;
        }

        /**
         * 为TextView设置字符串
         *
         * @param viewId
         * @param text
         * @return
         */
        public final ViewHolder setText(int viewId, String text) {
            TextView view = (TextView) findViewById(viewId);
            view.setText(text);
            return this;
        }
    }
}