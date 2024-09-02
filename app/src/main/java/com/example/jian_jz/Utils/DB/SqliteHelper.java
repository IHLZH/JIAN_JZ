package com.example.jian_jz.Utils.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SqliteHelper extends SQLiteOpenHelper {
    public SqliteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //账单表
        String billTable = "create table tb_bill(" +
                "id integer primary key autoincrement," +
                "userId varchar(20)," +
                "time varchar(20) not null," +
                "cost varchar(20) not null," +
                "sortName varchar(20) not null," +
                "sortImg integer not null," +
                "income tinyint not null);";
        db.execSQL(billTable);

        //用户表
        String userTable = "create table tb_user(" +
                "userId varchar(20) primary key," +
                "pwd varchar(20) not null," +
                "avater integer," +
                "nickname varchar(20));";
        db.execSQL(userTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
