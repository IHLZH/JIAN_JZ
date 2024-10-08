package com.example.jian_jz.ui.Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.jian_jz.ui.Adapter.AddFragmentAdapter;
import com.example.jian_jz.Base.BaseActivity;
import com.example.jian_jz.Entity.Bill;
import com.example.jian_jz.Event.BtnTypeEvent;
import com.example.jian_jz.Event.IncomeEvent;
import com.example.jian_jz.Event.MessageEvent;
import com.example.jian_jz.Event.ModifyEvent;
import com.example.jian_jz.ui.Fragment.AddInFragment;
import com.example.jian_jz.ui.Fragment.AddOutFragment;
import com.example.jian_jz.R;
import com.example.jian_jz.Utils.DB.DBUtil;
import com.example.jian_jz.databinding.ActivityAddBinding;
import com.example.jian_jz.databinding.ItemAddJsjBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddActivity extends BaseActivity<ActivityAddBinding> {
    private String TAG = "AddActivity";
    private Integer id;
    private boolean income;//是否是收入
    private String time = ""; //创建时间
    private String sortName; //分类名称
    private Integer sortImg; //分类图标
    private Double cost = 0.0; //金额
    private String remark;//备注
    private boolean isZero = true;
    private boolean isDot = false;
    private boolean isModify = false;
    private SQLiteDatabase DB;

    @Override
    protected void onCreated(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        initFragment();
        initTabLayout();
        setListeners();
        getBillModifyIntent();
    }

    private void getBillModifyIntent() {
        Intent intent = getIntent();
        Bill bill = (Bill)intent.getSerializableExtra("bill");
        id = intent.getIntExtra("id", -1);
        if(bill != null){
            time = bill.getTime();
            isModify = true;
            if(bill.getCost() > 0)isZero = false;
        }
        if(bill.isIncome()){
                binding.vpAdd.setCurrentItem(1);
        }else binding.vpAdd.setCurrentItem(0);
        //设置类型和金额
        binding.layoutAddJsj.itemTbTypeTv.setText(bill.getSortName());
        binding.layoutAddJsj.tbNoteMoney.setText(String.format(Locale.CHINA,"%.2f", bill.getCost()));
        //发布粘性事件
        EventBus.getDefault().postSticky(new ModifyEvent(new IncomeEvent(bill.isIncome()), new MessageEvent(bill.getSortName())));
        binding.layoutAddJsj.addJsj.setVisibility(View.VISIBLE);
    }

    private void setListeners() {
        ItemAddJsjBinding jsj = binding.layoutAddJsj;
        jsj.tbNoteClear.setOnClickListener(new jsjOnClickListener());
        jsj.tbCalcNumDel.setOnClickListener(new jsjOnClickListener());
        jsj.tbCalcNumDone.setOnClickListener(new jsjOnClickListener());
        jsj.tbCalcNum0.setOnClickListener(new jsjOnClickListener());
        jsj.tbCalcNum1.setOnClickListener(new jsjOnClickListener());
        jsj.tbCalcNum2.setOnClickListener(new jsjOnClickListener());
        jsj.tbCalcNum3.setOnClickListener(new jsjOnClickListener());
        jsj.tbCalcNum4.setOnClickListener(new jsjOnClickListener());
        jsj.tbCalcNum5.setOnClickListener(new jsjOnClickListener());
        jsj.tbCalcNum6.setOnClickListener(new jsjOnClickListener());
        jsj.tbCalcNum7.setOnClickListener(new jsjOnClickListener());
        jsj.tbCalcNum8.setOnClickListener(new jsjOnClickListener());
        jsj.tbCalcNum9.setOnClickListener(new jsjOnClickListener());
        jsj.tbCalcNumDot.setOnClickListener(new jsjOnClickListener());
    }

    public class jsjOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.tb_note_clear: //清空金额
                    clearMoney();
                    break;
                case R.id.tb_calc_num_del: //删除键
                    subMoney();
                    break;
                case R.id.tb_calc_num_done: //确定
                    if(isModify){
                        ModifyMoneyFormDB(getMoney());
                    }
                    else addMoneyToDB(getMoney());
                    break;
                case R.id.tb_calc_num_0:
                    addMoney('0');
                    break;
                case R.id.tb_calc_num_1:
                    addMoney('1');
                    break;
                case R.id.tb_calc_num_2:
                    addMoney('2');
                    break;
                case R.id.tb_calc_num_3:
                    addMoney('3');
                    break;
                case R.id.tb_calc_num_4:
                    addMoney('4');
                    break;
                case R.id.tb_calc_num_5:
                    addMoney('5');
                    break;
                case R.id.tb_calc_num_6:
                    addMoney('6');
                    break;
                case R.id.tb_calc_num_7:
                    addMoney('7');
                    break;
                case R.id.tb_calc_num_8:
                    addMoney('8');
                    break;
                case R.id.tb_calc_num_9:
                    addMoney('9');
                    break;
                case R.id.tb_calc_num_dot:
                    addMoney('.');
                    break;
            }
        }
    }

    private void ModifyMoneyFormDB(Bill bill) {
        DB = DBUtil.getSqLiteDatabase();

        int mincome;
        if(income)mincome = 1;
        else mincome = 0;

        ContentValues contentValues = new ContentValues();
        contentValues.put("time", bill.getTime());
        contentValues.put("cost", String.valueOf(bill.getCost()));
        contentValues.put("sortName", bill.getSortName());
        contentValues.put("sortImg", bill.getSortImg());
        contentValues.put("income", mincome);

        String whereClause = "id = ?";
        String[] whereArgs = {String.valueOf(id)};

        int row = DB.update("tb_bill", contentValues, whereClause, whereArgs);
        if(row > 0){
            Log.i(TAG, "ModifyMoneyFormDB: 更新成功！");
        }
    }

    private void addMoneyToDB(Bill bill) {
        DB = DBUtil.getSqLiteDatabase();
        int mincome;
        if(income)mincome = 1;
        else mincome = 0;

        ContentValues contentValues = new ContentValues();
        contentValues.put("time", bill.getTime());
        contentValues.put("cost", String.valueOf(bill.getCost()));
        contentValues.put("sortName", bill.getSortName());
        contentValues.put("sortImg", bill.getSortImg());
        contentValues.put("income", mincome);

        long insert = DB.insert("tb_bill", null, contentValues);
        if(insert > 0){
            Log.i(TAG, "数据插入成功！");
            this.finish();
        }
    }

    private Bill getMoney() {
        cost = Double.parseDouble(binding.layoutAddJsj.tbNoteMoney.getText().toString());
        getTime();
        Bill bill = new Bill(cost, time, sortName, sortImg, income);
        return bill;
    }

    private void getTime() {
        if(time == null){
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            time = year + "-";
            if(month >= 10)time += month;
            else time += "0" + month;
            time += "-";
            if(day >= 10)time += day;
            else time += "0" + day;
        }
    }

    private void subMoney() {
        if(isZero)return;
        String sumCost = binding.layoutAddJsj.tbNoteMoney.getText().toString();
        if(sumCost.substring(sumCost.length() - 1, sumCost.length()).equals(".")){
            isDot = false;
        }
        if(sumCost.length() > 1)sumCost = sumCost.substring(0, sumCost.length() - 1);
        else if (sumCost.length() == 1){
            sumCost = "0";
            isZero = true;
        }
        binding.layoutAddJsj.tbNoteMoney.setText(sumCost);
    }

    private void clearMoney() {
        binding.layoutAddJsj.tbNoteMoney.setText("0");
        isDot = false;
        isZero = true;
    }

    private void addMoney(char c) {
        if(c == '.' && isDot)return;
        else if(c == '.')isDot = true;
        StringBuffer sumCost = new StringBuffer(binding.layoutAddJsj.tbNoteMoney.getText().toString());
        if(isZero && c != '.'){
            if(c == '0')return;
            sumCost.delete(0, sumCost.length());
            isZero = false;
        }
        if(isZero && c == '.'){
            isZero = false;
            isDot = true;
        }
        sumCost.append(c);
        binding.layoutAddJsj.tbNoteMoney.setText(sumCost);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if(event.getMessage().equals("open_Jsj")){
            binding.layoutAddJsj.addJsj.setVisibility(View.VISIBLE);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBtnTypeEvent(BtnTypeEvent event){
        binding.layoutAddJsj.itemTbTypeTv.setText(event.getTypeMessage());
        sortName = event.getTypeMessage();
        sortImg = event.getImg();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void IncomeTypeEvent(IncomeEvent event){
        income = event.isIncome();
    }


    private void initTabLayout() {
        List<String> tabNameList = new ArrayList<>();
        tabNameList.add("支出");
        tabNameList.add("收入");

        TabLayoutMediator mediator = new TabLayoutMediator(
                binding.tbAdd,
                binding.vpAdd,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(tabNameList.get(position));
                    }
                }
        );

        mediator.attach();
    }

    private void initFragment() {
        List<Fragment> fragmentList = new ArrayList<>();

        fragmentList.add(new AddOutFragment());
        fragmentList.add(new AddInFragment());

        AddFragmentAdapter addFragmentAdapter = new AddFragmentAdapter(fragmentList, this);
        binding.vpAdd.setAdapter(addFragmentAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}