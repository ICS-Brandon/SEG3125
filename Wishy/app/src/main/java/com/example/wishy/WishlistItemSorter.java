package com.example.wishy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WishlistItemSorter {

    public List<WishlistItem> sortselector(String sort, List<WishlistItem> wishItems){

        List<WishlistItem> wishItemsSorted = new ArrayList<>();

        switch(sort){
            case "name":
                wishItemsSorted = sortByName(wishItems);
                break;
            case "brand":
                wishItemsSorted = sortByBrand(wishItems);
                break;
            case "price":
                wishItemsSorted = sortByPrice(wishItems);
                break;
            case "tags":
                wishItemsSorted = sortByTag(wishItems);
                break;
            default:
                break;
        }

        return wishItemsSorted;
    }

    public List<WishlistItem> sortByName(List<WishlistItem> wish){
        Collections.sort(wish, new Comparator<WishlistItem>() {
            @Override
            public int compare(WishlistItem o1, WishlistItem o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });


        return wish;
    }

    public List<WishlistItem> sortByBrand(List<WishlistItem> wish){
        Collections.sort(wish, new Comparator<WishlistItem>() {
            @Override
            public int compare(WishlistItem o1, WishlistItem o2) {
                return o1.getBrand().compareTo(o2.getBrand());
            }
        });
        return wish;
    }

    public List<WishlistItem> sortByPrice(List<WishlistItem> wish){
        Comparator<WishlistItem> priceComparator = new Comparator<WishlistItem>(){
            public int compare(WishlistItem o1, WishlistItem o2) {
                if(o1.getPrice() > o2.getPrice()){
                    return 1;
                }
                if(o1.getPrice() < o2.getPrice()){
                    return -1;
                }
                else{
                    return 0;
                }
            }
        };

        Collections.sort(wish, priceComparator);

        return wish;
    }

    public List<WishlistItem> sortByTag(List<WishlistItem> wish){
        Collections.sort(wish, new Comparator<WishlistItem>() {
            @Override
            public int compare(WishlistItem o1, WishlistItem o2) {
                return o1.getMainTag().compareTo(o2.getMainTag());
            }
        });

        return wish;
    }
}
