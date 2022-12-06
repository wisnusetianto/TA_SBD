package com.example.sbd_modul3.model;

public class Toko {
    //! Properties sesuai dengan tabel mahasiswa
    private String id_toko;
    private String alamat;

    public void setId_toko(String id_toko) {
        this.id_toko = id_toko;
    }
    public String getId_toko() {
        return id_toko;
    }
    
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    public String getAlamat() {
        return alamat;
    }

}
