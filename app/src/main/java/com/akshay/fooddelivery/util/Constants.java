package com.akshay.fooddelivery.util;

/**
 * Created by Akshay on 13-05-2018.
 */

public class Constants {

    /**
     * Constants related to locations in Firebase, such as the name of the node
     * where user lists are stored (ie "userLists")
     */

    public static final String FIREBASE_LOCATION_MENU_ITEM_LIST = "menuItems";
    public static final String FIREBASE_LOCATION_MENU_MENUCATEGORY = "menuCategory";
    public static final String FIREBASE_LOCATION_RESTAURANT_INFO = "restaurantInfo";
    public static final String FIREBASE_LOCATION_BRAND_LIST = "BrandList";
    public static final String FIREBASE_LOCATION_BRAND_MODEL_LIST = "BrandModelList";
    public static final String FIREBASE_LOCATION_MODEL_VARIANT_LIST = "ModelVariant";
    public static final String FIREBASE_LOCATION_ORDER_LIST = "OrderList";
    public static final String FIREBASE_LOCATION_ACCOUNT_LIST = "AccountList";
    public static final String FIREBASE_LOCATION_NOTE_LIST = "NoteList";
    public static final String FIREBASE_LOCATION_PRICE_LIST = "PriceList";

    /**
     * Constants for Firebase object properties
     */
    public static final String FIREBASE_PROPERTY_TIMESTAMP_LAST_CHANGED = "timestampLastChanged";
    public static final String FIREBASE_PROPERTY_TIMESTAMP = "timestamp";
    public static final String FIREBASE_PROPERTY_BRAND_QTY = "brandQuantity";
    public static final String FIREBASE_PROPERTY_BOOKING_DATE = "bookingDate";
    public static final String FIREBASE_PROPERTY_ORDER_STATUS = "orderStatus";
    public static final String FIREBASE_PROPERTY_NOTE_DATE = "noteDate";
    public static final String FIREBASE_PROPERTY_VARIANT_BEST_PRICE = "variantBestPrice";
    public static final String FIREBASE_PROPERTY_RESTAURANT_NAME = "restaurantName";

    /**
     * Constants for bundles, extras and shared preferences keys
     */
    public static final String KEY_DIALOG_ADD_MENU = "ADD_MENU_ITEM";
    public static final String KEY_DIALOG_ADD_BRAND_MODEL = "ADD_BRAND_MODEL";
    public static final String KEY_DIALOG_ADD_MODEL_VARIANT = "ADD_MODEL_VARIANT";
    public static final String KEY_DIALOG_ADD_ACCOUNT = "ADD_ACCOUNT";
    public static final String KEY_DIALOG_ADD_PRICE = "ADD_PRICE";
    public static final String KEY_DIALOG_EDIT_PRICE = "EDIT_PRICE";

    public static final String KEY_BRAND_NAME = "BRAND_NAME";
    public static final String KEY_BRAND_KEY = "BRAND_KEY";
    public static final String KEY_BRAND_MODEL_NAME = "BRAND_MODEL_NAME";
    public static final String KEY_BRAND_MODEL_KEY = "BRAND_MODEL_KEY";
    public static final String KEY_ORDER = "ORDER_KEY";
    public static final String KEY_ACCOUNT_NAME = "ACCOUNT_NAME";
    public static final String KEY_ACCOUNT_KEY = "ACCOUNT_KEY";
    public static final String KEY_NOTE_KEY = "NOTE_KEY";
    public static final String KEY_VARIANT_KEY = "VARIANT_KEY";
    public static final String KEY_VARIANT_NAME = "VARIANT_NAME";
    public static final String KEY_PRICE_KEY = "PRICE_NAME";

    public static final String COUNTRY_TIMEZONE= "india";
    public static final String DATE_FORMAT= "dd-MM-yyyy HH:mm:ss";
    public static final String ADD_NEW_ORDER = "ADD_NEW_ORDER";
    public static final String EDIT_ORDER = "EDIT_ORDER";
    public static final String ADD_NEW_NOTE = "ADD_NEW_NOTE";
    public static final String EDIT_NOTE = "EDIT_NOTE";

    /*
    * Constants for Order Status
    * */
    public static final String ORDER_STATUS_PENDING = "ongoing_prepared";
    public static final String ORDER_STATUS_COMPLETED = "completed";
    public static final String FIREBASE_LOCATION_RECEIVED_ORDER = "receivedOrder";
    public static final String ORDER_STATUS_ONGOING = "ongoing";
    public static final String KEY_ORDER_MENU_ITEM_NAME = "menuItemsName";
    public static final String KEY_ORDER_MENU_ITEM_QUANTITY = "menuItemsQuantity";
    public static final String KEY_UID = "uid";
    public static final String KEY_SHOW_ORDER_MENU_ITEMS = "showOrderMenuItems";
    public static final String KEY_ORDER_KEY = "orderKey";
    public static final String FIREBASE_PROPERTY_ORDERED_ITEMS = "orderedItems";
    public static final String KEY_DIALOG_ORDERED_ITEMS = "orderedItems";
    public static final String ORDER_STATUS_PREPARED = "prepared";
    public static final String FIREBASE_LOCATION_CART_ITEMS = "cartItems";
    public static final String KEY_ADD_QUANTITY = "addQuantity";
    public static final String KEY_MINUS_QUANTITY = "minusQuantity";
    public static final String KEY_RESTAURANT_INFO = "restaurantInfo";
    public static final String FIREBASE_LOCATION_USER_ORDERS = "userOrders";
    public static final String FIREBASE_LOCATION_CART_DETAILS = "cartDetails";
    public static final String FIREBASE_LOCATION_CART_ITEM_LIST = "cartItems";
    public static final String FIREBASE_LOCATION_TRACK_ORDER = "trackOrder";
    public static final String FIREBASE_PROPERTY_MENU_CATEGORY = "category";

    /*
    * Constants for Data Removal
    * */
    public static int REMOVAL_TAG = 1;
}
