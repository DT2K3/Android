package com.example.lab3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private ArrayList<Student> mStudentList;
    private Context mContext;

    public StudentAdapter(Context context, ArrayList<Student> studentList) {
        mContext = context;
        mStudentList = studentList;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item, parent, false);
        return new StudentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        final Student currentStudent = mStudentList.get(position);
        holder.nameTextView.setText(currentStudent.getName());
        holder.mssvTextView.setText(String.valueOf(currentStudent.getMssv()));
        holder.addressTextView.setText(currentStudent.getAddress());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, StudentDetailActivity.class);
                intent.putExtra("mssv", currentStudent.getMssv());
                intent.putExtra("name", currentStudent.getName());
                intent.putExtra("address", currentStudent.getAddress());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mStudentList.size();
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public TextView mssvTextView;
        public TextView addressTextView;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.tv_name);
            mssvTextView = itemView.findViewById(R.id.tv_MSSV);
            addressTextView = itemView.findViewById(R.id.tv_address);
        }
    }
}
