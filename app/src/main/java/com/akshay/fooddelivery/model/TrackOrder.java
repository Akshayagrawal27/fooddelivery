package com.akshay.fooddelivery.model;

public class TrackOrder {

    private String orderId;
    private String orderStatus;
    private CartDetails orderDetails;

    public TrackOrder() {
    }

    public TrackOrder(String orderId, String orderStatus, CartDetails orderDetails) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.orderDetails = orderDetails;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public CartDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(CartDetails orderDetails) {
        this.orderDetails = orderDetails;
    }


}
