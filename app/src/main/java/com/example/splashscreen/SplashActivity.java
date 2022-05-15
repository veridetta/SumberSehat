package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.splashscreen.db.DBUser;
import com.example.splashscreen.db.UserModel;
import com.example.splashscreen.ui.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initAdmin();
    }

    void initAdmin(){
        DBUser dbUser = DBUser.getInstance(this);

        if(dbUser.userDao().getAdmin("admin")!=null){
            pindahPage();
        }else{
            dbUser.userDao().insertUser(new UserModel("Admin", "admin@gmail.com",
                    "admin123", "Laki-laki", "20", "admin"));
            pindahPage();
        }
    }
    void pindahPage(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.putExtra("nama","syahrul");
                finish();
                startActivity(intent);
            }
        },2000);
    }
}