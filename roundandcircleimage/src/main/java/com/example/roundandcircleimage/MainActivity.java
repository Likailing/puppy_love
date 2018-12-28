package com.example.roundandcircleimage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.roundandcircleimage.activity.RoundImageViewActivity1;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);


        button1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //更倾向第一种RoundImageViewActivity1
        if (v == button1){
            Intent intent = new Intent(MainActivity.this, RoundImageViewActivity1.class);
            startActivity(intent);
        }
    }
}
