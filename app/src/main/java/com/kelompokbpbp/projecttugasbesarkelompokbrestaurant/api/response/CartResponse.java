package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.Keranjang;

import java.util.List;

public class CartResponse {
    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private List<Keranjang> data = null;

    @SerializedName("total")
    @Expose
    private String total = null;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Keranjang> getData() {
        return data;
    }

    public void setData(List<Keranjang> data) {
        this.data = data;
    }
}
