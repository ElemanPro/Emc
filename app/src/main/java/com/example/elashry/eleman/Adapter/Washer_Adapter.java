package com.example.elashry.eleman.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.elashry.eleman.Model.Product_Model;
import com.example.elashry.eleman.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Delta on 01/08/2017.
 */

public class Washer_Adapter extends RecyclerView.Adapter <Washer_Adapter.ViewHoler>{

    private Context mContext;
    LayoutInflater inflater;
    private List<Product_Model> pro_List;

    public Washer_Adapter(Context mContext,List<Product_Model> pro_List) {
        this.mContext = mContext;
        this.pro_List =pro_List;
       inflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.product_row,parent,false);
        ViewHoler holer = new ViewHoler(view);
        return holer;
    }

    @Override
    public void onBindViewHolder(ViewHoler holder, int position) {
        Picasso.with(mContext).load(Uri.parse(pro_List.get(position).getPro_Image_url().toString())).into(holder.product_image);
        holder.product_categ.setText(pro_List.get(position).getPro_Categ().toString());
        holder.product_name.setText(pro_List.get(position).getPro_Name().toString());
        holder.product_price.setText(pro_List.get(position).getPro_Price().toString());
    }

    @Override
    public int getItemCount() {
        return pro_List.size();
    }
    class ViewHoler extends RecyclerView.ViewHolder{
        ImageView product_image;
        TextView product_categ;
        TextView product_name;
        TextView product_price;
        public ViewHoler(View itemView) {
            super(itemView);
            product_image     = (ImageView) itemView.findViewById(R.id.product_image);
            product_categ     = (TextView) itemView.findViewById(R.id.product_categ);
            product_name      = (TextView) itemView.findViewById(R.id.product_name);
            product_price     = (TextView) itemView.findViewById(R.id.product_price);

        }

    }
}
