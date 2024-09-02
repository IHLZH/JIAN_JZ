package com.example.jian_jz.ui.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.jian_jz.Base.BaseFragment;
import com.example.jian_jz.Event.BtnTypeEvent;
import com.example.jian_jz.Event.IncomeEvent;
import com.example.jian_jz.Event.MessageEvent;
import com.example.jian_jz.Event.ModifyEvent;
import com.example.jian_jz.R;
import com.example.jian_jz.databinding.FragmentAddOutBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class AddOutFragment extends BaseFragment<FragmentAddOutBinding> {
    private String TAG = "AddOutFragment";
    private FragmentAddOutBinding binding;
    private Context context;
    private List<TextView> textViewList;
    private Integer textColor;
    private Integer defaultColor;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        binding = getBinding();
        context = getContext();
        EventBus.getDefault().register(this);
        initTextViewList();
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
                    setTextColor(binding.textAddBangong.getId());
                    break;
                case R.id.btn_add_canyin:
                    EventBus.getDefault().post(new BtnTypeEvent("餐饮",R.mipmap.sort_canyin));
                    setTextColor(binding.textAddCanyin.getId());
                    break;
                case R.id.btn_add_gouwu:
                    EventBus.getDefault().post(new BtnTypeEvent("购物",R.mipmap.sort_gouwu));
                    setTextColor(binding.textAddGouwu.getId());
                    break;
                case R.id.btn_add_jiaotong:
                    EventBus.getDefault().post(new BtnTypeEvent("交通",R.mipmap.sort_jiaotong));
                    setTextColor(binding.textAddJiaotong.getId());
                    break;
                case R.id.btn_add_juanzeng:
                    EventBus.getDefault().post(new BtnTypeEvent("捐赠",R.mipmap.sort_juanzeng));
                    setTextColor(binding.textAddJuanzeng.getId());
                    break;
                case R.id.btn_add_lingshi:
                    EventBus.getDefault().post(new BtnTypeEvent("零食",R.mipmap.sort_lingshi));
                    setTextColor(binding.textAddLingshi.getId());
                    break;
                case R.id.btn_add_shuma:
                    EventBus.getDefault().post(new BtnTypeEvent("数码",R.mipmap.sort_shuma));
                    setTextColor(binding.textAddShuma.getId());
                    break;
                case R.id.btn_add_yiliao:
                    EventBus.getDefault().post(new BtnTypeEvent("医疗",R.mipmap.sort_yiliao));
                    setTextColor(binding.textAddYiliao.getId());
                    break;
                case R.id.btn_add_yule:
                    EventBus.getDefault().post(new BtnTypeEvent("娱乐",R.mipmap.sort_yule));
                    setTextColor(binding.textAddYule.getId());
                    break;
                case R.id.btn_add_yundong:
                    EventBus.getDefault().post(new BtnTypeEvent("运动",R.mipmap.sort_yundong));
                    setTextColor(binding.textAddYundong.getId());
                    break;
            }
        }
    }

    private void initTextViewList() {
        textColor = ContextCompat.getColor(context, R.color.app_color);
        defaultColor = ContextCompat.getColor(context, R.color.defalut);
        textViewList = new ArrayList<>();
        textViewList.add(binding.textAddBangong);
        textViewList.add(binding.textAddCanyin);
        textViewList.add(binding.textAddGouwu);
        textViewList.add(binding.textAddJiaotong);
        textViewList.add(binding.textAddJuanzeng);
        textViewList.add(binding.textAddLingshi);
        textViewList.add(binding.textAddShuma);
        textViewList.add(binding.textAddYiliao);
        textViewList.add(binding.textAddYule);
        textViewList.add(binding.textAddYundong);
    }

    private void setTextColor(int id) {
        for (TextView textView : textViewList) {
            if(textView.getId() == id)textView.setTextColor(textColor);
            else textView.setTextColor(defaultColor);
        }
    }
    @Subscribe(sticky = true)
    public void onMosifyEvent(ModifyEvent modifyEvent){
        if(!modifyEvent.getIncomeEvent()){
            setOnClick(modifyEvent.getMessageEvent());
            EventBus.getDefault().removeStickyEvent(modifyEvent);
        }
    }

    private void setOnClick(String messageEvent) {
        switch(messageEvent){
            case "办公":
                setTextColor(binding.textAddBangong.getId());
                break;
            case "餐饮":
                setTextColor(binding.textAddCanyin.getId());
                break;
            case "购物":
                setTextColor(binding.textAddGouwu.getId());
                break;
            case "交通":
                setTextColor(binding.textAddJiaotong.getId());
                break;
            case "捐赠":
                setTextColor(binding.textAddJuanzeng.getId());
                break;
            case "零食":
                setTextColor(binding.textAddLingshi.getId());
                break;
            case "数码":
                setTextColor(binding.textAddShuma.getId());
                break;
            case "医疗":
                setTextColor(binding.textAddYiliao.getId());
                break;
            case "娱乐":
                setTextColor(binding.textAddYule.getId());
                break;
            case "运动":
                setTextColor(binding.textAddYundong.getId());
                break;
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
        EventBus.getDefault().unregister(this);
        Log.i(TAG, "onDestroyView: ");
    }

}
