package com.potter.harry.quakereport;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class Pageractivity extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public Pageractivity(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                today tab1 = new today();
                return tab1;
            case 1:
                today tab2 = new today();
                return tab2;
            case 2:
                thirdday tab3 = new thirdday();
                return tab3;

            case 3:
                fourthday tab4 = new fourthday();
                return tab4;

            case 4:
                fifthday tab5 = new fifthday();
                return tab5;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}