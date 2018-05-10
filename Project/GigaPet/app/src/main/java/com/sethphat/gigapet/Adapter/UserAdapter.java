package com.sethphat.gigapet.Adapter;

import android.app.Activity;
import android.content.Context;
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
import com.sethphat.gigapet.Models.User;
import com.sethphat.gigapet.R;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {
    Activity context;
    ArrayList<User> listUser;
    int layout;

    public UserAdapter(@NonNull Activity context, int resource, @NonNull ArrayList<User> objects) {
        super(context, resource, objects);

        this.context = context;
        this.listUser = objects;
        this.layout = resource;
    }

    static class ViewHolder{
        ImageView imgPet;
        TextView txtPetName;
        TextView txtDate;
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
            view.txtDate = (TextView) convertView.findViewById(R.id.txtDate);
            view.txtPetName = (TextView) convertView.findViewById(R.id.txtPetName);
            view.imgPet = (ImageView) convertView.findViewById(R.id.imgPet);
            convertView.setTag(view);
        }
        else {
            // set again this tag
            view = (ViewHolder) convertView.getTag();
        }

        // data at this position
        User user = listUser.get(position);

        // set data
        view.txtPetName.setText(user.getPetName());
        view.txtDate.setText(context.getString(R.string.last_time) + HelperFunction.date(Setting.DATE_FORMAT, user.getLastTime()));
        view.imgPet.setImageDrawable(Setting.PetImage(context, user.getType(), user.getEvolution(), user.getPetSkin()));

        return convertView;
    }
}
