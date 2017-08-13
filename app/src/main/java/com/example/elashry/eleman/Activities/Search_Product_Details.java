package com.example.elashry.eleman.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.elashry.eleman.Model.Product_Model;
import com.example.elashry.eleman.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Search_Product_Details extends AppCompatActivity {

    private TextView pro_name,pro_price,pro_categ;
    private ImageView pro_image,categ_icon;
    private ProgressBar mProgressBar;
    private final String products_url ="http://semicolonsoft.com/app/api/find/products";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__product__details);

        init_View();
        GetProduct_data_from_intent();
    }

    private void GetProduct_data_from_intent() {
        Intent intent = getIntent();
        if (intent.getExtras()!=null)
        {
            Product_Model model = (Product_Model) intent.getSerializableExtra("pro_data");
            pro_name.setText(model.getPro_Name().toString());
            pro_price.setText(model.getPro_Price());
            if (model.getPro_Categ().toString().equals("1"))
            {
               categ_icon.setImageResource(R.mipmap.washer);
                pro_categ.setText("غسالات");
            }
            else if (model.getPro_Categ().toString().equals("2"))
            {
                categ_icon.setImageResource(R.mipmap.fridg);
                pro_categ.setText("تلاجات");

            }
            else if (model.getPro_Categ().toString().equals("3"))
            {
                categ_icon.setImageResource(R.mipmap.oven);
                pro_categ.setText("بوتجازات");

            }
            else if (model.getPro_Categ().toString().equals("4"))
            {
                categ_icon.setImageResource(R.mipmap.tv);
                pro_categ.setText("تليفزيونات");
            }
            else if (model.getPro_Categ().toString().equals("5"))
            {
                categ_icon.setImageResource(R.mipmap.screen);
                pro_categ.setText("شاشات");
            }
            else if (model.getPro_Categ().toString().equals("6"))
            {
                categ_icon.setImageResource(R.mipmap.takief);
                pro_categ.setText("تكييفات");
            }
            new asyn_task().execute(model.getPro_Image_url());

        }

    }

    private void init_View() {
        pro_name     = (TextView) findViewById(R.id.search_product_name);
        pro_price    = (TextView) findViewById(R.id.search_product_price);
        pro_categ    = (TextView) findViewById(R.id.search_product_categ);
        pro_image    = (ImageView) findViewById(R.id.search_product_image);
        categ_icon   = (ImageView) findViewById(R.id.search_categ_icon);
        mProgressBar = (ProgressBar) findViewById(R.id.search_prog_bar);
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
           pro_image.setImageBitmap(bitmap);
            mProgressBar.setVisibility(View.GONE);
            pro_image.setVisibility(View.VISIBLE);
        }
    }
}
