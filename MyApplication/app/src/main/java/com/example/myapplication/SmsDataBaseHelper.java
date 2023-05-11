package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SmsDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Sms.db";

    public SmsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_SMS_TABLE = "CREATE TABLE " + "SmsContract.SmsEntry.TABLE_NAME" + " (" +
                SmsContract.SmsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                SmsContract.SmsEntry.COLUMN_SENDER + " TEXT NOT NULL, " +
                SmsContract.SmsEntry.COLUMN_MESSAGE + " TEXT NOT NULL, " +
                SmsContract.SmsEntry.COLUMN_GROUP + " TEXT NOT NULL);";
        db.execSQL(SQL_CREATE_SMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SmsContract.SmsEntry.TABLE_NAME);
        onCreate(db);
    }
}

