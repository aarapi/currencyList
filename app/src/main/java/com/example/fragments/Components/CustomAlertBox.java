package com.example.fragments.Components;
import android.content.Context;
import android.widget.Toast;

import com.rahman.dialog.Activity.SmartDialog;
import com.rahman.dialog.ListenerCallBack.SmartDialogClickListener;
import com.rahman.dialog.Utilities.SmartDialogBuilder;

public class CustomAlertBox implements SmartDialogClickListener {
    private Context mContext;
    private SmartDialogBuilder smartDialogBuilder;
    private String title, alertText;
    private boolean negativeHide;

    public CustomAlertBox(final Context mContext, String title, String alertText, boolean negativeHide) {
        this.mContext = mContext;
        this.title = title;
        this.alertText = alertText;
        this.negativeHide = negativeHide;
    }

    public void showAlertBox(){
        smartDialogBuilder = new SmartDialogBuilder(mContext);
        smartDialogBuilder.setTitle(title)
                .setSubTitle(alertText)
                .setCancalable(false)
                .setNegativeButtonHide(negativeHide)
                .setPositiveButton("OK",this)
                .setNegativeButton("Cancel", this)
                .build()
                .show();
    }

    @Override
    public void onClick(SmartDialog smartDialog) {
        Toast.makeText(mContext,"Ok button Click",Toast.LENGTH_SHORT).show();
        smartDialog.dismiss();
//        Toast.makeText(mContext,"Cancel button Click",Toast.LENGTH_SHORT).show();
//        smartDialog.dismiss();
    }



}
