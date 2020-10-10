package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.bumptech.glide.Glide;

@Entity
public class Keranjang {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "namaMakanan")
    public String namaMakanan;
    @ColumnInfo(name = "jumlah")
    public String jumlah;
    @ColumnInfo(name = "harga")
    public String harga;
    @ColumnInfo(name = "totalHarga")
    public String totalHarga;
    //@ColumnInfo(name = "username")
    //public String username;
    @ColumnInfo(name = "fotoMenu")
    public String fotoMenu;

    public Keranjang(String namaMakanan, String jumlah, String totalHarga) {
        this.namaMakanan = namaMakanan;
        this.jumlah = jumlah;
        this.totalHarga = totalHarga;
        //this.username = username;
        this.fotoMenu = fotoMenu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaMakanan() {
        return namaMakanan;
    }

    public void setNamaMakanan(String namaMakanan) {
        this.namaMakanan = namaMakanan;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(String totalHarga) {
        this.totalHarga = totalHarga;
    }

    /*public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }*/

    public String getFotoMenu() {
        return fotoMenu;
    }
    @BindingAdapter({"android:loadImg"})
    public static void setFotoMenu(ImageView imageView, String fotoMenu) {
        Glide.with(imageView.getContext())
                .load(fotoMenu)
                .into(imageView);
    }
}
