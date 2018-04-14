package com.example.sen.shop;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sen.shop.Adapter.ItemAdapter;
import com.example.sen.shop.models.itemManager;
import com.example.sen.shop.models.itemShop;
import com.example.sen.shop.repository.ItemRepository;

public class MainActivity extends AppCompatActivity {

    private ListView lvItem;
    ItemAdapter adapter;
    itemManager itemManager;
    ItemRepository itemRepository;
    private ImageButton btnGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGo = (ImageButton) findViewById(R.id.btnGo);
        lvItem = (ListView) findViewById(R.id.lvItem);

        //Khởi tạo các sản phẩm
        //lấy sản phẩm từ database
        itemRepository = new ItemRepository(this);
        //Add item
        //addFood();
        //deleteFoodbyID();

        if (itemRepository.getAllItem().size() > 0)//nếu đã có
            //lưu lấy ra mảng
            itemManager.get().setProducts(itemRepository.getAllItem());
        else {//nếu chưa có trong database
            //dùng hàm tạo sẳn để test
            try {
                itemManager.generateProducts();
            } catch (NullPointerException Exception) {
                Toast.makeText(this, "Lỗi: " + Exception, Toast.LENGTH_LONG).show();
            }
        }

        adapter = new ItemAdapter(this, itemManager.get().getProducts());//khởi tạo adapter
        lvItem.setAdapter(adapter);//hiển thị lên listview
        //set sự kiện khi click vào mỗi item
        lvItem.setOnItemClickListener(new ItemClickListener());

        try{
            btnGo.setOnClickListener(new ButtonClickListener());
        }catch (Exception e){
            Toast.makeText(this, "Lỗi: " + e, Toast.LENGTH_LONG).show();
        }



    }

    class ItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // Đi đến màn hình Categories

            Toast.makeText(MainActivity.this, "dfsg", Toast.LENGTH_LONG).show();
        }
    }



    class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this, "dfsg", Toast.LENGTH_LONG).show();
        }
    }


    //Hàm tự động gọi khi trở lại activity này
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //cập nhật lại listview
        if (resultCode == Activity.RESULT_OK) {
            adapter.notifyDataSetChanged();
        }

    }

    public void addFood() {
        String itemName = "Food";
        itemRepository.addItem(new itemShop(itemName, R.mipmap.food));
    }

    public void deleteFoodbyID() {
        itemRepository.deleteItembyID(1);
    }

    public void getID() {
        itemRepository.getID();
    }

    public void getAllItem() {
        itemRepository.getAllItem();
    }


}
