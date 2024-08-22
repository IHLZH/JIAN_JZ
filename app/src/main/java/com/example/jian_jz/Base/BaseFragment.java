package com.example.jian_jz.Base;

// This code is related to Android framework and utilizes ViewBinding
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ContentView;
import androidx.annotation.LayoutRes;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

public abstract class BaseFragment<VB extends ViewBinding> extends Fragment {
    private VB binding = null;
    public VB getBinding() {
        return binding;
    }

    public BaseFragment() {
        super();
    }

    @ContentView
    public BaseFragment(@LayoutRes int contentLayoutId) {
        super(contentLayoutId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Using reflection to invoke the inflate method in the specified ViewBinding to fill the view
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        Class<VB> clazz = (Class<VB>) type.getActualTypeArguments()[0];
        try {
            Method method = clazz.getMethod("inflate", LayoutInflater.class, ViewGroup.class, boolean.class);
            binding = (VB) method.invoke(null, inflater, container, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}