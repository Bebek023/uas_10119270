package com.example.uas_10119270;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

//10119270
//Albanna Rahmadani Sulistyo
//IF-7

public class CustomPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 2;

    public CustomPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0 :
                return FirstPager.newInstance(0, "Page# 1");
            case 1 :
                return SecondPager.newInstance(1, "Page# 2");
        }
        return null;
    }


}
