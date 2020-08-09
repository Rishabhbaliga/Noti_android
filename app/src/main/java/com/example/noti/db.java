package com.example.noti;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class db extends SQLiteOpenHelper {
    public static String DB_NAME = "todolist";
    public db(MainActivity context) {
        super(context, DB_NAME, null, 1);
    }
    public db(details context) {
        super(context, DB_NAME, null, 1);
    }
    public db(view context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table entry(title varchar(50), content varchar(1000))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}