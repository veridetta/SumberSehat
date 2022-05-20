package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.splashscreen.db.DBUser;
import com.example.splashscreen.db.ObatModel;
import com.example.splashscreen.db.UserModel;
import com.example.splashscreen.ui.AdminActivity;
import com.example.splashscreen.ui.LoginActivity;
import com.example.splashscreen.ui.MainActivity;

public class SplashActivity extends AppCompatActivity {

    Intent intent;
    DBUser dbUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initAdmin();
    }

    void initAdmin(){
         dbUser = DBUser.getInstance(this);

        if(dbUser.userDao().getAdmin("admin")!=null){

        }else{
            dbUser.userDao().insertUser(new UserModel("Admin", "admin@gmail.com",
                    "admin123", "Laki-laki", "20", "admin"));
        }
        //get sharedpreference
        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        String nama = sharedPreferences.getString("nama", "");
        String email = sharedPreferences.getString("email", "");
        String role = sharedPreferences.getString("role", "user");
        Integer id_user = sharedPreferences.getInt("id_user", 0);
        Boolean isLogin = sharedPreferences.getBoolean("isLogin", false);

        if(dbUser.obatDao().getObatAll()!=null){

        }else{
            tambahObat();
        }

        if(isLogin){
            if(role.equals("user")){
                pindahUser();
            }else{
                pindahAdmin();
            }
        }else{
            pindahPage();
        }
    }
    void pindahPage(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                intent = new Intent(getApplicationContext(), LoginActivity.class);
                finish();
                startActivity(intent);
            }
        },1500);
    }
    void pindahUser(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                intent = new Intent(getApplicationContext(), MainActivity.class);
                finish();
                startActivity(intent);
            }
        },2000);
    }
    void pindahAdmin(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                intent = new Intent(getApplicationContext(), AdminActivity.class);
                finish();
                startActivity(intent);
            }
        },2000);
    }
    void tambahObat(){
        //input obat ke database jika tidak ada obat
        dbUser.obatDao().insertObat(new ObatModel("asmasolon", "4000","asma",
                "https://miro.medium.com/focal/92/92/50/50/1*QPllkvDm_lXdVPekf6Rugw.jpeg",
                "-","10"));

        dbUser.obatDao().insertObat(new ObatModel("neo napacin", "5000","asma",
                "https://miro.medium.com/focal/92/92/50/50/1*QPllkvDm_lXdVPekf6Rugw.jpeg",
                "-","10"));
        dbUser.obatDao().insertObat(new ObatModel("neo napacin 2", "5000","asma",
                "https://cdn0-production-images-kly.akamaized.net/MXuUWdw3wsbcd2U1jqIwPyMZAwI=/640x360/smart/filters:quality(75):strip_icc():format(jpeg)/kly-media-production/medias/3172766/original/024217600_1594118560-20-mg-label-blister-pack-208512__3_.jpg",
                "-","10"));

    }
}