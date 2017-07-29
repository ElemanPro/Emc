package com.example.elashry.eleman.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.elashry.eleman.R;

public class Category extends AppCompatActivity {


    ImageView img ,img2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        img = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Category.this,Detail.class);
                startActivity(i);
            }
        });


        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Category.this,ItemCategory.class);
                startActivity(i);
            }
        });
    }
}
