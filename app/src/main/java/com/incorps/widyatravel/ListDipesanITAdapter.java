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

public class ListDipesanITAdapter extends RecyclerView.Adapter<ListDipesanITAdapter.ListViewHolder> {

    private ArrayList<IndividualTrip> listIndividualTrip;

    public ListDipesanITAdapter(ArrayList<IndividualTrip> list) {
        this.listIndividualTrip = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_dipesan_it, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        IndividualTrip individualTrip = listIndividualTrip.get(position);
        holder.kotaAwal.setText(individualTrip.getKotaPenjemputan());
        holder.kotaTujuan.setText(individualTrip.getKotaTujuan());
        holder.tanggal.setText(individualTrip.getTanggalPenjemputan());
        holder.waktu.setText(individualTrip.getWaktuPenjemputan() + " WIB");
        holder.orang.setText(individualTrip.getJumlahOrang() + " Orang");
        holder.harga.setText("Rp. " + individualTrip.getHarga());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intentDetail = new Intent(context, DetailIndividualTripDipesanActivity.class);
                intentDetail.putExtra("kodeTransaksi", listIndividualTrip.get(holder.getAdapterPosition()).getKodeTransaksi());
                intentDetail.putExtra("namaDriver", listIndividualTrip.get(holder.getAdapterPosition()).getNamaDriver());
                intentDetail.putExtra("kotaPenjemputan", listIndividualTrip.get(holder.getAdapterPosition()).getKotaPenjemputan());
                intentDetail.putExtra("lokasiPenjemputan", listIndividualTrip.get(holder.getAdapterPosition()).getLokasiPenjemputan());
                intentDetail.putExtra("kotaTujuan", listIndividualTrip.get(holder.getAdapterPosition()).getKotaTujuan());
                intentDetail.putExtra("tanggal", listIndividualTrip.get(holder.getAdapterPosition()).getTanggalPenjemputan());
                intentDetail.putExtra("waktuPenjemputan", listIndividualTrip.get(holder.getAdapterPosition()).getWaktuPenjemputan());
                intentDetail.putExtra("harga", listIndividualTrip.get(holder.getAdapterPosition()).getHarga());
                intentDetail.putExtra("jumlahOrang", listIndividualTrip.get(holder.getAdapterPosition()).getJumlahOrang());
                intentDetail.putExtra("statusTransaksi", listIndividualTrip.get(holder.getAdapterPosition()).getStatusTransaksi());
                context.startActivity(intentDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listIndividualTrip.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView kotaAwal, kotaTujuan, tanggal, waktu, orang, harga;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            kotaAwal = itemView.findViewById(R.id.kota_awal);
            kotaTujuan = itemView.findViewById(R.id.kota_tujuan);
            tanggal = itemView.findViewById(R.id.tanggal);
            waktu = itemView.findViewById(R.id.waktu);
            orang = itemView.findViewById(R.id.orang);
            harga = itemView.findViewById(R.id.harga);
        }
    }
}