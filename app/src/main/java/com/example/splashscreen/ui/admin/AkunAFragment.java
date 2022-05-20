package com.example.splashscreen.ui.admin;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.splashscreen.R;
import com.example.splashscreen.ui.LoginActivity;


public class AkunAFragment extends Fragment {

    TextView tvName, tvEmail, tvJk;
    Button btnLogout;
    String name, email, jk;
    View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_akun_a, container, false);
        initView();
        initPreferences();
        setValue();
        btnClick();
        return view;
    };

    void initView(){
        tvName = view.findViewById(R.id.tv_name);
        tvEmail = view.findViewById(R.id.tv_email);
        tvJk = view.findViewById(R.id.tv_jk);
        btnLogout = view.findViewById(R.id.btn_logout);
    }

    void setValue(){
        tvName.setText(name);
        tvEmail.setText(email);
        tvJk.setText(jk);

    }

    void initPreferences(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", MODE_PRIVATE);
        name = sharedPreferences.getString("name", "");
        email = sharedPreferences.getString("email", "");
        jk = sharedPreferences.getString("jk", "");
    }
    void btnClick(){
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //clear sharedpreferences
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                //clear session
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }
}