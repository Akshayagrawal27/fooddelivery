package com.akshay.fooddelivery.model;

import com.akshay.fooddelivery.util.Utils;

import java.util.HashMap;

/**
 * Created by Akshay on 23-02-2018.
 */

public class MenuItems {

    /*private String itemName;
    private int itemPrice;
    private String itemCategory;
    private boolean vegetarian;
    private boolean available;*/

    private String menuId;
    private String category;
    private String menuName;
    private int quantity;
    private boolean available;
    private Long price;
    private HashMap<String, Object> timestampLastChanged;

    public MenuItems() {
    }

    public MenuItems(String menuId, String category, String menuName, int quantity, Long price) {
        this.menuId = menuId;
        this.category = category;
        this.menuName = menuName;
        this.quantity = quantity;
        this.price = price;
    }

    public MenuItems(String menuId, String category, String menuName, Long price) {
        this.menuId = menuId;
        this.category = category;
        this.menuName = menuName;
        this.available = true;
        this.price = price;
        this.timestampLastChanged = Utils.getCurrentTimeStamp();
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public HashMap<String, Object> getTimestampLastChanged() {
        return timestampLastChanged;
    }

    public void setTimestampLastChanged(HashMap<String, Object> timestampLastChanged) {
        this.timestampLastChanged = timestampLastChanged;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /*public MenuItems() {
    }

    public MenuItems(String itemName, int itemPrice, String itemCategory, boolean vegetarian,  boolean available) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCategory = itemCategory;
        this.vegetarian = vegetarian;
        this.available = available;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }*/
}
