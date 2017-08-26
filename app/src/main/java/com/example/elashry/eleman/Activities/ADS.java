package com.example.elashry.eleman.Activities;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import com.example.elashry.eleman.Controller;
import com.example.elashry.eleman.R;
import com.example.elashry.eleman.Adapter.AdapterAds;
import com.example.elashry.eleman.Model.AdsModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ADS extends AppCompatActivity {

    RequestQueue queue;
    String url = "http://semicolonsoft.com/clients/emc/api/find/advertisement";
    RecyclerView recyclerView;
    List<AdsModel> feedsList = new ArrayList<AdsModel>();
    AdapterAds adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads);
        //Initialize RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new AdapterAds(this, feedsList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);
        //Getting Instance of Volley Request Queue
        queue = Controller.getInstance().getRequestQueue();
        //Volley's inbuilt class to make Json array request
        JsonArrayRequest newsReq = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject obj = response.getJSONObject(i);
                        AdsModel feeds = new AdsModel(obj.getString("ads_images"), obj.getString("ads_name"),obj.getString("ads_detailes"));

                        // adding movie to movies array
                        feedsList.add(feeds);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    } finally {
                        //Notify adapter about data changes
                        adapter.notifyItemChanged(i);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
            }
        });
        //Adding JsonArrayRequest to Request Queue
        queue.add(newsReq);

    }

    private void Network_aviliable()
    {
        ConnectivityManager cm = (ConnectivityManager)ADS.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
        boolean data = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();

        if (!wifi && !data)
        {
            startActivity(new Intent(this, Check_Internet_connection.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

        }
        else {
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        Network_aviliable();
    }

}
