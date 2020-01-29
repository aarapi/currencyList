package com.example.fragments;

import android.content.ClipData;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.example.fragments.Adapters.CustomGridViewAdapter;
import com.example.fragments.BaseModels.BaseFragment;
import com.example.fragments.Data.Item;

import java.util.ArrayList;

public  class FragmentTest extends BaseFragment {
    private GridView gridView;
    private  ArrayList<Item> gridArray = new ArrayList<Item>();
    private CustomGridViewAdapter customGridAdapter;
    private int homeIcon;
    private Button button;
    public FragmentTest(){
        super(R.layout.fragment_countries_layout);
    }

    @Override
    public void initViews() {
         homeIcon =  R.drawable.ic_launcher_background;

        gridArray.add(new Item(homeIcon,"Home"));
        gridArray.add(new Item(homeIcon,"User"));
        gridArray.add(new Item(homeIcon,"House"));
        gridArray.add(new Item(homeIcon,"Friend"));
        gridArray.add(new Item(homeIcon,"Friend"));
        gridArray.add(new Item(homeIcon,"Friend"));

        gridView =  containerView.findViewById(R.id.gridView1);

        button = containerView.findViewById(R.id.buttonPanel);
    }

    @Override
    public void setViews() {
        customGridAdapter = new CustomGridViewAdapter(this.getContext(), R.layout.row_grid, gridArray);
        gridView.setAdapter(customGridAdapter);
    }

    @Override
    public void bindEvents() {

    }
    public Button getButton(){
        return button;
    }


}
