package com.example.lab4_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ProgressBar pbFirst, pbSecond;
    private TextView tvMsgWorking, tvMsgReturned;
    private boolean isRunning;
    private int MAX_SEC;
    private int intTest;
    private Thread bgThread;
    //có thể lỗi ở đây
    private Handler handler;
    //
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewByIds();
        initVariable();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRunning= true;
                pbFirst.setVisibility(View.VISIBLE);
                pbSecond.setVisibility(View.GONE);
                btnStart.setVisibility(View.GONE);
                bgThread.start();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        initBgThread();
    }
    @Override
    protected void onStop() {
        super.onStop();
        isRunning = false;
    }

    private void findViewByIds() {
        pbFirst = (ProgressBar) findViewById(R.id.pb_first);
        pbSecond = (ProgressBar) findViewById(R.id.pb_second);
        tvMsgWorking = (TextView) findViewById(R.id.tv_working);
        tvMsgReturned = (TextView) findViewById(R.id.tv_return);
        btnStart = (Button) findViewById(R.id.btn_start);
    }

    private void initVariable() {
        isRunning = false;
        MAX_SEC = 20;
        intTest = 1;
        pbFirst.setVisibility(View.GONE);
        pbSecond.setVisibility(View.GONE);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                String returnValue = (String) msg.obj;

                tvMsgReturned.setText(getString(R.string.returned_by_bg_thread) + returnValue);
                pbFirst.incrementProgressBy(2);

                //kiểm tra nếu luồng có dừng không
                if (pbFirst.getProgress() == MAX_SEC) {
                    tvMsgReturned.setText(getString(R.string.done_background_thread_has_been_stopped));
                    tvMsgWorking.setText(getString(R.string.done));
                    pbFirst.setVisibility(View.GONE);
                    pbSecond.setVisibility(View.GONE);
                    btnStart.setVisibility(View.VISIBLE);
                    isRunning = false;
                } else {
                    tvMsgWorking.setText(getString(R.string.working) + pbFirst.getProgress());
                }
            }
        };
    }

    private void initBgThread() {
        bgThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < MAX_SEC && isRunning; i++) {
                        Thread.sleep(1000);

                        Random rnd = new Random();
                        String data = "Thread value: " + (int) rnd.nextInt(101);
                        //thay đổi biến toàn cục
                        data += getString(R.string.global_value_seen) + " " + intTest;
                        intTest++;
                        if (isRunning) {
                            Message msg = handler.obtainMessage(1, (String) data);
                            handler.sendMessage(msg);
                        }
                    }
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

}


