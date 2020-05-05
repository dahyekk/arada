package com.example.arada.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class MemoDAO {
    DBHelper dbHelper = null;
    String sql;
    public ArrayList<HashMap<String, String>> selectAll(Context context) {

        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

        dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();   //데이터 베이스 연결
        String sql = "select _no, _date, record from schlist order by _no ";
        Cursor cursor = db.rawQuery(sql, null); //sql 구문 실행
        while (cursor.moveToNext()) {                      //결과(Resultset)를 list에 담기
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("_no", cursor.getString(0));
            map.put("_date", cursor.getString(1));
            map.put("record", cursor.getString(2));
            list.add(map);
        }
        dbHelper.close();
        return list;
    }
}
