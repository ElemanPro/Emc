package com.example.elashry.eleman.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.elashry.eleman.R;

public class Category extends AppCompatActivity {

    private Toolbar mCat_ToolBar;
    ImageView img ,img2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        init_View();
        img = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Category.this,Maintenance.class);
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

    private void init_View() {

        mCat_ToolBar = (Toolbar) findViewById(R.id.mCat_ToolBar);
        this.setSupportActionBar(mCat_ToolBar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.manager_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.manager_menu)
        {
            startActivity(new Intent(Category.this, Login.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
