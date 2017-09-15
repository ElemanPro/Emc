package com.semicolon.emcmisir.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.semicolon.emcmisir.Adapter.ViewPagerAdapter;
import com.semicolon.emcmisir.Fragment.Maintenance;
import com.semicolon.emcmisir.Fragment.Matgar_Orders;
import com.semicolon.emcmisir.Fragment.Order;
import com.semicolon.emcmisir.R;

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
        mTab.addTab(mTab.newTab().setText("حجز(قطع غيار)"));
        mTab.addTab(mTab.newTab().setText("حجز(متجر)"));
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Maintenance());
        adapter.addFragment(new Order());
        adapter.addFragment(new Matgar_Orders());

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
