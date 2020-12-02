package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.response;

import com.google.gson.annotations.SerializedName;

public class MessageResponse {

    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
