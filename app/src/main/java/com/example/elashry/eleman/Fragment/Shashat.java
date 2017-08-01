package com.example.elashry.eleman.Fragment;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.elashry.eleman.Adapter.Washer_Adapter;
import com.example.elashry.eleman.Controller;
import com.example.elashry.eleman.Model.Product_Model;
import com.example.elashry.eleman.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Shashat extends Fragment {
    private RecyclerView mrRecyclerView;
    private Context mContext;
    private List<Product_Model> pro_List;
    private final String products_url ="http://semicolonsoft.com/app/api/find/products";
    private TextView nopro_txt;
    private LinearLayout progBar_container;


    public Shashat() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.shashat,container,false);
        init_View(view);
        Get_proData(products_url);
        return view;
    }
    private void Get_proData(String products_url) {

        new Asyn_task().execute(products_url);
    }
    private void init_View(View view) {
        mContext =view.getContext();
        mrRecyclerView = (RecyclerView) view.findViewById(R.id.shashat_recyView);
        mrRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mrRecyclerView.setVisibility(View.GONE);

        progBar_container = (LinearLayout) view.findViewById(R.id.progressBar_container);
        progBar_container.setVisibility(View.VISIBLE);

        nopro_txt         = (TextView) view.findViewById(R.id.nopro_txt);

    }
    private class Asyn_task extends AsyncTask<String, Void,Void>
    {

        @Override
        protected Void doInBackground(String... strings) {
            JsonArrayRequest mJsonArrayRequest = new JsonArrayRequest(strings[0],
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Log.e("data",response.toString());
                            JSONObject object;
                            pro_List = new ArrayList<>();

                            for (int index=0;index<response.length();index++)
                            {
                                try {
                                    object =response.getJSONObject(index);
                                    if (object.get("cat_id_fk").toString().equals("5"))
                                    {
                                        pro_List.add(new Product_Model("شاشات",object.get("ptoduct_name").toString(),object.get("product_price").toString(),object.get("product_image").toString()));
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (pro_List.size()>0)
                            {
                                Washer_Adapter adapter = new Washer_Adapter(mContext,pro_List);
                                mrRecyclerView.setAdapter(adapter);
                                mrRecyclerView.setVisibility(View.VISIBLE);
                                progBar_container.setVisibility(View.GONE);
                            }
                            else if (pro_List.size()==0)
                            {
                                nopro_txt.setVisibility(View.VISIBLE);
                                mrRecyclerView.setVisibility(View.GONE);
                                progBar_container.setVisibility(View.GONE);
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
            return null;
        }

    }

}
