package com.sethphat.gigapet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sethphat.gigapet.Adapter.ItemAdapter;
import com.sethphat.gigapet.Common.DBAccess;
import com.sethphat.gigapet.Configs.Setting;
import com.sethphat.gigapet.Models.Category;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity {

    TextView tvMoney;
    private ListView lvItemCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories_page_layout);


        lvItemCategories = (ListView) findViewById(R.id.lvItemCategories);

        tvMoney = (TextView) findViewById(R.id.tvMoney);
        tvMoney.setText(Setting.DefaultGold + " ");


        ArrayList<String> test = new ArrayList<>();
        final String item_a = "a";
        final String item_b = "b";
        test.add(item_a);
        test.add(item_b);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, test);

        adapter.notifyDataSetChanged();
        lvItemCategories.setAdapter(adapter);

        lvItemCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = "";
                if (position == 0) {
                    name = item_a;
                } else {
                    name = item_b;
                }
                Toast.makeText(CategoriesActivity.this, name, Toast.LENGTH_SHORT).show();
            }
        });

    }


    /**
     * Back screen
     *
     * @param view
     */
    public void backMain(View view) {
        onBackPressed();
    }

}
