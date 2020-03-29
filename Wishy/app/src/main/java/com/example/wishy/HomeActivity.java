package com.example.wishy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner sortSpinner;
    private SpinnerHandler sorter = new SpinnerHandler();
    private WishlistItemAdapter wishAdapter;
    private ArrayList<WishlistItem> wishItems;
    private ListView wishlistView;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.home_layout);

        sortSpinner =  findViewById(R.id.sortSpinner);
        sortSpinner.setOnItemSelectedListener(this);

        wishlistView = findViewById(R.id.itemScrollView);

        wishItems = new ArrayList<>();

        getwishItems();
        loadSpinnerOptions();
    }

    //Method to go to Add/Edit activity
    public void addItem(View view){

        Intent intent = new Intent(HomeActivity.this,AddEditActivity.class);
        startActivity(intent);

    }

    //Method to go to Settings activity
    public void openSettings(View view){

        Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
        startActivity(intent);

    }

    //Method to go to Help activity
    public void openHelp(View view){

        Intent intent = new Intent(HomeActivity.this,HelpActivity.class);
        startActivity(intent);

    }

    //Method called when item from the spinner is selected
    @Override
    public void onItemSelected(AdapterView<?> parent, View arg1, int position,long id) {
        String sorting = parent.getItemAtPosition(position).toString().trim();
        displayItemsBy(sorting);
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0){
        //TODO dunno;
    }

    //Method to load the options for the spinner
    public void loadSpinnerOptions(){
        sorter.addOptionsFromUser();
        List<String> options = sorter.getSortOptions();

        ArrayAdapter<String> sortAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,options);
        sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(sortAdapter);
    }

    public void getwishItems(){
        //TODO get list of wishlisted items to store in arraylist
    }

    //Method to display the wishlist items in the listview
    public void displayItemsBy(String sortingBy){

        //Create new adapter for the listview, set it and create a new sorter for the items
        wishAdapter = new WishlistItemAdapter(this,wishItems);
        wishlistView.setAdapter(wishAdapter);
        WishlistItemSorter wishSorter = new WishlistItemSorter();

        //TODO put users items in arraylist
        //Create arraylist of items, create dummy item and sort array based on user selection
        List<WishlistItem> userItems = new ArrayList<>();
        userItems = wishSorter.sortSelector(sortingBy,userItems);
        WishlistItem wishlistItem = new WishlistItem(34.99,"Hollister Co.","Jacket","www.google.ca");

        //Add items to the adapter
        wishAdapter.add(wishlistItem);
        /*for(WishlistItem w : userItems){
            wishAdapter.add(w);
        }*/

    }
}
