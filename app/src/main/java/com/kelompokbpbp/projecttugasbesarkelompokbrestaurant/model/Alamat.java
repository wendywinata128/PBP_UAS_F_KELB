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

    @ColumnInfo(name = "point1")
    private double point1;

    @ColumnInfo(name = "point2")
    private double point2;

    public Alamat(String username, double point1, double point2,String addressName , String addressDetail) {
        this.username = username;
        this.point1 = point1;
        this.point2 = point2;
        this.addressName = addressName;
        this.addressDetail = addressDetail;
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

    public double getPoint1() {
        return point1;
    }

    public void setPoint1(double point1) {
        this.point1 = point1;
    }

    public double getPoint2() {
        return point2;
    }

    public void setPoint2(double point2) {
        this.point2 = point2;
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
