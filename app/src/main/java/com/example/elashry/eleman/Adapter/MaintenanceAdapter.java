package com.example.elashry.eleman.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.elashry.eleman.Activities.ShowMaintenance_Details;
import com.example.elashry.eleman.Model.MaintenanceModel;
import com.example.elashry.eleman.R;

import java.util.List;

/**
 * Created by elashry on 8/6/2017.
 */

public class MaintenanceAdapter extends RecyclerView.Adapter <MaintenanceAdapter.ViewHoler>{

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
        holder.devType.setText(maintenance_List.get(position).getDtype().toString());
        holder.warranty_state.setText(maintenance_List.get(position).getWstate().toString());
        holder.date.setText(maintenance_List.get(position).getOdate().toString());
    }

    @Override
    public int getItemCount() {
        return maintenance_List.size();
    }


    class ViewHoler extends RecyclerView.ViewHolder{

        TextView client_name,devType,warranty_state,date;
        public ViewHoler(View itemView) {
            super(itemView);

            client_name = (TextView) itemView.findViewById(R.id.manager_main_client_name);
            devType = (TextView) itemView.findViewById(R.id.manager_main_devType);
            warranty_state = (TextView) itemView.findViewById(R.id.manager_main_warranty_state);
            date = (TextView) itemView.findViewById(R.id.manager_main_date);
        }

    }

}
