package com.sethphat.gigapet.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sethphat.gigapet.Common.HelperFunction;
import com.sethphat.gigapet.Configs.Setting;
import com.sethphat.gigapet.Models.ShopItem;
import com.sethphat.gigapet.R;

import java.util.ArrayList;

public class ShopItemAdapter extends ArrayAdapter<ShopItem> {
    Activity context;
    ArrayList<ShopItem> listItem;
    int layout;
    boolean isBackground;

    public ShopItemAdapter(@NonNull Activity context, int resource, @NonNull ArrayList<ShopItem> objects, boolean isBackground) {
        super(context, resource, objects);

        this.context = context;
        this.listItem = objects;
        this.layout = resource;
        this.isBackground = isBackground;
    }

    static class ViewHolder{
        ImageView imgItem;
        TextView txtName;
        TextView txtDescription;
        TextView txtPrice;
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
            convertView.setTag(view);
        }
        else {
            // set again this tag
            view = (ViewHolder) convertView.getTag();
        }

        // data at this position
        ShopItem item = listItem.get(position);

        // set data
        view.txtName.setText(item.getName());
        view.txtDescription.setText(item.getDescription());
        view.txtPrice.setText(item.getPrice());

        if (!isBackground)
            view.imgItem.setImageDrawable(Setting.GetImageShopItem(context, item.getImage()));
        else
            view.imgItem.setImageDrawable(Setting.GetBackgroundImg(context, item.getImage()));

        return convertView;
    }
}
