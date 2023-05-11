package com.example.bt2_2;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ThumbnailAdapter extends ArrayAdapter<Thumbnail> {

    private Context context;
    private int resource;
    private Thumbnail[] thumbnails;

    public ThumbnailAdapter(Context context, int resource, Thumbnail[] objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.thumbnails = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(resource, parent, false);
        }

        Thumbnail thumbnail = thumbnails[position];
        ImageView thumbnailImage = view.findViewById(R.id.img_drop_drow);
        thumbnailImage.setImageResource(thumbnail.getImg());
        TextView thumbnailText = view.findViewById(R.id.textThumnail);
        thumbnailText.setText(thumbnail.getName());
        // create dialog and set values
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.item_thumbnail);
        ImageView thumbnailImageDialog = dialog.findViewById(R.id.img_drop_drow);
        TextView thumbnailTextDialog = dialog.findViewById(R.id.textThumnail);
        thumbnailImageDialog.setImageResource(thumbnail.getImg());
        thumbnailTextDialog.setText(thumbnail.getName());

        // show dialog when item is clicked
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_thumbnail, parent, false);
        }

        Thumbnail thumbnail = thumbnails[position];
        ImageView thumbnailImage = view.findViewById(R.id.img_drop_drow);
        thumbnailImage.setImageResource(thumbnail.getImg());
        TextView thumbnailText = view.findViewById(R.id.textThumnail);
        thumbnailText.setText(thumbnail.getName());

        return view;
    }
}