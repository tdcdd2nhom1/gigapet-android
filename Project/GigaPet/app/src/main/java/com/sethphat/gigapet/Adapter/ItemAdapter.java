package com.sethphat.gigapet.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sethphat.gigapet.Models.Category;
import com.sethphat.gigapet.R;

import java.util.ArrayList;

/**
 * Created by saharahoangvu on 18/04/2018.
 */

public class ItemAdapter extends ArrayAdapter<Category> {
    Activity activity;//activity chứa listview

    public ItemAdapter(Activity activity, ArrayList<Category> items) {
        super(activity, 0, items);
        this.activity = activity;
    }

    //hàm hiện thị từng item lên listview
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        //lấy layout cho từng item
        if (convertView == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            convertView = inflater.inflate(R.layout.shop_item_layout, null);
        }
        //lấy các textview trong mỗi view
        TextView tvTotal = (TextView) convertView.findViewById(R.id.tvTotal);
        TextView tvItemsName = (TextView) convertView.findViewById(R.id.tvName);
        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.imgIcon);

        //hiển thị data lên từng item của listview ở vị trí position
        Category category = getItem(position);
        tvItemsName.setText(category.getName());
        tvTotal.setText(Long.toString(category.getTotalItems()));

        // load img qua url, plugin Picasso
        Glide.with(getContext()).load(R.drawable.foodtruck).into(imgIcon);

        return convertView;//trả về 1 view khi đã thiết đặt xong
    }
}
