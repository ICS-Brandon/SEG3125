package com.example.wishy;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AddEditActivity extends AppCompatActivity {

    private FirebaseHandler fHandler;
    private FirebaseAuth fAuth;
    private WishlistItem wishItem;
    private EditText urlInput, itemName, itemPrice, itemBrand;
    private Button doneButton, deleteButton, favButton;
    private ImageView imageView;
    private String name,brand,price,url;
    private boolean editItem;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.add_edit_layout);

        //Initializing EditTexts and attaching a Textwatcher
        urlInput = findViewById(R.id.urlInput);
        urlInput.addTextChangedListener(watcher);

        itemName = findViewById(R.id.itemName);
        itemName.addTextChangedListener(watcher);

        itemBrand = findViewById(R.id.itemBrand);
        itemBrand.addTextChangedListener(watcher);

        itemPrice = findViewById(R.id.itemPrice);
        itemPrice.addTextChangedListener(watcher);

        //Intializing Buttons and setting correct properties
        doneButton = findViewById(R.id.doneButton);
        doneButton.setClickable(false);

        deleteButton = findViewById(R.id.deleteButton);

        favButton = findViewById(R.id.favouriteButton);
        favButton.setClickable(false);

        //Intializing ImageView
        imageView = findViewById(R.id.wishImage);

        //Getting intent passed from previous activity
        Intent getIntent = getIntent();
        wishItem = getIntent.getParcelableExtra("item");

        /*if(wishId == null){
            Toast.makeText(getApplicationContext(),"Error: No Wishlist Item Found",Toast.LENGTH_LONG);
            finish();
        }*/

        //Intializing FirebaseHandler and Firebase Auth
        fHandler = new FirebaseHandler();
        fAuth = FirebaseAuth.getInstance();

        //Checking if the activity is being used to add an item or edit one
        if(wishItem == null){
            editItem = false;
        }
        else{
            editItem = true;
            populateFields();
        }

        if(editItem)
            favButton.setClickable(true);

    }

    //OnClick to return to the home activity
    public void openHomeActivity(View view){

        Intent intent = new Intent(AddEditActivity.this, HomeActivity.class);
        startActivity(intent);

    }

    //OnClick to go to help activity
    public void openHelpActivity(View view){

        Intent intent = new Intent(AddEditActivity.this, HelpActivity.class);
        startActivity(intent);

    }

    //OnClick to go to settings activity
    public void openSettingsActivity(View view){

        Intent intent = new Intent(AddEditActivity.this, HelpActivity.class);
        startActivity(intent);

    }

    //Method to favourite/unfavourite a wishlist item
    public void favItem(View view){

        //Get wishlist item to edit

        if(wishItem.getFavourited() == true){
            wishItem.setFavourited(false);
        }
        else{
            wishItem.setFavourited(true);
        }

        /*WishlistItem item = fHandler.getWishlistItem(wishItem.getWishID());

        //TODO add highlighted fav icon
        //Checks if it's favourited or not and toggles it and updates the database
        if(item.getFavourited() == true){
            item.setFavourited(true);
            fHandler.addWishlistItem(item);
        }else{
            item.setFavourited(false);
            fHandler.addWishlistItem(item);
            favButton.setBackgroundResource(R.drawable.favourite_icon);
        }*/

    }


    //Method to delete an item from the wishlist
    public void deleteItem(View view){

        //Get item from database and then delete it
        //WishlistItem item = fHandler.getWishlistItem(wishId);
        //fHandler.deleteWishlistItem(item);
        Intent returnIntent = new Intent();
        returnIntent.putExtra("item",wishItem);
        setResult(0,returnIntent);
        finish();

    }

    //Method to add an item to the database
    public void addItem(View view){

        //Convert price into double value and create wishlist item to add
        double priceDouble = Double.parseDouble(price);
        WishlistItem wishlistItem = new WishlistItem(priceDouble,brand,name,url);
        //fHandler.addWishlistItem(wishlistItem);
        finish();

    }

    //Method to populate the EditText fields if being used to edit
    public void populateFields(){

        //Pass current user to get item and take values of item to put into fields
        FirebaseUser fUser = fAuth.getCurrentUser();
        fHandler.setfUser(fUser);


        urlInput.setText(wishItem.getUrl());
        itemName.setText(wishItem.getName());
        itemBrand.setText(wishItem.getBrand());
        itemPrice.setText(String.valueOf(wishItem.getPrice()));

        if(wishItem.getFavourited() == true){
            favButton.setBackgroundResource(R.drawable.favourite_icon);
        }

    }

    //Textwatcher for EditText fields
    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            //Get string values of all fields and check if they're null, if not then set the done button to clickable
            name = itemName.getText().toString().trim();
            brand = itemBrand.getText().toString().trim();
            price = itemPrice.getText().toString().trim();
            url = urlInput.getText().toString().trim();

            if(name != null && brand != null && price != null && url != null){
                if(URLUtil.isValidUrl(url)){
                    doneButton.setClickable(true);
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };
}
