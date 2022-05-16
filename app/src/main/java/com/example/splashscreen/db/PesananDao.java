package com.example.splashscreen.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PesananDao {
    //mengambil data user
    String pesanan_table = "pesanan_table";
    @Query("SELECT * FROM "+pesanan_table)
    List<ObatModel> getPesananAll();

    //mengambil data berdasarkan id_obat
    @Query("SELECT * FROM "+pesanan_table+" WHERE id_user = :id_user")
    ObatModel getObat(String id_user);

    //menginput data
    @Insert
    void insertObat(ObatModel obatModel);
    //mengupdate data
    @Update
    void updateObat(ObatModel obatModel);
   //menghapus data
    @Delete
    void deleteObat(ObatModel obatModel);
}
