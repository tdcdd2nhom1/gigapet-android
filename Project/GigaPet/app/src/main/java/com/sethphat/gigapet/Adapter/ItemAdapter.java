package com.sethphat.gigapet.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sethphat.gigapet.CategoriesActivity;
import com.sethphat.gigapet.Common.DBAccess;
import com.sethphat.gigapet.Configs.SQLiteAccess;
import com.sethphat.gigapet.Models.Category;
import com.sethphat.gigapet.R;
import com.sethphat.gigapet.ShopPageActivity;

import java.util.ArrayList;

/**
 * Created by saharahoangvu on 18/04/2018.
 */

public class ItemAdapter extends ArrayAdapter<Category> {
    Activity activity;//activity chứa listview
    private LayoutInflater layoutInflater;
    private ArrayList<Category> items;
    private Context context;


    public ItemAdapter(@NonNull Context context, Activity activity, ArrayList<Category> items) {
        super(activity, 0, items);
        this.activity = activity;
        this.items = items;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    //hàm hiện thị từng item lên listview
    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        //lấy layout cho từng item
        final ViewHolder holder;
        // lam chong doi a`?? viewholder dau
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.shop_item_layout, null);
            holder = new ViewHolder();

            holder.imgIcon = (ImageView) convertView.findViewById(R.id.imgIcon);
            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            holder.tvTotal = (TextView) convertView.findViewById(R.id.tvTotal);
            holder.btnGoCategories = (ImageButton) convertView.findViewById(R.id.btnGoCategories);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //hiển thị data lên từng item của listview ở vị trí position
        final Category category = getItem(position);

        final String name = category.getName();
        holder.tvName.setText(name);
        holder.tvTotal.setText(Long.toString(category.getTotalItems()));

        holder.btnGoCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity, CategoriesActivity.class);
                activity.startActivity(intent);
            }
        });


        // load img qua url
        switch (name) {
            case "Food":
                Glide.with(getContext()).load(R.drawable.foodtruck).into(holder.imgIcon); // <== hard code???
                break;
            case "Drink":
                Glide.with(getContext()).load(R.drawable.water).into(holder.imgIcon);
                break;
            case "Background":
                Glide.with(getContext()).load(R.drawable.background).into(holder.imgIcon);
                break;
            case "Clothes/Skins":
                Glide.with(getContext()).load(R.drawable.skin).into(holder.imgIcon);
                break;
            default:
                break;
        }

        return convertView;//trả về 1 view khi đã thiết đặt xong
    }

    static class ViewHolder {
        ImageView imgIcon;
        TextView tvName;
        TextView tvTotal;
        ImageButton btnGoCategories;
    }
}
