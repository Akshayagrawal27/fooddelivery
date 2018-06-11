package com.akshay.fooddelivery.model;

/**
 * Created by Akshay on 21-02-2018.
 */

public class RestaurantsInfo {

    String restaurantName;
    String restaurantCuisine;
    String ratings;
    String expectedTime;
    String expectedPrice;

    public RestaurantsInfo() {
    }

    public RestaurantsInfo(String restaurantName, String restaurantCuisine, String ratings, String expectedTime, String expectedPrice) {
        this.restaurantName = restaurantName;
        this.restaurantCuisine = restaurantCuisine;
        this.ratings = ratings;
        this.expectedTime = expectedTime;
        this.expectedPrice = expectedPrice;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantCuisine() {
        return restaurantCuisine;
    }

    public void setRestaurantCuisine(String restaurantCuisine) {
        this.restaurantCuisine = restaurantCuisine;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public String getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(String expectedTime) {
        this.expectedTime = expectedTime;
    }

    public String getExpectedPrice() {
        return expectedPrice;
    }

    public void setExpectedPrice(String expectedPrice) {
        this.expectedPrice = expectedPrice;
    }
}
