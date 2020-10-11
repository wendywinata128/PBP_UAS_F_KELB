package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.Alamat;


import java.util.ArrayList;
import java.util.List;

@Dao
public interface AddressDAO {

    @Query("SELECT * from alamat where username = :username")
    List<Alamat> getAllAlamat(String username);

    @Insert
    void insert(Alamat alamat);

    @Update
    void update(Alamat alamat);

    @Delete
    void delete(Alamat alamat);
}
