package com.sethphat.gigapet;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sethphat.gigapet.Adapter.ItemAdapter;
import com.sethphat.gigapet.Common.DBAccess;
import com.sethphat.gigapet.Configs.IntentKey;
import com.sethphat.gigapet.Configs.MusicService;
import com.sethphat.gigapet.Configs.Setting;
import com.sethphat.gigapet.Models.Category;
import com.sethphat.gigapet.Models.User;
import com.sethphat.gigapet.databinding.MainGameLayoutBinding;

import java.util.ArrayList;


public class ShopPageActivity extends AppCompatActivity {

    private ListView lvItem;
    ItemAdapter adapter;
    private ImageButton btnGoCategories;
    private ArrayList<Category> listCate;
    TextView tvMoney;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_page_layout);


        lvItem = (ListView) findViewById(R.id.lvItem);
        tvMoney = (TextView) findViewById(R.id.tvMoney);


        tvMoney.setText(Setting.UserData.getGold() + "");

        // Retrieve all data
        listCate = DBAccess.CateRepo.GetAll();

        //Set adapter
        adapter = new ItemAdapter(this, this, listCate);

        lvItem.setAdapter(adapter);

        //set sự kiện khi click vào mỗi item

        lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                //catePage(view);
                Intent i = new Intent(ShopPageActivity.this, CategoriesActivity.class);
                i.putExtra(IntentKey.CATEGORY_ID, listCate.get(position).getID());
                startActivityForResult(i, 11);
            }
        });

        MusicService.PlaySong(this, 4);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        tvMoney.setText(Setting.UserData.getGold() + "");
    }

    /**
     * Back screen
     *
     * @param view
     */
    public void backMain(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        // set final
        setResult(69);
        finish();
    }
}

