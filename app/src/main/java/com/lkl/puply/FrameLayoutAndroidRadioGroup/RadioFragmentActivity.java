package com.lkl.puply.FrameLayoutAndroidRadioGroup;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lkl.puply.R;

import java.util.ArrayList;
import java.util.List;

public class RadioFragmentActivity extends AppCompatActivity implements View.OnClickListener, RadioFragmentFragment.OnFragmentInteractionListener {
    private RelativeLayout oneRl, twoRl, threeRl, fourRl;
    private List<RadioFragmentFragment> fragmentList = new ArrayList<>();
    private int mIndex;
    private List<TextView> tvList = new ArrayList<>();
    private List<View> viewList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_fragment);

        initView();
        initListener();
    }


    private void initView() {
        oneRl = findViewById(R.id.income_one_rl);
        twoRl = findViewById(R.id.income_two_rl);
        threeRl = findViewById(R.id.income_three_rl);
        fourRl = findViewById(R.id.income_four_rl);

        tvList.clear();
        tvList.add((TextView) findViewById(R.id.income_one_tv));
        tvList.add((TextView) findViewById(R.id.income_two_tv));
        tvList.add((TextView) findViewById(R.id.income_three_tv));
        tvList.add((TextView) findViewById(R.id.income_four_tv));
        viewList.clear();
        viewList.add(findViewById(R.id.income_view_line_one));
        viewList.add(findViewById(R.id.income_view_line_two));
        viewList.add(findViewById(R.id.income_view_line_three));
        viewList.add(findViewById(R.id.income_view_line_four));

        fragmentList.clear();
        for (int i = 0; i < 4; i++) {
            RadioFragmentFragment incomeFragment = RadioFragmentFragment.newInstance(i+"");
            fragmentList.add(incomeFragment);
        }

        //默认显示第一个
        getSupportFragmentManager().beginTransaction().add(R.id.income_framelayout,fragmentList.get(0)).commit();
        setTxtBg(0);
    }

    private void initListener() {
        oneRl.setOnClickListener(this);
        twoRl.setOnClickListener(this);
        threeRl.setOnClickListener(this);
        fourRl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == oneRl){
            switchFragment(0);
            setTxtBg(0);
        }else if (v == twoRl){
            switchFragment(1);
            setTxtBg(1);
        }else if (v == threeRl){
            switchFragment(2);
            setTxtBg(2);
        }else if (v == fourRl){
            switchFragment(3);
            setTxtBg(3);
        }
    }

    public void switchFragment(int index){
        if (mIndex == index){
            return;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(fragmentList.get(mIndex));
        if (!fragmentList.get(index).isAdded()) {
            fragmentTransaction.add(R.id.income_framelayout, fragmentList.get(index)).show(fragmentList.get(index));
        }else {
            fragmentTransaction.show(fragmentList.get(index));
        }
        fragmentTransaction.commit();
        mIndex = index;
    }

    public void setTxtBg(int index){
        for (int i = 0; i < tvList.size(); i++) {
            if (index == i){
                tvList.get(i).setTextColor(Color.parseColor("#FD2A5B"));
                viewList.get(i).setVisibility(View.VISIBLE);
            }else {
                tvList.get(i).setTextColor(Color.parseColor("#333333"));
                viewList.get(i).setVisibility(View.GONE);
            }
        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
