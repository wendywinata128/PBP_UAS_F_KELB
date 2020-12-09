package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.User;

import java.util.List;

public class AllUserResponse {
    @SerializedName("data")
    @Expose
    private List<User> data;

    @SerializedName("message")
    @Expose
    public String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }
}
