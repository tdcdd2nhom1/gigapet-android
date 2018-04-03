package com.sethphat.gigapet;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.sethphat.gigapet.Configs.Setting;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_layout);
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
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle("Giới thiệu về game");
        dialog.setMessage("GigaPet v" + this.getString(R.string.game_version) + "\nMột sản phẩm được làm bởi nhóm 1, thành viên:\n" +
                " - Trần Minh Phát - Leader\n" +
                " - Lê Bảo Long - Tester\n" +
                " - Nguyễn Thị Thanh Hương - Graphic reviewer/Tester\n" +
                " - Nguyễn Hoàng Vũ - Graphic designer\n" +
                " - Nguyễn Cao Thọ - Tester\n\n" +
                "Mọi chi tiết, thắc mắc xin liên hệ với Mr.Phát qua:\n" +
                " - Email: phatTranMinh96@gmail.com\n" +
                " - Phone: 0937348373\n\n" +
                "Have fun!");

        dialog.setPositiveButton("Ok", null);
        dialog.setNegativeButton("Cancel", null);

        dialog.show();
    }

    /**
     * Settings game
     * @param view
     */
    public void showSetting(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Cài đặt");

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
            }
        });

        dialog.setNegativeButton("Cancel", null);
        dialog.show();
    }
}
