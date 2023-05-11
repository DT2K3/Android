
package com.example.bt2104;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.util.ArrayList;

public class MySMSReceiver extends BroadcastReceiver {

    public static ArrayList<String> vipMessages = new ArrayList<>();
    public static ArrayList<String> promotionMessages = new ArrayList<>();
    public static ArrayList<String> normalMessages = new ArrayList<>();
    private static SmsListener smsListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        processSms(context, intent);
    }

    public static void setSmsListener(SmsListener listener) {
        smsListener = listener;
    }

    private void processSms(Context context, Intent intent) {
        Bundle myBundle = intent.getExtras();
        String message = "";
        String Body = "";
        String SĐT = "";
        if (myBundle != null) {
            Object[] mySMS = (Object[]) myBundle.get("pdus");
            for (int i = 0; i < mySMS.length; i++) {
                SmsMessage sms;
                sms = SmsMessage.createFromPdu((byte[]) mySMS[i], "3gpp2");
                Body += sms.getMessageBody();
                SĐT = sms.getOriginatingAddress();
            }
            message = "Có một tin nhắn từ " + SĐT + "\n" + Body + "được gửi đến";
            // Phân loại tin nhắn theo từ khóa
            if (Body.contains("VIP")) {
                Toast.makeText(context, "Tin nhắn VIP: " + message, Toast.LENGTH_LONG).show();
                vipMessages.add(Body);
            } else if (Body.contains("Khuyến mãi")) {
                Toast.makeText(context, "Tin nhắn khuyến mãi: " + message, Toast.LENGTH_LONG).show();
                promotionMessages.add(Body);
            } else {
                normalMessages.add(Body);
                Toast.makeText(context, "Tin nhắn bình thường: " + message, Toast.LENGTH_LONG).show();
            }
        }
        // Sau khi xử lý tin nhắn, gọi phương thức onSmsReceived của SmsListener
        if (smsListener != null) {
            smsListener.onSmsReceived();
        }
    }
}
