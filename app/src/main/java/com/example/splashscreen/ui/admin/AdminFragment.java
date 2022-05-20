package com.example.splashscreen.ui.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.splashscreen.R;
import com.example.splashscreen.db.DBUser;
import com.example.splashscreen.db.KeranjangModel;
import com.example.splashscreen.db.PesananModel;
import com.example.splashscreen.ui.user.adapter.KeranjangAdapter;
import com.example.splashscreen.ui.user.adapter.PesananAdapter;

import java.util.ArrayList;
import java.util.List;


public class AdminFragment extends Fragment {
    private RecyclerView recyclerView;
    private PesananAdapter pesananAdapter;
    private List<PesananModel> pesananModelList;

    View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_admin, container, false);
        initView();
        return view;
    };

    void initView(){
        recyclerView = view.findViewById(R.id.rc_pesanan);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        pesananAdapter = new PesananAdapter();
        recyclerView.setAdapter(pesananAdapter);

        pesananModelList = new ArrayList<>();
        //get string from intent
        String kategori = getActivity().getIntent().getStringExtra("kategori");
        DBUser dbUser = DBUser.getInstance(getActivity());
        //ketika login berhasil
        pesananModelList = dbUser.pesananDao().getPesananAll();
        Log.d("obat", "onCreate: "+pesananModelList.size());
        pesananAdapter.setObatModelList(getActivity(),pesananModelList);
    }
}