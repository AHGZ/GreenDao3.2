package com.hgz.test.greendao3.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hgz.test.greendao3.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2017/10/13.
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<User> list;
    public MyAdapter(Context context, List<User> list) {
        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list==null ? 0:list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView=convertView.inflate(context,android.R.layout.simple_list_item_1,null);
        }
        TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
        textView.setText("编号为："+list.get(position).getId()+"        姓名为："+list.get(position).getName());
        return convertView;
    }
}
