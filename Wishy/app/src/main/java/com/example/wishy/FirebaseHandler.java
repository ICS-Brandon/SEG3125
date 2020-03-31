package com.example.wishy;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FirebaseHandler {

    private FirebaseFirestore fStore;
    private FirebaseUser fUser;
    private FirebaseAuth fAuth;
    private WishlistItem returnItem;
    private List<DocumentSnapshot> docSnaps;

    public FirebaseHandler(){
        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        fUser = fAuth.getCurrentUser();
    }

    //Set the current Firebase Auth user
    public void setfUser(FirebaseUser f){
        fUser = f;
    }

    //get the wishlist item with the corresponding wishId
    public WishlistItem getWishlistItem(String wishID){

        //Get a document reference to the item
        DocumentReference docRef = fStore.collection("user_profiles").document(fUser.getUid()).collection("wishlist").document(wishID);

        //Get snapshot of document and change into Wishlist item and return value
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                returnItem = documentSnapshot.toObject(WishlistItem.class);
            }
        });

        if(returnItem == null){
            returnItem = new WishlistItem(0,"Null","Null","Null");
        }

        return returnItem;
    }

    //Add item to the wishlist of a user
    public void addWishlistItem(WishlistItem wishItem){
        fStore.collection("user_profiles").document(fUser.getUid()).collection("wishlist").document(wishItem.getWishID()).set(wishItem).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }

    //Delete item from the wishlist of a user
    public void deleteWishlistItem(WishlistItem wishItem){
        fStore.collection("user_profiles").document(fUser.getUid()).collection("wishlist").document(wishItem.getWishID()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                return;
            }
        });
    }

    //Add a user to the database
    public void addUserInfo(HashMap<String,Object> map){
        fStore.collection("user_profiles").document(fUser.getUid()).set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                return;
            }
        });
    }

    public ArrayList<WishlistItem> getAllWishItems(String uID){

        ArrayList<WishlistItem> wishItems = new ArrayList<>();

        fStore.collection("user_profiles").document(uID).collection("wishlist").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                docSnaps.addAll(task.getResult().getDocuments());
            }
        });

        for(DocumentSnapshot s : docSnaps){
            wishItems.add(s.toObject(WishlistItem.class));
        }

        return wishItems;
    }
}
