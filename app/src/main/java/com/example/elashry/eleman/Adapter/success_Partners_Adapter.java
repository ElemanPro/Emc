package com.example.elashry.eleman.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.elashry.eleman.Model.Success_Partners_Model;
import com.example.elashry.eleman.R;

import java.util.List;

/**
 * Created by Delta on 27/08/2017.
 */

public class success_Partners_Adapter extends RecyclerView.Adapter<success_Partners_Adapter.ViewHolder> {

    Context mContext;
    private List<Success_Partners_Model> list_success_Partners;
    private LayoutInflater inflater;

    public success_Partners_Adapter(Context mContext, List<Success_Partners_Model> list_success_Partners) {
        this.mContext = mContext;
        this.list_success_Partners = list_success_Partners;
        inflater =LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.success_partners_row,parent,false);
        ViewHolder vHolder = new ViewHolder(view);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.success_partner_Name.setText(list_success_Partners.get(position).getName().toString());
    }

    @Override
    public int getItemCount() {
        return list_success_Partners.size();
    }
      class ViewHolder extends RecyclerView.ViewHolder {
          private TextView success_partner_Name;
          //private CircleImageView success_partner_Image;
        public ViewHolder(View itemView) {
            super(itemView);
            success_partner_Name = (TextView) itemView.findViewById(R.id.success_partner_name);
            //success_partner_Image= (CircleImageView) itemView.findViewById(R.id.success_partner_image);
        }
    }
}