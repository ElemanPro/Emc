package com.semicolon.emcmisir.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.semicolon.emcmisir.Fragment.Fragment_Matgar;
import com.semicolon.emcmisir.R;

public class Matgar extends AppCompatActivity {
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapseToolBar;
    private final String matgar_url ="http://semicolonsoft.com/app/api/find/matgar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matgar);
        init_View();
        getSupportFragmentManager().beginTransaction().add(R.id.Fragments_Container,new Fragment_Matgar()).commit();



        try {
            Glide.with(this).load(R.drawable.pic).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    private void init_View() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        collapseToolBar  = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {



    }

    /**
     * Adding few albums for testing
     */


    /**
     * RecyclerView item decoration - give equal margin around grid item
     */



    /**
     * Converting dp to pixel
     */



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.item_categ_search:
                Intent intent = new Intent(Matgar.this,Search_Matgar.class);
                startActivity(intent);
                break;
            default:return super.onOptionsItemSelected(item);
        }
        return true;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Matgar.this,Category.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
}
