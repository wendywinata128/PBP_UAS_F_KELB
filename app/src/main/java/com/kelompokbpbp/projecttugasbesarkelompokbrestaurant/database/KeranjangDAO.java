package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.Keranjang;

import java.util.List;

@Dao
public interface KeranjangDAO {
    @Query("SELECT * from keranjang where username = :username")
    List<Keranjang> getAllKeranjang(String username);

    @Insert
    void insert(Keranjang keranjang);

    @Update
    void update(Keranjang keranjang);

    @Delete
    void delete(Keranjang keranjang);
}
