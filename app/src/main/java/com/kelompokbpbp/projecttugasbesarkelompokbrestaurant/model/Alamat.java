package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity
public class Alamat implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name ="address_name")
    private String addressName;

    @ColumnInfo(name = "address_detail")
    private String addressDetail;

    @ColumnInfo(name = "json_address")
    private String jsonAddress;

    public Alamat(String username,String jsonAddress,String addressName , String addressDetail) {
        this.username = username;
        this.jsonAddress = jsonAddress;
        this.addressName = addressName;
        this.addressDetail = addressDetail;
    }

    public String getJsonAddress() {
        return jsonAddress;
    }

    public void setJsonAddress(String jsonAddress) {
        this.jsonAddress = jsonAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }
}
