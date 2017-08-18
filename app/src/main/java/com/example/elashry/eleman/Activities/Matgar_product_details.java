package com.example.elashry.eleman.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.elashry.eleman.App_URL;
import com.example.elashry.eleman.Model.Image_details_Model;
import com.example.elashry.eleman.Model.MatgarModel;
import com.example.elashry.eleman.R;
import com.squareup.picasso.Picasso;

public class Matgar_product_details extends AppCompatActivity {

    private ImageView dev_image;
    private TextView client_name,client_details,dev_name,dev_price,date;
    private ProgressBar mBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matgar_product_details);
        init_View();
        GetData_from_Intent();
    }



    private void init_View() {
        dev_image      = (ImageView) findViewById(R.id.matgar_details_dev_image);
        dev_name       = (TextView) findViewById(R.id.matgar_details_dev_name);
        dev_price      = (TextView) findViewById(R.id.matgar_details_dev_price);
        client_name    = (TextView) findViewById(R.id.matgar_details_client_name);
        client_details = (TextView) findViewById(R.id.matgar_details_client_details);
        date           = (TextView) findViewById(R.id.matgar_details_date);
        mBar           = (ProgressBar) findViewById(R.id.matgar_details_progBar);
    }
    private void GetData_from_Intent() {
        Intent intent = getIntent();
        if (intent.getExtras() !=null)
        {
            final MatgarModel matgarModel = (MatgarModel) intent.getSerializableExtra("dev_data");
            client_name.setText(matgarModel.getClient_name().toString());
            client_details.setText(matgarModel.getClient_details().toString());
            dev_name.setText(matgarModel.getProduct_name().toString());
            dev_price.setText(matgarModel.getProduct_price().toString()+" "+"جنيه");
            date.setText(matgarModel.getDate().toString());
            Picasso.with(Matgar_product_details.this).load(App_URL.image_url+matgarModel.getProduct_image().toString()).noFade().into(dev_image);
            mBar.setVisibility(View.GONE);
            dev_image.setVisibility(View.VISIBLE);
            dev_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Matgar_product_details.this, Zooming_Image.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    Image_details_Model image_details_model = new Image_details_Model(matgarModel.getProduct_name().toString()+" "+matgarModel.getProduct_price().toString(),matgarModel.getProduct_image().toString());
                    intent.putExtra("image_details",image_details_model);
                    intent.putExtra("flag","1");
                    startActivity(intent);
                }
            });
           // new asyn_task(matgarModel).execute(matgarModel.getProduct_image().toString());


        }
    }
    class asyn_task extends AsyncTask<String ,Void,Void> {

        MatgarModel matgarModel;
        public asyn_task(MatgarModel matgarModel) {
        this.matgarModel = matgarModel;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mBar.setVisibility(View.VISIBLE);
            dev_image.setVisibility(View.INVISIBLE);

        }

        @Override
        protected Void doInBackground(String... strings) {
            Picasso.with(Matgar_product_details.this).load(matgarModel.getProduct_image().toString()).noFade().into(dev_image);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mBar.setVisibility(View.GONE);
            dev_image.setVisibility(View.VISIBLE);
        }
    }
}
