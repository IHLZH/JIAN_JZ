package com.example.jian_jz.Utils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jian_jz.Entity.Bill;
import com.example.jian_jz.Entity.Header;
import com.example.jian_jz.Utils.DB.DBUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListUtil {
    private static List<Bill> billList;
    private static List<Header> headerList;
    private static List<String> timeHeaderList;
    private static Map<String, Double> mpIn;
    private static Map<String, Double> mpOut;
    private static SQLiteDatabase DB = DBUtil.getSqLiteDatabase();

    public static List<Bill> getBillListByTime(String time){
        billList = new ArrayList<>();
        headerList = new ArrayList<>();
        timeHeaderList = new ArrayList<>();

        mpIn = new HashMap<>();
        mpOut = new HashMap<>();

        String selection = "time like '"+ time +"%'";
        Cursor cursor = DB.query("tb_bill", null, selection, null, null, null, null);

        while(cursor.moveToNext()){
            int idId = cursor.getColumnIndex("id");
            int id = cursor.getInt(idId);
            int idCost = cursor.getColumnIndex("cost");
            Double cost = Double.parseDouble(cursor.getString(idCost));
            int idTime = cursor.getColumnIndex("time");
            String mtime = cursor.getString(idTime);
            inHeaderList(mtime);
            int idName = cursor.getColumnIndex("sortName");
            String sortName = cursor.getString(idName);
            int idImg = cursor.getColumnIndex("sortImg");
            Integer sortImg = cursor.getInt(idImg);
            int idIncome = cursor.getColumnIndex("income");
            int mincome = cursor.getInt(idIncome);
            boolean income = true;

            if(mincome == 0){
                if(mpOut.isEmpty()){
                    mpOut.put(mtime, cost);
                } else mpOut.put(mtime, mpOut.get(mtime) + cost);
                income = false;
            }else {
                if(mpIn.isEmpty()){
                    mpIn.put(mtime, cost);
                }else mpIn.put(mtime, mpIn.get(mtime) + cost);
            }
            Bill bill = new Bill(id, cost, mtime, sortName, sortImg, income);
            billList.add(bill);
        }

        cursor.close();
        return billList;
    }

    public static List<Header> getHeaderList(){
        for (String time : timeHeaderList) {
            if(!mpIn.isEmpty() && !mpOut.isEmpty())headerList.add(new Header(mpIn.get(time), mpOut.get(time), time));
        }
        return headerList;
    }

    private static void inHeaderList(String mtime) {
        if(!timeHeaderList.contains(mtime)){
            timeHeaderList.add(mtime);
            mpIn.put(mtime, 0.0);
            mpOut.put(mtime, 0.0);
        }
    }
}
