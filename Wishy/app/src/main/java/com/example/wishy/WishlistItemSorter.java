package com.example.wishy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WishlistItemSorter {

    public ArrayList<WishlistItem> sortSelector(int sort, ArrayList<WishlistItem> wishItems){

        ArrayList<WishlistItem> wishItemsSorted = new ArrayList<>();

        switch(sort){
            case 1:
                wishItemsSorted = sortByName(wishItems);
                break;
            case 2:
                wishItemsSorted = sortByBrand(wishItems);
                break;
            case 3:
                wishItemsSorted = sortByPrice(wishItems);
                break;
            case 4:
                wishItemsSorted = sortByTag(wishItems);
                break;
            default:
                break;
        }

        return wishItemsSorted;
    }

    public ArrayList<WishlistItem> sortByName(ArrayList<WishlistItem> wish){
        Collections.sort(wish, new Comparator<WishlistItem>() {
            @Override
            public int compare(WishlistItem o1, WishlistItem o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });


        return wish;
    }

    public ArrayList<WishlistItem> sortByBrand(ArrayList<WishlistItem> wish){
        Collections.sort(wish, new Comparator<WishlistItem>() {
            @Override
            public int compare(WishlistItem o1, WishlistItem o2) {
                return o1.getBrand().compareTo(o2.getBrand());
            }
        });
        return wish;
    }

    public ArrayList<WishlistItem> sortByPrice(ArrayList<WishlistItem> wish){
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

    public ArrayList<WishlistItem> sortByTag(ArrayList<WishlistItem> wish){
        Collections.sort(wish, new Comparator<WishlistItem>() {
            @Override
            public int compare(WishlistItem o1, WishlistItem o2) {
                return o1.getMainTag().compareTo(o2.getMainTag());
            }
        });

        return wish;
    }
}
