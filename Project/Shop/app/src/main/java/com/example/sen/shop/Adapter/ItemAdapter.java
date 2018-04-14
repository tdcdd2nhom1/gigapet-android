package com.example.sen.shop.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.sen.shop.R;
import com.example.sen.shop.models.itemShop;

import java.util.ArrayList;

/**
 * Created by minhc_000 on 12/08/2015.
 */
public class ItemAdapter extends ArrayAdapter<itemShop> {
    Activity activity;//activity chứa listview

    public ItemAdapter(Activity activity, ArrayList<itemShop> products) {
        super(activity, 0, products);
        this.activity = activity;
    }

    //hàm hiện thị từng item lên listview
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        //position là vị tri của mỗi item. nó sẽ chạy hết mảng
        //lấy layout cho từng item
        if (convertView == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            convertView = inflater.inflate(R.layout.layout_shopitem, null);
        }
        //lấy các textview trong mỗi view
        TextView tvProductName = (TextView) convertView
                .findViewById(R.id.tvName);
        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.imgIcon);

        //hiển thị dư liệu lên từng item của listview ở vị trí position
        itemShop item = getItem(position);
        tvProductName.setText(item.getItemName());
        imgIcon.setImageResource(item.getIconShop());

        return convertView;//trả về 1 view khi đã thiết đặt xong
    }

}
