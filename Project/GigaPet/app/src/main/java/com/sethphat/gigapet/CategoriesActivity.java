package com.sethphat.gigapet;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sethphat.gigapet.Adapter.ShopItemAdapter;
import com.sethphat.gigapet.Common.DBAccess;
import com.sethphat.gigapet.Configs.IntentKey;
import com.sethphat.gigapet.Configs.Setting;
import com.sethphat.gigapet.Models.Category;
import com.sethphat.gigapet.Models.ShopItem;
import com.sethphat.gigapet.Models.User;
import com.sethphat.gigapet.Models.UserItem;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity {

    private ArrayList<ShopItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories_page_layout);

        // retrieve ID
        int CategoryID = getIntent().getIntExtra(IntentKey.CATEGORY_ID, 0);
        if (CategoryID == 0)
        {
            finish();
        }

        // controls
        ListView lvItem = (ListView) findViewById(R.id.lvItem);
        final TextView tvMoney = (TextView) findViewById(R.id.tvMoney);
        TextView tvName = (TextView) findViewById(R.id.tvName);

        // set data
        tvMoney.setText(Setting.UserData.getGold() + "");

        // get category info
        Category cate = DBAccess.CateRepo.GetByID(CategoryID);
        if (cate == null)
            finish();
        tvName.setText(cate.getName());

        Log.d("CATEGORY_ID", cate.getID()+"");
        // get list
        final boolean isBackground = Setting.BACKGROUND_CATEGORY == CategoryID;
        items = DBAccess.ShopItem.GetByCategory(CategoryID, Setting.UserData.getType(), Setting.UserData.getEvolution());
        ShopItemAdapter adapter = new ShopItemAdapter(this, R.layout.shopitem_list_view, items, isBackground);
        lvItem.setAdapter(adapter);

        lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Mua hang o day
                final ShopItem buyItem = items.get(position);

                AlertDialog.Builder dialog = new AlertDialog.Builder(CategoriesActivity.this);

                dialog.setTitle(R.string.confirm_buy_title);
                dialog.setMessage(R.string.confirm_buy_body);
                dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        buy_item(buyItem);
                    }
                });
                dialog.setNegativeButton("No", null);

                dialog.show();
            }

            public void buy_item(ShopItem buyItem)
            {
                // check gold
                if (Setting.UserData.getGold() < buyItem.getPrice())
                {
                    // not enough money
                    Toast.makeText(CategoriesActivity.this, R.string.money_not_enough, Toast.LENGTH_SHORT).show();
                    return;
                }

                UserItem item_storage = DBAccess.UserItemRepo.FindItem(Setting.UserData.getID(), buyItem.getID());

                // check if this background is existed
                if (isBackground && item_storage != null)
                {
                    Toast.makeText(CategoriesActivity.this, R.string.background_existed, Toast.LENGTH_SHORT).show();
                    return;
                }

                // buy now :D
                if (item_storage == null)
                {
                    // new item
                    UserItem new_item = new UserItem(Setting.UserData.getID(), buyItem.getID(), 1);
                    DBAccess.UserItemRepo.Insert(new_item);
                }
                else {
                    // update
                    item_storage.setQuantity(item_storage.getQuantity() + 1);
                    DBAccess.UserItemRepo.Update(item_storage);
                }

                // down money
                Setting.UserData.setGold(Setting.UserData.getGold() - buyItem.getPrice());
                DBAccess.UserRepo.Update(Setting.UserData);
                tvMoney.setText(Setting.UserData.getGold() + "");
                Toast.makeText(CategoriesActivity.this, R.string.buy_ok, Toast.LENGTH_SHORT).show();
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

    @Override
    public void onBackPressed() {
        // set final
        setResult(69);
        finish();
    }
}
