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

    private Menu menu;

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
    //@ColumnInfo(name = "username")
    //public String username;
    @ColumnInfo(name = "fotoMenu")
    public String fotoMenu;

    public Keranjang(Menu menu,String namaMakanan, int jumlah, String totalHarga) {
        this.namaMakanan = namaMakanan;
        this.jumlah = jumlah;
        this.totalHarga = totalHarga;
        //this.username = username;
        this.menu = menu;
        this.fotoMenu = fotoMenu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Menu getMenu(){
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
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

    /*public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }*/

    public String getFotoMenu() {
        return fotoMenu;
    }
    //@BindingAdapter({"android:loadImg"})


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Keranjang keranjang = (Keranjang) o;
        return getJumlah() == keranjang.getJumlah() &&
                getMenu().equals(keranjang.getMenu());
    }

    @BindingAdapter("android:setVal")
    public static void getSelectedSpinnerValue(Spinner spinner, int jumlah) {
        spinner.setSelection(jumlah - 1, true);
    }

    public static DiffUtil.ItemCallback<Keranjang> itemCallback = new DiffUtil.ItemCallback<Keranjang>() {
        @Override
        public boolean areItemsTheSame(@NonNull Keranjang oldItem, @NonNull Keranjang newItem) {
            return oldItem.getJumlah() == newItem.getJumlah();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Keranjang oldItem, @NonNull Keranjang newItem) {
            return oldItem.equals(newItem);
        }
    };
}
