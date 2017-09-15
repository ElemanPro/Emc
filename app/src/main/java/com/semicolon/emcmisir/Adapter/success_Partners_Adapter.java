package com.semicolon.emcmisir.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.semicolon.emcmisir.Activities.WebViiew;
import com.semicolon.emcmisir.App_URL;
import com.semicolon.emcmisir.Model.AdvertsmentModel;
import com.semicolon.emcmisir.R;
import com.squareup.picasso.Picasso;

import java.util.List;



public class success_Partners_Adapter extends RecyclerView.Adapter<success_Partners_Adapter.ViewHolder> {

    Context mContext;
    private List<AdvertsmentModel> list_success_Partners;
    private LayoutInflater inflater;

    public success_Partners_Adapter(Context mContext, List<AdvertsmentModel> list_success_Partners) {
        this.mContext = mContext;
        this.list_success_Partners = list_success_Partners;
        inflater =LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.success_partners_row,parent,false);
        final ViewHolder vHolder = new ViewHolder(view);
        vHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, WebViiew.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("link",list_success_Partners.get(vHolder.getLayoutPosition()).getAds_details().toString());
                mContext.startActivity(intent);
            }
        });
        return vHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.success_partner_Name.setText(list_success_Partners.get(position).getAds_name().toString());
        Picasso.with(mContext).load(App_URL.image_url+list_success_Partners.get(position).getAds_image().toString()).noFade().into(holder.success_partner_Image);

    }

    @Override
    public int getItemCount() {
        return list_success_Partners.size();
    }
      class ViewHolder extends RecyclerView.ViewHolder {
          private TextView success_partner_Name;
          private ImageView success_partner_Image;
        public ViewHolder(View itemView) {
            super(itemView);
            success_partner_Name = (TextView) itemView.findViewById(R.id.success_partner_name);
            success_partner_Image= (ImageView) itemView.findViewById(R.id.success_partner_image);
        }
    }
}
