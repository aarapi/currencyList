package com.example.fragments.basemodels;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.fragments.tasks.SendRequest;
import com.example.fragments.data.RequestModel;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseActivity extends AppCompatActivity {
    public FragmentManager fragmentManager;
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
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public abstract void initViews();
    public abstract void bindEvents();
    public abstract void setViews();
    public abstract int getLayoutContent();

    public void sendRequest(RequestModel requestModel) {

        DataReceiver receiver = new DataReceiver();
        String _action = BaseFragment.ACTION_DATA_RECEIVER_BASE + requestModel.p_action;
        getActivity().registerReceiver(receiver, new IntentFilter(_action));

        SendRequest sendRequest = new SendRequest(getActivity());
        sendRequest.execute(requestModel.url);
    }
    public abstract Activity getActivity();

    class DataReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context p_context, Intent p_intent) {
            int _action = p_intent.getIntExtra("action", -1);
            String data = p_intent.getStringExtra("data");

            onDataReceive(_action, data);

        }
    }

    public void onDataReceive(int action, String data) {

    }
}

