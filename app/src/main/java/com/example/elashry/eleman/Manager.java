package com.example.elashry.eleman;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class Manager extends AppCompatActivity {

    private EditText pro_Name,pro_Price;
    //private EditText client_Name,client_phone,client_address,dev_type,dev_state,dev_brand,dev_damage;
    private TextView upload_Image;
    private Spinner spinner_proType;
    private ImageView pro_Image;
    private Button addBtn;
    private final int RC=1,RC2=2;
    private Uri uri;
    private final String pro_url ="http://semicolonsoft.com/app/api/find/products";
    int pro_categ = 0;
    private AlertDialog.Builder mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        
        init_View();
        upload_pro_Image(upload_Image);
        Add_Pro_Data(addBtn);
    }

    private void Add_Pro_Data(Button addBtn) {
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* String c_name = client_Name.getText().toString();
                String c_phone=client_phone.getText().toString();
                String c_address=client_address.getText().toString();
                String dev_t = dev_type.getText().toString();
                String dev_s = dev_state.getText().toString();
                String dev_b = dev_brand.getText().toString();
                String dev_d = dev_damage.getText().toString();
                String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date().getTime());*/
                final String pro_name = pro_Name.getText().toString();
                final String pro_price= pro_Price.getText().toString();
                final String pro_image_uri = uri.toString();


                if (spinner_proType.getSelectedItem().toString().equals("غسالات"))
                {
                    pro_categ=1;
                }
                else if (spinner_proType.getSelectedItem().toString().equals("تلاجات"))
                {
                    pro_categ=2;
                }
                else if (spinner_proType.getSelectedItem().toString().equals("بوتاجازات"))
                {
                    pro_categ=3;
                }
                else if (spinner_proType.getSelectedItem().toString().equals("تيليفزيونات"))
                {
                    pro_categ=4;
                }
                else if (spinner_proType.getSelectedItem().toString().equals("شاشات"))
                {
                    pro_categ=5;
                }
                else if (spinner_proType.getSelectedItem().toString().equals("تكييفات"))
                {
                    pro_categ=6;
                }

                Toast.makeText(Manager.this,pro_name+"\n"+pro_price+"\n"+pro_image_uri+"\n"+pro_categ, Toast.LENGTH_SHORT).show();
                StringRequest request = new StringRequest(Request.Method.POST, pro_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(Manager.this,response+"1", Toast.LENGTH_SHORT).show();
                                Log.e("fffffffff",response);
                            }
                        }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(Manager.this,error.getMessage()+"2", Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> map = new HashMap<String, String>();
                        map.put("cat_id_fk",String.valueOf(pro_categ));
                        map.put("ptoduct_name",pro_name);
                        map.put("product_price",pro_price);
                        map.put("product_image",pro_image_uri);
                        return  map;
                    }
                };
                Controller.getInstance().addToRequestQueue(request,"string req");

            }

        });
    }


    private void upload_pro_Image(TextView upload_image) {
        upload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view2 = Manager.this.getLayoutInflater().inflate(R.layout.alert_dialog_title,null,false);
                mDialog.setCustomTitle(view2);
                final String items[] = {"اختيار صورة","إلتقاط صورة"};
                mDialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (items[i].toString().equals("اختيار صورة"))
                        {

                            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                            intent.setType("image/*");
                            startActivityForResult(intent,RC);
                        }
                        else if (items[i].toString().equals("إلتقاط صورة"))
                        {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent,RC2);
                        }
                    }
                });
                mDialog.setNegativeButton("إلغاء", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                mDialog.show();
            }
        });
    }

    private void init_View() {
        /*client_Name = (EditText) findViewById(R.id.client_Name);
        client_phone= (EditText) findViewById(R.id.client_Phone);
        client_address= (EditText) findViewById(R.id.client_address);
        client_phone= (EditText) findViewById(R.id.client_Phone);
        dev_type= (EditText) findViewById(R.id.dev_type);
        dev_state= (EditText) findViewById(R.id.dev_state);
        dev_brand= (EditText) findViewById(R.id.dev_brand);
        dev_damage= (EditText) findViewById(R.id.dev_damage);*/


        pro_Name = (EditText) findViewById(R.id.pro_Name);
        pro_Price= (EditText) findViewById(R.id.pro_Price);
        upload_Image = (TextView) findViewById(R.id.upload_Image);
        pro_Image = (ImageView) findViewById(R.id.pro_Image);
        spinner_proType = (Spinner) findViewById(R.id.spinner_pro_Type);
        addBtn = (Button) findViewById(R.id.addPro_Btn);

        ////////////////////////////////////////////////////////
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.spinner_array));
        spinner_proType.setAdapter(adapter);
        //////////////////////////////////////
        mDialog = new AlertDialog.Builder(this);
        mDialog.setCancelable(false);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK&&requestCode==RC)
        {
            if (data !=null)
            {
                uri = data.getData();
                pro_Image.setImageURI(uri);
            }

        }
        else if (resultCode==RESULT_OK&&requestCode==RC2)
        {
            if (data !=null)
            {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                uri           =getUriFrom_Bitmap(bitmap,Manager.this);

                pro_Image.setImageURI(uri);

            }

        }


    }
    private Uri getUriFrom_Bitmap(Bitmap bitmap, Context mContext)
    {
        ByteArrayOutputStream ops = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,ops);
        String path = MediaStore.Images.Media.insertImage(mContext.getContentResolver(),bitmap,"title",null);
        return Uri.parse(path);

    }
}
