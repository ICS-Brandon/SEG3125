package com.example.wishy;

import java.util.ArrayList;
import java.util.List;

public class UserProfile {

    private List<WishlistItem> wishItems;
    private String userID;

    public UserProfile(List<WishlistItem> wish, String uID){
        wishItems = wish;
        userID = uID;
    }

    public UserProfile(String uID){
        wishItems = new ArrayList<>();
        userID = uID;
    }
}
