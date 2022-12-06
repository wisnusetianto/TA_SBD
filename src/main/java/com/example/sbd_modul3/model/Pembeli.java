package com.example.sbd_modul3.model;

public class Pembeli {
    //! Properties sesuai dengan tabel mahasiswa
    private String id_pembeli;
    private String nama_pembeli;
    private String email;
    private String no_tlp;
    
    public void setId_pembeli(String id_pembeli){
        this.id_pembeli = id_pembeli;
    }
    public String getId_pembeli(){
        return id_pembeli;
    }

    public void setNama_pembeli(String nama_pembeli){
        this.nama_pembeli = nama_pembeli;
    }
    public String getNama_pembeli(){
        return nama_pembeli;
    }

    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }

    public void setNo_tlp(String no_tlp){
        this.no_tlp = no_tlp;
    }
    public String getNo_tlp(){
        return no_tlp;
    }

   
}
