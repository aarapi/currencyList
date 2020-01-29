package com.example.fragments.BaseModels;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.fragments.R;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseActivity extends AppCompatActivity {
    public FragmentManager fragmentManager;

    DataReceiver dataReceiver = new DataReceiver();
    public Context mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentManager = getSupportFragmentManager();
        setContentView(getLayoutContent());
    }

    @Override
    protected void onResume() {
        super.onResume();
        initViews();
        setViews();
        bindEvents();
        registerReceiver(dataReceiver, new IntentFilter("TEST_RECEIVER"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(dataReceiver);
    }

    public abstract void initViews();
    public abstract void bindEvents();
    public abstract void setViews();
    public abstract int getLayoutContent();
    public abstract Activity getActivity();
    public void onDataReceive(int p_action, List<Object> p_list) {}
    public void testReceiver(){
        Intent intent = new Intent();
        intent.setAction("TEST_RECEIVER");
        sendBroadcast(intent);
    }
    class DataReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            List<Object> test = new ArrayList<>();
            String testStr = "Hello";
            test.add(testStr);
            test.add(testStr);
            onDataReceive(101, test);
        }
    }
}

