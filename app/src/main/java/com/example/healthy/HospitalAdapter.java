package com.example.healthy;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.healthy.Hospital;
import com.example.healthy.R;

import java.util.ArrayList;

public class HospitalAdapter extends BaseAdapter {
    Context context;
    ArrayList<Hospital> list;
    int layout;

    public HospitalAdapter(Context context, ArrayList<Hospital> list, int layout) {
        this.context = context;
        this.list = list;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(layout, null);

        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView location = (TextView) convertView.findViewById(R.id.location);
        TextView phone = (TextView) convertView.findViewById(R.id.phone);

        Hospital item = list.get(position);

        name.setText(item.getName());
        location.setText(item.getLocation());
        phone.setText(item.getPhone());

        return convertView;
    }
}
