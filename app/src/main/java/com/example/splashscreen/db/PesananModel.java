package com.example.splashscreen.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity (tableName = "pesanan_table")
public class PesananModel {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "id_user")
    public String id_user;
    @ColumnInfo(name = "id_obat")
    public String id_obat;
    @ColumnInfo(name = "kuantitas")
    public String kuantitas;
    @ColumnInfo(name = "status")
    public String status;


    public PesananModel(int id, String id_user, String id_obat, String kuantitas, String status){
        this.id = id;
        this.id_user = id_user;
        this.id_obat = id_obat;
        this.kuantitas = kuantitas;
        this.status = status;

    }
    @Ignore
    public PesananModel( String id_user, String id_obat, String kuantitas, String status){
        this.id_user = id_user;
        this.id_obat = id_obat;
        this.kuantitas = kuantitas;
        this.status = status;
    }
}
