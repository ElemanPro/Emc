package com.example.elashry.eleman;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.elashry.eleman.Activities.Category;
import com.example.elashry.eleman.Adapter.ViewPagerAdapter;
import com.example.elashry.eleman.Fragment.Maintenance;
import com.example.elashry.eleman.Fragment.Order;

public class Manager extends AppCompatActivity {

    private TabLayout mTab;
    private ViewPager pager;
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        
        init_View();

    }

    private void init_View() {
        mToolbar = (Toolbar) findViewById(R.id._mngr_mtoolBar);
        setSupportActionBar(mToolbar);

        mTab = (TabLayout) findViewById(R.id.mngr_mTab);
        pager= (ViewPager) findViewById(R.id.mngr_viewPager);

        mTab.addTab(mTab.newTab().setText("صيانه"));
        mTab.addTab(mTab.newTab().setText("حجز"));

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Maintenance());
        adapter.addFragment(new Order());

        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTab));
        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mngr_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.mngr_signout)
        {
            SharedPreferences spref = getSharedPreferences("loginspref",MODE_PRIVATE);
            spref.edit().clear().commit();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Manager.this, Category.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
}
