package com.example.fragments.basemodels;

import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fragments.data.RequestModel;
import com.example.fragments.tasks.SendRequest;

public abstract class BaseFragment extends Fragment {
    protected View containerView;
private int layoutId;
    public static String ACTION_DATA_RECEIVER_BASE = "com.example.fragments";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       containerView = inflater.inflate(layoutId, null);
       return containerView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        setViews();
        bindEvents();
        sendRequestMessage();


    }


    public BaseFragment(int layoutId) {
        this.layoutId = layoutId;
    }

    public abstract void initViews();
    public abstract void bindEvents();
    public abstract void setViews();

    protected void sendRequestMessage() {
    }

    ;


}
