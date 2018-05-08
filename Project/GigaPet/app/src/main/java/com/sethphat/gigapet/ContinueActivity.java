package com.sethphat.gigapet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sethphat.gigapet.Common.DBAccess;
import com.sethphat.gigapet.Common.FontChangeCrawler;
import com.sethphat.gigapet.Configs.IntentKey;
import com.sethphat.gigapet.Configs.Setting;
import com.sethphat.gigapet.Models.User;

import java.util.ArrayList;

public class ContinueActivity extends AppCompatActivity {

    private ArrayList<User> listUser;
    private ArrayList<String> listUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.continue_layout);

        // load font
        FontChangeCrawler fontChanger = new FontChangeCrawler(getAssets(), Setting.Font_Path);
        fontChanger.replaceFonts((ViewGroup) this.findViewById(android.R.id.content));

        // load all users
        listUser = DBAccess.UserRepo.GetAll();
        listUsername = new ArrayList<>();

        for (User user : listUser)
        {
            listUsername.add(user.getPetName());
        }

        // render
        render();
    }

    private void render()
    {
        ListView lvSave = (ListView) findViewById(R.id.lvSave);

        // create arrAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listUsername);

        // set source
        lvSave.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        // EVENT
        lvSave.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int index, long id) {
                Intent i = new Intent(ContinueActivity.this, MainGameActivity.class);
                i.putExtra(IntentKey.USER_ID, listUser.get(index).getID());
                startActivity(i);
                finish();
            }
        });
    }

}
