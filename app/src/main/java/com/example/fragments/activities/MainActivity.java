package com.example.fragments.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.fragments.basemodels.BaseActivity;
import com.example.fragments.data.CurrencyModel;
import com.example.fragments.fragments.FragmentCurrencyList;
import com.example.fragments.R;
import com.example.fragments.fragments.FragmentCurrencyConverter;
import com.example.fragments.utilities.CheckSetup;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;


public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private FragmentCurrencyList fragmentCurrencyList;
    private BaseActivity activity;
    private ViewPager2 viewPager;
    private static final int NUM_PAGES = 2;
    private List<CurrencyModel> currencyModels;

    private ImageView iv_refresh, iv_menu;
    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private FragmentStateAdapter pagerAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewPager = findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
        activity = this;






  }

    @Override
    public void initViews() {
        iv_menu = findViewById(R.id.iv_menu);
        iv_refresh = findViewById(R.id.iv_refresh);

    }

    @Override
    public void setViews() {

    }

    @Override
    public void bindEvents() {
        iv_refresh.setOnClickListener(this);
        iv_menu.setOnClickListener(this);
    }

    @Override
    public int getLayoutContent() {
        return R.layout.main_activity;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        return true;
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onDataReceive(int action, String data) {


        if (action == CheckSetup.LocalActions.ANNOYING_PROJECTS_GET_RATES_LIST) {
            fragmentCurrencyList.onDataRecive(action, data);

        }

    }

    @Override
    public void onClick(View v) {
        fragmentCurrencyList.sendRequestMessage();
    }

    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @Override
        public Fragment createFragment(int position) {
            if (position == 0) {
                fragmentCurrencyList = new FragmentCurrencyList(activity);
                return fragmentCurrencyList;
            } else
                return new FragmentCurrencyConverter(activity);

        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }

    public List<CurrencyModel> getCurrencyModels() {
        return currencyModels;
    }

    public void setCurrencyModels(List<CurrencyModel> currencyModels) {
        this.currencyModels = currencyModels;
    }
}

