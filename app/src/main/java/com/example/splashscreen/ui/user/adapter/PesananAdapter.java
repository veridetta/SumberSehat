package com.example.splashscreen.ui.user.adapter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.example.splashscreen.db.PesananModel;
import com.example.splashscreen.db.UserModel;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class PesananAdapter extends RecyclerView.Adapter<PesananAdapter.MyViewHolder> {

    private List<PesananModel> datalist;
    private Context context;

    public void setObatModelList(Context context,final List<PesananModel> datalist){
        this.context = context;
        if(this.datalist== null){
            this.datalist= datalist;
            notifyItemChanged(0, datalist.size());
        } else {
            final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return PesananAdapter.this.datalist.size();
                }

                @Override
                public int getNewListSize() {
                    return datalist.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return PesananAdapter.this.datalist.get(oldItemPosition).id ==
                            datalist.get(newItemPosition).id;
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {

                    PesananModel newMovie = PesananAdapter.this.datalist.get(oldItemPosition);

                    PesananModel oldMovie = datalist.get(newItemPosition);

                    return newMovie.id == oldMovie.id ;
                }
            });
            this.datalist= datalist;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public PesananAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pesanan,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PesananAdapter.MyViewHolder holder, int position) {

        holder.tvIdPesanan.setText("ID Pesanan : PSJ#"+datalist.get(position).id);
        String[] listIdobat = datalist.get(position).id_obat.split(",");
        String[] listKuantitas = datalist.get(position).kuantitas.split(",");
        List<String> listNamaObat = new ArrayList<>();
        List<String> listHarga = new ArrayList<>();
        DBUser dbUser = DBUser.getInstance(context);
        for (int i = 0; i < listIdobat.length; i++) {
            String idobat = listIdobat[i];

            ObatModel obatModel = dbUser.obatDao().getObatbyId(Integer.valueOf(idobat));
            listNamaObat.add(obatModel.nama);
            listHarga.add(obatModel.harga);
        }
        UserModel userModel = dbUser.userDao().getUserById(Integer.valueOf(datalist.get(position).id_user));
        String forNamaObat = "";
        String forHarga = "";
        Integer totalHarga = 0;
        for (int i = 0; i < listNamaObat.size(); i++) {
            forNamaObat += listNamaObat.get(i)+" x"+listKuantitas[i]+"\n";
            forHarga += rupiah(Double.parseDouble(listKuantitas[i])*Integer.parseInt(listHarga.get(i)))+"\n";
            totalHarga += Integer.parseInt(listKuantitas[i])*Integer.parseInt(listHarga.get(i));
        }
        holder.tvNama.setText(userModel.nama);
        holder.tvListObat.setText(forNamaObat);
        holder.tvListHarga.setText(forHarga);
        holder.tvTotal.setText(rupiah(totalHarga));
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

        TextView tvNama, tvListHarga, tvListObat, tvTotal, tvIdPesanan;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvNama = itemView.findViewById(R.id.tv_nama_user);
            tvListHarga = itemView.findViewById(R.id.tv_list_harga);
            tvListObat = itemView.findViewById(R.id.tv_list_obat);
            tvTotal = itemView.findViewById(R.id.tv_total_harga);
            tvIdPesanan = itemView.findViewById(R.id.tv_id_pesanan);

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
