package com.example.bt2104_2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Receiver3KhongQuanTrong extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("message");
        Log.d("Receiver", "Receiver3KhongQuanTrong: " + message);
    }
}