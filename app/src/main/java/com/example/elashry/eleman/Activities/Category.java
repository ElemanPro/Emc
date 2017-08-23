package com.example.elashry.eleman.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.elashry.eleman.App_URL;
import com.example.elashry.eleman.Controller;
import com.example.elashry.eleman.Model.AdvertsmentModel;
import com.example.elashry.eleman.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Category extends AppCompatActivity  implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    HashMap<String, String> file_maps;
    private SliderLayout mDemoSlider;
    private final String ads_url =App_URL.advertisement;
    private List<AdvertsmentModel> adsModelList;
    public static ArrayList<String> names,imges,links;
    private Toolbar mCat_ToolBar;
    ImageView img ,img2 ,img3,img4,img5,img6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        init_View();
        img = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        img4 = (ImageView) findViewById(R.id.img4);
        img5 = (ImageView) findViewById(R.id.img5);
        img6 = (ImageView) findViewById(R.id.img6);


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Category.this,Maintenance.class);
                startActivity(i);
            }
        });


        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Category.this,ItemCategory.class);
                startActivity(i);
            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Category.this,MapsActivity.class);
                startActivity(i);
            }
        });
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
        img6.setOnClickListener(new View.OnClickListener() {
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
        Toast.makeText(this, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
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
                        JSONObject object;
                        adsModelList = new ArrayList<>();
                        names = new ArrayList<>();
                        imges = new ArrayList<>();
                        links = new ArrayList<>();
                        for (int index=0;index<response.length();index++)
                        {
                            try {

                                object =response.getJSONObject(index);

                                AdvertsmentModel adsModel = new AdvertsmentModel(object.get("ads_name").toString(),object.get("ads_detailes").toString(),object.get("ads_images").toString(),object.get("ads_date_add").toString());
                                adsModelList.add(adsModel);
                               names.add(object.get("ads_name").toString());
                               imges.add(object.get("ads_images").toString());
                               links.add(object.get("ads_detailes").toString());

                                file_maps = new HashMap<>();
                                for(int i=0;i<names.size();i++){
                                    file_maps.put(names.get(i),  App_URL.image_url+imges.get(i));
                                }


//                                for (String name : file_maps.keySet()) {
//                                    TextSliderView textSliderView = new TextSliderView(Category.this);
//                                    // initialize a SliderLayout
//                                    textSliderView.description(name)
//                                            .image(name)
//                                            .setScaleType(BaseSliderView.ScaleType.Fit)
//                                            .setOnSliderClickListener(Category.this);
//
//                                    //add your extra information
//                                    textSliderView.bundle(new Bundle());
//                                    textSliderView.getBundle().putString("extra", name);
//                                    mDemoSlider.addSlider(textSliderView);
//                                }
                                for(int i=0;i<links.size();i++) {
                                    TextSliderView textSliderView = new TextSliderView(Category.this);
                                    // initialize a SliderLayout
                                    textSliderView.description(names.get(i))
                                            .image(names.get(i))
                                            .setScaleType(BaseSliderView.ScaleType.Fit)
                                            .setOnSliderClickListener(Category.this);

                                    //add your extra information
                                    textSliderView.bundle(new Bundle());
                                    textSliderView.getBundle().putString("extra", links.get(i));
                                    mDemoSlider.addSlider(textSliderView);
                                }
                                mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
                                mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                                mDemoSlider.setCustomAnimation(new DescriptionAnimation());
                                mDemoSlider.setDuration(3000);
                                mDemoSlider.addOnPageChangeListener(Category.this);



                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
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
