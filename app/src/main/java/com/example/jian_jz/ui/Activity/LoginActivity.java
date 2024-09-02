package com.example.jian_jz.ui.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.jian_jz.Base.BaseActivity;
import com.example.jian_jz.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity<ActivityLoginBinding> {

    @Override
    protected void onCreated(Bundle savedInstanceState) {
        //控件绑定监听器
        setLinsteners();
    }

    private void jumpToMainActivity() {
        Intent intent = new Intent();
        intent.setClass(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void setLinsteners() {
        //跳转注册页面
        binding.tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToMainActivity();
            }
        });


    }
}