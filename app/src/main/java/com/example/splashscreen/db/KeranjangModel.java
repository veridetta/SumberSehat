package com.example.splashscreen.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity (tableName = "keranjang_table")
public class KeranjangModel {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "id_obat")
    public String id_obat;
    @ColumnInfo(name = "id_user")
    public String id_user;
    @ColumnInfo(name = "kuantitas")
    public String kuantitas;


    public KeranjangModel(int id, String id_obat, String id_user, String kuantitas){
        this.id = id;
        this.id_obat =id_obat;
        this.id_user = id_user;
        this.kuantitas = kuantitas;
    }
    @Ignore
    public KeranjangModel(String id_obat,String id_user, String kuantitas){
        this.id_obat =id_obat;
        this.id_user = id_user;
        this.kuantitas = kuantitas;
    }
}
