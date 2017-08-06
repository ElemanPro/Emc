package com.example.elashry.eleman.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.elashry.eleman.Model.OrderModel;
import com.example.elashry.eleman.R;

import java.util.List;

/**
 * Created by elashry on 8/6/2017.
 */

public class OrderAdapter extends RecyclerView.Adapter <OrderAdapter.ViewHoler>{

    private Context mContext;
    LayoutInflater inflater;
    private List<OrderModel> order_List;

    public OrderAdapter(Context mContext, List<OrderModel> pro_List) {
        this.mContext = mContext;
        this.order_List =pro_List;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public OrderAdapter.ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.order_item,parent,false);
        OrderAdapter.ViewHoler holer = new OrderAdapter.ViewHoler(view);
        return holer;
    }

    @Override
    public void onBindViewHolder(OrderAdapter.ViewHoler holder, int position) {
        holder.c_name.setText(order_List.get(position).getClient_name().toString());
        holder.c_phone.setText(order_List.get(position).getClient_phone().toString());
        holder.c_address.setText(order_List.get(position).getOrder_address().toString());
        holder.dev_quantity.setText(order_List.get(position).getQuantity().toString());
        holder.order_date.setText(order_List.get(position).getOrder_date().toString());


    }

    @Override
    public int getItemCount() {
        return order_List.size();
    }

    class ViewHoler extends RecyclerView.ViewHolder{
        private TextView c_name,c_phone,c_address,dev_quantity,order_date;
        public ViewHoler(View itemView) {
            super(itemView);

            c_name = (TextView) itemView.findViewById(R.id.order_client_name);
            c_phone= (TextView) itemView.findViewById(R.id.order_client_phone);
            c_address = (TextView) itemView.findViewById(R.id.order_client_address);
            dev_quantity  = (TextView) itemView.findViewById(R.id.order_dev_quantity);
            order_date = (TextView) itemView.findViewById(R.id.order_date);
        }

    }

}
