package com.example.elashry.eleman.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elashry.eleman.Activities.ADS;
import com.example.elashry.eleman.Activities.Category;
import com.example.elashry.eleman.Activities.WebViiew;
import com.example.elashry.eleman.App_URL;
import com.example.elashry.eleman.R;

import com.android.volley.toolbox.NetworkImageView;
import com.example.elashry.eleman.Controller;
import com.example.elashry.eleman.Model.AdsModel;
import com.squareup.picasso.Picasso;


import java.util.List;

/**
 * Created by AndroidNovice on 6/5/2016.
 */
public class AdapterAds extends RecyclerView.Adapter<AdapterAds.MyViewHolder> implements View.OnClickListener{

    private List<AdsModel> feedsList;
    private Context context;
    private LayoutInflater inflater;
    ADS ads;

    public AdapterAds(Context context, List<AdsModel> feedsList) {

        this.context = context;
        this.feedsList = feedsList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = inflater.inflate(R.layout.item_ads, parent, false);
        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        AdsModel feeds = feedsList.get(position);
        //Pass the values of feeds object to Views
        Picasso.with(context).load(App_URL.image_url+feeds.getImg()).into(holder.imageview);

        holder.title.setText(feeds.getTitle());
        //     holder.content.setText(feeds.getContent());
    }

    @Override
    public int getItemCount() {
        return feedsList.size();
    }


    @Override
    public void onClick(View v) {

        int position = (int) v.getTag();

        AdsModel   feeds = feedsList.get(position);

      Toast t=  Toast.makeText(context, feeds.getLink() + "222", Toast.LENGTH_SHORT);
        View vt=t.getView();
        vt.setBackgroundColor(Color.CYAN);

        t.show();


        Intent i=new Intent(ads,WebViiew.class);

        i.putExtra("link",feeds.getLink());

        ads.startActivity(i);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView content, title;
        private ImageView imageview;
        private ProgressBar ratingbar;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageview = (ImageView) itemView.findViewById(R.id.img);

            title = (TextView) itemView.findViewById(R.id.title);
            //    content = (TextView) itemView.findViewById(R.id.content_view);
            // Volley's NetworkImageView which will load Image from URL


        }
    }

}