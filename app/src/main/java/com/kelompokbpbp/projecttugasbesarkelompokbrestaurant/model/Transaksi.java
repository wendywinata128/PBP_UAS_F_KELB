package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Transaksi implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("customer_name")
    private String customerName;

    @SerializedName("total_price")
    private String totalPrice;

    @SerializedName("status")
    private String status;

    public Transaksi(int id, String customerName, String totalPrice, String status){
        this.id = id;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
