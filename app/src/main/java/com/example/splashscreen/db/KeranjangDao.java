package com.example.splashscreen.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface KeranjangDao {
    //mengambil data user
    String keranjang_table = "keranjang_table";
    @Query("SELECT * FROM "+keranjang_table)
    List<KeranjangModel> getKeranjangAll();

    //mengambil data berdasarkan id_keranjang
    @Query("SELECT * FROM "+keranjang_table+" WHERE id_user = :id_user")
    KeranjangModel getKeranjang(String id_user);

    //menginput data
    @Insert
    void insertObat(KeranjangModel keranjangModel);
    //mengupdate data
    @Update
    void updateObat(KeranjangModel keranjangModel);
   //menghapus data
    @Delete
    void deleteObat(KeranjangModel keranjangModel);
}
