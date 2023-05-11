package com.example.bt2_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Thumbnail> thumbnail = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner)findViewById(R.id.thumbnail);

        //khai báo apdapter
        ThumbnailAdapter adapter = new ThumbnailAdapter(this,R.layout.item_thumbnail,Thumbnail.values());

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Thumbnail thumbnail = Thumbnail.values()[position];
                ImageView imageView = view.findViewById(R.id.img_drop_drow);
                imageView.setImageResource(thumbnail.getImg());
                imageView.setVisibility(View.GONE);
                TextView textView = view.findViewById(R.id.textThumnail);
                textView.setText(thumbnail.getName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing.
            }
        });


    }
}
