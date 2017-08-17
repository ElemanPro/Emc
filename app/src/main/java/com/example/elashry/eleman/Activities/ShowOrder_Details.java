package com.example.elashry.eleman.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.elashry.eleman.Controller;
import com.example.elashry.eleman.Model.OrderModel;
import com.example.elashry.eleman.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ShowOrder_Details extends AppCompatActivity {

    private TextView client_name,client_phone,dev_categ,dev_name,dev_quantity,order_address,order_date;
    private ImageView dev_image;
    private final String products_url ="https://semicolonsoft.com/clients/emc/api/find/products";
    private TextView nopro_txt;
    private LinearLayout progBar_container,order_data_container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_order__details);
        init_View();
        GetDataFromIntent();

    }

    private void GetDataFromIntent() {

        Intent intent = getIntent();
        if (intent.getExtras()!=null)
        {
            final OrderModel orderModel = (OrderModel) intent.getSerializableExtra("order_data");
            Getproduct_data(products_url,orderModel);
            dev_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(ShowOrder_Details.this, Zooming_Image.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("pro_data",orderModel);
                    intent.putExtra("flag","2");
                    startActivity(intent);
                }
            });

        }
    }

    private void Getproduct_data(String products_url, final OrderModel orderModel) {
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
                                    client_name.setText(orderModel.getClient_name().toString());
                                    client_phone.setText(orderModel.getClient_phone().toString());
                                    order_address.setText(orderModel.getOrder_address().toString());
                                    dev_quantity.setText(orderModel.getQuantity().toString());
                                    order_date.setText(orderModel.getOrder_date().toString());
                                    dev_name.setText(object.get("product_title").toString());
                                    Picasso.with(ShowOrder_Details.this).load("https://semicolonsoft.com/clients/emc/public/uploads/thumbs/"+object.get("product_photo").toString()).noFade().into(dev_image);
                                    if (object.get("cat_id_fk").toString().equals("1"))
                                    {
                                        dev_categ.setText("غسالات");
                                        order_data_container.setVisibility(View.VISIBLE);
                                        progBar_container.setVisibility(View.GONE);

                                    }
                                    else if (object.get("cat_id_fk").toString().equals("2"))
                                    {
                                        dev_categ.setText("تلاجات");
                                        order_data_container.setVisibility(View.VISIBLE);
                                        progBar_container.setVisibility(View.GONE);
                                    }
                                    else if (object.get("cat_id_fk").toString().equals("3"))
                                    {
                                        dev_categ.setText("بوتاجازات");
                                        order_data_container.setVisibility(View.VISIBLE);
                                        progBar_container.setVisibility(View.GONE);
                                    }
                                    else if (object.get("cat_id_fk").toString().equals("4"))
                                    {
                                        dev_categ.setText("تيليفزيونات");
                                        order_data_container.setVisibility(View.VISIBLE);
                                        progBar_container.setVisibility(View.GONE);
                                    }
                                    else if (object.get("cat_id_fk").toString().equals("5"))
                                    {
                                        dev_categ.setText("شاشات");
                                        order_data_container.setVisibility(View.VISIBLE);
                                        progBar_container.setVisibility(View.GONE);
                                    }
                                    else if (object.get("cat_id_fk").toString().equals("6"))
                                    {
                                        dev_categ.setText("تكييفات");
                                        order_data_container.setVisibility(View.VISIBLE);
                                        progBar_container.setVisibility(View.GONE);

                                    }

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

    private void init_View() {
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("تفاصيل الطلب");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        client_name    = (TextView) findViewById(R.id.order_details_clientname);
        order_address  = (TextView) findViewById(R.id.order_details_order_address);
        client_phone   = (TextView) findViewById(R.id.order_details_client_phone);

        dev_categ      = (TextView) findViewById(R.id.order_details_devcateg);
        dev_name       = (TextView) findViewById(R.id.order_details_devname);
        dev_image      = (ImageView) findViewById(R.id.order_details_devimage);
        dev_quantity   = (TextView) findViewById(R.id.order_details_devquantity);
        order_date     = (TextView) findViewById(R.id.order_details_orderdate);


        progBar_container    = (LinearLayout)findViewById(R.id.progressBar_container);
        order_data_container = (LinearLayout)findViewById(R.id.order_data_container);
        order_data_container.setVisibility(View.GONE);
        progBar_container.setVisibility(View.VISIBLE);
        nopro_txt            = (TextView)findViewById(R.id.nopro_txt);
        nopro_txt.setVisibility(View.GONE);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);

    }
}
