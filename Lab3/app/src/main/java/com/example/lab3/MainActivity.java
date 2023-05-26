package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etMssv, etAddress;
    private Button btnAdd, btnDelete;
    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;
    private ArrayList<Student> studentList;
    private DataHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize views
        etName = findViewById(R.id.et_name);
        etMssv = findViewById(R.id.et_mssv);
        etAddress = findViewById(R.id.et_address);
        btnAdd = findViewById(R.id.btn_add);
        btnDelete = findViewById(R.id.btn_delete);
        recyclerView = findViewById(R.id.rv_students);

        // initialize DataHelper object
        dbHelper = new DataHelper(this);

        // initialize student list from database
        studentList = new ArrayList<>();
        Cursor cursor = dbHelper.getData();
        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            int mssv = cursor.getInt(0);
            String address = cursor.getString(2);
            Student student = new Student(name, mssv, address);
            studentList.add(student);
        }
        cursor.close();

        // set up adapter and layout manager for RecyclerView
        studentAdapter = new StudentAdapter(this, studentList);
        recyclerView.setAdapter(studentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // set up click listeners for buttons
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                int mssv = Integer.parseInt(etMssv.getText().toString());
                String address = etAddress.getText().toString();

                // insert new student record into database
                boolean inserted = dbHelper.insertuserdata(mssv, name, address);

                if (inserted) {
                    Student student = new Student(name, mssv, address);
                    studentList.add(student);
                    studentAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Student added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to add student", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (studentList.isEmpty()) {
                    Toast.makeText(MainActivity.this, "No students to delete", Toast.LENGTH_SHORT).show();
                } else {
                    // delete all student records from database
                    int deleted = dbHelper.deleteAllRecords();

                    if (deleted > 0) {
                        studentList.clear();
                        studentAdapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "All students deleted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Failed to delete students", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.close();
    }
}
