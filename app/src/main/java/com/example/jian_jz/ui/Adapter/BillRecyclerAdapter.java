package com.example.jian_jz.ui.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jian_jz.Entity.BHitem;
import com.example.jian_jz.Entity.Bill;
import com.example.jian_jz.Entity.Header;
import com.example.jian_jz.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class BillRecyclerAdapter extends RecyclerView.Adapter<BillRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<Bill> billList;
    private List<Header> headerList;
    private static List<BHitem> bHitems = new ArrayList<>();
    private Integer HEADER_TYPE = 1;
    private Integer ITEM_TYPE = 2;
    private Boolean isFirst = true;
    private Integer HeaderNum = 0;
    private Integer ItemNum = 0;
    private Map<Integer, Integer> ifAccept = new HashMap<Integer, Integer>();

    public BillRecyclerAdapter(Context context, List<Bill> billList, List<Header> headerList) {
        this.context = context;
        this.billList = billList;
        this.headerList = headerList;
        HeaderNum = 0;
        ItemNum = 0;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == HEADER_TYPE){
            return new HeaderViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recycler_bill_header,parent, false));
        }else{
            return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recycler_bill_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if(viewType == HEADER_TYPE){
            Header header = headerList.get(position - ItemNum);
            bHitems.add(header);
            HeaderViewHolder headerViewHolder = (HeaderViewHolder)holder;
            headerViewHolder.getTv_bill_header_date().setText(header.getTime());
            headerViewHolder.getTv_bill_header_ie().setText("收入：" + String.format(Locale.CHINA, "%.2f", header.getIn()) + " 支出：" + String.format(Locale.CHINA, "%.2f", header.getOut()));
        }else{
            Bill bill = billList.get(position - HeaderNum);
            bHitems.add(bill);
            ItemViewHolder itemViewHolder = (ItemViewHolder)holder;
            itemViewHolder.getImg_bill_item_icon().setImageResource(bill.getSortImg());
            itemViewHolder.getTv_bill_item_name().setText(bill.getSortName());
            if(bill.isIncome()){
                itemViewHolder.getTv_bill_item_num().setText("+" + String.format(Locale.CHINA, "%.2f", bill.getCost()));
            }else{
                itemViewHolder.getTv_bill_item_num().setText("-" + String.format(Locale.CHINA, "%.2f", bill.getCost()));
            }
        }
    }

    @Override
    public int getItemCount() {
        return billList.size() + headerList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(ifAccept.get(position) != null){
            return ifAccept.get(position);
        }else if(position == 0){
            isFirst = true;
            ifAccept.put(position, HEADER_TYPE);
            HeaderNum++;
            return HEADER_TYPE;
        } else if(isFirst){
            Log.i("isFirstBill", "getItemViewType: " + position + String.valueOf(isFirst));
            isFirst = false;
            ifAccept.put(position, ITEM_TYPE);
            ItemNum++;
            return ITEM_TYPE;
        } else if (billList.get(position - HeaderNum).getTime().equals(billList.get(position - 1 - HeaderNum).getTime())) {
            ifAccept.put(position, ITEM_TYPE);
            ItemNum++;
            return ITEM_TYPE;
        } else{
            isFirst = true;
            ifAccept.put(position, HEADER_TYPE);
            HeaderNum++;
            return HEADER_TYPE;
        }
    }

    static class HeaderViewHolder extends ViewHolder{
        private TextView tv_bill_header_date;
        private TextView tv_bill_header_ie;
        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_bill_header_date = itemView.findViewById(R.id.tv_bill_header_date);
            tv_bill_header_ie = itemView.findViewById(R.id.tv_bill_header_ie);
        }

        public TextView getTv_bill_header_date() {
            return tv_bill_header_date;
        }

        public TextView getTv_bill_header_ie() {
            return tv_bill_header_ie;
        }
    }

    static class ItemViewHolder extends ViewHolder{
        private ImageView img_bill_item_icon;
        private TextView tv_bill_item_name;
        private ImageView img_bill_item_drag;
        private TextView tv_bill_item_num;
        private RelativeLayout rl_bill_item;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            img_bill_item_icon = itemView.findViewById(R.id.img_bill_item_icon);
            tv_bill_item_name = itemView.findViewById(R.id.tv_bill_item_name);
            img_bill_item_drag = itemView.findViewById(R.id.img_bill_item_drag);
            tv_bill_item_num = itemView.findViewById(R.id.tv_bill_item_num);
            rl_bill_item = itemView.findViewById(R.id.rl_bill_item);
        }

        public ImageView getImg_bill_item_icon() {
            return img_bill_item_icon;
        }

        public TextView getTv_bill_item_name() {
            return tv_bill_item_name;
        }

        public ImageView getImg_bill_item_drag() {
            return img_bill_item_drag;
        }

        public TextView getTv_bill_item_num() {
            return tv_bill_item_num;
        }

        public RelativeLayout getRl_bill_item() {
            return rl_bill_item;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnCreateContextMenuListener(this);
        }

        //创建上下文菜单
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            int position = getBindingAdapterPosition();
            Intent intent = new Intent();
            intent.putExtra("position_key", position);
            menu.add(0, 0, 0, "修改").setIntent(intent);
            menu.add(0, 1, 0, "删除").setIntent(intent);
        }
    }

    public static ItemViewHolder getItemViewHolder(View view){
        return new ItemViewHolder(view);
    }

    public static List<BHitem> getBHitems(){
        return  bHitems;
    }

    public static void setbHitems(List<BHitem> bHitems) {
        BillRecyclerAdapter.bHitems = bHitems;
    }

}