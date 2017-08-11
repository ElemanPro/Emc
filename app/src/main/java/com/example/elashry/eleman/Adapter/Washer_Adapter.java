package com.example.elashry.eleman.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.elashry.eleman.Model.Product_Model;
import com.example.elashry.eleman.R;

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
        return holer;
    }

    @Override
    public void onBindViewHolder(ViewHoler holder, int position) {

       // Picasso.with(mContext).load(Uri.parse(pro_List.get(position).getPro_Image_url().toString())).into(holder.product_image);
        asyn_task task = new asyn_task(holder);
        task.execute(pro_List.get(position).getPro_Image_url().toString());
      //  holder.product_image.setImageBitmap(new asyn_task(holder).execute();
        holder.product_categ.setText(pro_List.get(position).getPro_Categ().toString());
        holder.product_name.setText(pro_List.get(position).getPro_Name().toString());
        holder.product_price.setText(pro_List.get(position).getPro_Price().toString()+" LE");

        if (pro_List.get(position).getPro_Categ().toString().equals("غسالات"))
        {
         holder.categ_icon.setImageResource(R.mipmap.washer);
        }
        else if (pro_List.get(position).getPro_Categ().toString().equals("تلاجات"))
        {
            holder.categ_icon.setImageResource(R.mipmap.fridg);

        }
        else if (pro_List.get(position).getPro_Categ().toString().equals("بوتاجازات"))
        {
            holder.categ_icon.setImageResource(R.mipmap.oven);

        }
        else if (pro_List.get(position).getPro_Categ().toString().equals("تيليفزيونات"))
        {
            holder.categ_icon.setImageResource(R.mipmap.tv);
        }
        else if (pro_List.get(position).getPro_Categ().toString().equals("شاشات"))
        {
            holder.categ_icon.setImageResource(R.mipmap.screen);
        }
        else if (pro_List.get(position).getPro_Categ().toString().equals("تكييفات"))
        {
            holder.categ_icon.setImageResource(R.mipmap.takief);

        }

    }

    @Override
    public int getItemCount() {
        return pro_List.size();
    }
    class ViewHoler extends RecyclerView.ViewHolder{
        ImageView product_image,categ_icon;
        TextView product_categ;
        TextView product_name;
        TextView product_price;
        public ViewHoler(View itemView) {
            super(itemView);
            product_image     = (ImageView) itemView.findViewById(R.id.product_image);
            categ_icon        = (ImageView) itemView.findViewById(R.id.categ_icon);
            product_categ     = (TextView) itemView.findViewById(R.id.product_categ);
            product_name      = (TextView) itemView.findViewById(R.id.product_name);
            product_price     = (TextView) itemView.findViewById(R.id.product_price);


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
        }
    }
}
