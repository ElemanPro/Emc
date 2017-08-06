package com.example.elashry.eleman.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.elashry.eleman.Model.MaintenanceModel;
import com.example.elashry.eleman.R;

import java.util.List;

/**
 * Created by Delta on 06/08/2017.
 */

public class Maintenance extends Fragment {

    private RecyclerView mrRecyclerView;
    private Context mContext;
    private List<MaintenanceModel> order_List;
    private final String maintenance_url ="http://semicolonsoft.com/app/api/find/maintenance";
    private TextView nopro_txt;
    private LinearLayout progBar_container;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.maintenance,container,false);
        init_View(view);
        Get_ordData(maintenance_url);
        return view;
    }
    private void Get_ordData(String maintenance_url) {

      //  new Maintenance.Asyn_task().execute(maintenance_url);
    }
    private void init_View(View view) {
        mContext =view.getContext();
        mrRecyclerView = (RecyclerView) view.findViewById(R.id.maintenance_RecyView);
        mrRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mrRecyclerView.setVisibility(View.GONE);

       /* progBar_container = (LinearLayout) view.findViewById(R.id.progressBar_container);
        progBar_container.setVisibility(View.VISIBLE);
*/

    }
   /* private class Asyn_task extends AsyncTask<String, Void,Void>
    {

        @Override
        protected Void doInBackground(String... strings) {
            JsonArrayRequest mJsonArrayRequest = new JsonArrayRequest(strings[0],
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Log.e("data",response.toString());
                            JSONObject object;
                            order_List = new ArrayList<>();

                            for (int index=0;index<response.length();index++)
                            {
                                try {
                                    object =response.getJSONObject(index);
                                    if (object.get("cat_id_fk").toString().equals("2"))
                                    {
                                        order_List.add(new MaintenanceModel(object.get("client_name").toString(),object.get("client_phone").toString(),object.get("client_location").toString(),object.get("device_type").toString(),object.get("warranty_state").toString(),object.get("device_brand").toString(),object.get("damage_type").toString(),object.get("order_date").toString()));
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (order_List.size()>0)
                            {
                               *//* mrRecyclerView.setVisibility(View.VISIBLE);
                                progBar_container.setVisibility(View.GONE);
                         *//*   }
                            else if (order_List.size()==0)
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

    }*/
}

