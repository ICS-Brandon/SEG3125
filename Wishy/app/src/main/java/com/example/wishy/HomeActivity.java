package com.example.wishy;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner sortSpinner;
    private SpinnerHandler sorter = new SpinnerHandler();
    private EditText searchBar;
    private WishlistItemAdapter wishAdapter;
    private ArrayList<WishlistItem> wishItems;
    private ListView wishlistView;
    private FirebaseHandler fHandler;
    private FirebaseAuth fAuth;
    private WishlistItemSorter wishSorter;
    private int sortingBy;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.home_layout);

        sortingBy = 0;

        searchBar = findViewById(R.id.searchBar);
        searchBar.addTextChangedListener(itemSearch);

        sortSpinner =  findViewById(R.id.sortSpinner);
        sortSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> spinAdapter = ArrayAdapter.createFromResource(this,R.array.sortOptions,android.R.layout.simple_spinner_item);
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(spinAdapter);

        wishlistView = findViewById(R.id.itemScrollView);

        wishItems = new ArrayList<>();

        fHandler = new FirebaseHandler();
        fAuth = FirebaseAuth.getInstance();

        WishlistItem one = new WishlistItem(34.99,"Hollister","The Denim Jacket","www.google.ca","TEST",this);
        one.setFavourited(true);
        WishlistItem two = new WishlistItem(80.00,"Asos","A Denim Jacket","www.google.ca",this);
        WishlistItem three = new WishlistItem(99.99,"Holly","Denim Jacket","www.google.ca",this);
        wishItems.add(three);
        wishItems.add(one);
        wishItems.add(two);
        wishAdapter = new WishlistItemAdapter(this,wishItems);

        wishlistView.setAdapter(wishAdapter);
        wishSorter = new WishlistItemSorter();
        wishAdapter.notifyDataSetChanged();
    }

    //Method to go to Add/Edit activity
    public void addItem(View view){

        Intent intent = new Intent(HomeActivity.this,AddEditActivity.class);
        startActivityForResult(intent,0);

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

        if(position == 0){
            sortingBy = 2;
        }
        else{
            sortingBy = position;
            displayItemsBy(sortingBy);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0){
        //TODO dunno;
    }

    //Method to display the wishlist items in the listview
    public void displayItemsBy(int sortingBy){
        wishItems = wishSorter.sortSelector(sortingBy,wishItems);
        wishAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if(resultCode == 0){
            WishlistItem removeItem = null;
            WishlistItem item = data.getParcelableExtra("item");
            for(WishlistItem w: wishItems){
                if (w.getWishID().equals(item.getWishID())) {
                    removeItem = w;
                }
            }
            if(removeItem != null){
                wishItems.remove(removeItem);
            }
            wishAdapter.notifyDataSetChanged();
        }
        else if(resultCode == 1){
            WishlistItem item = data.getParcelableExtra("item");
            WishlistItem removeItem = null;
            for(WishlistItem w : wishItems){
                if(w.getWishID().equals(item.getWishID())){
                    removeItem = w;
                }
            }

            if(removeItem != null){
                wishItems.remove(removeItem);
                wishItems.add(item);
            }
            Log.d("IMAGEID",String.valueOf(R.drawable.test_image));
            Log.d("IMAGEID",String.valueOf(item.getImage()));
            wishItems = wishSorter.sortSelector(sortingBy,wishItems);
            wishAdapter.notifyDataSetChanged();
        }
        else if(resultCode == -1){
            WishlistItem item = data.getParcelableExtra("item");
            wishItems.add(item);
            wishItems = wishSorter.sortSelector(sortingBy,wishItems);
            wishAdapter.notifyDataSetChanged();
        }

        super.onActivityResult(requestCode,resultCode,data);
    }

    private TextWatcher itemSearch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String searchKey = searchBar.getText().toString().trim();
            wishAdapter.filterBySearch(searchKey,sortingBy);
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };
}
