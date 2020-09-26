package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Menu {
    @PrimaryKey
    private int id;
    private String nama;
    private String harga;
    private String jenis;


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

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public Menu(String nama, String harga , String jenis) {
        this.nama = nama;
        this.harga = harga;
        this.jenis = jenis;
    }
}
