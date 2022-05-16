package com.example.splashscreen.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.splashscreen.R;
import com.example.splashscreen.ui.user.AkunFragment;
import com.example.splashscreen.ui.user.HomeFragment;
import com.example.splashscreen.ui.user.KeranjangFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    String TAG = MainActivity.class.getSimpleName();
    BottomNavigationView bottomNavigationView;
    Fragment fragment;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initView();
        navSetup();
    }

    void initView(){
        bottomNavigationView = findViewById(R.id.btm_nav);
        bottomNavigationView.inflateMenu(R.menu.bottom_nav);
        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.frameLayout, new HomeFragment()).commit();
    }

    void navSetup(){
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.nav_profile:
                        fragment = new AkunFragment();
                        break;
                    case R.id.nav_cart:
                        fragment = new KeranjangFragment();
                        break;
                    default:
                        fragment = new HomeFragment();
                        break;
                }
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frameLayout, fragment).commit();
                return true;
            }
        });
    }
}