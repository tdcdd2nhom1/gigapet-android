package com.example.sen.shop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvItem;
    private List<itemShop> listContact = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bước 1: Tạo data
        initData();

        //Bước 2: Tạo adapter
        itemAdapter adapter = new itemAdapter(listContact, this);

        //Bước 3: Tạo ListView Setadapter vào ListView
        lvItem = (ListView) findViewById(R.id.lvItem);
        lvItem.setAdapter(adapter);

        lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemShop itemModel = listContact.get(position);
                Toast.makeText(MainActivity.this, itemModel.getItemName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initData() {
        listContact.add(new itemShop("Food", R.mipmap.foodtruck));
        listContact.add(new itemShop("Water", R.mipmap.icecream));
        listContact.add(new itemShop("Quần áo", R.mipmap.ic_launcher));
        listContact.add(new itemShop("Giày dép", R.mipmap.piggybank));

    }
}
