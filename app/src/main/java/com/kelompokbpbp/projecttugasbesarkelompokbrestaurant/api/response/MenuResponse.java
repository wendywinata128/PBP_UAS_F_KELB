package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.api.response;

import com.google.gson.annotations.SerializedName;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.Menu;

import java.util.List;

public class MenuResponse {
    @SerializedName("message")
    private String message = null;

    @SerializedName("data")
    private List<Menu> data = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Menu> getData() {
        return data;
    }

    public void setData(List<Menu> data) {
        this.data = data;
    }
}
