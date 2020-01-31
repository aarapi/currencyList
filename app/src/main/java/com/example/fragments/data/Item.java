package com.example.fragments.data;

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
