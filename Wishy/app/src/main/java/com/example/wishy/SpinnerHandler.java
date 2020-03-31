package com.example.wishy;

import java.util.ArrayList;
import java.util.List;

public class SpinnerHandler {

    private List<String> sortOptions;

    public SpinnerHandler(List<String> options){
        sortOptions = options;
        sortOptions.add("Name");
        sortOptions.add("Brand");
        sortOptions.add("Price");
        sortOptions.add("Favourited");
    }

    public SpinnerHandler(){
        sortOptions = new ArrayList<>();
        sortOptions.add("Name");
        sortOptions.add("Brand");
        sortOptions.add("Price");
        sortOptions.add("Favourited");
    }

    public void setSortOptions(List<String> o){
        sortOptions = o;
    }

    public List<String> getSortOptions(){
        return sortOptions;
    }

    public void addOptionsFromUser(){
        ArrayList<String> userOptions = new ArrayList<>();
        for(String o : userOptions){
            sortOptions.add(o);
        }
    }

}
