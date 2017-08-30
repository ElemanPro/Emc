package com.example.elashry.eleman.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.elashry.eleman.Activities.Order;
import com.example.elashry.eleman.Activities.Zooming_Image;
import com.example.elashry.eleman.App_URL;
import com.example.elashry.eleman.Model.Image_details_Model;
import com.example.elashry.eleman.Model.Product_Model;
import com.example.elashry.eleman.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Delta on 01/08/2017.
 */

public class Product_Adapter extends RecyclerView.Adapter <Product_Adapter.ViewHoler>{

    private Context mContext;
    LayoutInflater inflater;
    private List<Product_Model> pro_List;

    public Product_Adapter(Context mContext, List<Product_Model> pro_List) {
        this.mContext = mContext;
        this.pro_List =pro_List;
       inflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.product_row,parent,false);
        ViewHoler holer = new ViewHoler(view);
        holer.prog_bar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(mContext,R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        return holer;
    }

    @Override
    public void onBindViewHolder(final ViewHoler holder, final int position) {

        Picasso.with(mContext).load(App_URL.image_url+pro_List.get(position).getProduct_Image_url().toString()).noFade().into(holder.product_image);
        holder.prog_bar.setVisibility(View.GONE);
        holder.product_image.setVisibility(View.VISIBLE);
        holder.product_categ.setText(pro_List.get(position).getProduct_Category_fk().toString());
        holder.product_name.setText(pro_List.get(position).getProduct_title().toString());
        if (pro_List.get(position).getProduct_price().isEmpty())
        {

        }
        else
            {
                holder.product_price.setText(pro_List.get(position).getProduct_price().toString()+" "+"جنيه");
            }



        if (pro_List.get(position).getProduct_Category_fk().toString().equals("1"))
        {
         holder.categ_icon.setImageResource(R.mipmap.washer);
            holder.product_categ.setText("غسالات");
        }
        else if (pro_List.get(position).getProduct_Category_fk().toString().equals("2"))
        {
            holder.categ_icon.setImageResource(R.mipmap.fridg);
            holder.product_categ.setText("تلاجات");

        }
        else if (pro_List.get(position).getProduct_Category_fk().toString().equals("3"))
        {
            holder.categ_icon.setImageResource(R.mipmap.oven);
            holder.product_categ.setText("بوتاجازات");

        }
        else if (pro_List.get(position).getProduct_Category_fk().toString().equals("4"))
        {
            holder.categ_icon.setImageResource(R.mipmap.tv);
            holder.product_categ.setText("تيليفزيونات");
        }
        else if (pro_List.get(position).getProduct_Category_fk().toString().equals("5"))
        {
            holder.categ_icon.setImageResource(R.mipmap.screen);
            holder.product_categ.setText("شاشات");
        }
        else if (pro_List.get(position).getProduct_Category_fk().toString().equals("6"))
        {
            holder.categ_icon.setImageResource(R.mipmap.takief);
            holder.product_categ.setText("تكييفات");

        }
        holder.product_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, Zooming_Image.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Image_details_Model model = new Image_details_Model(pro_List.get(position).getProduct_title(),pro_List.get(position).getProduct_Image_url().toString());
                intent.putExtra("image_details",model);
                intent.putExtra("flag","1");
                mContext.startActivity(intent);
            }
        });
        holder.product_popmenu_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(mContext,view);
                popupMenu.getMenuInflater().inflate(R.menu.pro_reg_menu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId()==R.id.pro_reg_menu)
                        {
                            Product_Model model = pro_List.get(position);
                            Intent intent = new Intent(mContext, Order.class);
                            intent.putExtra("pro_data",model);
                            intent.putExtra("flag","0");
                            mContext.startActivity(intent);
                            return true;

                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return pro_List.size();
    }
    class ViewHoler extends RecyclerView.ViewHolder{
        ImageView product_image,categ_icon,product_popmenu_icon;
        TextView product_categ,product_name,product_price;
        ProgressBar prog_bar;
        public ViewHoler(View itemView) {
            super(itemView);
            product_image        = (ImageView) itemView.findViewById(R.id.product_image);
            categ_icon           = (ImageView) itemView.findViewById(R.id.categ_icon);
            product_categ        = (TextView) itemView.findViewById(R.id.product_categ);
            product_name         = (TextView) itemView.findViewById(R.id.product_name);
            product_price         = (TextView) itemView.findViewById(R.id.product_price);
            prog_bar             = (ProgressBar) itemView.findViewById(R.id.prog_bar);
            product_popmenu_icon = (ImageView) itemView.findViewById(R.id.product_popmenu_icon);
        }

    }


}
