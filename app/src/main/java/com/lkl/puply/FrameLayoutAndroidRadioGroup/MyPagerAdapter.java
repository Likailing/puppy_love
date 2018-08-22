package com.lkl.puply.FrameLayoutAndroidRadioGroup;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Likailing date:2018/8/22 0022 time:16:57
 * Email : 13297970902@163.com
 * Description :
 */

public class MyPagerAdapter extends FragmentPagerAdapter {
    private List<RadioFragmentFragment> fragmentList = new ArrayList<>();
    private String[] titles;

    public MyPagerAdapter(FragmentManager fm, List<RadioFragmentFragment> fragmentList, String[] titles) {
        super(fm);
        this.fragmentList = fragmentList;
        this.titles = titles;
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
