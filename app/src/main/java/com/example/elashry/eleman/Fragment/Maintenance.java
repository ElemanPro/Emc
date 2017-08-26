package com.example.elashry.eleman.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.elashry.eleman.Activities.Check_Internet_connection;
import com.example.elashry.eleman.Adapter.MaintenanceAdapter;
import com.example.elashry.eleman.App_URL;
import com.example.elashry.eleman.Controller;
import com.example.elashry.eleman.Model.MaintenanceModel;
import com.example.elashry.eleman.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.elashry.eleman.Adapter.MaintenanceAdapter.mflag;

/**
 * Created by Delta on 06/08/2017.
 */

public class Maintenance extends Fragment {

    private LinearLayout pbc;
    private TextView no_main_txt;
    private RecyclerView mrRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;
    private Context mContext;
    private List<MaintenanceModel> maintenance_List;
    //private final String maintenance_url ="http://semicolonsoft.com/clients/emc/api/find/app_maintenance";
    private ProgressBar prog_bar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.maintenance,container,false);
        init_View(view);

        boolean isConnected = Check_Network();
        if (isConnected==true)
        {
            Get_Maintenance_Data(App_URL.app_maintenance);        }
        else
        {
            Intent intent = new Intent(mContext, Check_Internet_connection.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("flag","2");
            mContext.startActivity(intent);
        }
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Get_Maintenance_Data(App_URL.app_maintenance);
            }
        });
        return view;
    }
    private void Get_Maintenance_Data(String maintenance_url) {

        JsonArrayRequest mJsonArrayRequest = new JsonArrayRequest(maintenance_url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("data",response.toString());
                        JSONObject object;
                        maintenance_List = new ArrayList<>();

                        for (int index=0;index<response.length();index++)
                        {
                            try {
                                object = response.getJSONObject(index);
                                maintenance_List.add(new MaintenanceModel(object.get("client_name").toString(),object.get("client_phone").toString(),object.get("client_location").toString(),object.get("device_type").toString(),object.get("warranty_state").toString(),object.get("device_brand").toString(),object.get("damage_type").toString(),object.get("order_date").toString()));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        if (maintenance_List.size()>0)
                        {
                            MaintenanceAdapter adapter = new MaintenanceAdapter(mContext,maintenance_List);
                            mrRecyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            mrRecyclerView.setVisibility(View.VISIBLE);
                            pbc.setVisibility(View.GONE);
                            no_main_txt.setVisibility(View.GONE);
                            mRefreshLayout.setRefreshing(false);
                        }
                        else if (maintenance_List.size()==0)
                        {
                            mrRecyclerView.setVisibility(View.GONE);
                            pbc.setVisibility(View.GONE);
                            no_main_txt.setVisibility(View.VISIBLE);
                            mRefreshLayout.setRefreshing(false);
                        }

                    }
                }
                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mRefreshLayout.setRefreshing(false);
                    }
                }
        );
        Controller.getInstance().addToRequestQueue(mJsonArrayRequest,"json array req");
    }
    private void init_View(View view) {
        mContext =view.getContext();
        pbc            = (LinearLayout) view.findViewById(R.id.progressBar_container);
        pbc.setVisibility(View.VISIBLE);
        prog_bar       = (ProgressBar) view.findViewById(R.id.m_progBar);
        prog_bar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(mContext,R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        no_main_txt    = (TextView) view.findViewById(R.id.no_main_txt);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swap_refresh);
        mRefreshLayout.setColorSchemeColors(ContextCompat.getColor(mContext,R.color.colorPrimary));
        mrRecyclerView = (RecyclerView) view.findViewById(R.id.maintenance_RecyView);
        mrRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));




    }
    private boolean Check_Network()
    {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
        boolean mobile_data = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();

        if (!wifi&&!mobile_data)
        {
            return false;
        }
        else
        {
            return true;
        }

    }

}

