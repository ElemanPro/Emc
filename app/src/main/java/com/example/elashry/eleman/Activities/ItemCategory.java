package com.example.elashry.eleman.Activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.elashry.eleman.Adapter.ViewPagerAdapter;
import com.example.elashry.eleman.Fragment.Botgaaz;
import com.example.elashry.eleman.Fragment.Fridge;
import com.example.elashry.eleman.Fragment.Shashat;
import com.example.elashry.eleman.Fragment.Takief;
import com.example.elashry.eleman.Fragment.Televtion;
import com.example.elashry.eleman.Fragment.Washer;
import com.example.elashry.eleman.R;


public class ItemCategory extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout mTab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_category);
        init_View();
        create_Tab();
        setUp_viewPager();

    }

    private void setUp_viewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new Washer());
        adapter.addFragment(new Fridge());
        adapter.addFragment(new Botgaaz());
        adapter.addFragment(new Shashat());
        adapter.addFragment(new Televtion());
        adapter.addFragment(new Takief());




        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTab));
        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    private void init_View() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        mTab      = (TabLayout) findViewById(R.id.mTab);


    }
    private void create_Tab() {

        mTab.addTab(mTab.newTab().setText("غسالات").setIcon(R.mipmap.washing_machine));
        mTab.addTab(mTab.newTab().setText("تلاجات").setIcon(R.mipmap.fridge));
        mTab.addTab(mTab.newTab().setText("بوتاجازات").setIcon(R.mipmap.cooker));
        mTab.addTab(mTab.newTab().setText("شاشات").setIcon(R.drawable.television));
        mTab.addTab(mTab.newTab().setText("تيليفزيونات").setIcon(R.drawable.televisions));
        mTab.addTab(mTab.newTab().setText("تكييفات").setIcon(R.drawable.refrigerator));
        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}
