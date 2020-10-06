package com.incorps.widyatravel;

public class IndividualTrip {
    String kodeTransaksi;
    String namaDriver;
    String kotaPenjemputan;
    String lokasiPenjemputan;
    String kotaTujuan;
    String tanggalPenjemputan;
    String waktuPenjemputan;
    String harga;
    String jumlahOrang;
    String statusTransaksi;

    public IndividualTrip(String kodeTransaksi, String namaDriver, String kotaPenjemputan, String lokasiPenjemputan, String kotaTujuan, String tanggalPenjemputan, String waktuPenjemputan, String harga, String jumlahOrang, String statusTransaksi) {
        this.kodeTransaksi = kodeTransaksi;
        this.namaDriver = namaDriver;
        this.kotaPenjemputan = kotaPenjemputan;
        this.lokasiPenjemputan = lokasiPenjemputan;
        this.kotaTujuan = kotaTujuan;
        this.tanggalPenjemputan = tanggalPenjemputan;
        this.waktuPenjemputan = waktuPenjemputan;
        this.harga = harga;
        this.jumlahOrang = jumlahOrang;
        this.statusTransaksi = statusTransaksi;
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

    public String getKotaPenjemputan() {
        return getKota(kotaPenjemputan);
    }

    public void setKotaPenjemputan(String kotaPenjemputan) {
        this.kotaPenjemputan = kotaPenjemputan;
    }

    public String getLokasiPenjemputan() {
        return lokasiPenjemputan;
    }

    public void setLokasiPenjemputan(String lokasiPenjemputan) {
        this.lokasiPenjemputan = lokasiPenjemputan;
    }

    public String getKotaTujuan() {
        return getKota(kotaTujuan);
    }

    public void setKotaTujuan(String kotaTujuan) {
        this.kotaTujuan = kotaTujuan;
    }

    public String getTanggalPenjemputan() {
        return tanggalPenjemputan;
    }

    public void setTanggalPenjemputan(String tanggalPenjemputan) {
        this.tanggalPenjemputan = tanggalPenjemputan;
    }

    public String getWaktuPenjemputan() {
        return waktuPenjemputan;
    }

    public void setWaktuPenjemputan(String waktuPenjemputan) {
        this.waktuPenjemputan = waktuPenjemputan;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getJumlahOrang() {
        return jumlahOrang;
    }

    public void setJumlahOrang(String jumlahOrang) {
        this.jumlahOrang = jumlahOrang;
    }

    public String getStatusTransaksi() {
        return statusTransaksi;
    }

    public void setStatusTransaksi(String statusTransaksi) {
        this.statusTransaksi = statusTransaksi;
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
