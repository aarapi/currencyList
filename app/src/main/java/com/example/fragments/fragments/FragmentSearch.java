package com.example.fragments;

import android.content.Intent;
import android.graphics.Point;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.example.fragments.BaseModels.BaseActivity;
import com.example.fragments.BaseModels.BaseFragment;
import com.ferfalk.simplesearchview.SimpleSearchView;
import com.ferfalk.simplesearchview.utils.DimensUtils;

public class FragmentSearch extends BaseFragment {
    public static final int EXTRA_REVEAL_CENTER_PADDING = 40;
    private SimpleSearchView searchView;
    private BaseActivity activity;
    public FragmentSearch(BaseActivity activity) {
        super(R.layout.test_activity);
        this.activity = activity;



    }

    @Override
    public void initViews() {
        Toolbar toolbar = containerView.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        searchView = containerView.findViewById(R.id.searchView);
        searchView.enableVoiceSearch(true);
        setHasOptionsMenu(true);
    }

    @Override
    public void setViews() {

    }

    @Override
    public void bindEvents() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (searchView.onActivityResult(requestCode, resultCode, data)) {
            return;
        }
        startActivityForResult(data,resultCode);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.test_menu, menu);
        setupSearchView(menu);
    }

    private void setupSearchView(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        // Adding padding to the animation because of the hidden menu item
        Point revealCenter = searchView.getRevealAnimationCenter();
        revealCenter.x -= DimensUtils.convertDpToPx(EXTRA_REVEAL_CENTER_PADDING, activity);
    }


}


