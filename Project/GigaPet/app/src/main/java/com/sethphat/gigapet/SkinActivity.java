package com.sethphat.gigapet;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.sethphat.gigapet.Adapter.UserItemAdapter;
import com.sethphat.gigapet.Common.DBAccess;
import com.sethphat.gigapet.Configs.Setting;
import com.sethphat.gigapet.Models.ShopItem;
import com.sethphat.gigapet.Models.User;
import com.sethphat.gigapet.Models.UserItem;

import java.util.ArrayList;
import java.util.Iterator;

public class SkinActivity extends AppCompatActivity {

    private ArrayList<UserItem> items;
    private UserItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skin_layout);

        // Get current user item
        items = DBAccess.UserItemRepo.GetAllByUserID(Setting.UserData.getID());
        initDefaultSkin();
        Iterator<UserItem> iter = items.iterator();

        // filter for Skin only
        while (iter.hasNext())
        {
            UserItem item = iter.next();
            if (item.getShopItemObj() != null && item.getShopItemObj().getEvolution() == Setting.UserData.getEvolution())
                continue;

            // get shop item
            ShopItem shopItem = DBAccess.ShopItem.GetByID(item.getShopItemID());

            if (shopItem == null || shopItem.getCategoryID() != Setting.SKIN_CATEGORY)
            {
                iter.remove();
                continue;
            }

            if (shopItem.getEvolution() != Setting.UserData.getEvolution())
            {
                iter.remove();
                continue;
            }

            item.setShopItemObj(shopItem);
        }

        // init
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
        adapter = new UserItemAdapter(this, R.layout.shopitem_list_view, items, Setting.SKIN_CATEGORY);
        lvItem.setAdapter(adapter);

        // using item
        lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                // Ask permission
                AlertDialog.Builder dialog = new AlertDialog.Builder(SkinActivity.this);

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

        // no decrease anything

        // change pet skin
        Setting.UserData.setPetSkin(item.getShopItemObj().getBackgroundIMG());
        DBAccess.UserRepo.Update(Setting.UserData);
        adapter.notifyDataSetChanged();

        // Mess
        Toast.makeText(this, R.string.skin_success, Toast.LENGTH_SHORT).show();
    }

    private void initDefaultSkin()
    {
        ShopItem default_level1 = new ShopItem(0, Setting.SKIN_CATEGORY, "Default Evolution 1", "Default skin in evolution 1", 0, 0, Setting.UserData.getType(), 1, 0);
        ShopItem default_level2 = new ShopItem(0, Setting.SKIN_CATEGORY, "Default Evolution 2", "Default skin in evolution 1", 0, 0, Setting.UserData.getType(), 2, 0);
        ShopItem default_level3 = new ShopItem(0, Setting.SKIN_CATEGORY, "Default Evolution 3", "Default skin in evolution 1", 0, 0, Setting.UserData.getType(), 3, 0);

        if (Setting.UserData.getEvolution() <= 1)
            items.add(new UserItem(Setting.UserData.getID(), 0,0, default_level1));
        if (Setting.UserData.getEvolution() <= 2)
            items.add(new UserItem(Setting.UserData.getID(), 0,0, default_level2));
        if (Setting.UserData.getEvolution() <= 3)
            items.add(new UserItem(Setting.UserData.getID(), 0,0, default_level3));
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
