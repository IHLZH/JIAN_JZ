package com.example.jian_jz.Base;

// This code is part of an Android application using ViewBinding and AppCompatActivity
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

public abstract class BaseActivity<VB extends ViewBinding> extends AppCompatActivity {

    protected VB binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        if (parameterizedType != null) {
            Class<VB> bindingClass = (Class<VB>) parameterizedType.getActualTypeArguments()[0];
            try {
                Method inflateMethod = bindingClass.getMethod("inflate", LayoutInflater.class);
                binding = (VB) inflateMethod.invoke(null, getLayoutInflater());
                setContentView(binding.getRoot());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        onCreated(savedInstanceState);
    }

    protected abstract void onCreated(Bundle savedInstanceState);
}