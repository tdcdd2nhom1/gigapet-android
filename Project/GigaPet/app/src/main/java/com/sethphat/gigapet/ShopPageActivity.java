package com.sethphat.gigapet;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.sethphat.gigapet.Adapter.ItemAdapter;
import com.sethphat.gigapet.Models.Category;
import com.sethphat.gigapet.SQLHelper.CategoryHelper;

import java.util.ArrayList;


public class ShopPageActivity extends AppCompatActivity {

    private ListView lvItem;
    ItemAdapter adapter;
    CategoryHelper categoryHelper;
    private ImageButton btnGoCategories;
    private ArrayList<Category> listCate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_page_layout);


        btnGoCategories = (ImageButton) findViewById(R.id.btnGoCategories);
        lvItem = (ListView) findViewById(R.id.lvItem);


        categoryHelper = new CategoryHelper(this);

        listCate = categoryHelper.GetAll();

        adapter = new ItemAdapter(this, listCate); //khởi tạo adapter
        lvItem.setAdapter(adapter); //hiển thị lên listview

        //set sự kiện khi click vào mỗi item

        lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                //catePage(view);
                Toast.makeText(ShopPageActivity.this, "Vị trí: " + position, Toast.LENGTH_LONG).show();

            }
        });




    }

    /**
     * Go to Categories page
     *
     * @param view
     */
    public void catePage(View view) {
        Intent i = new Intent(ShopPageActivity.this, CategoriesActivity.class);
        startActivity(i);
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

