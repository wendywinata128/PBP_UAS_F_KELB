package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.Keranjang;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.User;

@Database(entities = {User.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDAO userDao();
}
