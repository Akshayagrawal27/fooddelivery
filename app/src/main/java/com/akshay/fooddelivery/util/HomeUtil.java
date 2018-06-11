package com.akshay.fooddelivery.util;

import com.akshay.fooddelivery.model.RestaurantInfo;
import com.akshay.fooddelivery.util.Utils;

public class HomeUtil {

    public static RestaurantInfo getRestaurantInfo(RestaurantInfo restaurantsInfo) {
        if (restaurantsInfo != null){
            RestaurantInfo restaurantInfo = restaurantsInfo;
            restaurantInfo.setRestaurantName(Utils.getNameToDisplay(restaurantsInfo.getRestaurantName()));
            restaurantInfo.setAddress(Utils.getNameToDisplay(restaurantsInfo.getAddress()));
            return restaurantInfo;
        }
        return restaurantsInfo;

    }
}
