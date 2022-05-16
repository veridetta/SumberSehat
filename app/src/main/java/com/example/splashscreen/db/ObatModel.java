package com.example.splashscreen.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity (tableName = "obat_table")
public class ObatModel {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "nama")
    public String nama;
    @ColumnInfo(name = "harga")
    public String harga;
    @ColumnInfo(name = "kategori")
    public String kategori;
    @ColumnInfo(name = "gambar")
    public String gambar;
    @ColumnInfo(name = "deskripsi")
    public String deskripsi;
    @ColumnInfo(name = "stok")
    public String stok;


    public ObatModel(int id, String nama, String harga, String kategori,
                     String gambar, String deskripsi, String stok){
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
        this.gambar = gambar;
        this.deskripsi = deskripsi;
        this.stok = stok;
    }
    @Ignore
    public ObatModel(String nama, String harga, String kategori,
                     String gambar, String deskripsi, String stok){
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
        this.gambar = gambar;
        this.deskripsi = deskripsi;
        this.stok = stok;
    }

}
