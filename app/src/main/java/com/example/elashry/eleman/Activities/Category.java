package com.example.elashry.eleman.Activities;

import android.content.Intent;
import android.os.Bundle;
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
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.elashry.eleman.Adapter.success_Partners_Adapter;
import com.example.elashry.eleman.App_URL;
import com.example.elashry.eleman.Controller;
import com.example.elashry.eleman.Model.AdvertsmentModel;
import com.example.elashry.eleman.Model.Success_Partners_Model;
import com.example.elashry.eleman.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Category extends AppCompatActivity  implements  BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    HashMap<String, String> file_maps;
    private SliderLayout mDemoSlider;
    private TextView sp_tv;
    private final String ads_url =App_URL.advertisement;
    private List<AdvertsmentModel> adsModelList;
    public static ArrayList<String> names,imges,links;
    public static List<HashMap<String,String>> adv_list;
    private Toolbar mCat_ToolBar;
    ImageView img ,img2 ,img4,img5,img6;
    LinearLayout about,cut;
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
       // img6 = (ImageView) findViewById(R.id.img6);
        about = (LinearLayout) findViewById(R.id.about);
        cut = (LinearLayout) findViewById(R.id.cut);


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Category.this,Maintenance.class);
                startActivity(i);
            }
        });


        cut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Category.this,ItemCategory.class);
                startActivity(i);
            }
        });

        /*img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Category.this,ADS.class);
                startActivity(i);
            }
        });*/
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Category.this,Matgar.class);
                startActivity(i);
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Category.this,Contact.class);
                startActivity(i);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Category.this,About.class);
                startActivity(i);
            }
        });


    }

    private void init_View() {
        Get_Ads_Data();
        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        mCat_ToolBar = (Toolbar) findViewById(R.id.mCat_ToolBar);
        this.setSupportActionBar(mCat_ToolBar);

        List<Success_Partners_Model> list_Success_partners_models = new ArrayList<>();
        list_Success_partners_models.add(new Success_Partners_Model("وكيل صيانه يونيون اير","emad.png"));
        list_Success_partners_models.add(new Success_Partners_Model("وكيل صيانه توشيبا العربي","mohammed.png"));
        list_Success_partners_models.add(new Success_Partners_Model("وكيل صيانه ال جي","ahmed.png"));
        list_Success_partners_models.add(new Success_Partners_Model("وكيل صيانه عبوده العشماوي","ali.png"));
        list_Success_partners_models.add(new Success_Partners_Model("وكيل صيانه ","abdallah.png"));

        success_Partners_recyclerView = (RecyclerView) findViewById(R.id.success_Partners_recyclerView);
        success_Partners_recyclerView.setNestedScrollingEnabled(false);
        success_Partners_recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayout.HORIZONTAL,false));
        success_Partners_recyclerView.setHasFixedSize(true);
        success_Partners_recyclerView.setAdapter(new success_Partners_Adapter(this,list_Success_partners_models));

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
                        JSONObject object;
                        adsModelList = new ArrayList<>();
                        /*names = new ArrayList<>();
                        imges = new ArrayList<>();
                        links = new ArrayList<>();*/
                        for (int index=0;index<response.length();index++)
                        {
                            try {

                                object =response.getJSONObject(index);

                                AdvertsmentModel adsModel = new AdvertsmentModel(object.get("ads_name").toString(),object.get("ads_detailes").toString(),object.get("ads_images").toString(),object.get("ads_date_add").toString());
                                adsModelList.add(adsModel);

                            } catch (JSONException e) {
                                e.printStackTrace();
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
