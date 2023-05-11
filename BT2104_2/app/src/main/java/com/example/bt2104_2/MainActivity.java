package com.example.bt2104_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Gửi một Broadcast thứ tự
        Intent intent = new Intent("com.example.bt2104_2");
        intent.putExtra("message", "Broadcast message");
        sendOrderedBroadcast(intent, null);
    }
}
