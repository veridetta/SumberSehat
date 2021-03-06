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
    List<PesananModel> getPesananAll();

    //mengambil data berdasarkan id_obat
    @Query("SELECT * FROM "+pesanan_table+" WHERE id_user = :id_user")
    ObatModel getObat(String id_user);

    //menginput data
    @Insert
    void insertPesanan(PesananModel pesananModel);
    //mengupdate data
    @Update
    void updatePesanan(PesananModel pesananModel);
   //menghapus data
    @Delete
    void deletePesanan(PesananModel pesananModel);
}
