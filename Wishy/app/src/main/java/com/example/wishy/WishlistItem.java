package com.example.wishy;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/* Class that hold the Wishlist item, has several setters, getters, and constructors*/

public class WishlistItem implements Parcelable {

    private double price;
    private String brand, name, mainTag, url, wishID;
    private String image;
    private boolean favourited;
    private Context mContext;

    public WishlistItem(double p, String b, String n, String u, String t, Context c, String i){
        price = p;
        brand = b;
        name = n;
        image = i;
        favourited = false;
        mainTag = t;
        wishID = UUID.randomUUID().toString();
        url = u;
        mContext = c;
    }

    public WishlistItem(double p, String b, String n, String u,Context c,String i){
        price = p;
        brand = b;
        name = n;
        image = i;
        favourited = false;
        wishID = UUID.randomUUID().toString();
        url = u;
        mainTag = "";
        mContext = c;
    }

    public WishlistItem(double p, String b, String n, String u, String t, Context c){
        price = p;
        brand = b;
        name = n;
        mainTag = t;
        favourited = false;
        wishID = UUID.randomUUID().toString();
        url = u;
        mContext = c;
        image = "content://com.android.providers.downloads.documents/document/raw%3A%2Fstorage%2Femulated%2F0%2FDownload%2Ftest_image.png";
    }

    public WishlistItem(double p, String b, String n, String u, Context c){
        price = p;
        brand = b;
        name = n;
        mContext = c;
        image = "content://com.android.providers.downloads.documents/document/raw%3A%2Fstorage%2Femulated%2F0%2FDownload%2Ftest_image.png";
        favourited = false;
        wishID = UUID.randomUUID().toString();
        url = u;
        mainTag = "";
    }

    protected WishlistItem(Parcel in) {
        price = in.readDouble();
        brand = in.readString();
        name = in.readString();
        mainTag = in.readString();
        url = in.readString();
        wishID = in.readString();
        image = in.readString();
        favourited = in.readByte() != 0;
    }

    public static final Creator<WishlistItem> CREATOR = new Creator<WishlistItem>() {
        @Override
        public WishlistItem createFromParcel(Parcel in) {
            return new WishlistItem(in);
        }

        @Override
        public WishlistItem[] newArray(int size) {
            return new WishlistItem[size];
        }
    };

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

    public String getImage(){
        return image;
    }

    public void setImage(String i){
        image = i;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(price);
        dest.writeString(brand);
        dest.writeString(name);
        dest.writeString(mainTag);
        dest.writeString(url);
        dest.writeString(wishID);
        dest.writeString(image);
        dest.writeByte((byte) (favourited ? 1 : 0));
    }
}
