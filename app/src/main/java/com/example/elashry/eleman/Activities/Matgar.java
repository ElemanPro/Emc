package com.example.elashry.eleman.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.elashry.eleman.Fragment.Fragment_Matgar;
import com.example.elashry.eleman.R;

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


       /* AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);*/

        // hiding & showing the title when toolbar expanded & collapsed
      /*  appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });*/
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
                Intent intent = new Intent(Matgar.this,Search_Matgar_Activity.class);
                startActivity(intent);
                break;
            default:return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
