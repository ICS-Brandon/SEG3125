package com.example.wishy;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import ir.hatamiarash.toast.RTLToast;

public class AddEditActivity extends AppCompatActivity {

    private FirebaseHandler fHandler;
    private FirebaseAuth fAuth;
    private WishlistItem wishItem;
    private EditText urlInput, itemName, itemPrice, itemBrand, itemTag;
    private Button doneButton, deleteButton, favButton, editImage;
    private ImageView imageView;
    private String name,brand,url, tag;
    private Double price;
    private String id;
    private boolean editItem, addItem, favourited;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.add_edit_layout);

        RTLToast.Config.getInstance().setTextSize(10).apply();

        addItem = false;

        //Initializing EditTexts and attaching a Textwatcher
        editImage = findViewById(R.id.editImageButton);

        urlInput = findViewById(R.id.urlInput);
        urlInput.addTextChangedListener(watcher);

        itemName = findViewById(R.id.itemName);
        itemName.addTextChangedListener(watcher);

        itemBrand = findViewById(R.id.itemBrand);
        itemBrand.addTextChangedListener(watcher);

        itemPrice = findViewById(R.id.itemPrice);
        itemPrice.addTextChangedListener(watcher);

        itemTag = findViewById(R.id.itemTag);
        itemTag.addTextChangedListener(watcher);

        //Intializing Buttons and setting correct properties
        doneButton = findViewById(R.id.doneButton);

        deleteButton = findViewById(R.id.deleteButton);

        favButton = findViewById(R.id.favouriteButton);
        favButton.setClickable(false);

        //Intializing ImageView
        imageView = findViewById(R.id.wishImage);

        //Getting intent passed from previous activity
        Intent getIntent = getIntent();
        wishItem = getIntent.getParcelableExtra("item");

        //Intializing FirebaseHandler and Firebase Auth
        fHandler = new FirebaseHandler();
        fAuth = FirebaseAuth.getInstance();

        //Checking if the activity is being used to add an item or edit one
        if(wishItem != null){
            editItem = true;
            favourited = wishItem.getFavourited();
            favButton.setClickable(true);
            if(favourited)
                favButton.setBackgroundResource(R.drawable.white_favourite_icon_filled);
            populateFields();
        }
        else {
            editItem = false;
        }

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

        if(favourited != wishItem.getFavourited()){
            addItem = true;
        }

        if(favourited){
            wishItem.setFavourited(false);
            favButton.setBackgroundResource(R.drawable.favourite_icon);
            favourited = false;
        }
        else{
            wishItem.setFavourited(true);
            favButton.setBackgroundResource(R.drawable.white_favourite_icon_filled);
            favourited = true;
        }

    }


    //Method to delete an item from the wishlist
    public void deleteItem(View view){

        //Get item from database and then delete it
        Intent returnIntent = new Intent();
        returnIntent.putExtra("item",wishItem);
        setResult(0,returnIntent);
        finish();

    }

    //Method to add an item to the database
    public void createItem(View view){

        //Convert price into double value and create wishlist item to add
        if(addItem && !editItem){
            addItemToList();
        }
        else if(addItem && editItem){
            editItemInList();
        }

    }

    public void addItemToList(){
        WishlistItem testItem = new WishlistItem(price,brand,name,url,this);
        testItem.setMainTag(tag);
        if(id != null)
            testItem.setImage(id);
        Intent testIntent = new Intent();
        testIntent.putExtra("item",testItem);
        setResult(-1,testIntent);
        finish();
    }

    public void editItemInList(){
        WishlistItem wishlistItem = new WishlistItem(price,brand,name,url,this);
        wishlistItem.setMainTag(tag);
        if(id != null)
            wishlistItem.setImage(id);
        wishlistItem.setWishID(wishItem.getWishID());
        Intent returnIntent = new Intent();
        returnIntent.putExtra("item",wishlistItem);
        setResult(1,returnIntent);
        finish();
    }

    public void changeImage(View view){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),0);
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
        itemTag.setText(wishItem.getMainTag());
        Picasso.get().load(wishItem.getImage()).into(imageView);

    }

    public boolean isEmptyString(String s){
        if(s.equals(null) || s.equals("")){
            return false;
        }
        else{
            return true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == RESULT_OK){
            Uri filePath = data.getData();
            String path = filePath.toString();
            id = path;
            Picasso.get().load(path).into(imageView);
            addItem = true;
        }
    }
    //Textwatcher for EditText fields
    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            //Get string values of all fields and check if they're null, if not then set the done button to clickable
            name = itemName.getText().toString().trim();
            brand = itemBrand.getText().toString().trim();
            String priceString = itemPrice.getText().toString();
            url = urlInput.getText().toString().trim();
            tag = itemTag.getText().toString().trim();

            if(editItem) {
                if(isEmptyString(name) && isEmptyString(brand) && isEmptyString(priceString) && isEmptyString(url)){
                    price = Double.parseDouble(priceString);
                    if(!name.equals(wishItem.getName())){
                        addItem = true;
                    }
                    else if(!brand.equals(wishItem.getBrand())){
                        addItem = true;
                    }
                    else if(price != wishItem.getPrice()){
                        addItem = true;
                    }
                    else if(!url.equals(wishItem.getUrl()) && URLUtil.isValidUrl(url)){
                        addItem = true;
                    }
                    else if(!tag.equals(wishItem.getMainTag())){
                        addItem = true;
                    }
                }
                else
                    RTLToast.warning(getApplicationContext(),"Warning: Cannot have empty fields",Toast.LENGTH_SHORT);
            }
            else if(!editItem){
                if(isEmptyString(name) && isEmptyString(brand) && isEmptyString(priceString) && isEmptyString(url)) {
                    price = Double.parseDouble(priceString);
                    addItem = true;
                }
            }
        }
    };
}
