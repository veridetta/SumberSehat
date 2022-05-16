package com.example.splashscreen.ui.user.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.splashscreen.R;
import com.example.splashscreen.db.ObatModel;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

public class ObatAdapter extends RecyclerView.Adapter<ObatAdapter.MyViewHolder> {

    private List<ObatModel> datalist;
    private Context context;

    public void setObatModelList(Context context,final List<ObatModel> datalist){
        this.context = context;
        if(this.datalist== null){
            this.datalist= datalist;
            notifyItemChanged(0, datalist.size());
        } else {
            final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return ObatAdapter.this.datalist.size();
                }

                @Override
                public int getNewListSize() {
                    return datalist.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return ObatAdapter.this.datalist.get(oldItemPosition).nama == datalist.get(newItemPosition).nama;
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {

                    ObatModel newMovie = ObatAdapter.this.datalist.get(oldItemPosition);

                    ObatModel oldMovie = datalist.get(newItemPosition);

                    return newMovie.nama == oldMovie.nama ;
                }
            });
            this.datalist= datalist;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public ObatAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produk,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ObatAdapter.MyViewHolder holder, int position) {
        holder.tvNama.setText(datalist.get(position).nama);
        holder.tvHarga.setText(rupiah(Double.parseDouble(datalist.get(position).harga)));
        holder.tvDeskripsi.setText(datalist.get(position).deskripsi);
        Glide.with(context).load("https://miro.medium.com/focal/92/92/50/50/1*QPllkvDm_lXdVPekf6Rugw.jpeg")
                .apply(RequestOptions.centerCropTransform()).into(holder.imgObat);
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

        TextView tvNama, tvHarga, tvDeskripsi;
        ImageView imgObat;

        public MyViewHolder(View view) {
            super(view);
            tvNama = view.findViewById(R.id.tv_nama_obat);
            tvHarga = view.findViewById(R.id.tv_harga);
            tvDeskripsi = view.findViewById(R.id.tv_deskripsi_obat);
            imgObat = view.findViewById(R.id.img_obat);
        }
    }

    String rupiah(double harga){
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);

        return kursIndonesia.format(harga);
    }
}
