package com.akshay.fooddelivery.util;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.akshay.fooddelivery.model.CartDetails;
import com.akshay.fooddelivery.model.CartItems;
import com.akshay.fooddelivery.model.ConfirmOrder;
import com.akshay.fooddelivery.ui.TrackOrder.TrackOrderActivity;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

public class CartUtil {

    public static void incrementItemQuantity(CartItems cartItems) {
        cartItems.setItemQuantity(cartItems.getItemQuantity() + 1);
        cartItems.setItemTotalPrice(cartItems.getItemQuantity() * cartItems.getPrice());
        updateQuantity(cartItems, cartItems.getItemId());
    }

    public static void decrementItemQuantity(CartItems cartItems) {

        cartItems.setItemQuantity(cartItems.getItemQuantity() - 1);
        cartItems.setItemTotalPrice(cartItems.getItemQuantity() * cartItems.getPrice());

        if (cartItems.getItemQuantity() > 0){
            updateQuantity(cartItems, cartItems.getItemId());
        }else if (cartItems.getItemQuantity() == 0){
            updateQuantity(null, cartItems.getItemId());
        }else{
            updateQuantity(null, cartItems.getItemId());
        }
    }

    private static void updateQuantity(CartItems cartItems, String itemId) {
        FirebaseUtil.updateCartItem(cartItems, itemId);
    }

    public static double getPackagingCharges() {
        return 20;
    }

    public static double getGSTCharges(double totalItemValue) {
        return totalItemValue * 0.05;
    }

    public static double getDeliveryCharges() {
        return 20;
    }

    private static String getCurrencyString(Object obj){
        return "\u20B9 " + obj;
    }

    public static void confirmOrder(ConfirmOrder confirmOrder, CartDetails cartDetails, final Context context) {
        FirebaseUtil.confirmOrder(confirmOrder, cartDetails, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                Utils.showToast(context, "orderConfirmed");
                Intent intent = new Intent(context, TrackOrderActivity.class);
                context.startActivity(intent);
            }
        });
    }
}
