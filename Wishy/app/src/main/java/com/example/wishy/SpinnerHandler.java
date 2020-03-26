package com.example.wishy;

import java.util.ArrayList;
import java.util.List;

public class SpinnerHandler {

    private List<String> sortOptions;

    public SpinnerHandler(List<String> options){
        sortOptions = options;
    }

    public SpinnerHandler(){
        sortOptions = new ArrayList<>();
    }

    public void setSortOptions(List<String> o){
        sortOptions = o;
    }

    public List<String> getSortOptions(){
        return sortOptions;
    }

    public void addOptionsFromUser(){
        List<String> userOptions = new ArrayList<>();
        userOptions.add("Placeholder");
        for(String o : userOptions){
            sortOptions.add(o);
        }
    }

}
