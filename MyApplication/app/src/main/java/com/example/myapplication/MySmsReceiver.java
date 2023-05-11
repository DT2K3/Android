package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class MySmsReceiver extends BroadcastReceiver {
    private static final String APP_NUMBER = "0987654321";
    private static final String APP_NAME = "MyApp";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                if (pdus != null) {
                    for (Object pdu : pdus) {
                        SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
                        String sender = smsMessage.getDisplayOriginatingAddress();
                        String message = smsMessage.getDisplayMessageBody();
                        if (sender.equals(APP_NUMBER)) {
                            // Phân nhóm tin nhắn vào MyApp
                            // Xử lý tin nhắn ở đây
                        }
                    }
                }
            }
        }
    }
}

