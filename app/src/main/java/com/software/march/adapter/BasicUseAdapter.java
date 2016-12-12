package com.software.march.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.software.march.R;
import com.software.march.appcommonlibrary.CommonAdapter;

import java.util.List;
import java.util.Map;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description
 * @date 2016/11/5
 */
public class BasicUseAdapter extends CommonAdapter<String> {

    private Map<Integer, String> mMap;

    public BasicUseAdapter(Context context, List<String> data, Map<Integer, String> map) {
        super(context, data);
        mMap = map;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_basic_use;
    }

    @Override
    public void convert(ViewHolder holder, int position, String item) {
        TextView tvTitle = (TextView) holder.findViewById(R.id.tv_title);
        TextView tvContent = (TextView) holder.findViewById(R.id.tv_content);

        tvTitle.setVisibility(View.GONE);
        for (int i = 0; i < mMap.size(); i++) {
            if (mMap.keySet().contains(position)) {
                tvTitle.setVisibility(View.VISIBLE);
                tvTitle.setText(mMap.get(position));
                break;
            }
        }
        tvContent.setText(item);
        tvContent.setTag(position);
        tvContent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int position = (int) v.getTag();
                String item = mData.get(position);
            }
        });
    }
}