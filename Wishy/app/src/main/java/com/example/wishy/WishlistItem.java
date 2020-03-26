package com.example.wishy;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.List;

public class WishlistItem {

    private double price;
    private String brand, name, mainTag;
    private List<String> tags;
    private Bitmap imageBitmap;

    public WishlistItem(double p, String b, String n, List<String> t, Bitmap i){
        price = p;
        brand = b;
        name = n;
        tags = t;
        imageBitmap = i;
        if(t.size() > 0)
            mainTag = t.get(0);
    }

    public WishlistItem(double p, String b, String n, Bitmap i){
        price = p;
        brand = b;
        name = n;
        tags = new ArrayList<>();
        imageBitmap = i;
    }

    public WishlistItem(double p, String b, String n, List<String> t){
        price = p;
        brand = b;
        name = n;
        tags = t;
        if(t.size() > 0)
            mainTag = t.get(0);
        imageBitmap = BitmapFactory.decodeFile("/drawable/test_image.png");
    }

    public WishlistItem(double p, String b, String n){
        price = p;
        brand = b;
        name = n;
        tags = new ArrayList<>();
        imageBitmap = BitmapFactory.decodeFile("/drawable/test_image.png");
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

    public Bitmap getImageBitmap(){
        return imageBitmap;
    }

    public void setImageBitmap(Bitmap i){
        imageBitmap = i;
    }

    public String getMainTag(){
        return mainTag;
    }

    public void setMainTag(String t){
        mainTag = t;
    }
}
