package com.example.wishy;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/* Class that hold the Wishlist item, has several setters, getters, and constructors*/

public class WishlistItem {

    private double price;
    private String brand, name, mainTag, url, wishID;
    private List<String> tags;
    private int imageID;
    private boolean favourited;

    public WishlistItem(double p, String b, String n, String u, List<String> t, int i){
        price = p;
        brand = b;
        name = n;
        tags = t;
        imageID = i;
        if(t.size() > 0)
            mainTag = t.get(0);
        favourited = false;
        wishID = UUID.randomUUID().toString();
        url = u;
    }

    public WishlistItem(double p, String b, String n, String u, int i){
        price = p;
        brand = b;
        name = n;
        tags = new ArrayList<>();
        imageID = i;
        favourited = false;
        wishID = UUID.randomUUID().toString();
        url = u;
    }

    public WishlistItem(double p, String b, String n, String u, List<String> t){
        price = p;
        brand = b;
        name = n;
        tags = t;
        if(t.size() > 0)
            mainTag = t.get(0);
        imageID = R.drawable.test_image;
        favourited = false;
        wishID = UUID.randomUUID().toString();
        url = u;
    }

    public WishlistItem(double p, String b, String n, String u){
        price = p;
        brand = b;
        name = n;
        tags = new ArrayList<>();
        imageID = R.drawable.test_image;
        favourited = false;
        wishID = UUID.randomUUID().toString();
        url = u;
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

    public boolean getFavourited(){
        return favourited;
    }

    public void setFavourited(boolean f){
        favourited = f;
    }

    public String getWishID(){
        return wishID;
    }

    public void setWishID(String s){
        wishID = s;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String u){
        url = u;
    }
}
