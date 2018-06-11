package com.akshay.fooddelivery.util;

import com.akshay.fooddelivery.model.CartDetails;
import com.akshay.fooddelivery.model.CartItems;
import com.akshay.fooddelivery.model.MenuItems;
import com.akshay.fooddelivery.model.RestaurantInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class MenuUtil {

    public static void addItemToCart(final MenuItems menuItems, final RestaurantInfo restaurantInfo) {

        final CartItems cartItems = new CartItems();
        cartItems.setItemId(menuItems.getMenuId());
        cartItems.setItemName(menuItems.getMenuName());
        cartItems.setItemQuantity(1);
        cartItems.setPrice(menuItems.getPrice());
        cartItems.setItemTotalPrice(menuItems.getPrice());

        FirebaseUtil.getCartDetailsReference()/*.child(restaurantInfo.getUid())*/.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.getValue() == null){
                    FirebaseUtil.addCartDetails(new CartDetails(restaurantInfo.getRestaurantName(), restaurantInfo.getAddress()), restaurantInfo);
                    FirebaseUtil.addItemToCart(menuItems);
                }else{
                    FirebaseUtil.addItemToCart(menuItems);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
