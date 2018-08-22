package com.lkl.puply;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lkl.puply.FrameLayoutAndroidRadioGroup.RadioFragmentActivity;
import com.lkl.puply.FrameLayoutAndroidRadioGroup.TabLayoutViewPagerActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RadioFragmentActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.tv_test2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TabLayoutViewPagerActivity.class);
                startActivity(intent);
            }
        });
    }
}
