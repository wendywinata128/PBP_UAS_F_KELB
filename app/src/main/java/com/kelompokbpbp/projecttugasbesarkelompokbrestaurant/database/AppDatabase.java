package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.Alamat;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.Keranjang;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.User;

@Database(entities = {User.class, Alamat.class},version = 1)
abstract public class AppDatabase extends RoomDatabase {
    abstract public UserDAO userDao();
    abstract public AddressDAO addressDAO();
}
