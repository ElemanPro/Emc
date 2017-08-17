package com.example.elashry.eleman.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.elashry.eleman.Model.Product_Model;
import com.example.elashry.eleman.R;

public class Device_Details extends AppCompatActivity {

    private TextView dev_Name,dev_Price,dev_Categ;
    private ImageView dev_Image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device__details);
        init_View();
        GetData_FromIntent();
    }

    private void GetData_FromIntent() {
        Intent intent = getIntent();
        if (intent.getExtras() !=null)
        {
            Product_Model model = (Product_Model) intent.getSerializableExtra("product_data");
            dev_Name.setText(model.getProduct_title().toString());
            dev_Categ.setText(model.getProduct_Category_fk().toString());

        }
    }

    private void init_View() {
        dev_Name = (TextView) findViewById(R.id.device_details_devName);
        dev_Price= (TextView) findViewById(R.id.device_details_devPrice);
        dev_Categ= (TextView) findViewById(R.id.device_details_devCateg);
        dev_Image= (ImageView) findViewById(R.id.device_details_devImage);
    }
}
