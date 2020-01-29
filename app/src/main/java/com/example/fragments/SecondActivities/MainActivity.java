package com.example.fragments.SecondActivities;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fragments.BaseModels.BaseActivity;
import com.example.fragments.Components.CustomAlertBox;
import com.example.fragments.FragmentSearch;
import com.example.fragments.FragmentTest;
import com.example.fragments.FragmentHome;
import com.example.fragments.R;
import com.example.fragments.tasks.SendRequest;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import static com.example.fragments.utilities.HttpUtil.getResponse;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    private BottomNavigationView bottomNavigationView;
    private Fragment selectedFragment;
    private Button button, testButton;
    private ImageView imageView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SendRequest sendRequest = new SendRequest();

        sendRequest.execute("http://data.fixer.io/api/latest?access_key=2156c20147807a01dbad5bfbc1190771");


  }

    CustomAlertBox customAlertBox;
    @Override
    public void initViews() {
        bottomNavigationView = findViewById(R.id.bt_navigation_menu);
        customAlertBox  = new CustomAlertBox(this, "Hello","Try to escape", true);
        customAlertBox.showAlertBox();
        imageView = findViewById(R.id.iv_test);
    }

    @Override
    public void setViews() {

    }

    @Override
    public void bindEvents() {
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public int getLayoutContent() {
        return R.layout.main_activity;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        selectedFragment = null;
         FragmentHome selectedFragment1 =null;
        switch (menuItem.getItemId()){
            case R.id.nav_home:
                 selectedFragment = new FragmentHome(fragmentManager);
                break;
            case R.id.nav_countries:
                selectedFragment = new FragmentTest();

                break;
            case R.id.nav_search:
              selectedFragment = new   FragmentSearch(this);
                break;
        }
        fragmentManager.beginTransaction()
                .replace(R.id.fr_fragment_container, selectedFragment).commit();
        return true;
    }

    @Override
    public void onDataReceive(int p_action, List<Object> p_list) {
        super.onDataReceive(p_action, p_list);
    }

    @Override
    public Activity getActivity() {
        return this;
    }


}
