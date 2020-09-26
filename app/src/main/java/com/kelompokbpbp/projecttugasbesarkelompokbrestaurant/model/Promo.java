package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Promo {
    @PrimaryKey
    private int id;
    private String nama;
    private String description;

    public Promo(String nama, String description) {
        this.nama = nama;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
