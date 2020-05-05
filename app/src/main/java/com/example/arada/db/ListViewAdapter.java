package com.example.arada.db;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.arada.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ListViewAdapter extends BaseAdapter {
    ArrayList<HashMap<String, String>> list;
    public void setList(ArrayList<HashMap<String, String>> list) {
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
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
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);

        }
        TextView date = (TextView) convertView.findViewById(R.id.list_txt1);
        TextView record = (TextView) convertView.findViewById(R.id.list_txt2);

        date.setText(list.get(pos).get("_date"));
        record.setText(list.get(pos).get("record"));
        return convertView;
    }
}
