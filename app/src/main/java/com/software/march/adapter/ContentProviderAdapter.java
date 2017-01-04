package com.software.march.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.software.march.R;
import com.software.march.bean.MediaItemBean;

import java.util.List;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 重写ArrayAdapter
 * @date 2016/12/22
 */
public class ContentProviderAdapter extends ArrayAdapter<MediaItemBean> {

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
    protected List<MediaItemBean> mData;

    public ContentProviderAdapter(Context context, int resource, List<MediaItemBean> objects) {
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

        MediaItemBean item = getItem(position);
        Log.e("ContentProvider", item.toString());
        // ContentProvider: MediaItemBean{_id=571754, title='okhttp-utils-test', artist='<unknown>', displayName='okhttp-utils-test.mp4', data='/storage/emulated/0/okhttp-utils-test.mp4', duration=171062, size=11663457, albumId=0, artistId=0}
        // ContentProvider: MediaItemBean{_id=644508, title='沧海一声笑 霍建华-陈乔恩', artist='yinyuetai.com', displayName='沧海一声笑 霍建华-陈乔恩.mp4', data='/storage/emulated/0/YinyuetaiVideo/沧海一声笑 霍建华-陈乔恩.mp4', duration=295683, size=28922398, albumId=0, artistId=0}
        holder.tv.setText(item.getTitle());

        return convertView;
    }

    private final class ViewHolder {

        TextView tv;

        public ViewHolder(View itemView) {
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}