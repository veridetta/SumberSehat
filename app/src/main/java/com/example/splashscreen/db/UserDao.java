package com.example.splashscreen.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    //mengambil data user
    String user_table = "user_table";
    @Query("SELECT * FROM "+user_table)
    List<UserModel> getAllUser();

    //mengambil data berdasarkan email dan password
    @Query("SELECT * FROM "+user_table+" WHERE email = :email AND password = :password")
    UserModel getUser(String email, String password);

    //menginput data
    @Insert
    void insertUser(UserModel userModel);
    //mengupdate data
    @Update
    void updateUser(UserModel userModel);
   //menghapus data
    @Delete
    void deleteUser(UserModel userModel);
}
