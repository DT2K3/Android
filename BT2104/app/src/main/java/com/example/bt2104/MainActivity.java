package com.example.bt2104;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SmsListener {
    private ArrayList<String> vipMessages = new ArrayList<>();
    private ArrayList<String> promotionMessages = new ArrayList<>();
    private ArrayList<String> normalMessages = new ArrayList<>();
    private ListView mListView1;
    private ListView ListView2;
    private ListView ListView3;
    private ArrayAdapter<String> adapter1;
    private ArrayAdapter<String> adapter2;
    private ArrayAdapter<String> adapter3;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView1 = findViewById(R.id.vip_list_view);
        ListView2 = findViewById(R.id.normal_list_view);
        ListView3 = findViewById(R.id.promotion_list_view);
        MySMSReceiver.setSmsListener(this);
        // kiểm tra và yêu cầu người dùng đồng ý chức năng
        if(ActivityCompat.checkSelfPermission(MainActivity.this , Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.RECEIVE_SMS},1);
            return;
        }
        MySMSReceiver.setSmsListener(this);
        // Khởi tạo ArrayAdapter và đặt nó cho ListView
        adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, MySMSReceiver.vipMessages);
        mListView1.setAdapter(adapter1);
        adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, MySMSReceiver.normalMessages);
        ListView2.setAdapter(adapter2);
        adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, MySMSReceiver.promotionMessages);
        ListView3.setAdapter(adapter3);
    }
    @Override
    public void onSmsReceived() {
        adapter1.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();
        adapter3.notifyDataSetChanged();
    }
}