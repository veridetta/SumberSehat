package com.example.splashscreen.ui.user;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.splashscreen.R;
import com.example.splashscreen.db.DBUser;
import com.example.splashscreen.db.ObatModel;
import com.example.splashscreen.ui.admin.AkunAFragment;
import com.example.splashscreen.ui.admin.ProdukFragment;
import com.example.splashscreen.ui.user.adapter.ObatAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProdukFragmentUser extends Fragment {
    private RecyclerView recyclerView;
    private ObatAdapter obatAdapter;
    private List<ObatModel> obatModelList;
    private static final String ARG_KATEGORI = "kategori";

    // TODO: Rename and change types of parameters
    private String getArgKategori;

    public static AkunAFragment newInstance(String getArgKategori) {
        AkunAFragment fragment = new AkunAFragment();
        Bundle args = new Bundle();
        args.putString(ARG_KATEGORI, getArgKategori);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            getArgKategori = getArguments().getString(ARG_KATEGORI);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_produk_user, container, false);
        recyclerView = view.findViewById(R.id.rc_produk);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        obatAdapter = new ObatAdapter();
        recyclerView.setAdapter(obatAdapter);
        Toast.makeText(getActivity(), "Kategori : " + getArgKategori, Toast.LENGTH_SHORT).show();
        obatModelList = new ArrayList<>();
        //get string from intent
        //String kategori = getActivity().getIntent().getStringExtra("kategori");
        DBUser dbUser = DBUser.getInstance(getActivity());
        //ketika login berhasil
        obatModelList = dbUser.obatDao().getObat(getArgKategori);
        Log.d("obat", "onCreate: "+obatModelList.size());
        obatAdapter.setObatModelList(getActivity(),obatModelList);
        return view;
    };
}