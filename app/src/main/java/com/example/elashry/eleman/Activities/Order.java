package com.example.elashry.eleman.Activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.elashry.eleman.App_URL;
import com.example.elashry.eleman.Controller;
import com.example.elashry.eleman.Model.MatgarModel;
import com.example.elashry.eleman.Model.Product_Model;
import com.example.elashry.eleman.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Order extends AppCompatActivity {
    private EditText cname,cphone,order_address,order_amount;
    private ProgressDialog progressDialog;
    private AlertDialog.Builder mdialog;
    private Product_Model model;
    private MatgarModel matgarModel;
    private static String flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        cname= (EditText) findViewById(R.id.cname);
        cphone= (EditText) findViewById(R.id.cphone);
        order_address= (EditText) findViewById(R.id.order_address);
        order_amount= (EditText) findViewById(R.id.quintity);
        mdialog       = new AlertDialog.Builder(this);
        mdialog.setCancelable(false);
        mdialog.setNegativeButton("إلغاء", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);

        GetData_from_Intent();

    }

    private void GetData_from_Intent() {
        Intent intent = getIntent();
        if (intent.getExtras()!=null)
        {
            if (intent.getExtras().getString("flag").toString().equals("1"))
            {
                flag = intent.getExtras().getString("flag").toString();
                matgarModel = (MatgarModel) intent.getSerializableExtra("matgar_item_data");

            }
            else if (intent.getExtras().getString("flag").toString().equals("0")){
                model = (Product_Model) intent.getSerializableExtra("pro_data");
                flag = intent.getExtras().getString("flag").toString();
            }


        }
    }


    public void registerOrder(View view) {
        String client_name          = cname.getText().toString();
        String client_phone         = cphone.getText().toString();
        String order_location       = order_address.getText().toString();
        String order_quantity       = order_amount.getText().toString();



        if(TextUtils.isEmpty(client_name))
        {
            mdialog.setMessage("تاكد من ادخال اسم العميل");
            mdialog.show();
        }
        else if (TextUtils.isEmpty(client_phone))
        {
            mdialog.setMessage("تاكد من ادخال رقم المحمول");
            mdialog.show();
        }
        else if (!client_phone.matches("^(010|011|012)[0-9]{8}$"))
        {
            mdialog.setMessage("تاكد من ادخال رقم المحمول بشكل صحيح");
            mdialog.show();
        }
        else if (TextUtils.isEmpty(order_location))
        {
            mdialog.setMessage("تاكد من ادخال العنوان");
            mdialog.show();
        }
        else if (TextUtils.isEmpty(order_quantity))
        {
            mdialog.setMessage("تاكد من ادخال الكميه");
            mdialog.show();
        }
        else
        {
            boolean isConnected = Check_Network();
            if (isConnected==true)
            {
                String date = new SimpleDateFormat("EEE ,dd MMM yyyy HH:mm aa").format(new Date().getTime());
                progressDialog.setMessage("sending "+ cname.getText().toString()+" data to server");
                progressDialog.show();
                if (flag.toString().equals("0"))
                {
                    add_order(App_URL.add_itemCateg_data,client_name,client_phone,order_location,order_quantity,date,model.getProduct_id_fk().toString(),"0");
                }
                else if (flag.toString().equals("1"))
                {
                    add_order(App_URL.matgar_order,client_name,client_phone,order_location,order_quantity,date,"0",matgarModel.getMatgar_pk().toString());
                }

            }
            else
            {
                progressDialog.dismiss();
                mdialog.setMessage("تحقق من الاتصال بالانترنت");
                mdialog.setNegativeButton("إلغاء", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(Order.this, "إلغاء", Toast.LENGTH_SHORT).show();
                    }
                });
                mdialog.show();
            }



        }
    }
    private void add_order(String url,final String c_name, final String c_phone, final String o_address, final String o_amount, final String date, final String pro_id_fk,final String matger_id_fk)
    {

            StringRequest strReq = new StringRequest(Request.Method.POST,
                    url, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        String msg = jsonResponse.get("message").toString();
                        if (msg.equals("1"))
                        {
                            progressDialog.dismiss();
                            mdialog.setMessage("تم ارسال الطلب بنجاح");
                            mdialog.setNegativeButton("إلغاء", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    finish();
                                }
                            });
                            mdialog.show();

                        }
                        else
                        {
                            progressDialog.dismiss();
                            mdialog.setMessage("خطا اثناء ارسال البيانات ");
                            mdialog.setNegativeButton("إلغاء", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(Order.this, "إلغاء", Toast.LENGTH_SHORT).show();
                                }
                            });
                            mdialog.show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),
                            error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }) {

                @Override
                protected Map<String, String> getParams() {
                    // Posting params to register url
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("product_id_fk",pro_id_fk);
                    params.put("client_name",c_name);
                    params.put("client_phone",c_phone);
                    params.put("order_location",o_address);
                    params.put("quantity",o_amount);
                    params.put("order_date",date);
                    params.put("matgar_id_fk",matger_id_fk);

                    return params;
                }

            };
            // Adding request to request queue
            Controller.getInstance().addToRequestQueue(strReq,"re");

        }
    private boolean Check_Network()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
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
