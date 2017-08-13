package com.example.elashry.eleman.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.elashry.eleman.Adapter.ViewPagerAdapter;
import com.example.elashry.eleman.Controller;
import com.example.elashry.eleman.Fragment.Botgaaz;
import com.example.elashry.eleman.Fragment.Fridge;
import com.example.elashry.eleman.Fragment.Shashat;
import com.example.elashry.eleman.Fragment.Takief;
import com.example.elashry.eleman.Fragment.Televtion;
import com.example.elashry.eleman.Fragment.Washer;
import com.example.elashry.eleman.Model.Product_Model;
import com.example.elashry.eleman.R;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class  ItemCategory extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout mTab;
    private Toolbar mToolbar;
    private List<String> suggestion;
    private List<Product_Model> pro_List;
    private MaterialSearchView mSearchView;
    private final String products_url ="http://semicolonsoft.com/app/api/find/products";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_category);
        init_View();
        create_Tab();
        setUp_viewPager();
       // get_suggesions(products_url);

    }


    private void init_View() {
        viewPager   = (ViewPager) findViewById(R.id.viewpager);
        mTab        = (TabLayout) findViewById(R.id.mTab);
        mToolbar    = (Toolbar) findViewById(R.id.mToolBar);
        mSearchView = (MaterialSearchView) findViewById(R.id.item_categ_search_view);
        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                GetProductby_name(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                get_suggesions(products_url);
                return false;
            }
        });
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    private void create_Tab() {

        mTab.addTab(mTab.newTab().setText("غسالات").setIcon(R.mipmap.washing_machine));
        mTab.addTab(mTab.newTab().setText("تلاجات").setIcon(R.mipmap.fridge));
        mTab.addTab(mTab.newTab().setText("بوتاجازات").setIcon(R.mipmap.cooker));
        mTab.addTab(mTab.newTab().setText("تيليفزيونات").setIcon(R.drawable.televisions));
        mTab.addTab(mTab.newTab().setText("شاشات").setIcon(R.drawable.television));
        mTab.addTab(mTab.newTab().setText("تكييفات").setIcon(R.mipmap.air_condition));
        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition()==0)
                {
                    mTab.setSelectedTabIndicatorColor(ContextCompat.getColor(ItemCategory.this,R.color.index0));
                }
                else if (tab.getPosition()==1)
                {
                    mTab.setSelectedTabIndicatorColor(ContextCompat.getColor(ItemCategory.this,R.color.index1));

                }
                else if (tab.getPosition()==2)
                {
                    mTab.setSelectedTabIndicatorColor(ContextCompat.getColor(ItemCategory.this,R.color.index2));

                }
                else if (tab.getPosition()==3)
                {
                    mTab.setSelectedTabIndicatorColor(ContextCompat.getColor(ItemCategory.this,R.color.index3));

                }
                else if (tab.getPosition()==4)
                {
                    mTab.setSelectedTabIndicatorColor(ContextCompat.getColor(ItemCategory.this,R.color.index4));

                }
                else if (tab.getPosition()==5)
                {
                    mTab.setSelectedTabIndicatorColor(ContextCompat.getColor(ItemCategory.this,R.color.index5));

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void get_suggesions(final String products_url) {
        JsonArrayRequest mJsonArrayRequest = new JsonArrayRequest(products_url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("data",response.toString());
                        JSONObject object;
                        suggestion = new ArrayList<>();

                        for (int index=0;index<response.length();index++)
                        {
                            try {
                                object =response.getJSONObject(index);
                                if (object.get("cat_id_fk").toString().equals("1"))
                                {
                                    suggestion.add(object.get("ptoduct_name").toString());



                                }
                                else if (object.get("cat_id_fk").toString().equals("2"))
                                {
                                    suggestion.add(object.get("ptoduct_name").toString());



                                }
                                else if (object.get("cat_id_fk").toString().equals("3"))
                                {
                                    suggestion.add(object.get("ptoduct_name").toString());



                                }
                                else if (object.get("cat_id_fk").toString().equals("4"))
                                {
                                    suggestion.add(object.get("ptoduct_name").toString());



                                }
                                else if (object.get("cat_id_fk").toString().equals("5"))
                                {
                                    suggestion.add(object.get("ptoduct_name").toString());



                                }
                               else if (object.get("cat_id_fk").toString().equals("6"))
                                {
                                    suggestion.add(object.get("ptoduct_name").toString());


                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        if (suggestion.size()>0)
                        {
                            String [] sugggestions = suggestion.toArray(new String[suggestion.size()]);
                            mSearchView.setSuggestions(sugggestions);
                            suggestion.clear();
                        }
                        else if (suggestion.size()==0)
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
    private void setUp_viewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new Washer());
        adapter.addFragment(new Fridge());
        adapter.addFragment(new Botgaaz());
        adapter.addFragment(new Televtion());
        adapter.addFragment(new Shashat());
        adapter.addFragment(new Takief());




        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTab));
        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        MenuItem item = menu.findItem(R.id.item_categ_search);
        mSearchView.setMenuItem(item);
        return true;
    }

    private void GetProductby_name(final String pro_name)
    {
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
                                if (object.get("ptoduct_name").toString().equals(pro_name))
                                {
                                    pro_List.add(new Product_Model(object.get("cat_id_fk").toString(),object.get("ptoduct_name").toString(),object.get("product_price").toString(),object.get("product_image").toString()));

                                }



                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        if (pro_List.size()>0)
                        {
                            Product_Model model = pro_List.get(0);
                            Intent intent = new Intent(ItemCategory.this,Search_Product_Details.class);
                            intent.putExtra("pro_data",model);
                            startActivity(intent);
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

    @Override
    public void onBackPressed() {
        if (mSearchView.isSearchOpen())
        {
            mSearchView.closeSearch();
        }
        else{
        super.onBackPressed();
    }
    }



}
