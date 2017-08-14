package com.example.elashry.eleman.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.elashry.eleman.Model.MatgarModel;
import com.example.elashry.eleman.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
            MatgarModel matgarModel = (MatgarModel) intent.getSerializableExtra("dev_data");
            client_name.setText(matgarModel.getClient_name().toString());
            client_details.setText(matgarModel.getClient_details().toString());
            dev_name.setText(matgarModel.getProduct_name().toString());
            dev_price.setText(matgarModel.getProduct_price().toString());
            date.setText(matgarModel.getDate().toString());
            new asyn_task().execute(matgarModel.getProduct_image().toString());


        }
    }
    class asyn_task extends AsyncTask<String ,Void,Bitmap> {

        URL url =null;
        InputStream input = null;
        HttpURLConnection urlConnection=null;
        Bitmap bitmap=null;
        public asyn_task() {

        }


        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                input = urlConnection.getInputStream();
                bitmap = BitmapFactory.decodeStream(input);
                return bitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            dev_image.setImageBitmap(bitmap);
            mBar.setVisibility(View.GONE);
            dev_image.setVisibility(View.VISIBLE);
        }
    }
}
