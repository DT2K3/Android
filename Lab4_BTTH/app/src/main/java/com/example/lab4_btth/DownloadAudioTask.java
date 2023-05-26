package com.example.lab4_btth;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class DownloadAudioTask extends AsyncTask<String, Void, String> {
    private Context mContext;

    public DownloadAudioTask(Context context) {
        this.mContext = context;
    }

    @Override
    protected String doInBackground(String... urls) {
        String result = "";
        String url = urls[0];
        String fileName = urls[1];

        try {
            URL audioUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) audioUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            File file = new File(mContext.getExternalFilesDir(null), fileName);
            FileOutputStream outputStream = new FileOutputStream(file);

            InputStream inputStream = connection.getInputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.close();
            inputStream.close();

            result = file.getAbsolutePath();
        } catch (IOException e) {
            Log.e("DownloadAudioTask", "Error while downloading audio: " + e.getMessage());
        }
        return result;
    }
    @Override
    protected void onPostExecute(String result) {
        // Do something with the downloaded file path
    }
}
