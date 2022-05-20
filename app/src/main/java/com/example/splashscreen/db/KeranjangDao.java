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
    List<KeranjangModel> getKeranjang(String id_user);

    //menginput data
    @Insert
    void insertObat(KeranjangModel keranjangModel);
    //mengupdate data
    @Query("UPDATE "+keranjang_table+" set kuantitas=:kuantitas WHERE id = :id  ")
    void updateKeranjang(String id, String kuantitas );
   //menghapus data
   @Query("DELETE from "+keranjang_table+" WHERE id = :id  ")
   void deleteKeranjang(String id );
}
