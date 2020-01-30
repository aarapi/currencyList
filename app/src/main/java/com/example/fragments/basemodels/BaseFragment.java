package com.example.fragments.BaseModels;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {
public View containerView;
private int layoutId;
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

    }

    public BaseFragment(int layoutId) {
        this.layoutId = layoutId;
    }

    public abstract void initViews();
    public abstract void bindEvents();
    public abstract void setViews();

}
