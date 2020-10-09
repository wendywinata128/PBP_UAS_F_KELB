package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.User;

@Dao
public interface UserDAO {
    @Query("SELECT * From user where username = :usernamefound")
    User getUser(String usernamefound);

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);
}
