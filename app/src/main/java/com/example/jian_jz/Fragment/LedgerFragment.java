package com.example.jian_jz.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jian_jz.Activity.AddActivity;
import com.example.jian_jz.Adapter.BillRecyclerAdapter;
import com.example.jian_jz.Entity.Bill;
import com.example.jian_jz.Entity.Header;
import com.example.jian_jz.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LedgerFragment extends Fragment {
    protected RecyclerView recyclerView;
    protected BillRecyclerAdapter billRecyclerAdapter;
    protected RecyclerView.LayoutManager layoutManager;
    protected TextView tv_bill_out;
    protected TextView tv_bill_in;
    protected ImageView img_bill_add;
    protected LinearLayout ll_time_select;
    protected TextView tv_bill_time_year;
    protected TextView tv_bill_time_month;
    //protected
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ledger, null);
        initViews(view);
        //获取数据源
        List<Bill> billList = new ArrayList<>();
        billList.add(new Bill(1, 1, 1145.14, "2024-08-21", "餐饮", R.mipmap.sort_canyin, false));
        List<Header> headerList = new ArrayList<>();
        headerList.add(new Header(1000.0, 1145.14, "2024-08-21"));
        //获取Adapter和LayoutManager
        billRecyclerAdapter = new BillRecyclerAdapter(getContext(), billList, headerList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        //设置Adapter和LayoutManager
        recyclerView.setAdapter(billRecyclerAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        //设置收支和监听器
        setSumOfInAndOut(headerList);
        setTimeSelect();
        JumpToAdd();
        return view;
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

    private void setTimeSelect() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        tv_bill_time_year.setText(String.valueOf(year));
        if(month >= 10)tv_bill_time_month.setText(String.valueOf(month));
        else tv_bill_time_month.setText("0" + month);
    }

    private void setSumOfInAndOut(List<Header> headerList) {
        Double sumIn = 0.0;
        Double sumOut = 0.0;
        for (Header header : headerList) {
            sumIn += header.getIn();
            sumOut += header.getOut();
        }
        tv_bill_in.setText(String.valueOf(sumIn));
        tv_bill_out.setText(String.valueOf(sumOut));
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.rcv_bill_list);
        tv_bill_out = view.findViewById(R.id.tv_bill_out);
        tv_bill_in = view.findViewById(R.id.tv_bill_in);
        img_bill_add = view.findViewById(R.id.img_bill_add);
        ll_time_select = view.findViewById(R.id.ll_time_select);
        tv_bill_time_year = view.findViewById(R.id.tv_bill_time_year);
        tv_bill_time_month = view.findViewById(R.id.tv_bill_time_month);
    }


}
