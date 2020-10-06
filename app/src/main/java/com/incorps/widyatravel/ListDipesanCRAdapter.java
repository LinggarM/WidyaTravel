package com.incorps.widyatravel;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

class ListDipesanCRAdapter extends RecyclerView.Adapter<ListDipesanCRAdapter.ListViewHolder> {

    private ArrayList<CarRent> listCarRent;

    public ListDipesanCRAdapter(ArrayList<CarRent> list) {
        this.listCarRent = list;
    }

    @NonNull
    @Override
    public ListDipesanCRAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_dipesan_cr, viewGroup, false);
        return new ListDipesanCRAdapter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListDipesanCRAdapter.ListViewHolder holder, int position) {
        CarRent carRent = listCarRent.get(position);
        holder.kota.setText(carRent.getKotaPeminjaman());
        holder.tanggal.setText(carRent.getTanggalPeminjaman());
        holder.waktu.setText(carRent.getWaktuPeminjaman() + " WIB");
        holder.lama.setText(carRent.getLamaPeminjaman() + " Hari");
        holder.harga.setText("Rp. " + carRent.getHarga());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intentDetail = new Intent(context, DetailCarRentDipesanActivity.class);
                intentDetail.putExtra("kodeTransaksi", listCarRent.get(holder.getAdapterPosition()).getKodeTransaksi());
                intentDetail.putExtra("namaDriver", listCarRent.get(holder.getAdapterPosition()).getNamaDriver());
                intentDetail.putExtra("kotaPeminjaman", listCarRent.get(holder.getAdapterPosition()).getKotaPeminjaman());
                intentDetail.putExtra("lokasiPeminjaman", listCarRent.get(holder.getAdapterPosition()).getLokasiPeminjaman());
                intentDetail.putExtra("tanggal", listCarRent.get(holder.getAdapterPosition()).getTanggalPeminjaman());
                intentDetail.putExtra("waktuPeminjaman", listCarRent.get(holder.getAdapterPosition()).getWaktuPeminjaman());
                intentDetail.putExtra("harga", listCarRent.get(holder.getAdapterPosition()).getHarga());
                intentDetail.putExtra("lamaPeminjaman", listCarRent.get(holder.getAdapterPosition()).getLamaPeminjaman());
                intentDetail.putExtra("statusTransaksi", listCarRent.get(holder.getAdapterPosition()).getStatusTransaksi());
                intentDetail.putExtra("rating", listCarRent.get(holder.getAdapterPosition()).getRating());
                context.startActivity(intentDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listCarRent.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView kota, tanggal, waktu, lama, harga;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            kota = itemView.findViewById(R.id.kota);
            tanggal = itemView.findViewById(R.id.tanggal);
            waktu = itemView.findViewById(R.id.waktu);
            lama = itemView.findViewById(R.id.lama);
            harga = itemView.findViewById(R.id.harga);
        }
    }
}
