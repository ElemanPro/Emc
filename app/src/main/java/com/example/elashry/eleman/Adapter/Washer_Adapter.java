package com.example.elashry.eleman.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.elashry.eleman.Activities.Zooming_Image;
import com.example.elashry.eleman.App_URL;
import com.example.elashry.eleman.Model.Image_details_Model;
import com.example.elashry.eleman.Model.Product_Model;
import com.example.elashry.eleman.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
        holer.prog_bar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(mContext,R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        return holer;
    }

    @Override
    public void onBindViewHolder(ViewHoler holder, final int position) {

        Picasso.with(mContext).load(App_URL.image_url+pro_List.get(position).getProduct_Image_url().toString()).noFade().into(holder.product_image);
        holder.prog_bar.setVisibility(View.GONE);
        holder.product_image.setVisibility(View.VISIBLE);
        holder.product_categ.setText(pro_List.get(position).getProduct_Category_fk().toString());
        holder.product_name.setText(pro_List.get(position).getProduct_title().toString());



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

    }

    @Override
    public int getItemCount() {
        return pro_List.size();
    }
    class ViewHoler extends RecyclerView.ViewHolder{
        ImageView product_image,categ_icon;
        TextView product_categ,product_name;
        ProgressBar prog_bar;
        public ViewHoler(View itemView) {
            super(itemView);
            product_image     = (ImageView) itemView.findViewById(R.id.product_image);
            categ_icon        = (ImageView) itemView.findViewById(R.id.categ_icon);
            product_categ     = (TextView) itemView.findViewById(R.id.product_categ);
            product_name      = (TextView) itemView.findViewById(R.id.product_name);
            prog_bar          = (ProgressBar) itemView.findViewById(R.id.prog_bar);

        }

    }

    class asyn_task extends AsyncTask<String ,Void,Bitmap>{
        ViewHoler holer;
        URL url =null;
        InputStream input = null;
        HttpURLConnection urlConnection=null;
        Bitmap bitmap=null;
        public asyn_task(ViewHoler holer) {
            this.holer = holer;
        }

        @Override
        protected void onPreExecute() {


        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                input = urlConnection.getInputStream();
                bitmap = BitmapFactory.decodeStream(input);
                return bitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            holer.product_image.setImageBitmap(bitmap);
            holer.prog_bar.setVisibility(View.GONE);
            holer.product_image.setVisibility(View.VISIBLE);
        }
    }
}
