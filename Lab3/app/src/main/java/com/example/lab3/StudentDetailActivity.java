package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class StudentDetailActivity extends AppCompatActivity {

    private TextView mMssvTextView;
    private TextView mNameTextView;
    private TextView mAddressTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        // Ánh xạ các TextView từ layout file
        mMssvTextView = findViewById(R.id.tv_mssv);
        mNameTextView = findViewById(R.id.tv_name);
        mAddressTextView = findViewById(R.id.tv_address);

        // Lấy dữ liệu được truyền từ Intent và hiển thị lên giao diện
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int mssv = extras.getInt("mssv");
            String name = extras.getString("name");
            String address = extras.getString("address");

            mMssvTextView.setText(Integer.toString(mssv));
            mNameTextView.setText(name);
            mAddressTextView.setText(address);
        }
    }
}
