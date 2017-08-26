package com.example.elashry.eleman.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.elashry.eleman.Activities.ShowMaintenance_Details;
import com.example.elashry.eleman.Model.MaintenanceModel;
import com.example.elashry.eleman.R;

import java.util.List;

/**
 * Created by elashry on 8/6/2017.
 */

public class MaintenanceAdapter extends RecyclerView.Adapter <MaintenanceAdapter.ViewHoler>{

    public static String mflag;

    Context mContext;
    LayoutInflater inflater;
    private List<MaintenanceModel> maintenance_List;

    public MaintenanceAdapter(Context mContext,List<MaintenanceModel> maintenance_List) {
        this.mContext = mContext;
        this.maintenance_List =maintenance_List;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public MaintenanceAdapter.ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.manager_maintenance_row,parent,false);
        final ViewHoler holder = new MaintenanceAdapter.ViewHoler(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaintenanceModel  maintenanceModel = new MaintenanceModel(maintenance_List.get(holder.getLayoutPosition()).getCname().toString(),maintenance_List.get(holder.getLayoutPosition()).getCphone().toString(),maintenance_List.get(holder.getLayoutPosition()).getCaddress().toString(),maintenance_List.get(holder.getLayoutPosition()).getDtype().toString(),maintenance_List.get(holder.getLayoutPosition()).getWstate().toString(),maintenance_List.get(holder.getLayoutPosition()).getDbrand().toString(),maintenance_List.get(holder.getLayoutPosition()).getDamagetype().toString(),maintenance_List.get(holder.getLayoutPosition()).getOdate().toString());
                Intent intent = new Intent(mContext, ShowMaintenance_Details.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("maintenance_data",maintenanceModel);
                mContext.startActivity(intent);

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(MaintenanceAdapter.ViewHoler holder, int position) {
        holder.client_name.setText(maintenance_List.get(position).getCname().toString());
        holder.dev_name.setText(maintenance_List.get(position).getDtype().toString()+" "+maintenance_List.get(position).getDbrand().toString());
        holder.date.setText(maintenance_List.get(position).getOdate().toString());
       /* if(mflag.equals("0")){
            // rm.setBackgroundColor(ContextCompat.getColor(mContext,R.color.colorPrimary));
            holder.rm.setBackgroundColor(Color.parseColor("#000000"));
        }else {
            holder.rm.setBackgroundColor(Color.parseColor("#ffffff"));
        }*/
    }

    @Override
    public int getItemCount() {
        return maintenance_List.size();
    }

    class ViewHoler extends RecyclerView.ViewHolder{

        TextView client_name,dev_name,date;
        LinearLayout rm;
        public ViewHoler(View itemView) {
            super(itemView);

            client_name = (TextView) itemView.findViewById(R.id.mngr_main_client_name);
            dev_name = (TextView) itemView.findViewById(R.id.mngr_main_dev_name);
            date = (TextView) itemView.findViewById(R.id.mngr_main_date);
            rm             =(LinearLayout) itemView.findViewById(R.id.maintenancerow) ;


        }

    }

}
