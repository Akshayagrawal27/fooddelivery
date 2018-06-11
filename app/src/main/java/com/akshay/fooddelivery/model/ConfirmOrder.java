package com.akshay.fooddelivery.model;

import com.akshay.fooddelivery.util.Utils;

import java.util.HashMap;
import java.util.List;

public class ConfirmOrder {

    private String orderId;
    private String orderNo;
    private List<MenuItems> orderedItems;
    private String orderStatus;
    private double orderAmount;
    private String orderInstruction;
    private HashMap<String, Object> orderDateTimeStamp;

    public ConfirmOrder() {
    }

    public ConfirmOrder(String orderId, List<MenuItems> orderedItems, double orderAmount, String orderStatus) {
        this.orderId = orderId;
        this.orderedItems = orderedItems;
        this.orderAmount = orderAmount;
        this.orderStatus = orderStatus;
        this.orderDateTimeStamp = Utils.getCurrentTimeStamp();
    }

    public ConfirmOrder(String orderId, List<MenuItems> orderedItems, String orderStatus, double orderAmount, String orderInstruction) {
        this.orderId = orderId;
        this.orderedItems = orderedItems;
        this.orderStatus = orderStatus;
        this.orderAmount = orderAmount;
        this.orderInstruction = orderInstruction;
        this.orderDateTimeStamp = Utils.getCurrentTimeStamp();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public List<MenuItems> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<MenuItems> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderInstruction() {
        return orderInstruction;
    }

    public void setOrderInstruction(String orderInstruction) {
        this.orderInstruction = orderInstruction;
    }
}
