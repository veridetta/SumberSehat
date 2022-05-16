package com.example.splashscreen.ui.user;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.splashscreen.R;
import com.example.splashscreen.ui.LoginActivity;
import com.example.splashscreen.ui.MainActivity;
import com.example.splashscreen.ui.admin.ProdukFragment;


public class HomeFragment extends Fragment {

    LinearLayout katAsma, katBatuk, katDemam, katSakitKepala;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        katAsma = view.findViewById(R.id.kat_asma);
        katBatuk = view.findViewById(R.id.kat_batuk);
        katDemam = view.findViewById(R.id.kat_demam);
        katSakitKepala = view.findViewById(R.id.kat_sakit_kepala);

        katAsma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveActivity(view);
            }
        });
        katDemam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveActivity(view);
            }
        });
        katSakitKepala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveActivity(view);
            }
        });
        katBatuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveActivity(view);
            }
        });
        return view;
    }

    public void moveActivity(View view) {
        //get id dari view
        int id = view.getId();
        Intent intent;
        switch (id){
            case R.id.kat_asma:
                 intent = new Intent(getActivity(), ProdukActivity.class);
                 intent.putExtra("kategori", "asma");
                 getActivity().finish();
                 break;
            case R.id.kat_batuk:
                intent = new Intent(getActivity(), ProdukActivity.class);
                intent.putExtra("kategori", "batuk");
                break;
            case R.id.kat_demam:
                intent = new Intent(getActivity(), ProdukActivity.class);
                intent.putExtra("kategori", "demam");
                break;
            case R.id.kat_sakit_kepala:
                intent = new Intent(getActivity(), ProdukActivity.class);
                intent.putExtra("kategori", "sakit_kepala");
                break;
            default:
                intent = new Intent(getActivity(), ProdukActivity.class);
                intent.putExtra("kategori", "batuk");
                break;
        }
        startActivity(intent);

    }
}