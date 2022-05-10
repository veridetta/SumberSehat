package com.example.splashscreen.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
            if (dbUser.userDao().getUser(email, password) != null){
                Toast.makeText(this, "Login Register", Toast.LENGTH_SHORT).show();
                //intent
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
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