package com.example.huangzx.tablayoutdemo;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;


public class Activity2 extends AppCompatActivity {

    private CustomViewPager viewPager;

    private TabLayoutItem[] tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        viewPager = (CustomViewPager) findViewById(R.id.viewpager);
        SimpleFragmentPagerAdapter pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this);
//        viewPager.setScanScroll(false);
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);

        tabLayoutInit(tabLayout);
    }

    private void tabLayoutInit(TabLayout tabLayout) {
        tabLayout.setupWithViewPager(this.viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        String[] strings = {"没头没脑", "脑洞全开", "开开心心"};
        int[] unReadNumber = {1, 2, 3};
        tabs = new TabLayoutItem[tabLayout.getTabCount()];

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tabs[i] = new TabLayoutItem(this);
            assert tab != null;
            tab.setCustomView(tabs[i].getItemView(strings[i], unReadNumber[i]));
            if (i == 0) {
                tabs[i].setSelectTextTitle();
            } else {
                tabs[i].setUnSelectTextTitle();
            }
        }
        onClickListenerTabLayout(tabLayout);
    }

    /**
     * 切换当前显示的fragment
     *
     * @param i 切换到的位置
     */
    public void updateCurrentFragment(int i) {
        if (viewPager != null) {
            viewPager.setCurrentItem(i);
        }
    }

    private void onClickListenerTabLayout(TabLayout tabLayoutCircleMain) {
        tabLayoutCircleMain.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                tabs[pos].setSelectTextTitle();
                updateCurrentFragment(pos);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                tabs[pos].setUnSelectTextTitle();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public class TabLayoutItem {
        AppCompatTextView textNumberTabItem;
        AppCompatTextView textTitleTabItem;
        View view;

        /**
         * 构造方法，初始化布局以及find控件
         * @param context Context
         */
        public TabLayoutItem(Context context) {
            view = LayoutInflater.from(context).inflate(R.layout.tab_item, null);
            textNumberTabItem =
                    (AppCompatTextView) view.findViewById(R.id.textNumberTabItem);
            textTitleTabItem =
                    (AppCompatTextView) view.findViewById(R.id.textTitleTabItem);

        }

        /**
         * 获取一个Item视图
         * @param tabTitle 标题
         * @param unReadNumber 未读数（右上角的红色数字）
         * @return Item视图
         */
        public View getItemView(String tabTitle, int unReadNumber) {
            textTitleTabItem.setText(tabTitle);
            setUnReadNumber(unReadNumber);
            return view;
        }

        /**
         * 设置未读数，0的时候隐藏
         * @param unReadNumber 未读数
         */
        public void setUnReadNumber(final int unReadNumber) {
            new Handler(Looper.getMainLooper()).postAtFrontOfQueue(new Runnable() {

                @Override
                public void run() {
                    if (unReadNumber == 0) {
                        textNumberTabItem.setVisibility(View.GONE);
                    } else {
                        String s = String.valueOf(unReadNumber);
                        textNumberTabItem.setText(s);
                        textNumberTabItem.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

        /**
         * 设置选中Text颜色
         */
        public void setSelectTextTitle() {
            textTitleTabItem.setTextColor(getResources().getColor(R.color.background_floating_material_dark));
        }

        /**
         * 设置未选中Text颜色
         */
        public void setUnSelectTextTitle() {
            textTitleTabItem.setTextColor(getResources().getColor(R.color.gold));
        }
    }
}
