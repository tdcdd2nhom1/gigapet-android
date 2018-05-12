package com.sethphat.gigapet;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.sethphat.gigapet.Configs.MusicService;
import com.sethphat.gigapet.Configs.SQLiteAccess;
import com.sethphat.gigapet.Configs.Setting;

import java.io.IOException;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_layout);

        MusicService.PlaySong(this, 1);
    }

    /**
     * Start new game
     * @param view
     */
    public void startGame(View view) {
        Intent i = new Intent(HomePageActivity.this, StartNewActivity.class);
        startActivity(i);
    }

    /**
     * Continue game
     * @param view
     */
    public void continueGame(View view) {
        Intent i = new Intent(HomePageActivity.this, ContinueActivity.class);
        startActivity(i);
    }

    /**
     * Show help game
     * @param view
     */
    public void showHelp(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle("Hướng dẫn về game");
        dialog.setMessage("GigaPet v" + this.getString(R.string.game_version) + "\n" +
                " - Hãy tạo cho mình một con thú cưng\n" +
                " - Hãy chú ý chăm sóc thú cưng của bạn\n" +
                " - Trong quá trình chăm sóc, thú cưng của bạn sẽ tăng tim\n" +
                " - Sau khi tăng tim nhất định, thú bạn sẽ tiến hóa\n" +
                " - ... Và rất còn nhiều điều mới mẻ, các bạn hãy tìm hiểu nhé \n\nHave fun!!");

        dialog.setPositiveButton("Ok", null);
        dialog.setNegativeButton("Cancel", null);

        dialog.show();
    }

    /**
     * Show info
     * @param view
     */
    public void showInfo(View view) {

        SQLiteAccess.CreateDummyData();

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle(R.string.intro_title);

        // string builder
        String mess = getString(R.string.introduction);
        mess = mess.replace("{version}", getString(R.string.game_version));

        dialog.setMessage(mess);

        dialog.setPositiveButton("Ok", null);

        dialog.show();
    }

    /**
     * Settings game
     * @param view
     */
    public void showSetting(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.setting);

        // view
        View v = getLayoutInflater().inflate(R.layout.dialog_setting, null);

        // find item
        RadioButton rdbOn = v.findViewById(R.id.rdbOn);
        final RadioButton rdbOff = v.findViewById(R.id.rdbOff);

        // check current status
        if (Setting.isIsSoundOn())
            rdbOn.setChecked(true);
        else
            rdbOff.setChecked(true);

        dialog.setView(v);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                boolean isOn = true;
                if (rdbOff.isChecked())
                    isOn = false;

                Setting.setIsSoundOn(isOn);
                MusicService.TurnMusic();
            }
        });

        dialog.setNegativeButton("Cancel", null);
        dialog.show();
    }

}
