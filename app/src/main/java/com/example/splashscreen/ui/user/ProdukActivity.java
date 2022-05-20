package com.example.splashscreen.ui.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.splashscreen.R;
import com.example.splashscreen.db.DBUser;
import com.example.splashscreen.db.ObatModel;
import com.example.splashscreen.db.UserModel;
import com.example.splashscreen.ui.user.adapter.ObatAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProdukActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ObatAdapter obatAdapter;
    private List<ObatModel> obatModelList;
    private ImageView btnKeranjang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk);

        recyclerView = findViewById(R.id.rc_produk);
        btnKeranjang = findViewById(R.id.btn_cart);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        obatAdapter = new ObatAdapter();
        recyclerView.setAdapter(obatAdapter);

        obatModelList = new ArrayList<>();
        //get string from intent
        String kategori = getIntent().getStringExtra("kategori");
        DBUser dbUser = DBUser.getInstance(this);
        //ketika login berhasil
        obatModelList = dbUser.obatDao().getObat(kategori);
        Log.d("obat", "onCreate: "+obatModelList.size());
        obatAdapter.setObatModelList(getApplicationContext(),obatModelList);

    }
}