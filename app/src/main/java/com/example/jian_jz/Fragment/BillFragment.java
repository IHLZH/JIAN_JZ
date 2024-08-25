package com.example.jian_jz.Fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.jian_jz.Activity.AddActivity;
import com.example.jian_jz.Adapter.BillRecyclerAdapter;
import com.example.jian_jz.Entity.Bill;
import com.example.jian_jz.Entity.Header;
import com.example.jian_jz.R;
import com.example.jian_jz.Utils.ListUtil;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class BillFragment extends Fragment {
    private Context context;
    protected RecyclerView recyclerView;
    protected BillRecyclerAdapter billRecyclerAdapter;
    protected RecyclerView.LayoutManager layoutManager;
    protected TextView tv_bill_out;
    protected TextView tv_bill_in;
    protected ImageView img_bill_add;
    protected LinearLayout ll_time_select;
    protected TextView tv_bill_time_year;
    protected TextView tv_bill_time_month;
    protected TextView tv_bill_remainder;
    //数据源
    private List<Bill> billList;
    private List<Header> headerList;
    private Calendar selectDate;
    private String YMTime;
    private Calendar calendar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ledger, null);
        context = getContext();
        initViews(view);
        initData();
        //获取数据源
        billList = ListUtil.getBillListByTime(YMTime);
        //Log.i("billListUpdate", "onCreateView: " + YMTime);
        Collections.reverse(billList);
        headerList = ListUtil.getHeaderList();
        Collections.reverse(headerList);
        //Log.i("billFragment", "onCreateView: 111");
        //获取Adapter和LayoutManager
        billRecyclerAdapter = new BillRecyclerAdapter(getContext(), billList, headerList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        //设置Adapter和LayoutManager
        recyclerView.setAdapter(billRecyclerAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        //设置收支和监听器
        setSumOfInAndOut(headerList);
        setTimeSelect(calendar);
        setTimeSelectClickListener();
        JumpToAdd();
        return view;
    }

    private void setTimeSelectClickListener() {
        ll_time_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTime(selectDate);
            }
        });
    }

    private void selectTime(Calendar selectDate) {
        TimePickerView timePickerView = new TimePickerBuilder(context, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                setSelectDate(calendar);
                setYMTime(getYMTime(calendar));
                setTimeSelect(calendar);
                updateList(calendar);
                setSumOfInAndOut(headerList);
                billRecyclerAdapter = new BillRecyclerAdapter(getContext(), billList, headerList);
                recyclerView.setAdapter(billRecyclerAdapter);
            }
        })
                .setType(new boolean[]{true, true, false, false, false, false})
                .setCancelText("取消")
                .setSubmitText("确认")
                .setTitleText("确认月份")
                .setDate(selectDate)
                .isCyclic(true)
                .isDialog(false)
                .build();

        timePickerView.show();
    }

    private void updateList(Calendar calendar) {
        billList = ListUtil.getBillListByTime(getYMTime(calendar));
        Collections.reverse(billList);
        headerList = ListUtil.getHeaderList();
        Collections.reverse(headerList);
    }

    private void JumpToAdd() {
        img_bill_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getContext(), AddActivity.class);
                startActivity(intent);
            }
        });
    }

    //获取年月
    private String getYMTime(Calendar calendar){
        String time = "";
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        time += year + "-";
        if(month >= 10)time += month;
        else time += "0" + month;
        return time;
    }

    private String getDTime(Calendar calendar){
        String time = "";
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        if(day >= 10)time += day;
        else time += "0" + day;
        return time;
    }

    private void setTimeSelect(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        tv_bill_time_year.setText(String.valueOf(year) + "年");
        if(month >= 10)tv_bill_time_month.setText(String.valueOf(month) + "月");
        else tv_bill_time_month.setText("0" + month + "月");
    }

    private void setSumOfInAndOut(List<Header> headerList) {
        double sumIn = 0.0;
        double sumOut = 0.0;
        for (Header header : headerList) {
            sumIn += header.getIn();
            sumOut += header.getOut();
        }
        tv_bill_in.setText(String.format(Locale.CHINA,"%.2f", sumIn));
        tv_bill_out.setText(String.format(Locale.CHINA,"%.2f", sumOut));
        tv_bill_remainder.setText(String.format(Locale.CHINA, "%.2f", sumIn - sumOut));
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.rcv_bill_list);
        tv_bill_out = view.findViewById(R.id.tv_bill_out);
        tv_bill_in = view.findViewById(R.id.tv_bill_in);
        img_bill_add = view.findViewById(R.id.img_bill_add);
        ll_time_select = view.findViewById(R.id.ll_time_select);
        tv_bill_time_year = view.findViewById(R.id.tv_bill_time_year);
        tv_bill_time_month = view.findViewById(R.id.tv_bill_time_month);
        tv_bill_remainder = view.findViewById(R.id.tv_bill_remainder);
    }

    private void initData() {
        if (selectDate == null)selectDate = Calendar.getInstance();
        if(calendar == null)calendar = Calendar.getInstance();
        if(YMTime == null)YMTime = getYMTime(calendar);
    }

    public void setSelectDate(Calendar selectDate) {
        this.selectDate = selectDate;
    }

    public void setYMTime(String YMTime) {
        this.YMTime = YMTime;
    }


}
