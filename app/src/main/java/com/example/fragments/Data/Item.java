package com.example.fragments.Data;

import android.graphics.Bitmap;

public class Item {
   private int homeIcon;
   private String name;
    public Item(int homeIcon, String name){
        this.homeIcon = homeIcon;
        this.name = name;
    }

    public int getHomeIcon() {
        return homeIcon;
    }

    public String getName() {
        return name;
    }
}
