package com.example.jian_jz.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.jian_jz.Base.BaseActivity;
import com.example.jian_jz.R;
import com.example.jian_jz.Utils.DB.DBUtil;
import com.example.jian_jz.Utils.DB.SqliteHelper;
import com.example.jian_jz.databinding.ActivityInitBinding;

public class InitActivity extends BaseActivity<ActivityInitBinding> {
    private String TAG = "InitActivity";

    @Override
    protected void onCreated(Bundle savedInstanceState) {
        initSqliteDatabase();

        getWindow().setBackgroundDrawable(null);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        jumpToLoginActivity();
        this.finish();
    }

    private void initSqliteDatabase() {
        SqliteHelper sqliteHelper = new SqliteHelper(
                this,
                "DB.db",
                null,
                1
        );

        if(DBUtil.setSqLiteDatabase(sqliteHelper.getWritableDatabase())){
            Log.i(TAG, "initSqliteDatabase: 数据库初始化成功！");
        }
    }

    private void jumpToLoginActivity() {
        Intent intent = new Intent();
        intent.setClass(this, LoginActivity.class);
        startActivity(intent);
    }
}