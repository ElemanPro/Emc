package com.semicolon.emcmisir.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.semicolon.emcmisir.Fragment.Maintenance;
import com.semicolon.emcmisir.Fragment.Order;



public class Manager_ViewPager extends FragmentPagerAdapter {
    int tab_count;

    public Manager_ViewPager(FragmentManager manager, int tab_count) {
        super(manager);
        this.tab_count = tab_count;
    }
    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                return new Maintenance();
            case 1:
                return new Order();
            default:return null;
        }


    }

    @Override
    public int getCount() {
        return tab_count;
    }

}
