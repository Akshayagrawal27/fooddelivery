package com.akshay.fooddelivery.model;

/**
 * Created by Akshay on 22-02-2018.
 */

public class CartItems {

    private String itemId;
    private String itemName;
    private int itemQuantity;
    private long price;
    private long itemTotalPrice;

    public CartItems() {
    }

    public CartItems(String itemId, String itemName, int itemQuantity, long price, long itemTotalPrice) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.price = price;
        this.itemTotalPrice = itemTotalPrice;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getItemTotalPrice() {
        return itemTotalPrice;
    }

    public void setItemTotalPrice(long itemTotalPrice) {
        this.itemTotalPrice = itemTotalPrice;
    }
}
