package com.example.elashry.eleman.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.elashry.eleman.Controller;
import com.example.elashry.eleman.Model.Image_details_Model;
import com.example.elashry.eleman.Model.OrderModel;
import com.example.elashry.eleman.R;
import com.gjiazhe.panoramaimageview.GyroscopeObserver;
import com.gjiazhe.panoramaimageview.PanoramaImageView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Zooming_Image extends AppCompatActivity {
    private TextView image_details;
    private PanoramaImageView image;
    private GyroscopeObserver gyroscopeObserver;
    private final String products_url ="https://semicolonsoft.com/clients/emc/api/find/products";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zooming__image);
        init_View();
        Get_DataFrom_Intent();
    }



    private void init_View() {
        image_details     = (TextView) findViewById(R.id.zooming_image_details);
        gyroscopeObserver = new GyroscopeObserver();
        gyroscopeObserver.setMaxRotateRadian(Math.PI/9);
        image             = (PanoramaImageView) findViewById(R.id.panorama_image_view);
        image.setGyroscopeObserver(gyroscopeObserver);
        image.setEnablePanoramaMode(true);
        image.setEnableScrollbar(true);
        image.setInvertScrollDirection(true);


    }
    private void Get_DataFrom_Intent() {
        Intent intent = getIntent();
        if (intent.getExtras()!=null) {
            if (intent.getExtras().getString("flag").toString().equals("1"))
            {
                Image_details_Model model = (Image_details_Model) intent.getSerializableExtra("image_details");
                image_details.setText(model.getImage_details().toString());
                Picasso.with(Zooming_Image.this).load("https://semicolonsoft.com/clients/emc/public/uploads/thumbs/"+model.getImage_url()).noFade().into(image);
            }
            else if (intent.getExtras().getString("flag").toString().equals("2"))
            {
                OrderModel model = (OrderModel) intent.getSerializableExtra("pro_data");
                GetProData(model);
            }

        }
    }

    private void GetProData( final OrderModel orderModel)
    {
        JsonArrayRequest mJsonArrayRequest = new JsonArrayRequest(products_url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("data",response.toString());
                        JSONObject object;
                        for (int index=0;index<response.length();index++)
                        {
                            try {
                                object =response.getJSONObject(index);
                                if (object.get("product_id_pk").toString().equals(orderModel.getProduct_id().toString()))
                                {
                                    Picasso.with(Zooming_Image.this).load("https://semicolonsoft.com/clients/emc/public/uploads/images/"+object.get("product_photo").toString()).noFade().into(image);
                                    image_details.setText(object.get("product_title").toString());


                                }
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


    @Override
    protected void onResume() {
        super.onResume();
        gyroscopeObserver.register(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        gyroscopeObserver.unregister();
    }
}
