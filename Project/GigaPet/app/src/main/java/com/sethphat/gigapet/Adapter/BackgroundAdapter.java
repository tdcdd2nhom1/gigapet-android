package com.sethphat.gigapet.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sethphat.gigapet.Configs.Setting;
import com.sethphat.gigapet.Models.ShopItem;
import com.sethphat.gigapet.Models.UserItem;
import com.sethphat.gigapet.R;

import java.util.ArrayList;

public class BackgroundAdapter extends ArrayAdapter<UserItem> {
    Activity context;
    ArrayList<UserItem> listItem;
    int layout;

    public BackgroundAdapter(@NonNull Activity context, int resource, @NonNull ArrayList<UserItem> objects) {
        super(context, resource, objects);

        this.context = context;
        this.listItem = objects;
        this.layout = resource;
    }

    static class ViewHolder{
        ImageView imgItem;
        TextView txtName;
        TextView txtDescription;
        TextView txtPrice;
        LinearLayout llItem;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // khai bao view holder
        final ViewHolder view;

        // get inflater hien tai cua this
        LayoutInflater inf = context.getLayoutInflater();

        // kiem tra convertView hien tai
        if (convertView == null)
        {
            // chua co, hkoi tao
            convertView = inf.inflate(layout, parent, false);

            // new viewholder
            view = new ViewHolder();

            // catch controls
            view.txtName = (TextView) convertView.findViewById(R.id.txtName);
            view.txtPrice = (TextView) convertView.findViewById(R.id.txtPrice);
            view.txtDescription = (TextView) convertView.findViewById(R.id.txtDescription);
            view.imgItem = (ImageView) convertView.findViewById(R.id.imgItem);
            view.llItem = (LinearLayout) convertView.findViewById(R.id.llItem);
            convertView.setTag(view);
        }
        else {
            // set again this tag
            view = (ViewHolder) convertView.getTag();
        }

        // data at this position
        UserItem item = listItem.get(position);

        // set data
        view.txtName.setText(item.getShopItemObj().getName());
        view.txtDescription.setText(item.getShopItemObj().getDescription());
        view.imgItem.setVisibility(View.GONE);
        view.llItem.setBackground(Setting.GetBackgroundImg(context, item.getShopItemObj().getBackgroundIMG()+".png"));
        view.llItem.getLayoutParams().height = 300;

        if (item.getShopItemObj().getBackgroundIMG() == Setting.UserData.getBackgroundIMG())
        {
            view.txtPrice.setText(R.string.current_background);
            view.txtPrice.setVisibility(View.VISIBLE);
        }
        else
        {
            view.txtPrice.setVisibility(View.GONE);
        }

        return convertView;
    }
}
