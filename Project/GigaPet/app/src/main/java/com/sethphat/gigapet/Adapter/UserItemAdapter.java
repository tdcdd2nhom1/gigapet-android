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
import com.sethphat.gigapet.Models.UserItem;
import com.sethphat.gigapet.R;

import java.util.ArrayList;

public class UserItemAdapter extends ArrayAdapter<UserItem> {
    Activity context;
    ArrayList<UserItem> listItem;
    int layout;
    int CateID;

    public UserItemAdapter(@NonNull Activity context, int resource, @NonNull ArrayList<UserItem> objects, int CategoryID) {
        super(context, resource, objects);

        this.context = context;
        this.listItem = objects;
        this.layout = resource;
        this.CateID = CategoryID;
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
        view.txtPrice.setText(item.getQuantity() + "");

        if (this.CateID == Setting.SKIN_CATEGORY)
        {
            if (Setting.UserData.getPetSkin() == item.getShopItemObj().getBackgroundIMG())
                view.txtPrice.setText(R.string.current_skin);
            else
                view.txtPrice.setText("");

            view.imgItem.setImageDrawable(Setting.PetImage(context, item.getShopItemObj().getTypePet(), item.getShopItemObj().getEvolution(), item.getShopItemObj().getBackgroundIMG()));
        }
        else {
            view.imgItem.setImageDrawable(Setting.GetImageShopItem(context, item.getShopItemObj().getImage()));
        }


        return convertView;
    }
}
