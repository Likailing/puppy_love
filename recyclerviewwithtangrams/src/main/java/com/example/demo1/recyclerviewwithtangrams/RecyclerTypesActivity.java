package com.example.demo1.recyclerviewwithtangrams;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.example.demo1.recyclerviewwithtangrams.adapter.MasSecondAdapter;
import com.example.demo1.recyclerviewwithtangrams.entity.JsonData;
import com.example.demo1.recyclerviewwithtangrams.entity.MasSecondEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class RecyclerTypesActivity extends AppCompatActivity {

    private RecyclerView mRecyclerview;
    private MasSecondAdapter adapter;
    private List<MasSecondEntity> list = new ArrayList<>();

    private String jsonData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_types);


        initView();
        loadData();
    }

    private void loadData() {
        jsonData = JsonData.EAT;
        if (!TextUtils.isEmpty(jsonData)){
            List<MasSecondEntity> data = new Gson().fromJson(jsonData, new TypeToken<List<MasSecondEntity>>(){}.getType());
            adapter.setList(data);
        }
    }

    private void initView() {
        mRecyclerview = findViewById(R.id.mas_second_recycler);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MasSecondAdapter(this,list);
        mRecyclerview.setAdapter(adapter);
    }
}
