package com.example.arada.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION=1;
    public DBHelper(Context context){
        super(context, "record.db", null, DATABASE_VERSION);
    }
    SQLiteDatabase db;
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createrecord = "CREATE TABLE IF NOT EXISTS  recordlist(" +
                "_NO"           + " INTEGER primary key autoincrement," +
                "_date"        + " TEXT," +
                "record"       + " TEXT" + ")" ;

        db.execSQL(createrecord);
        String createSch = "CREATE TABLE IF NOT EXISTS  schlist(" +
                "_NO"           + " INTEGER primary key autoincrement," +
                "_date"        + " TEXT," +
                "record"       + " TEXT" + ")" ;
        db.execSQL(createSch);
        String createDday = "CREATE TABLE IF NOT EXISTS  dday(" +
                "_NO"           + " INTEGER primary key autoincrement," +
                "_date"        + " TEXT," +
                "record"       + " TEXT" + ")" ;
        db.execSQL(createDday);
        db.execSQL("insert into recordlist (_date,record) values ('2020.05.02','00:48:34')");
        db.execSQL("insert into recordlist (_date,record) values ('2020.05.03','02:13:27')");
        db.execSQL("insert into recordlist (_date,record) values ('2020.05.04','01:37:40')");
        db.execSQL("insert into schlist (_date,record) values ('2020.05.06','프로젝트발표')");
    }
    public void CR(){



    }
    public void CS(){


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion != DATABASE_VERSION){
            db.execSQL("drop table recordlist");
            db.execSQL("drop table schlist");
            onCreate(db);
        }
    }
}