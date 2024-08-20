package com.example.jian_jz.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.jian_jz.Adapter.FragmentAdapter;
import com.example.jian_jz.Fragment.ChartFragment;
import com.example.jian_jz.Fragment.LedgerFragment;
import com.example.jian_jz.Fragment.MineFragment;
import com.example.jian_jz.Fragment.NoteFragment;
import com.example.jian_jz.R;
import com.example.jian_jz.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initFragment();
        initTabLayout();
    }

    private void initTabLayout() {
        List<String> tabNameList = new ArrayList<>();
        tabNameList.add("账单");
        tabNameList.add("图表");
        tabNameList.add("手账");
        tabNameList.add("我的");


        List<Integer> tabImageList = new ArrayList<>();
        tabImageList.add(R.mipmap.icon_bill);
        tabImageList.add(R.mipmap.icon_chart);
        tabImageList.add(R.mipmap.icon_note);
        tabImageList.add(R.mipmap.icon_mine);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                binding.tbMain,
                binding.vpMain,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(tabNameList.get(position));
                        tab.setIcon(tabImageList.get(position));
                    }
                }
        );


        binding.tbMain.setTabTextColors(R.color.app_color,R.color.normal);
        binding.tbMain.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position) {
                    case 0:
                        tab.setIcon(R.mipmap.icon_bill_click);
                        break;
                    case 1:
                        tab.setIcon(R.mipmap.icon_chart_click);
                        break;
                    case 2:
                        tab.setIcon(R.mipmap.icon_note_click);
                        break;
                    case 3:
                        tab.setIcon(R.mipmap.icon_mine_click);
                        break;
                }
            }

                @Override
            public void onTabUnselected (TabLayout.Tab tab){
                int position = tab.getPosition();
                switch (position) {
                    case 0:
                        tab.setIcon(R.mipmap.icon_bill);
                        break;
                    case 1:
                        tab.setIcon(R.mipmap.icon_chart);
                        break;
                    case 2:
                        tab.setIcon(R.mipmap.icon_note);
                        break;
                    case 3:
                        tab.setIcon(R.mipmap.icon_mine);
                        break;
                }
            }

            @Override
            public void onTabReselected (TabLayout.Tab tab){

            }
        });

        tabLayoutMediator.attach();
    }

    private void initFragment() {
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new LedgerFragment());
        fragmentList.add(new ChartFragment());
        fragmentList.add(new NoteFragment());
        fragmentList.add(new MineFragment());

        FragmentAdapter fragmentAdapter = new FragmentAdapter(
                fragmentList, this
        );

        binding.vpMain.setAdapter(fragmentAdapter);
    }
}