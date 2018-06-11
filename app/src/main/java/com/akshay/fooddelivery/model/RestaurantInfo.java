package com.akshay.fooddelivery.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.akshay.fooddelivery.util.Utils;

import java.util.HashMap;

public class RestaurantInfo implements Parcelable {

    private String uid;
    private String restaurantName;
    private String ownerName;
    private String contactNumber;
    private String address;
    private HashMap<String, Object> timeStampJoined;

    public RestaurantInfo() {
    }

    public RestaurantInfo(String uid, String restaurantName, String ownerName, String contactNumber, String address) {
        this.uid = uid;
        this.restaurantName = restaurantName;
        this.ownerName = ownerName;
        this.contactNumber = contactNumber;
        this.address = address;
        this.timeStampJoined = Utils.getCurrentTimeStamp();
    }

    protected RestaurantInfo(Parcel in) {
        uid = in.readString();
        restaurantName = in.readString();
        ownerName = in.readString();
        contactNumber = in.readString();
        address = in.readString();
    }

    public static final Creator<RestaurantInfo> CREATOR = new Creator<RestaurantInfo>() {
        @Override
        public RestaurantInfo createFromParcel(Parcel in) {
            return new RestaurantInfo(in);
        }

        @Override
        public RestaurantInfo[] newArray(int size) {
            return new RestaurantInfo[size];
        }
    };

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public HashMap<String, Object> getTimeStampJoined() {
        return timeStampJoined;
    }

    public void setTimeStampJoined(HashMap<String, Object> timeStampJoined) {
        this.timeStampJoined = timeStampJoined;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(uid);
        parcel.writeString(restaurantName);
        parcel.writeString(ownerName);
        parcel.writeString(contactNumber);
        parcel.writeString(address);
    }
}
