package com.example.sen.shop;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sen on 08-Apr-18.
 */

public class itemAdapter extends BaseAdapter {

    private List<itemShop> listContact;
    private Activity activity;

    public itemAdapter(List<itemShop> listContact, Activity activity) {
        this.listContact = listContact;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return listContact.size();
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
        if (convertView == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            convertView = inflater.inflate(R.layout.layout_shopitem, parent, false);
            ViewHolder holder = new ViewHolder();
            holder.tvName = (TextView) convertView.findViewById(R.id.tvname);
            holder.imgView = (ImageView) convertView.findViewById(R.id.imgIcon);
            convertView.setTag(holder);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        itemShop model = listContact.get(position);
        holder.tvName.setText(model.getItemName());
        holder.imgView.setImageResource(model.getIconShop());

        return convertView;
    }

    //Tạo một lần duy nhất để tránh việc tạo nhiều lần làm chậm ứng dụng
    static class ViewHolder {
        TextView tvName;
        ImageView imgView;
    }
}
