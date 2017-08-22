package com.example.elashry.eleman.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.elashry.eleman.Adapter.Product_Adapter;
import com.example.elashry.eleman.App_URL;
import com.example.elashry.eleman.Controller;
import com.example.elashry.eleman.Model.Product_Model;
import com.example.elashry.eleman.R;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Search_ItemCategory_Activity extends AppCompatActivity {
    private MaterialSearchView mSearchView;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private List<Product_Model> pro_List;
    //private final String products_url ="http://semicolonsoft.com/clients/emc/api/find/products";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__item_category);
        init_View();
        Get_proData(App_URL.product_url);

    }

    private void init_View() {
        mSearchView = (MaterialSearchView)findViewById(R.id.searchView);
        mSearchView.showSearch(true);
        mToolbar    = (Toolbar) findViewById(R.id.search_toolBar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mRecyclerView = (RecyclerView) findViewById(R.id.search_recyView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Get_searchData(App_URL.product_url,newText);
                return false;
            }
        });
        mSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {
                finish();
            }
        });
    }
    private void Get_proData(String products_url) {

        JsonArrayRequest mJsonArrayRequest = new JsonArrayRequest(products_url,
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

                                    pro_List.add(new Product_Model(object.get("product_id_pk").toString(),object.get("cat_id_fk").toString(),object.get("product_title").toString(),object.get("product_photo").toString()));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        if (pro_List.size()>0)
                        {
                            Product_Adapter adapter = new Product_Adapter(Search_ItemCategory_Activity.this,pro_List);
                            mRecyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();


                        }
                        else if (pro_List.size()==0)
                        {

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
    private void Get_searchData(String products_url, final String text_search) {

        JsonArrayRequest mJsonArrayRequest = new JsonArrayRequest(products_url,
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
                                if (object.get("product_title").toString().startsWith(text_search)) {
                                    pro_List.add(new Product_Model(object.get("product_id_pk").toString(),object.get("cat_id_fk").toString(),object.get("product_title").toString(),object.get("product_photo").toString()));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        if (pro_List.size()>0)
                        {
                            Product_Adapter adapter = new Product_Adapter(Search_ItemCategory_Activity.this,pro_List);
                            mRecyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();


                        }
                        else if (pro_List.size()==0)
                        {

                            Product_Adapter adapter = new Product_Adapter(Search_ItemCategory_Activity.this,pro_List);
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
