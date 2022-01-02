package com.mobile.pendaftaran;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.mobile.pendaftaran.databinding.ActivityMainBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HasilAdapter  extends RecyclerView.Adapter<HasilAdapter.HasilViewRecHolder> {
    private Context context;
    private List<Hasil> hasilList;

    public HasilAdapter(Context context, List<Hasil> hasilList) {
        this.context = context;
        this.hasilList = hasilList;
    }

    @NonNull
    @Override
    public HasilViewRecHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_hasil, null);
        return new HasilAdapter.HasilViewRecHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HasilViewRecHolder holder, int position) {
        final Hasil hasil = hasilList.get(position);
        holder.nama.setText(hasil.getNama());
        holder.alamat.setText("Alamat : " + hasil.getAlamat());
        holder.hp.setText(hasil.getHp());
        holder.jk.setText(hasil.getJenis_kelamin());
        holder.lokasi.setText("Lokasi Pendaftaran : " + hasil.getLokasi_pendaftaran());
        Picasso.get().load(Server.site_foto + hasil.getFoto()).into(holder.foto);
    }

    @Override
    public int getItemCount() {
        return hasilList.size();
    }

    public class HasilViewRecHolder extends RecyclerView.ViewHolder {
        ImageView foto;
        TextView nama, alamat, hp, jk, lokasi;
        public HasilViewRecHolder(@NonNull View itemView) {
            super(itemView);
            foto = itemView.findViewById(R.id.show_foto);
            nama = itemView.findViewById(R.id.show_nama);
            alamat = itemView.findViewById(R.id.show_alamat);
            hp = itemView.findViewById(R.id.show_hp);
            jk = itemView.findViewById(R.id.show_jk);
            lokasi = itemView.findViewById(R.id.show_lokasi);
        }
    }
}