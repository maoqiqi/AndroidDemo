package com.software.march.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.software.march.R;
import com.software.march.appcommonlibrary.CommonAdapter;

import java.util.List;
import java.util.Map;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 主页四个 Fragment 适配器
 * @date 2016/11/5
 */
public class FragmentAdapter extends CommonAdapter<String> {

    private Map<Integer, String> mMap;
    private Class<? extends AppCompatActivity>[] mClazzs;

    public FragmentAdapter(Context context, List<String> data, Map<Integer, String> map, Class<? extends AppCompatActivity>[] clazzs) {
        super(context, data);
        mMap = map;
        mClazzs = clazzs;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_base;
    }

    @Override
    public void convert(ViewHolder holder, int position, String item) {
        TextView tvTitle = (TextView) holder.findViewById(R.id.tv_title);
        TextView tvContent = (TextView) holder.findViewById(R.id.tv_content);

        tvTitle.setVisibility(View.GONE);
        if (mMap != null && mMap.size() > 0) {
            for (int i = 0; i < mMap.size(); i++) {
                if (mMap.keySet().contains(position)) {
                    tvTitle.setVisibility(View.VISIBLE);
                    tvTitle.setText(mMap.get(position));
                    break;
                }
            }
        }
        tvContent.setText(item);
        tvContent.setTag(position);
        tvContent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int position = (int) v.getTag();
                if (mClazzs[position] != null) {
                    Intent intent = new Intent(mContext, mClazzs[position]);
                    mContext.startActivity(intent);
                } else {
                    Toast.makeText(mContext, "《" + mData.get(position) + "》 正在开发中", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}