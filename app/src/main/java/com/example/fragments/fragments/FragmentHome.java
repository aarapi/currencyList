package com.example.fragments;

import android.transition.TransitionInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toolbar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.fragments.BaseModels.BaseFragment;

public class FragmentHome extends BaseFragment {
    private Button button;
    private ImageView imageView;
    private FragmentManager fragmentManager;
    private Toolbar toolbar;

    public FragmentHome(FragmentManager fragmentManager) {
        super(R.layout.fragment_home_layout);
        this.fragmentManager = fragmentManager;
    }



    @Override
    public void initViews() {
        button = containerView.findViewById(R.id.buttonPanel);
        imageView = containerView.findViewById(R.id.iv_test);
    }

    @Override
    public void setViews() {
    }

    @Override
    public void bindEvents() {
        final Fragment fragment = new FragmentTest();
        fragment.setSharedElementEnterTransition(TransitionInflater.
                from(getActivity()).inflateTransition(R.transition.transition_custom));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction().replace(R.id.fr_fragment_container,fragment
                       ).addSharedElement(imageView, "test_transition").
                        addToBackStack(null).commit();
            }
        });

    }
    public Button getButton(){
        return button;
    }



}
