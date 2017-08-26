package com.example.elashry.eleman.Activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.elashry.eleman.App_URL;
import com.example.elashry.eleman.Controller;
import com.example.elashry.eleman.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.example.elashry.eleman.Adapter.MaintenanceAdapter.mflag;

public class Maintenance extends AppCompatActivity {
    Button maintenance_order_Btn;
    EditText cname,cphone,caddress,dtype ,marka ,damage;
    Spinner spinner;
    ProgressDialog progressDialog;
    AlertDialog.Builder mdialog;

     String [] spinner_array=new String[]{"حاله الجهاز من الضمان","داخل الضمان","خارج الضمان"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance);
        maintenance_order_Btn= (Button) findViewById(R.id.maintenance_order_Btn);
        spinner= (Spinner) findViewById(R.id.mySpinner);
        cname= (EditText) findViewById(R.id.fullName);
        cphone= (EditText) findViewById(R.id.mobileNumber);
        caddress= (EditText) findViewById(R.id.location);
        dtype= (EditText) findViewById(R.id.type);
        marka= (EditText) findViewById(R.id.marka);
        damage= (EditText) findViewById(R.id.damage);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,spinner_array);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView = (TextView) adapterView.getChildAt(0);
                textView.setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mdialog       = new AlertDialog.Builder(this);
        mdialog.setCancelable(false);
        mdialog.setNegativeButton("إلغاء", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);


    }

    public void registerOrder(View view) {
        String client_name          = cname.getText().toString();
        String client_phone         = cphone.getText().toString();
        String client_address       = caddress.getText().toString();
        String device_name          = dtype.getText().toString();
        String device_marka         = marka.getText().toString();
        String device_warranty_state= spinner.getSelectedItem().toString();
        String device_damage_type   = damage.getText().toString();


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
        else if (TextUtils.isEmpty(client_address))
        {
            mdialog.setMessage("تاكد من ادخال العنوان");
            mdialog.show();
        }
        else if (TextUtils.isEmpty(device_name))
        {
            mdialog.setMessage("تاكد من ادخال اسم الجهاز");
            mdialog.show();
        }
        else if (TextUtils.isEmpty(device_marka))
        {
            mdialog.setMessage("تاكد من ادخال ماركه الجهاز");
            mdialog.show();
        }
        else if (device_warranty_state.toString().equals("حاله الجهاز من الضمان"))
        {
            mdialog.setMessage("تاكد من ادخال حاله الجهاز من الضمان");
            mdialog.show();
        }
        else if (TextUtils.isEmpty(device_damage_type))
        {
            mdialog.setMessage("تاكد من ادخال نوع العطل");
            mdialog.show();
        }
        else
        {

            boolean isConnected = Check_Network();
            if (isConnected==true)

            {
                progressDialog.setMessage("sending "+ cname.getText().toString()+" data to server");
                progressDialog.show();
                String date = new SimpleDateFormat("EEE ,dd MMM yyyy HH:mm aa", new Locale("ar","SA")).format(Calendar.getInstance().getTime());
                Add_Maintenance_order(client_name,client_phone,client_address,device_name,device_marka,device_warranty_state,device_damage_type,date);
            }
            else
                {
                    progressDialog.dismiss();
                    mdialog.setMessage("تحقق من الاتصال بالانترنت");
                    mdialog.setNegativeButton("إلغاء", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(Maintenance.this, "إلغاء", Toast.LENGTH_SHORT).show();
                        }
                    });
                    mdialog.show();
                }
                    }
    }
    private void Add_Maintenance_order(final String c_name, final String c_phone, final String c_address, final String dev_name, final String dev_marka, final String dev_warranty_state, final String dev_damage_type, final String date)
    {

 StringRequest strReq = new StringRequest(Request.Method.POST,
                    App_URL.add_maintenance_order, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        String msg = jsonResponse.get("message").toString();
                        if (msg.equals("1"))
                        {
                            progressDialog.dismiss();
                            mdialog.setMessage("تم ارسال الطلب بنجاح");
                            mdialog.setNegativeButton("موافق", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                    mflag="0";
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
                                        Toast.makeText(Maintenance.this, "إلغاء", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                mdialog.show();
                            }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                    Log.e("response",response.toString());

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
                    params.put("client_name",c_name);
                    params.put("client_phone",c_phone);
                    params.put("client_location",c_address);
                    params.put("device_type",dev_name);
                    params.put("warranty_state",dev_warranty_state);
                    params.put("device_brand",dev_marka);
                    params.put("damage_type",dev_damage_type);
                    params.put("order_date", date);

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
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Maintenance.this,Category.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
}
