package com.example.lab3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelper extends SQLiteOpenHelper {
    public DataHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("Create Table Userdetail (mssv TEXT Primary Key, name TEXT, address TEXT)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("Drop Table if exists Userdetail");
    }

    public boolean insertuserdata(int mssv, String name, String address){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mssv",mssv);
        contentValues.put("name",name);
        contentValues.put("address",address);
        long insert = DB.insert("Userdetail", null, contentValues);
        if(insert >0){
            return true;
        }
        else return false;
    }

    public Cursor getData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM Userdetail",null);
        return cursor;
    }

    public int deleteAllRecords() {
        SQLiteDatabase db = this.getWritableDatabase();
        int deletedRows =db.delete("Userdetail", null, null);
        db.close();
        return deletedRows;
    }
}
