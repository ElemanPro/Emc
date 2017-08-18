package com.example.elashry.eleman.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.elashry.eleman.Activities.ShowOrder_Details;
import com.example.elashry.eleman.App_URL;
import com.example.elashry.eleman.Controller;
import com.example.elashry.eleman.Model.OrderModel;
import com.example.elashry.eleman.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by elashry on 8/6/2017.
 */

public class OrderAdapter extends RecyclerView.Adapter <OrderAdapter.ViewHoler>{

    Context mContext;
    LayoutInflater inflater;
    //private final String products_url ="http://semicolonsoft.com/clients/emc/api/find/products";
    private List<OrderModel> order_List;

    public OrderAdapter(Context mContext, List<OrderModel> pro_List) {
        this.mContext = mContext;
        this.order_List =pro_List;
        inflater = LayoutInflater.from(mContext);
    }



    @Override
    public OrderAdapter.ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.manager_order_row,parent,false);
        OrderAdapter.ViewHoler holer = new OrderAdapter.ViewHoler(view);
        holer.progBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(mContext,R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        return holer;
    }

    @Override
    public void onBindViewHolder(final OrderAdapter.ViewHoler holder, int position) {
        OrderModel orderModel = new OrderModel(order_List.get(position).getOrder_id(),order_List.get(position).getProduct_id(),order_List.get(position).getQuantity(),order_List.get(position).getClient_name(),order_List.get(position).getClient_phone(),order_List.get(position).getOrder_date(),order_List.get(position).getOrder_address());
        holder.c_name.setText(order_List.get(position).getClient_name().toString());
        holder.order_date.setText(order_List.get(position).getOrder_date().toString());
        GetProData(holder,orderModel);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderModel orderModel = new OrderModel(order_List.get(holder.getLayoutPosition()).getOrder_id(),order_List.get(holder.getLayoutPosition()).getProduct_id(),order_List.get(holder.getLayoutPosition()).getQuantity(),order_List.get(holder.getLayoutPosition()).getClient_name(),order_List.get(holder.getLayoutPosition()).getClient_phone(),order_List.get(holder.getLayoutPosition()).getOrder_date(),order_List.get(holder.getLayoutPosition()).getOrder_address());
                Intent intent = new Intent(mContext, ShowOrder_Details.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("order_data",orderModel);
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return order_List.size();
    }

    class ViewHoler extends RecyclerView.ViewHolder{
        private TextView c_name,dev_name,order_date;
        private ImageView dev_image;
        private ProgressBar progBar;
        public ViewHoler(View itemView) {
            super(itemView);

            c_name       = (TextView) itemView.findViewById(R.id.mngr_order_clientname);
            dev_image    = (ImageView) itemView.findViewById(R.id.mngr_order_dev_image);
            dev_name     = (TextView) itemView.findViewById(R.id.mngr_order_dev_name);
            order_date   = (TextView) itemView.findViewById(R.id.mngr_order_date);
            progBar      = (ProgressBar) itemView.findViewById(R.id.mnger_order_progressBar);
        }

    }

    private void GetProData(final ViewHoler holder , final OrderModel orderModel)
    {
        JsonArrayRequest mJsonArrayRequest = new JsonArrayRequest(App_URL.product_url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("data",response.toString());
                        JSONObject object;
                        for (int index=0;index<response.length();index++)
                        {
                            try {
                                object =response.getJSONObject(index);
                                if (object.get("product_id_pk").toString().equals(orderModel.getProduct_id().toString()))
                                {
                                    holder.dev_name.setText(object.get("product_title").toString());
                                    Picasso.with(mContext).load(App_URL.image_url+object.get("product_photo").toString()).noFade().into(holder.dev_image);
                                    holder.dev_image.setVisibility(View.VISIBLE);
                                    holder.progBar.setVisibility(View.GONE);
                                   /* asyn_task task = new asyn_task(holder);
                                    task.execute(object.get("product_image").toString());*/




                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }
                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        Controller.getInstance().addToRequestQueue(mJsonArrayRequest,"json array req");

    }
    class asyn_task extends AsyncTask<String ,Void,Bitmap> {
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
            holer.dev_image.setImageBitmap(bitmap);
            holer.dev_image.setVisibility(View.VISIBLE);
            holer.progBar.setVisibility(View.GONE);
        }
    }
}
