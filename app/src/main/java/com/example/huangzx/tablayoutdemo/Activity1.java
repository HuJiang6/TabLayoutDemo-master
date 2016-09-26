package com.example.huangzx.tablayoutdemo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

public class Activity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        CustomViewPager viewPager = (CustomViewPager) findViewById(R.id.viewpager);
        SimpleFragmentPagerAdapter pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        /**
         * 绑定联动的ViewPager（必须先设置其适配器）
         */
        tabLayout.setupWithViewPager(viewPager);
        /**
         * 设置模式
         * public static final int MODE_SCROLLABLE = 0;//可滑动的，建议当有多个Item总长度超过屏幕宽度的时候使用
         * public static final int MODE_FIXED = 1;//自然是与上一个相对应的模式(默认使用该模式)，不能滑动，整个TabLayout是充满屏幕宽度的，建议当Item总长度不超过屏幕的时候使用
         */
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        /**
         * 设置Text的颜色，第一个为正常状态下的颜色，第二个为选中情况下的颜色
         * （还可以在布局中设置，像这样：
         * app:tabSelectedTextColor="@color/purple"
         * app:tabTextColor="@color/green"）
         * public void setTabTextColors(int normalColor, int selectedColor)
         */
        tabLayout.setTabTextColors(getResources().getColor(R.color.green), getResources().getColor(R.color.purple));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final String[] strings = {"TAB1", "TAB2", "TAB3"};

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setText(strings[i]);
        }

    }
}
