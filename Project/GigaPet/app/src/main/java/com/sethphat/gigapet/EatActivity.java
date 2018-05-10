package com.sethphat.gigapet;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.sethphat.gigapet.Common.DBAccess;
import com.sethphat.gigapet.Configs.Setting;
import com.sethphat.gigapet.Models.UserItem;

import java.util.ArrayList;

public class EatActivity extends AppCompatActivity {

    private ArrayList<UserItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eat_layout);

        // Get current user item
        items = DBAccess.UserItemRepo.GetAllByUserID(Setting.UserData.getID());

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

        //
    }

}
