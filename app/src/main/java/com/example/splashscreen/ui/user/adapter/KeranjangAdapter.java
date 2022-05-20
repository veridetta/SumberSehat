package com.example.splashscreen.ui.user.adapter;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.splashscreen.R;
import com.example.splashscreen.db.DBUser;
import com.example.splashscreen.db.KeranjangModel;
import com.example.splashscreen.db.ObatModel;
import com.example.splashscreen.ui.user.KeranjangFragment;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

public class KeranjangAdapter extends RecyclerView.Adapter<KeranjangAdapter.MyViewHolder> {

    private List<KeranjangModel> datalist;
    private Context context;
    private DBUser dbUser;
    private KeranjangFragment keranjangFragment;
    public void setObatModelList(KeranjangFragment fragment,final List<KeranjangModel> datalist){
        this.keranjangFragment = fragment;
        this.context = fragment.getActivity();
        if(this.datalist== null){
            this.datalist= datalist;
            notifyItemChanged(0, datalist.size());
        } else {
            final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return KeranjangAdapter.this.datalist.size();
                }

                @Override
                public int getNewListSize() {
                    return datalist.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return KeranjangAdapter.this.datalist.get(oldItemPosition).id_obat ==
                            datalist.get(newItemPosition).id_obat;
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {

                    KeranjangModel newMovie = KeranjangAdapter.this.datalist.get(oldItemPosition);

                    KeranjangModel oldMovie = datalist.get(newItemPosition);

                    return newMovie.id_obat == oldMovie.id_obat ;
                }
            });
            this.datalist= datalist;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public KeranjangAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        dbUser = DBUser.getInstance(context);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_keranjang,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Integer idObat = Integer.valueOf(datalist.get(position).id_obat);
        ObatModel obatModel = dbUser.obatDao().getObatbyId(idObat);
        holder.tvNama.setText(obatModel.nama);
        holder.etKuantitas.setText(String.valueOf(datalist.get(position).kuantitas));
        holder.tvHarga.setText(keranjangFragment.rupiah(Double.parseDouble(obatModel.harga)));
        Glide.with(context).load(obatModel.gambar)
                .apply(RequestOptions.centerCropTransform()).into(holder.imgObat);
        updateTotal(datalist.get(position).id_user);
        holder.btnMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kuantitas = String.valueOf(holder.etKuantitas.getText());
                holder.etKuantitas.setText(String.valueOf(Integer.parseInt(kuantitas)-1));
                DBUser dbUser = DBUser.getInstance(context);
                dbUser.keranjangDao().updateKeranjang(String.valueOf(datalist.get(position).id),String.valueOf(Integer.parseInt(kuantitas)-1));
                updateTotal(datalist.get(position).id_user);
                Toast.makeText(context, "Berhasil merubah keranjang", Toast.LENGTH_SHORT).show();
            }
        });
        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kuantitas = String.valueOf(holder.etKuantitas.getText());
                holder.etKuantitas.setText(String.valueOf(Integer.parseInt(kuantitas)+1));
                DBUser dbUser = DBUser.getInstance(context);
                dbUser.keranjangDao().updateKeranjang(String.valueOf(datalist.get(position).id),String.valueOf(Integer.parseInt(kuantitas)+1));
                updateTotal(datalist.get(position).id_user);
                Toast.makeText(context, "Berhasil merubah keranjang", Toast.LENGTH_SHORT).show();
            }
        });
        holder.etKuantitas.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                String kuantitas = String.valueOf(holder.etKuantitas.getText());
                DBUser dbUser = DBUser.getInstance(context);
                dbUser.keranjangDao().updateKeranjang(String.valueOf(datalist.get(position).id),kuantitas);
                updateTotal(datalist.get(position).id_user);
                Toast.makeText(context, "Berhasil merubah keranjang", Toast.LENGTH_SHORT).show();
            }
        });
        holder.btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBUser dbUser = DBUser.getInstance(context);
                dbUser.keranjangDao().deleteKeranjang(String.valueOf(datalist.get(position).id));
                updateTotal(datalist.get(position).id_user);
                Toast.makeText(context, "Berhasil menghapus dari keranjang", Toast.LENGTH_SHORT).show();
                //refresh data
                datalist.remove(position);
                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {

        if(datalist!= null){
            return datalist.size();
        } else {
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvNama, tvHarga;
        ImageView imgObat, btnPlus, btnMin, btnDel;
        EditText etKuantitas;
        public MyViewHolder(View view) {
            super(view);
            tvNama = view.findViewById(R.id.tv_nama_obat);
            tvHarga = view.findViewById(R.id.tv_harga);
            imgObat = view.findViewById(R.id.img_obat);
            btnPlus = view.findViewById(R.id.img_plus);
            btnDel = view.findViewById(R.id.img_delete);
            btnMin = view.findViewById(R.id.img_minus);
            etKuantitas = view.findViewById(R.id.et_kuantitas);
        }
    }
    void updateTotal(String idUser){
        DBUser dbUser = DBUser.getInstance(context);
        List<KeranjangModel> keranjangModel = dbUser.keranjangDao().getKeranjang(idUser);
        int total = 0;
        int total_harga_perbarang=0;
        for (KeranjangModel keranjang : keranjangModel){
            ObatModel obatModel = dbUser.obatDao().getObatbyId(Integer.valueOf(keranjang.id_obat));
            total_harga_perbarang = Integer.parseInt(obatModel.harga)*Integer.parseInt(keranjang.kuantitas);
            total += total_harga_perbarang;
        }
        keranjangFragment.ubahTotalHarga(keranjangFragment.rupiah(total));
    }
}
