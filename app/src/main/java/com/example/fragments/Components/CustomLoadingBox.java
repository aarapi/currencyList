package com.example.fragments.Components;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;

import androidx.annotation.NonNull;

import com.example.fragments.R;

public class CustomLoadingBox extends Dialog {
    private CustomLoadingBox loadingBox;
    public CustomLoadingBox(@NonNull Context context) {
        super(context);
        loadingBox = this;
    }
    public void setDialogView(){
        loadingBox.setCancelable(false);
        loadingBox.setCanceledOnTouchOutside(false);
        loadingBox.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        loadingBox.setContentView(R.layout.custom_loading_box);
        loadingBox.show();
    }



}
