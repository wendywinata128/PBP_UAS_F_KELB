package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model;

public class Keranjang {
    private int id;
    private String namaMakanan;
    private String jumlah;
    private String totalHarga;
    private String username;


    public Keranjang(String namaMakanan, String jumlah, String totalHarga, String username) {
        this.namaMakanan = namaMakanan;
        this.jumlah = jumlah;
        this.totalHarga = totalHarga;
        this.username = username;
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
}
