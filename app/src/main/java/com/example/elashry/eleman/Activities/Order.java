package com.example.elashry.eleman.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.elashry.eleman.Controller;
import com.example.elashry.eleman.Model.Product_Model;
import com.example.elashry.eleman.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Order extends AppCompatActivity {
    EditText cname,cphone,caddress,amount;
    ProgressDialog progressDialog;
    String dates;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        cname= (EditText) findViewById(R.id.cname);
        cphone= (EditText) findViewById(R.id.cphone);
        caddress= (EditText) findViewById(R.id.address);
        amount= (EditText) findViewById(R.id.quintity);

        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        dates = df.format(Calendar.getInstance().getTime());

        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);

        GetData_from_Intent();

    }

    private void GetData_from_Intent() {
        Intent intent = getIntent();
        if (intent.getExtras()!=null)
        {
            Product_Model model = (Product_Model) intent.getSerializableExtra("pro_data");
        }
    }


    public void registerOrder(View view) {
        if(   cname.getText().toString().isEmpty()&&cphone.getText().toString().isEmpty()&&
                caddress.getText().toString().isEmpty()&&amount.getText().toString().isEmpty())
        {
            Toast.makeText(Order.this,"You Should Enter data",Toast.LENGTH_LONG).show();
        }else
        {



            progressDialog.setMessage("sending "+ cname.getText().toString()+" data to server");
            progressDialog.show();
            StringRequest strReq = new StringRequest(Request.Method.POST,
                    "http://semicolonsoft.com/app/api/addorders", new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {

                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),
                            response, Toast.LENGTH_LONG).show();
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),
                            error.getMessage(), Toast.LENGTH_LONG).show();
                    finish();

                }
            }) {

                @Override
                protected Map<String, String> getParams() {
                    // Posting params to register url
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("client_name", cname.getText().toString());
                    params.put("client_phone", cphone.getText().toString());
                    params.put("order_location", caddress.getText().toString());
                    params.put("quantity", amount.getText().toString());
                    params.put("order_date", dates);

                    return params;
                }

            };
            // Adding request to request queue
            Controller.getInstance().addToRequestQueue(strReq,"re");

        }
    }
}
