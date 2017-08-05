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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.example.elashry.eleman.R.array.mySpinner;

public class Order extends AppCompatActivity {
    Button b1;
    EditText cname,cphone,caddress,amount,Odate;
    Calendar myCalendar;
    ProgressDialog progressDialog;
    String ss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        b1= (Button) findViewById(R.id.order);
        Odate= (EditText) findViewById(R.id.date);
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
        myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        Odate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Order.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);


    }
    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        Odate.setText(sdf.format(myCalendar.getTime()));
    }

    public void registerOrder(View view) {
        if(   cname.getText().toString().isEmpty()&&cphone.getText().toString().isEmpty()&&
                caddress.getText().toString().isEmpty()&&amount.getText().toString().isEmpty()&&
                Odate.getText().toString().isEmpty())
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
                    params.put("order_date", Odate.getText().toString());

                    return params;
                }

            };
            // Adding request to request queue
            Controller.getInstance().addToRequestQueue(strReq,"re");

        }
    }
}
