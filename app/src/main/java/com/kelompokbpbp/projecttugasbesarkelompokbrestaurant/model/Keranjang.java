package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model;

import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;
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
    public int jumlah;
    @ColumnInfo(name = "harga")
    public String harga;
    @ColumnInfo(name = "totalHarga")
    public String totalHarga;
    @ColumnInfo(name = "username")
    public String username;
    @ColumnInfo(name = "fotoMakanan")
    public String fotoMakanan;

    public Keranjang(String namaMakanan, int jumlah, String totalHarga,String harga, String username,String fotoMakanan) {
        this.namaMakanan = namaMakanan;
        this.jumlah = jumlah;
        this.totalHarga = totalHarga;
        this.harga = harga;
        this.username = username;
        this.fotoMakanan = fotoMakanan;
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

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFotoMakanan() {
        return fotoMakanan;
    }

    public void setFotoMakanan(String fotoMakanan) {
        this.fotoMakanan = fotoMakanan;
    }

    //
//    @BindingAdapter({"android:loadImg"})
//    public void setFotoMenu(ImageView imageView, String fotoMenu) {
//        Glide.with(imageView.getContext())
//                .load(fotoMenu)
//                .into(imageView);
//    }


}
