package com.example.login;

public class model {
    private String id;
    private String nama;
    private String alamat;
    private String No_hp;
    private String pesanan;

    public model(String id, String nama, String alamat, String no_hp, String pesanan) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.No_hp = no_hp;
        this.pesanan = pesanan;
    }

    public String getId() {
        return id;
    }
    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getNo_hp() {
        return No_hp;
    }

    public String getPesanan() {
        return pesanan;
    }
}
