package com.example.huangzx.tablayoutdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private android.support.v7.widget.AppCompatButton tabLayout1;
    private android.support.v7.widget.AppCompatButton tabLayout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.tabLayout2 = (AppCompatButton) findViewById(R.id.TabLayout2);
        this.tabLayout1 = (AppCompatButton) findViewById(R.id.TabLayout1);
        this.tabLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnToTabLaytout(2);
            }
        });
        this.tabLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnToTabLaytout(1);
            }
        });
    }

    private void turnToTabLaytout(int i) {
        switch (i){
            case 1://自定义
                Intent intent1 = new Intent(this, Activity1.class);
                startActivity(intent1);
                break;
            case 2://默认
                Intent intent2 = new Intent(this, Activity2.class);
                startActivity(intent2);
                break;
            default:
                break;
        }
    }
}
