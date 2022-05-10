package com.example.splashscreen.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity (tableName = "user_table")
public class UserModel {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "nama")
    public String nama;
    @ColumnInfo(name = "email")
    public String email;
    @ColumnInfo(name = "password")
    public String password;
    @ColumnInfo(name = "gender")
    public String gender;
    @ColumnInfo(name = "age")
    public String age;

    public UserModel(int id, String nama, String email, String password, String gender, String age){
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.age = age;
    }
    @Ignore
    public UserModel(String nama, String email, String password, String gender, String age){
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.age = age;
    }
}
