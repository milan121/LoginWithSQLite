package com.milanapp.loginwithsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper( Context context) {
        super(context,"login.db" , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("Create table user(email text primary key , password text) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists user");

    }

    public  boolean insertdata(String email,String password){

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("email",email);
        values.put("password",password);

        long ins = database.insert("user",null,values);

        if (ins==1)return false;
        else return true;

    }

    public  boolean checkmail(String email){

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select * from user where email = ?",new String[]{email});
        if (cursor.getCount()>0) return false;
        else return true;

    }
}
