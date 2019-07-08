package com.myapplicationdev.android.makeup.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.myapplicationdev.android.makeup.Interface.ItemClickListener;
import com.myapplicationdev.android.makeup.R;

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView textOrderId, textOrderStatus, textOrderPhone, textOrderAdress;

    private ItemClickListener itemClickListener;

    public OrderViewHolder(View itemView) {
        super(itemView);
        textOrderAdress = (TextView)itemView.findViewById(R.id.address);
        textOrderId = (TextView)itemView.findViewById(R.id.order_id);
        textOrderStatus = (TextView)itemView.findViewById(R.id.order_status);
        textOrderPhone = (TextView)itemView.findViewById(R.id.order_phone);

        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {


    }
}
