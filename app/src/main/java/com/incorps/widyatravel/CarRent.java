package com.incorps.widyatravel;

public class CarRent {
    String kodeTransaksi;
    String namaDriver;
    String kotaPeminjaman;
    String lokasiPeminjaman;
    String tanggalPeminjaman;
    String waktuPeminjaman;
    String lamaPeminjaman;
    String harga;
    String statusTransaksi;
    String rating;

    public CarRent(String kodeTransaksi, String namaDriver, String kotaPeminjaman, String lokasiPeminjaman, String tanggalPeminjaman, String waktuPeminjaman, String lamaPeminjaman, String harga, String statusTransaksi, String rating) {
        this.kodeTransaksi = kodeTransaksi;
        this.namaDriver = namaDriver;
        this.kotaPeminjaman = kotaPeminjaman;
        this.lokasiPeminjaman = lokasiPeminjaman;
        this.tanggalPeminjaman = tanggalPeminjaman;
        this.waktuPeminjaman = waktuPeminjaman;
        this.lamaPeminjaman = lamaPeminjaman;
        this.harga = harga;
        this.statusTransaksi = statusTransaksi;
        this.rating = rating;
    }

    public String getKodeTransaksi() {
        return kodeTransaksi;
    }

    public void setKodeTransaksi(String kodeTransaksi) {
        this.kodeTransaksi = kodeTransaksi;
    }

    public String getNamaDriver() {
        return namaDriver;
    }

    public void setNamaDriver(String namaDriver) {
        this.namaDriver = namaDriver;
    }

    public String getKotaPeminjaman() {
        return getKota(kotaPeminjaman);
    }

    public void setKotaPeminjaman(String kotaPeminjaman) {
        this.kotaPeminjaman = kotaPeminjaman;
    }

    public String getLokasiPeminjaman() {
        return lokasiPeminjaman;
    }

    public void setLokasiPeminjaman(String lokasiPeminjaman) {
        this.lokasiPeminjaman = lokasiPeminjaman;
    }

    public String getTanggalPeminjaman() {
        return tanggalPeminjaman;
    }

    public void setTanggalPeminjaman(String tanggalPeminjaman) {
        this.tanggalPeminjaman = tanggalPeminjaman;
    }

    public String getWaktuPeminjaman() {
        return waktuPeminjaman;
    }

    public void setWaktuPeminjaman(String waktuPeminjaman) {
        this.waktuPeminjaman = waktuPeminjaman;
    }

    public String getLamaPeminjaman() {
        return lamaPeminjaman;
    }

    public void setLamaPeminjaman(String lamaPeminjaman) {
        this.lamaPeminjaman = lamaPeminjaman;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getStatusTransaksi() {
        return statusTransaksi;
    }

    public void setStatusTransaksi(String statusTransaksi) {
        this.statusTransaksi = statusTransaksi;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getKota(String nomer){
        switch (nomer) {
            case "1" : return "Banyumas";
            case "2" : return "Batang";
            case "3" : return "Boyolali";
            case "4" : return "Brebes";
            case "5" : return "Cilacap";
            case "6" : return "Demak";
            case "7" : return "Kebumen";
            case "8" : return "Kendal";
            case "9" : return "Kudus";
            case "10" : return "Magelang";
            case "11" : return "Pati";
            case "12" : return "Pekalongan";
            case "13" : return "Pemalang";
            case "14" : return "Purbalingga";
            case "15" : return "Purworejo";
            case "16" : return "Rembang";
            case "17" : return "Salatiga";
            case "18" : return "Semarang";
            case "19" : return "Surakarta";
            case "20" : return "Tegal";
            case "21" : return "Temanggung";
            case "22" : return "Wonosobo";
            case "23" : return "Yogyakarta";
            case "24" : return "Banjarnegara";
        }
        return "0";
    }
}
