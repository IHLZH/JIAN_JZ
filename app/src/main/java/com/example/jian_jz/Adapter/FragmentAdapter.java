package com.example.jian_jz.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class FragmentAdapter extends FragmentStateAdapter {
    private List<Fragment> fragmentList;
    public FragmentAdapter(List<Fragment> fragmentList, @NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }
}
