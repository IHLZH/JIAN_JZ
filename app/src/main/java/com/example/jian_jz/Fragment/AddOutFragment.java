package com.example.jian_jz.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.jian_jz.Base.BaseFragment;
import com.example.jian_jz.Event.BtnTypeEvent;
import com.example.jian_jz.Event.IncomeEvent;
import com.example.jian_jz.Event.MessageEvent;
import com.example.jian_jz.R;
import com.example.jian_jz.databinding.FragmentAddOutBinding;

import org.greenrobot.eventbus.EventBus;

public class AddOutFragment extends BaseFragment<FragmentAddOutBinding> {
    private String TAG = "AddOutFragment";
    private FragmentAddOutBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        binding = getBinding();
        setListeners();
        return view;
    }

    private void setListeners() {
        binding.btnAddBangong.setOnClickListener(new OpenJsjListener());
        binding.btnAddCanyin.setOnClickListener(new OpenJsjListener());
        binding.btnAddGouwu.setOnClickListener(new OpenJsjListener());
        binding.btnAddJiaotong.setOnClickListener(new OpenJsjListener());
        binding.btnAddJuanzeng.setOnClickListener(new OpenJsjListener());
        binding.btnAddLingshi.setOnClickListener(new OpenJsjListener());
        binding.btnAddShuma.setOnClickListener(new OpenJsjListener());
        binding.btnAddYiliao.setOnClickListener(new OpenJsjListener());
        binding.btnAddYule.setOnClickListener(new OpenJsjListener());
        binding.btnAddYundong.setOnClickListener(new OpenJsjListener());
    }

    public class OpenJsjListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            EventBus.getDefault().post(new MessageEvent("open_Jsj"));
            EventBus.getDefault().post(new IncomeEvent(false));
            switch(v.getId()){
                case R.id.btn_add_bangong:
                    EventBus.getDefault().post(new BtnTypeEvent("办公",R.mipmap.sort_bangong));
                    break;
                case R.id.btn_add_canyin:
                    EventBus.getDefault().post(new BtnTypeEvent("餐饮",R.mipmap.sort_canyin));
                    break;
                case R.id.btn_add_gouwu:
                    EventBus.getDefault().post(new BtnTypeEvent("购物",R.mipmap.sort_gouwu));
                    break;
                case R.id.btn_add_jiaotong:
                    EventBus.getDefault().post(new BtnTypeEvent("交通",R.mipmap.sort_jiaotong));
                    break;
                case R.id.btn_add_juanzeng:
                    EventBus.getDefault().post(new BtnTypeEvent("捐赠",R.mipmap.sort_juanzeng));
                    break;
                case R.id.btn_add_lingshi:
                    EventBus.getDefault().post(new BtnTypeEvent("零食",R.mipmap.sort_lingshi));
                    break;
                case R.id.btn_add_shuma:
                    EventBus.getDefault().post(new BtnTypeEvent("数码",R.mipmap.sort_shuma));
                    break;
                case R.id.btn_add_yiliao:
                    EventBus.getDefault().post(new BtnTypeEvent("医疗",R.mipmap.sort_yiliao));
                    break;
                case R.id.btn_add_yule:
                    EventBus.getDefault().post(new BtnTypeEvent("娱乐",R.mipmap.sort_yule));
                    break;
                case R.id.btn_add_yundong:
                    EventBus.getDefault().post(new BtnTypeEvent("运动",R.mipmap.sort_yundong));
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
