package com.sethphat.gigapet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sethphat.gigapet.Adapter.BackgroundAdapter;
import com.sethphat.gigapet.Common.DBAccess;
import com.sethphat.gigapet.Common.HelperFunction;
import com.sethphat.gigapet.Configs.MusicService;
import com.sethphat.gigapet.Configs.Setting;
import com.sethphat.gigapet.Models.ShopItem;
import com.sethphat.gigapet.Models.UserItem;

import java.util.ArrayList;
import java.util.Iterator;

public class BackgroundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.background_layout);

        // controls
        ListView lvItem = (ListView) findViewById(R.id.lvItem);

        // get all user backgrounds
        final ArrayList<UserItem> allBackgrounds = DBAccess.UserItemRepo.GetAllByUserID(Setting.UserData.getID());
        allBackgrounds.add(0, HelperFunction.GetDefaultBackground(Setting.UserData.getID()));

        Iterator<UserItem> iter = allBackgrounds.iterator();

        // filter for Background only
        while (iter.hasNext())
        {
            UserItem item = iter.next();
            if (item.getShopItemID() == 0)
                continue;

            ShopItem shopItem = DBAccess.ShopItem.GetByID(item.getShopItemID());

            if (shopItem == null || shopItem.getCategoryID() != Setting.BACKGROUND_CATEGORY)
            {
                iter.remove();
                continue;
            }

            item.setShopItemObj(shopItem);
        }

        // create background adapter
        final BackgroundAdapter adapter = new BackgroundAdapter(this, R.layout.shopitem_list_view, allBackgrounds);
        lvItem.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        // change background
        lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserItem item = allBackgrounds.get(position);

                // current can't be change
                if (item.getShopItemObj().getBackgroundIMG() == Setting.UserData.getBackgroundIMG())
                    return;

                // change now
                Setting.UserData.setBackgroundIMG(item.getShopItemObj().getBackgroundIMG());

                // Update user
                DBAccess.UserRepo.Update(Setting.UserData);

                // notify data set
                adapter.notifyDataSetChanged();
            }
        });

        MusicService.PlaySong(this, 3);
    }

    public void backToPrevious(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        // set final
        setResult(69);
        finish();
    }
}
