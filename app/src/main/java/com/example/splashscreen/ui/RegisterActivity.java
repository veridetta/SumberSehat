package com.example.splashscreen.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.splashscreen.R;
import com.example.splashscreen.db.DBUser;
import com.example.splashscreen.db.UserDao;
import com.example.splashscreen.db.UserModel;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    EditText tx_nama, tx_umur, tx_email, tx_password;
    Spinner sp_jk;
    Button btnRegister, btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initSetup();
    }

    void initSetup(){
        // get id
        tx_nama = findViewById(R.id.tx_nama);
        tx_umur = findViewById(R.id.tx_umur);
        tx_email = findViewById(R.id.tx_email);
        tx_password = findViewById(R.id.tx_password);
        //button
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
        //spinner
        sp_jk = findViewById(R.id.sp_jk);
        setupSpinner();
        btnLogin.setOnClickListener(v -> {
            //intent
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
        btnRegister.setOnClickListener(v -> {
            //mengambil value dari setiap edittext
            String nama = tx_nama.getText().toString();
            String umur = tx_umur.getText().toString();
            String email = tx_email.getText().toString();
            String password = tx_password.getText().toString();
            String jk = sp_jk.getSelectedItem().toString();
            //panggil fungsi utama builder database
            DBUser dbUser = DBUser.getInstance(this);
            dbUser.userDao().insertUser(new UserModel(nama, email, password, jk, umur));
            Toast.makeText(this, "Berhasil Register", Toast.LENGTH_SHORT).show();
            //Intent
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
    void setupSpinner(){
        //kita akan mensetting spinner
        String[] jk = {"Pilih Jenis Kelamin", "Laki-Laki", "Perempuan"};
        //membuat adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, jk);
        //mengatur adapter
        sp_jk.setAdapter(adapter);
    }
}