package com.example.wishy;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class WishlistItemAdapter extends ArrayAdapter<WishlistItem> {

    public WishlistItemAdapter(Context context, ArrayList<WishlistItem> wishArray){
        super(context, 0, wishArray);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        WishlistItem wishItem = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.wishlist_item,parent,false);
        }

        TextView price = convertView.findViewById(R.id.itemPrice);
        TextView brand = convertView.findViewById(R.id.itemBrand);
        TextView mainTag = convertView.findViewWithTag(R.id.mainTag);
        ImageView background = convertView.findViewById(R.id.cardViewBackground);

        price.setText("$"+wishItem.getPrice());
        brand.setText(wishItem.getBrand());
        try {
            if (wishItem.getTags().get(0) != null) {
                mainTag.setText(wishItem.getTags().get(0));
            }
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Error: no tags");
        }
        return convertView;
    }
}
