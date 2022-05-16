package com.example.splashscreen.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ObatDao {
    //mengambil data user
    String obat_table = "obat_table";
    @Query("SELECT * FROM "+obat_table)
    ObatModel getObatAll();

    //mengambil data berdasarkan id_obat
    @Query("SELECT * FROM "+obat_table+" WHERE kategori = :kategori")
    List<ObatModel> getObat(String kategori);

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
