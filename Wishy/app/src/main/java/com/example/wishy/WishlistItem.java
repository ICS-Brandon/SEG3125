package com.example.wishy;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.List;

public class WishlistItem {

    private double price;
    private String brand, name, mainTag;
    private List<String> tags;
    private int imageID;

    public WishlistItem(double p, String b, String n, List<String> t, int i){
        price = p;
        brand = b;
        name = n;
        tags = t;
        imageID = i;
        if(t.size() > 0)
            mainTag = t.get(0);
    }

    public WishlistItem(double p, String b, String n, int i){
        price = p;
        brand = b;
        name = n;
        tags = new ArrayList<>();
        imageID = i;
    }

    public WishlistItem(double p, String b, String n, List<String> t){
        price = p;
        brand = b;
        name = n;
        tags = t;
        if(t.size() > 0)
            mainTag = t.get(0);
        imageID = R.drawable.test_image;
    }

    public WishlistItem(double p, String b, String n){
        price = p;
        brand = b;
        name = n;
        tags = new ArrayList<>();
        imageID = R.drawable.test_image;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double p){
        price = p;
    }

    public String getBrand(){
        return brand;
    }

    public void setBrand(String b){
        brand = b;
    }

    public String getName(){
        return name;
    }

    public void setName(String n){
        name = n;
    }

    public List<String> getTags(){
        return tags;
    }

    public void setTags(List<String> t){
        tags = t;
    }

    public int getImageID(){
        return imageID;
    }

    public void setImageID(int i){
        imageID = i;
    }

    public String getMainTag(){
        return mainTag;
    }

    public void setMainTag(String t){
        mainTag = t;
    }
}
