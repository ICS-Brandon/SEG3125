package com.example.wishy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Parcel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;


import java.util.ArrayList;
import java.util.List;

public class WishlistItemAdapter extends ArrayAdapter<WishlistItem> {

    private TextView price, brand, mainTag;
    private ImageView background;
    private Button editButton, favButton;

    public WishlistItemAdapter(Context context, ArrayList<WishlistItem> wishArray){
        super(context, 0, wishArray);
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
        mainTag = convertView.findViewWithTag(R.id.mainTag);
        background = convertView.findViewById(R.id.cardViewBackground);
        editButton = convertView.findViewById(R.id.editButton);
        favButton = convertView.findViewById(R.id.favButton);

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

                if(wishItem.getFavourited() == true){
                    wishItem.setFavourited(false);
                    favButton.setBackgroundResource(R.drawable.favourite_icon);
                }
                else{
                    wishItem.setFavourited(true);
                    favButton.setBackgroundResource(R.drawable.favourite_icon);
                }
            }
        });

        price.setText("$"+wishItem.getPrice());
        brand.setText(wishItem.getBrand());
        try {
            if (wishItem.getTags().get(0) != null && wishItem.getTags() != null) {
                mainTag.setText(wishItem.getTags().get(0));
            }
        }
        catch(IndexOutOfBoundsException e){
            //mainTag.setVisibility(View.INVISIBLE);
            System.out.println("Error: no tags");
        }
        return convertView;
    }

}
