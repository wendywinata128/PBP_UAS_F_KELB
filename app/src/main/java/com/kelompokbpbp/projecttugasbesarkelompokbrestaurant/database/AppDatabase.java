package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.Menu;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.Promo;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.User;

@Database(entities = {User.class, Promo.class, Menu.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
}
