package com.example.jian_jz.Utils.DB;

import android.database.sqlite.SQLiteDatabase;

public class DBUtil {
    private static SQLiteDatabase sqLiteDatabase;

    public static SQLiteDatabase getSqLiteDatabase() {
        return sqLiteDatabase;
    }

    public static boolean setSqLiteDatabase(SQLiteDatabase sqLiteDatabase) {
        DBUtil.sqLiteDatabase = sqLiteDatabase;
        if(DBUtil.sqLiteDatabase != null)return true;
        else return false;
    }
}
