package com.example.jian_jz.Fragment;

import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.media.metrics.Event;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.jian_jz.Base.BaseFragment;
import com.example.jian_jz.Event.BtnTypeEvent;
import com.example.jian_jz.Event.IncomeEvent;
import com.example.jian_jz.Event.MessageEvent;
import com.example.jian_jz.R;
import com.example.jian_jz.databinding.FragmentAddInBinding;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class AddInFragment extends BaseFragment<FragmentAddInBinding> {
    private String TAG = "AddInFragment";
    private FragmentAddInBinding binding;
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
        initTextViewList();
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
                    setTextColor(binding.textAddGongzi.getId());
                    break;
                case R.id.btn_add_jianzhi:
                    EventBus.getDefault().post(new BtnTypeEvent("兼职",R.mipmap.sort_jianzhi));
                    setTextColor(binding.textAddJianzhi.getId());
                    break;
                case R.id.btn_add_lijin:
                    EventBus.getDefault().post(new BtnTypeEvent("礼金",R.mipmap.sort_lijin));
                    setTextColor(binding.textAddLijin.getId());
                    break;
                case R.id.btn_add_lingqian:
                    EventBus.getDefault().post(new BtnTypeEvent("零钱",R.mipmap.sort_lingqian));
                    setTextColor(binding.textAddLingqian.getId());
                    break;
                case R.id.btn_add_liwu:
                    EventBus.getDefault().post(new BtnTypeEvent("礼物",R.mipmap.sort_liwu));
                    setTextColor(binding.textAddLiwu.getId());
                    break;
                case R.id.btn_add_qita:
                    EventBus.getDefault().post(new BtnTypeEvent("其他",R.mipmap.sort_shouxufei));
                    setTextColor(binding.textAddQita.getId());
                    break;
            }
        }
    }
    private void initTextViewList() {
        textColor = ContextCompat.getColor(context, R.color.app_color);
        defaultColor = ContextCompat.getColor(context, R.color.defalut);
        textViewList = new ArrayList<>();
        textViewList.add(binding.textAddGongzi);
        textViewList.add(binding.textAddJianzhi);
        textViewList.add(binding.textAddLijin);
        textViewList.add(binding.textAddLingqian);
        textViewList.add(binding.textAddLiwu);
        textViewList.add(binding.textAddQita);
    }

    private void setTextColor(int id) {
        for (TextView textView : textViewList) {
            if(textView.getId() == id)textView.setTextColor(textColor);
            else textView.setTextColor(defaultColor);
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
