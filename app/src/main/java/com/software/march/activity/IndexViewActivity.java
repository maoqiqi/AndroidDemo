package com.software.march.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.software.march.R;
import com.software.march.appcommonlibrary.CommonAdapter;
import com.software.march.bean.ContactBean;
import com.software.march.utils.SPUtils;
import com.software.march.view.IndexView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 测试联系人快速索引
 * @date 2017/1/20
 */
public class IndexViewActivity extends AppCompatActivity implements IndexView.OnIndexChangeListener {

    // sort_key:我们在取数据时可以按照该字段来排序
    // 联系人首字母
    private static final String PHONE_BOOK_LABEL = "phonebook_label";
    private static final String[] PROJECTION = new String[]{ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, PHONE_BOOK_LABEL};
    private static final String SELECTION = "((" + ContactsContract.Contacts.DISPLAY_NAME + " NOTNULL) AND (" + ContactsContract.Contacts.DISPLAY_NAME + " != '' ))";

    private ListView listView;
    private IndexView indexView;
    private TextView tvWord;

    private List<ContactBean> list;
    private CommonAdapter<ContactBean> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_index_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.list_view);
        indexView = (IndexView) findViewById(R.id.index_view);
        tvWord = (TextView) findViewById(R.id.tv_word);

        indexView.setOnIndexChangeListener(this);

        list = new ArrayList<>();
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, PROJECTION, SELECTION, null, ContactsContract.CommonDataKinds.Phone.SORT_KEY_PRIMARY);
        while (cursor.moveToNext()) {
            ContactBean bean = new ContactBean();
            bean.setName(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
            bean.setNumber(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
            bean.setLabel(cursor.getString(cursor.getColumnIndex(PHONE_BOOK_LABEL)));
            list.add(bean);
        }

        // 排序
        /*Collections.sort(list, new Comparator<ContactBean>() {
            @Override
            public int compare(ContactBean lhs, ContactBean rhs) {
                return lhs.getLabel().compareTo(rhs.getLabel());
            }
        });*/

        adapter = new CommonAdapter<ContactBean>(this, list) {

            @Override
            protected int getLayoutId() {
                return R.layout.item_index_view;
            }

            @Override
            public void convert(ViewHolder holder, int position, ContactBean item) {
                TextView tvLabel = (TextView) holder.findViewById(R.id.tv_label);
                if (position > 0) {
                    if (list.get(position - 1).getLabel().equals(item.getLabel())) {
                        tvLabel.setVisibility(View.GONE);
                    } else {
                        tvLabel.setVisibility(View.VISIBLE);
                    }
                } else {
                    tvLabel.setVisibility(View.VISIBLE);
                }
                tvLabel.setText(item.getLabel());
                holder.setText(R.id.tv_name, item.getName());
            }
        };

        listView.setAdapter(adapter);
    }

    @Override
    public void onIndexChange(String word) {
        tvWord.setVisibility(View.VISIBLE);
        tvWord.setText(word);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getLabel().equals(word)) {
                listView.setSelection(i);
                return;
            }
        }
    }

    @Override
    public void onEnd() {
        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                tvWord.setVisibility(View.GONE);
            }
        }.sendEmptyMessageDelayed(0, 2000);
    }
}