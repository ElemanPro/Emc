package com.semicolon.emcmisir.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.semicolon.emcmisir.Adapter.success_Partners_Adapter;
import com.semicolon.emcmisir.App_URL;
import com.semicolon.emcmisir.Controller;
import com.semicolon.emcmisir.Model.AdvertsmentModel;
import com.semicolon.emcmisir.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Category extends AppCompatActivity  implements  BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    private SliderLayout mDemoSlider;
    private final String ads_url =App_URL.advertisement;
    private List<AdvertsmentModel> adsModelList;
    private List<AdvertsmentModel> adsModelList_success_partner;
    public static ArrayList<String> names,imges,links;
    public static List<HashMap<String,String>> adv_list;
    private Toolbar mCat_ToolBar;
    private SwipeRefreshLayout categ_swip_refresh;
    ImageView img ,img2 ,img4,img5;
    LinearLayout linear_maintinance,linear_item_categ,linear_matgar,linear_contact,linear_about;
    private RecyclerView success_Partners_recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        init_View();

        img = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img4 = (ImageView) findViewById(R.id.img4);
        img5 = (ImageView) findViewById(R.id.img5);

    }

    private void init_View() {
        categ_swip_refresh = (SwipeRefreshLayout) findViewById(R.id.categ_swip_refresh);
        categ_swip_refresh.setNestedScrollingEnabled(false);
        categ_swip_refresh.setColorSchemeColors(ContextCompat.getColor(this,R.color.colorPrimary));
        categ_swip_refresh.setSoundEffectsEnabled(true);
        categ_swip_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Get_Ads_Data();
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
        Get_Ads_Data();
        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        mCat_ToolBar = (Toolbar) findViewById(R.id.mCat_ToolBar);
        this.setSupportActionBar(mCat_ToolBar);

        linear_maintinance = (LinearLayout) findViewById(R.id.linear_maintinance);
        linear_item_categ  = (LinearLayout)findViewById(R.id.linear_item_categ);
        linear_matgar      = (LinearLayout) findViewById(R.id.linear_matgar);
        linear_contact     = (LinearLayout) findViewById(R.id.linear_contact);
        linear_about       = (LinearLayout) findViewById(R.id.linear_about);
        linear_maintinance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Category.this,Maintenance.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        linear_item_categ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Category.this,ItemCategory.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
        linear_matgar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Category.this,Matgar.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
        linear_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Category.this,Contact.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
        linear_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Category.this,About.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        success_Partners_recyclerView = (RecyclerView) findViewById(R.id.success_Partners_recyclerView);
        success_Partners_recyclerView.setNestedScrollingEnabled(false);
        success_Partners_recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayout.HORIZONTAL,false));
        success_Partners_recyclerView.setHasFixedSize(true);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.manager_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.manager_menu)
        {
            startActivity(new Intent(Category.this, Login.class));
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
       // Toast.makeText(this, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(Category.this,WebViiew.class);
        i.putExtra( "link",slider.getBundle().get("extra")+"");
        startActivity(i);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    private void Get_Ads_Data() {
        JsonArrayRequest mJsonArrayRequest = new JsonArrayRequest(ads_url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("data",response.toString());
                        adv_list = new ArrayList<>();
                        adsModelList_success_partner = new ArrayList<>();
                        JSONObject object;
                        adsModelList = new ArrayList<>();

                        for (int index=0;index<response.length();index++)
                        {
                            try {

                                object =response.getJSONObject(index);
                                if (object.get("ads_type").toString().equals("ads"))
                                {
                                    AdvertsmentModel adsModel = new AdvertsmentModel(object.get("ads_name").toString(),object.get("ads_detailes").toString(),object.get("ads_images").toString(),object.get("ads_date_add").toString(),object.get("expiry_date").toString(),object.get("ads_type").toString());
                                    adsModelList.add(adsModel);
                                }
                                else if (object.get("ads_type").toString().equals("sponsor"))
                                {
                                    AdvertsmentModel adsModel = new AdvertsmentModel(object.get("ads_name").toString(),object.get("ads_detailes").toString(),object.get("ads_images").toString(),object.get("ads_date_add").toString(),object.get("expiry_date").toString(),object.get("ads_type").toString());
                                    adsModelList_success_partner.add(adsModel);
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                                categ_swip_refresh.setRefreshing(false);
                            }
                        }
                        if (adsModelList.size()>0)
                        {

                            Map<String,String> map = new HashMap<>();
                            Map<String,String> links = new HashMap<>();
                            for (int index=0;index<adsModelList.size();index++)
                            {
                                map.put(adsModelList.get(index).getAds_name().toString(),adsModelList.get(index).getAds_image().toString());
                                links.put(adsModelList.get(index).getAds_name().toString(),adsModelList.get(index).getAds_details().toString());

                            }
                            for (String name :map.keySet())
                            {
                                TextSliderView textSliderView = new TextSliderView(Category.this);
                                // initialize a SliderLayout
                                textSliderView.description(name.toString())
                                        .image(App_URL.image_url+map.get(name).toString())
                                        .setScaleType(BaseSliderView.ScaleType.Fit)
                                        .setOnSliderClickListener(Category.this);

                                textSliderView.bundle(new Bundle());
                                textSliderView.getBundle()
                                        .putString("extra", String.valueOf(links.get(name)));
                                    mDemoSlider.addSlider(textSliderView);


                            }
                            mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
                            mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                            mDemoSlider.setCustomAnimation(new DescriptionAnimation());
                            mDemoSlider.setDuration(4000);
                            mDemoSlider.addOnPageChangeListener(Category.this);
                            categ_swip_refresh.setRefreshing(false);
                        }
                        else
                            {
                                categ_swip_refresh.setRefreshing(false);
                            }
                        if (adsModelList_success_partner.size()>0)
                        {
                            success_Partners_Adapter  adapter = new success_Partners_Adapter(Category.this,adsModelList_success_partner);
                            success_Partners_recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            categ_swip_refresh.setRefreshing(false);
                        }
                        else
                            {
                                categ_swip_refresh.setRefreshing(false);
                            }

                    }
                }
                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        categ_swip_refresh.setRefreshing(false);
                    }
                }
        );
        Controller.getInstance().addToRequestQueue(mJsonArrayRequest,"json array req");
    }
    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);  }



}