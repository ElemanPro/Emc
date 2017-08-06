package com.example.elashry.eleman.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.elashry.eleman.Model.MaintenanceModel;
import com.example.elashry.eleman.R;

import java.util.List;

/**
 * Created by elashry on 8/6/2017.
 */

public class MaintenanceAdapter extends RecyclerView.Adapter <MaintenanceAdapter.ViewHoler>{

    private Context mContext;
    LayoutInflater inflater;
    private List<MaintenanceModel> order_List;

    public MaintenanceAdapter(Context mContext,List<MaintenanceModel> order_List) {
        this.mContext = mContext;
        this.order_List =order_List;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public MaintenanceAdapter.ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.metainance_item,parent,false);
        MaintenanceAdapter.ViewHoler holer = new MaintenanceAdapter.ViewHoler(view);
        return holer;
    }

    @Override
    public void onBindViewHolder(MaintenanceAdapter.ViewHoler holder, int position) {
        // Picasso.with(mContext).load(Uri.parse(pro_List.get(position).getPro_Image_url().toString())).into(holder.product_image);

        holder.cname.setText(order_List.get(position).getCname().toString());
        holder.cphone.setText(order_List.get(position).getCphone().toString());
        holder.caddress.setText(order_List.get(position).getCaddress().toString());
        holder.dtype.setText(order_List.get(position).getDtype().toString());
        holder.wstate.setText(order_List.get(position).getWstate().toString());
        holder.brand.setText(order_List.get(position).getDbrand().toString());
        holder.damagetype.setText(order_List.get(position).getDamagetype().toString());
        holder.odate.setText(order_List.get(position).getOdate().toString());
    }

    @Override
    public int getItemCount() {
        return order_List.size();
    }
    class ViewHoler extends RecyclerView.ViewHolder{
        TextView cname,cphone,caddress,dtype,wstate,brand,damagetype,odate;

        public ViewHoler(View itemView) {
            super(itemView);
            cname     = (TextView) itemView.findViewById(R.id.cname);
            cphone      = (TextView) itemView.findViewById(R.id.cphone);
            caddress     = (TextView) itemView.findViewById(R.id.caddress);
            dtype     = (TextView) itemView.findViewById(R.id.dtype);
            wstate      = (TextView) itemView.findViewById(R.id.wstate);
            brand     = (TextView) itemView.findViewById(R.id.dbrand);
            damagetype      = (TextView) itemView.findViewById(R.id.damage);
            odate     = (TextView) itemView.findViewById(R.id.odate);
        }

    }
}
