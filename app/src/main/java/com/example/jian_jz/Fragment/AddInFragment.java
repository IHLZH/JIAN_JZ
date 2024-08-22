package com.example.jian_jz.Fragment;

import android.bluetooth.le.ScanSettings;
import android.media.metrics.Event;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.jian_jz.Base.BaseFragment;
import com.example.jian_jz.Event.BtnTypeEvent;
import com.example.jian_jz.Event.IncomeEvent;
import com.example.jian_jz.Event.MessageEvent;
import com.example.jian_jz.R;
import com.example.jian_jz.databinding.FragmentAddInBinding;

import org.greenrobot.eventbus.EventBus;

public class AddInFragment extends BaseFragment<FragmentAddInBinding> {
    private String TAG = "AddInFragment";
    private FragmentAddInBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        binding = getBinding();
        setListeners();
        return view;
    }

    private void setListeners() {
        binding.btnAddGongzi.setOnClickListener(new OpenJsjListener());
        binding.btnAddJianzhi.setOnClickListener(new OpenJsjListener());
        binding.btnAddLijin.setOnClickListener(new OpenJsjListener());
        binding.btnAddLingqian.setOnClickListener(new OpenJsjListener());
        binding.btnAddLiwu.setOnClickListener(new OpenJsjListener());
        binding.btnAddQita.setOnClickListener(new OpenJsjListener());
    }

    public class OpenJsjListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            EventBus.getDefault().post(new MessageEvent("open_Jsj"));
            EventBus.getDefault().post(new IncomeEvent(true));
            switch(v.getId()){
                case R.id.btn_add_gongzi:
                    EventBus.getDefault().post(new BtnTypeEvent("工资",R.mipmap.sort_fanxian));
                    break;
                case R.id.btn_add_jianzhi:
                    EventBus.getDefault().post(new BtnTypeEvent("兼职",R.mipmap.sort_jianzhi));
                    break;
                case R.id.btn_add_lijin:
                    EventBus.getDefault().post(new BtnTypeEvent("礼金",R.mipmap.sort_lijin));
                    break;
                case R.id.btn_add_lingqian:
                    EventBus.getDefault().post(new BtnTypeEvent("零钱",R.mipmap.sort_lingqian));
                    break;
                case R.id.btn_add_liwu:
                    EventBus.getDefault().post(new BtnTypeEvent("礼物",R.mipmap.sort_liwu));
                    break;
                case R.id.btn_add_qita:
                    EventBus.getDefault().post(new BtnTypeEvent("其他",R.mipmap.sort_shouxufei));
                    break;
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView: ");
    }
}
