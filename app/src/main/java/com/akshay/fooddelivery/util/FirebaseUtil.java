package com.akshay.fooddelivery.util;

import android.provider.ContactsContract;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.view.MenuItem;

import com.akshay.fooddelivery.model.CartDetails;
import com.akshay.fooddelivery.model.CartItems;
import com.akshay.fooddelivery.model.ConfirmOrder;
import com.akshay.fooddelivery.model.MenuItems;
import com.akshay.fooddelivery.model.RestaurantInfo;
import com.akshay.fooddelivery.model.TrackOrder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * Created by Akshay on 13-05-2018.
 */

public class FirebaseUtil {

    public static String getUserUid(){
        return FirebaseAuth.getInstance().getUid();
    }
    public static DatabaseReference getRestaurantMenuListReference() {
        return FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_LOCATION_MENU_ITEM_LIST);
    }

    public static DatabaseReference getMenuCategoryListReference(){
        return FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_LOCATION_MENU_MENUCATEGORY);
    }

    public static DatabaseReference getRestaurantReceivedOrderReference(){
        return FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_LOCATION_RECEIVED_ORDER);
    }

    public static DatabaseReference getOrderOrderedItemsReference(String orderKey){
        return getRestaurantReceivedOrderReference().child(orderKey).child(Constants.FIREBASE_PROPERTY_ORDERED_ITEMS);
    }

    public static DatabaseReference getRestaurantInfoReference(){
        return FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_LOCATION_RESTAURANT_INFO);
    }

    public static DatabaseReference getCartItemsReference(){
        return FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_LOCATION_CART_ITEMS);
    }

    public static DatabaseReference getUserOrderReference(){
        return FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_LOCATION_USER_ORDERS);
    }

    public static DatabaseReference getCartDetailsReference(){
        return FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_LOCATION_CART_DETAILS);
    }

    /*public static void addMenuItem(MenuItems menuItems) {
        getRestaurantMenuListReference().child(menuItems.getMenuId()).setValue(menuItems);
    }

    public static void addMenuCategory(MenuCategory menuCategory) {
        getMenuCategoryListReference().child(menuCategory.getCategoryName()).setValue(menuCategory);
    }*/

    public static void updateOrderStatus(String orderKey, String status) {

        HashMap<String, Object> updateOrderStatus = new HashMap<String, Object>();
        updateOrderStatus.put("/" + Constants.FIREBASE_PROPERTY_ORDER_STATUS , status);

        getRestaurantReceivedOrderReference().child(orderKey).updateChildren(updateOrderStatus);

    }

    public static void addItemToCart(MenuItems menuItems){

        CartItems cartItems = new CartItems();
        cartItems.setItemId(menuItems.getMenuId());
        cartItems.setItemName(menuItems.getMenuName());
        cartItems.setItemQuantity(1);
        cartItems.setPrice(menuItems.getPrice());
        cartItems.setItemTotalPrice(menuItems.getPrice());

        getCartItemsReference().child(menuItems.getMenuId()).setValue(cartItems);
    }

    public static void updateCartItem(CartItems cartItems, String itemId) {
        getCartItemsReference().child(itemId).setValue(cartItems);
    }

    public static void confirmOrder(ConfirmOrder confirmOrder, CartDetails cartDetails, DatabaseReference.CompletionListener completionListener) {

        if (confirmOrder !=null){
            HashMap<String, Object> updateOrder = new HashMap<>();
            updateOrder.put(Constants.FIREBASE_LOCATION_USER_ORDERS + "/" + confirmOrder.getOrderId(), confirmOrder);
            updateOrder.put(Constants.FIREBASE_LOCATION_RECEIVED_ORDER + "/" + confirmOrder.getOrderId(), confirmOrder);
            updateOrder.put(Constants.FIREBASE_LOCATION_CART_ITEMS, null);
            updateOrder.put(Constants.FIREBASE_LOCATION_CART_DETAILS, null);
            updateOrder.put(Constants.FIREBASE_LOCATION_TRACK_ORDER, new TrackOrder(confirmOrder.getOrderId(), "ongoing", cartDetails));
            FirebaseDatabase.getInstance().getReference().updateChildren(updateOrder, completionListener);
        }else{
            //FirebaseDatabase.getInstance().getReference().updateChildren(null, completionListener);
        }
    }

    public static void addCartDetails(CartDetails cartDetails, RestaurantInfo restaurantInfo) {
        getCartDetailsReference().setValue(cartDetails);
    }

    public static DatabaseReference getTrackOrderReference() {
        return FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_LOCATION_TRACK_ORDER);
    }
}
