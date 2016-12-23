package com.software.march.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.software.march.R;
import com.software.march.bean.AppInfoBean;

import java.util.List;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 重写ArrayAdapter
 * @date 2016/12/22
 */
public class MyArrayAdapter extends ArrayAdapter<AppInfoBean> {

    /**
     * 上下文环境
     */
    protected Context mContext;

    /**
     * 布局资源文件
     */
    protected int resource;

    /**
     * 数据
     */
    protected List<AppInfoBean> mData;

    public MyArrayAdapter(Context context, int resource, List<AppInfoBean> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.resource = resource;
        this.mData = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(resource, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 填充数据
        AppInfoBean item = getItem(position);
        holder.ivIcon.setImageDrawable(item.getIcon());
        holder.tvLabel.setText(item.getAppName());

        return convertView;
    }

    private final class ViewHolder {

        ImageView ivIcon;

        TextView tvLabel;

        public ViewHolder(View itemView) {
            ivIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
            tvLabel = (TextView) itemView.findViewById(R.id.tv_label);
        }
    }
}