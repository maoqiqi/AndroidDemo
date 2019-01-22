package com.software.march.adapter;

import android.content.Context;

import com.software.march.R;
import com.software.march.appcommonlibrary.CommonAdapter;
import com.software.march.bean.PersonBean;

import java.util.List;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description
 * @date 2017/1/5
 */
public class PersonProviderAdapter extends CommonAdapter<PersonBean> {

    public PersonProviderAdapter(Context context, List<PersonBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_sqlite;
    }

    @Override
    public void convert(ViewHolder holder, int position, PersonBean item) {
        holder.setText(R.id.tv, item.getId() + "--" + item.getUserName() + "--" + item.getNickName() + "--" + item.getAge());
    }
}