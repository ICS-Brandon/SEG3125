package com.example.wishy;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/* Class that hold the Wishlist item, has several setters, getters, and constructors*/

public class WishlistItem implements Parcelable {

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

    protected WishlistItem(Parcel in) {
        price = in.readDouble();
        brand = in.readString();
        name = in.readString();
        mainTag = in.readString();
        url = in.readString();
        wishID = in.readString();
        tags = in.createStringArrayList();
        imageID = in.readInt();
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
        dest.writeStringList(tags);
        dest.writeInt(imageID);
        dest.writeByte((byte) (favourited ? 1 : 0));
    }
}
