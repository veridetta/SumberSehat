package com.example.splashscreen.db;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.splashscreen.ui.RegisterActivity;

@Database(entities = {UserModel.class,ObatModel.class,PesananModel.class,KeranjangModel.class}, version = 1)
public abstract class DBUser extends RoomDatabase {
    static String DbName = "pam_db";
    static DBUser instance;

    public static synchronized DBUser getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), DBUser.class, DbName)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public  abstract UserDao userDao();
    public  abstract ObatDao obatDao();
    public  abstract KeranjangDao keranjangDao();
    public  abstract PesananDao pesananDao();
}
