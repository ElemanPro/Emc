package com.example.elashry.eleman.Activities;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.elashry.eleman.Controller;
import com.example.elashry.eleman.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.example.elashry.eleman.R.array.mySpinner;

public class Order extends AppCompatActivity {
    Button b1;
    EditText cname,cphone,caddress,amount;
    Calendar myCalendar;
    ProgressDialog progressDialog;
    String dates;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        b1= (Button) findViewById(R.id.order);
        cname= (EditText) findViewById(R.id.cname);
        cphone= (EditText) findViewById(R.id.cphone);
        caddress= (EditText) findViewById(R.id.address);
        amount= (EditText) findViewById(R.id.quintity);




//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i =new Intent(Maintenance.this,Category.class);
//                startActivity(i);
//            }
//        });
        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        dates = df.format(Calendar.getInstance().getTime());




        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);


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
