package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model;

public class User {
    private int id;
    private String nama;
    private String nohp;
    private String alamat;
    private String username;
    private String password;

    public User(String nama , String nohp , String alamat , String username , String password){
        this.nama = nama;
        this.nohp = nohp;
        this.alamat = alamat;
        this.username = username;
        this.password = password;
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

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
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
}
