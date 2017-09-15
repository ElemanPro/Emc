package com.semicolon.emcmisir.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.semicolon.emcmisir.Adapter.MatgarAdapter;
import com.semicolon.emcmisir.App_URL;
import com.semicolon.emcmisir.Controller;
import com.semicolon.emcmisir.Model.MatgarModel;
import com.semicolon.emcmisir.R;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Search_Matgar extends AppCompatActivity {

    private MaterialSearchView mSearchView;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private List<MatgarModel> matgarModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__matgar);
        init_View();
        Get_matagar_pro_Data();
        mSearchView.showSearch(true);
    }
    private void init_View() {

        mSearchView = (MaterialSearchView)findViewById(R.id.search_matgar_searchView);
        mToolbar    = (Toolbar) findViewById(R.id.search_matgar_search_toolBar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mRecyclerView = (RecyclerView) findViewById(R.id.search_matgar_search_recyView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {
                finish();
            }
        });
        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Get_matagar_searchdata(App_URL.app_matgar,newText);
                return false;
            }
        });


    }
    private void Get_matagar_pro_Data() {
        JsonArrayRequest mJsonArrayRequest = new JsonArrayRequest(App_URL.app_matgar,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("data",response.toString());
                        JSONObject object;
                        matgarModelList = new ArrayList<>();

                        for (int index=0;index<response.length();index++)
                        {
                            try {
                                object =response.getJSONObject(index);
                                MatgarModel matgarModel = new MatgarModel(object.get("matgar_pk").toString(),object.get("client_name").toString(),object.get("client_details").toString(),object.get("product_name").toString(),object.get("product_price").toString(),object.get("product_image").toString(),object.get("date_add").toString());
                                matgarModelList.add(matgarModel);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        if (matgarModelList.size()>0)
                        {
                            MatgarAdapter adapter = new MatgarAdapter(Search_Matgar.this,matgarModelList);
                            mRecyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();



                        }
                        else if (matgarModelList.size()==0)
                        {
                            Toast.makeText(Search_Matgar.this, "لايوجد نتائج لعرضها", Toast.LENGTH_SHORT).show();
                            MatgarAdapter adapter = new MatgarAdapter(Search_Matgar.this,matgarModelList);
                            mRecyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

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
    private void Get_matagar_searchdata(String matgar_url, final String text_search) {
        JsonArrayRequest mJsonArrayRequest = new JsonArrayRequest(matgar_url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("data",response.toString());
                        JSONObject object;
                        matgarModelList = new ArrayList<>();

                        for (int index=0;index<response.length();index++)
                        {
                            try {
                                object =response.getJSONObject(index);
                                if (object.get("product_name").toString().startsWith(text_search.toString())) {
                                    MatgarModel matgarModel = new MatgarModel(object.get("matgar_pk").toString(),object.get("client_name").toString(), object.get("client_details").toString(), object.get("product_name").toString(), object.get("product_price").toString(), object.get("product_image").toString(), object.get("date_add").toString());
                                    matgarModelList.add(matgarModel);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        if (matgarModelList.size()>0)
                        {
                            MatgarAdapter adapter = new MatgarAdapter(Search_Matgar.this,matgarModelList);
                            mRecyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();



                        }
                        else if (matgarModelList.size()==0)

                        {
                            MatgarAdapter adapter = new MatgarAdapter(Search_Matgar.this,matgarModelList);
                            mRecyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
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
}
