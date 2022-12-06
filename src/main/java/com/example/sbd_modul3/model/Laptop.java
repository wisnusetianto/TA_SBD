package com.example.sbd_modul3.model;

public class Laptop {
    //! Properties sesuai dengan tabel laptop
    private String id_laptop;
    private String nama_laptop;
    private String harga;
    private String stok;
    private String deleted;

    //! Setter dan Getter Properties
    public String getNama_laptop() {
    return nama_laptop;
    }
    public void setNama_laptop(String nama_laptop) {
    this.nama_laptop = nama_laptop;
    }
    public String getId_laptop() {
    return id_laptop;
    }
    public void setId_laptop(String id_laptop) {
    this.id_laptop = id_laptop;
    }
    public String getHarga() {
    return harga;
    }
    public void setHarga(String harga) {
    this.harga = harga;
    }
    public String getStok() {
    return stok;
    }
    public void setStok(String stok) {
    this.stok = stok;
    }
    public String getDeleted() {
    return deleted;
    }
    public void setDeleted(String deleted) {
    this.deleted = deleted;
    }
}
