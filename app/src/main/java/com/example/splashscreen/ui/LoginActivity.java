package com.example.splashscreen.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.splashscreen.R;
import com.example.splashscreen.db.DBUser;
import com.example.splashscreen.db.UserModel;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    EditText  tx_email, tx_password;
    Button btnLogin, btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initSetup();
        buttonClick();
    }

    void initSetup(){
        // get id
        tx_email = findViewById(R.id.tx_email);
        tx_password = findViewById(R.id.tx_password);
        //button
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
    }

    void buttonClick(){
        btnLogin.setOnClickListener(v -> {
            //mengambil value dari setiap edittext
            String email = tx_email.getText().toString();
            String password = tx_password.getText().toString();
            //panggil fungsi utama builder database
            DBUser dbUser = DBUser.getInstance(this);
            //ketika login berhasil
            UserModel user = dbUser.userDao().getUser(email, password);
            if (user != null){
                if(user.role.equals("user")){
                    Toast.makeText(this, "Login ", Toast.LENGTH_SHORT).show();
                    //save data to sharedpreference
                    SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("email", email);
                    editor.putString("role", user.role);
                    editor.putInt("id_user", user.id);
                    editor.putBoolean("isLogin", true);
                    editor.apply();
                    //intent
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                    startActivity(intent);
                    finish();
                }

            }else{
                Toast.makeText(this, "Gagal Login", Toast.LENGTH_SHORT).show();
            }


        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}