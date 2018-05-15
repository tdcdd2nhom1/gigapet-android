package com.sethphat.gigapet;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.sethphat.gigapet.Adapter.UserItemAdapter;
import com.sethphat.gigapet.Common.DBAccess;
import com.sethphat.gigapet.Configs.MusicService;
import com.sethphat.gigapet.Configs.Setting;
import com.sethphat.gigapet.Models.ShopItem;
import com.sethphat.gigapet.Models.UserItem;

import java.util.ArrayList;
import java.util.Iterator;

public class EatActivity extends AppCompatActivity {

    private ArrayList<UserItem> items;
    private UserItemAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eat_layout);

        // Get current user item
        items = DBAccess.UserItemRepo.GetAllByUserID(Setting.UserData.getID());
        Iterator<UserItem> iter = items.iterator();

        // filter for Food only
        while (iter.hasNext())
        {
            UserItem item = iter.next();
            ShopItem shopItem = DBAccess.ShopItem.GetByID(item.getShopItemID());

            if (shopItem == null || shopItem.getCategoryID() != Setting.FOOD_CATEGORY)
            {
                iter.remove();
                continue;
            }

            item.setShopItemObj(shopItem);
        }

        // init
        MusicService.PlaySong(this, 3);
        render();
    }

    /**
     * Render this page
     */
    private void render()
    {
        // get control(s)
        ListView lvItem = (ListView) findViewById(R.id.lvItem);

        // adapter
        adapter = new UserItemAdapter(this, R.layout.shopitem_list_view, items, Setting.FOOD_CATEGORY);
        lvItem.setAdapter(adapter);

        // using item
        lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (items.get(position).getQuantity() <= 0)
                {
                    Toast.makeText(EatActivity.this, R.string.item_out, Toast.LENGTH_SHORT).show();
                    return;
                }

                // Ask permission
                AlertDialog.Builder dialog = new AlertDialog.Builder(EatActivity.this);

                dialog.setTitle(R.string.confirm_use_title);
                dialog.setMessage(R.string.confirm_use_body);
                dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        useItem(position);
                    }
                });
                dialog.setNegativeButton("No", null);

                dialog.show();
            }
        });

    }

    private void useItem(int index)
    {
        // use this item
        UserItem item = items.get(index);

        // decrease
        item.setQuantity(item.getQuantity() - 1);
        DBAccess.UserItemRepo.Update(item);

        // increase stats if exists
        Setting.UserData.setHunger(Setting.UserData.getHunger() + item.getShopItemObj().getRecover());
        DBAccess.UserRepo.Update(Setting.UserData);
        adapter.notifyDataSetChanged();

        // Mess
        Toast.makeText(this, R.string.eat_success, Toast.LENGTH_SHORT).show();
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
