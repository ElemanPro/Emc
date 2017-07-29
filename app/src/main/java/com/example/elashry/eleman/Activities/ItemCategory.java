package com.example.elashry.eleman.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.example.elashry.eleman.Adapter.ViewPagerAdapter;
import com.example.elashry.eleman.Fragment.Botgaaz;
import com.example.elashry.eleman.Fragment.Washer;
import com.example.elashry.eleman.Fragment.Refredge;
import com.example.elashry.eleman.Fragment.TV;
import com.example.elashry.eleman.Fragment.Oven;
import com.example.elashry.eleman.Fragment.Televtion;
import com.example.elashry.eleman.R;


public class ItemCategory extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    //This is our viewPager
    private ViewPager viewPager;

    Refredge refredge;
    Washer washer;
    TV tv;
    Oven oven;
    Televtion televtion;
    MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_category);

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        //Initializing the bottomNavigationView
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_call:
                                viewPager.setCurrentItem(0);
                                break;
                            /*case R.id.action_chat:
                                viewPager.setCurrentItem(1);
                                break;*/
                            case R.id.action_contact:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.action_washer:
                                viewPager.setCurrentItem(2);
                                break;
                            case R.id.action_refreger:
                                viewPager.setCurrentItem(3);
                                break;
                            case R.id.action_botgaz:
                                viewPager.setCurrentItem(4);
                                break;

                        }
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else
                {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: "+position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

       /*  //Disable ViewPager Swipe

       viewPager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }
        });

        */

        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        washer=new Washer();
        refredge=new Refredge();
        tv=new TV();

        televtion=new Televtion();
        oven=new Oven();

        adapter.addFragment(washer);
        adapter.addFragment(refredge);
        adapter.addFragment(tv);

        adapter.addFragment(televtion);
        adapter.addFragment(oven);
        viewPager.setAdapter(adapter);
    }
}
