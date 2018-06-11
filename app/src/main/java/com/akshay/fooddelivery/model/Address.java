package com.akshay.fooddelivery.model;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Akshay on 21-02-2018.
 */

public class Address {

    String city, locality, buildingName, pincode, state, landmark;
    String name, phoneNumber;

    public Address() {
    }

    public Address(String city, String locality, String buildingName, String pincode, String state, String landmark, String name, String phoneNumber) {
        this.city = city;
        this.locality = locality;
        this.buildingName = buildingName;
        this.pincode = pincode;
        this.state = state;
        this.landmark = landmark;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
