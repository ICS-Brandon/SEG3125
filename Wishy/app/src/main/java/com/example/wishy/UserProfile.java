package com.example.wishy;

import java.util.ArrayList;
import java.util.List;

public class UserProfile {

    private List<WishlistItem> wishItems;
    private String userID,profileName,username,email;

    public UserProfile(List<WishlistItem> wish, String uID, String n, String u, String e){
        wishItems = wish;
        userID = uID;
        profileName = n;
        username = u;
        email = e;
    }

    public UserProfile(String uID){
        wishItems = new ArrayList<>();
        userID = uID;
    }

    public UserProfile(String uID, String n, String u, String e){
        wishItems = new ArrayList<>();
        userID = uID;
        profileName = n;
        username = u;
        email = e;
    }

    public String getEmail() {
        return email;
    }

    public String getUserID(){
        return userID;
    }

    public String getProfileName(){
        return profileName;
    }

    public String getUsername(){
        return username;
    }

    public void setEmail(String e){
        email = e;
    }

    public void setUserID(String u){
        userID = u;
    }

    public void setProfileName(String n){
        profileName = n;
    }

    public void setUsername(String u){
        username = u;
    }
}
