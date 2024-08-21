package com.example.jian_jz.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jian_jz.Entity.Bill;
import com.example.jian_jz.Entity.Header;
import com.example.jian_jz.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillRecyclerAdapter extends RecyclerView.Adapter<BillRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<Bill> billList;
    private List<Header> headerList;
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
            HeaderViewHolder headerViewHolder = (HeaderViewHolder)holder;
            headerViewHolder.getTv_bill_header_date().setText(header.getTime());
            headerViewHolder.getTv_bill_header_ie().setText("收入：" + String.valueOf(header.getIn()) + " 支出：" + String.valueOf(header.getOut()));
        }else{
            Bill bill = billList.get(position - HeaderNum);
            ItemViewHolder itemViewHolder = (ItemViewHolder)holder;
            itemViewHolder.getImg_bill_item_icon().setImageResource(bill.getSortImg());
            itemViewHolder.getTv_bill_item_name().setText(bill.getSortName());
            if(bill.isIncome()){
                itemViewHolder.getTv_bill_item_num().setText("+" + String.valueOf(bill.getCost()));
            }else{
                itemViewHolder.getTv_bill_item_num().setText("-" + String.valueOf(bill.getCost()));
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
            if(isFirst == true)isFirst = false;
            ifAccept.put(position, ITEM_TYPE);
            ItemNum++;
            return ITEM_TYPE;
        } else if (billList.get(position).getTime().equals(billList.get(position - 1).getTime())) {
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

    class HeaderViewHolder extends ViewHolder{
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

    class ItemViewHolder extends ViewHolder{
        private ImageView img_bill_item_icon;
        private TextView tv_bill_item_name;
        private ImageView img_bill_item_drag;
        private TextView tv_bill_item_num;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            img_bill_item_icon = itemView.findViewById(R.id.img_bill_item_icon);
            tv_bill_item_name = itemView.findViewById(R.id.tv_bill_item_name);
            img_bill_item_drag = itemView.findViewById(R.id.img_bill_item_drag);
            tv_bill_item_num = itemView.findViewById(R.id.tv_bill_item_num);
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
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


}
