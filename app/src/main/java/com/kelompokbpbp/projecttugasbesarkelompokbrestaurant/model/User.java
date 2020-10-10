package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model;

import android.net.Uri;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class User implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "full_name")
    private String nama;

    @ColumnInfo(name = "handphone_number")
    private String nohp;

    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name="password")
    private String password;

    @ColumnInfo(name="photo")
    private String photoProfile;

    public User(String nama , String nohp , String username , String password, String photoProfile){
        this.nama = nama;
        this.nohp = nohp;
        this.username = username;
        this.password = password;
        this.photoProfile = photoProfile;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhotoProfile(){
        return photoProfile;
    }

    public void setPhotoProfile(String photoProfile) {
        this.photoProfile = photoProfile;
    }
}
