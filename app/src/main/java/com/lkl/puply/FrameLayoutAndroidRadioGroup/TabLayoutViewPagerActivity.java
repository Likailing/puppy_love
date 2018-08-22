package com.lkl.puply.FrameLayoutAndroidRadioGroup;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.lkl.puply.R;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutViewPagerActivity extends AppCompatActivity implements RadioFragmentFragment.OnFragmentInteractionListener{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String[] titleS = {"全部","待入账","已入账","已失效"};
    private List<RadioFragmentFragment> fragmentList = new ArrayList<>();
    private MyPagerAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout_view_pager);
        initView();
    }

    private void initView() {
        tabLayout = findViewById(R.id.table_layout);
        viewPager = findViewById(R.id.view_pager);

        for (int i = 0; i <titleS.length ; i++) {
            RadioFragmentFragment fragmentFragment = RadioFragmentFragment.newInstance(i+"");
            fragmentList.add(fragmentFragment);
        }

        adapter = new MyPagerAdapter(getSupportFragmentManager(),fragmentList,titleS);

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
