package com.example.splashscreen.ui.user;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Build;
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
import android.widget.Button;
import android.widget.TextView;

import com.example.splashscreen.R;
import com.example.splashscreen.db.DBUser;
import com.example.splashscreen.db.KeranjangModel;
import com.example.splashscreen.db.ObatModel;
import com.example.splashscreen.db.PesananModel;
import com.example.splashscreen.ui.user.adapter.KeranjangAdapter;
import com.example.splashscreen.ui.user.adapter.ObatAdapter;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;


public class KeranjangFragment extends Fragment {
    private RecyclerView recyclerView;
    private KeranjangAdapter keranjangAdapter;
    private List<KeranjangModel> keranjangModelList;
    private View view;
    String id_user;
    TextView tvTotal;
    Button btnBayar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_keranjang, container, false);
        initPreferences();
        initView();
        btnClick();
        return view;
    };

    void initView(){
        recyclerView = view.findViewById(R.id.rc_keranjang);
        tvTotal = view.findViewById(R.id.tv_total);
        btnBayar = view.findViewById(R.id.btn_bayar);
        btnBayar.setEnabled(false);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        keranjangAdapter = new KeranjangAdapter();
        recyclerView.setAdapter(keranjangAdapter);

        keranjangModelList = new ArrayList<>();
        //get string from intent
        String kategori = getActivity().getIntent().getStringExtra("kategori");
        DBUser dbUser = DBUser.getInstance(getActivity());
        //ketika login berhasil
        keranjangModelList = dbUser.keranjangDao().getKeranjang(id_user);
        Log.d("obat", "onCreate: "+keranjangModelList.size());
        keranjangAdapter.setObatModelList(this,keranjangModelList);
    }
    void initPreferences(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", MODE_PRIVATE);
         id_user = String.valueOf(sharedPreferences.getInt("id_user", 0));
    }
    public String rupiah(double harga){
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);

        return kursIndonesia.format(harga);
    }
    public void ubahTotalHarga(String value){
        tvTotal.setText(value);
        if (tvTotal.getText().toString().equals("Rp. 0")){
            btnBayar.setEnabled(false);
        }else {
            btnBayar.setEnabled(true);
        }
    }
    void btnClick(){
        btnBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBUser dbUser = DBUser.getInstance(getActivity());
                List<KeranjangModel> keranjangModel = dbUser.keranjangDao().getKeranjang(id_user);
                StringJoiner kumpulanIdObat = null;
                StringJoiner kumpulanKuantitas = null;
                StringJoiner kumpulanHarga = null;
                String id_userx = "";
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    kumpulanIdObat = new StringJoiner(",");
                    kumpulanKuantitas = new StringJoiner(",");
                    kumpulanHarga = new StringJoiner(",");
                }
                if(keranjangModel.size()>0){
                    for(KeranjangModel keranjangModel1 : keranjangModel){
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            kumpulanIdObat.add(keranjangModel1.id_obat);
                            kumpulanKuantitas.add(keranjangModel1.kuantitas);
                            kumpulanHarga.add(keranjangModel1.harga);
                        }
                        id_userx = keranjangModel1.id_user;
                        dbUser.keranjangDao().deleteKeranjang(String.valueOf(keranjangModel1.id));
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        dbUser.pesananDao().insertPesanan(new PesananModel(id_userx,
                                kumpulanIdObat.toString(), kumpulanKuantitas.toString(),
                                kumpulanHarga.toString(),
                                "Sudah Bayar"));
                    }
                    Fragment fragment = new ResultFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frameLayout, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });
    }
}