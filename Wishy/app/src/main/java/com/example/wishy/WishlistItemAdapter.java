package com.example.wishy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;


import java.util.ArrayList;

public class WishlistItemAdapter extends ArrayAdapter<WishlistItem> {

    private TextView price, brand;
    private TextView mainTag;
    private ImageView background;
    private Button editButton, favButton;
    private Context mContext;
    private ArrayList<WishlistItem> wishlistItems;
    private ArrayList<WishlistItem> holderList;
    private WishlistItemSorter wSorter;

    public WishlistItemAdapter(Context context, ArrayList<WishlistItem> wishArray){
        super(context, 0, wishArray);
        mContext = getContext();
        wishlistItems = wishArray;
        holderList = new ArrayList<>();
        holderList.addAll(wishlistItems);
        wSorter = new WishlistItemSorter();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        final WishlistItem wishItem = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.wishlist_item,parent,false);
        }

        price = convertView.findViewById(R.id.itemPrice);
        brand = convertView.findViewById(R.id.itemBrand);
        mainTag = convertView.findViewWithTag(R.id.mainTag2);
        background = convertView.findViewById(R.id.cardViewBackground);
        editButton = convertView.findViewById(R.id.editButton);
        favButton = convertView.findViewById(R.id.favButton);

        if(wishItem.getFavourited()){
            favButton.setBackgroundResource(R.drawable.black_favourite_icon_filled);
        }
        else{
        }
            favButton.setBackgroundResource(R.drawable.black_favourite_icon);

        editButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getContext(),AddEditActivity.class);
                intent.putExtra("item",wishItem);
                ((Activity) getContext()).startActivityForResult(intent,0);
            }
        });

        favButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(wishItem.getFavourited()){
                    wishItem.setFavourited(false);
                    setUnFavourited();
                }
                else{
                    wishItem.setFavourited(true);
                    setFavourited();
                }
            }
        });

        price.setText("$"+wishItem.getPrice());
        brand.setText(wishItem.getBrand());

        return convertView;
    }

    public void filterBySearch(String searchKey, int searchBy) {
        searchKey = searchKey.toLowerCase();
        wishlistItems.clear();
        if (searchKey.length() == 0) {
            wishlistItems.addAll(holderList);
            notifyDataSetChanged();
        } else {

            if(searchBy == 1){
                for (WishlistItem w : holderList) {
                    if (w.getName().toLowerCase().indexOf(searchKey) != -1) {
                        wishlistItems.add(w);
                    }
                    wishlistItems = wSorter.sortSelector(searchBy, wishlistItems);
                    notifyDataSetChanged();
                }
            }
            else if(searchBy == 2){
                for (WishlistItem w : holderList) {
                    if (w.getBrand().toLowerCase().indexOf(searchKey) != -1) {
                        wishlistItems.add(w);
                    }
                    wishlistItems = wSorter.sortSelector(searchBy, wishlistItems);
                    notifyDataSetChanged();
                }
            }
        }
    }

    public void setFavourited(){
        favButton.setBackgroundResource(R.drawable.black_favourite_icon_filled);
    }

    public void setUnFavourited(){
        favButton.setBackgroundResource(R.drawable.black_favourite_icon);
    }
}
