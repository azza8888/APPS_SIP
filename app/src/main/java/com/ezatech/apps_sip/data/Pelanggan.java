package com.ezatech.apps_sip.data;

public class Pelanggan {

    private String id_penerbit;
    private String no_pendaftaran;
    private String nama;
    private String alamat;
    private String jenis_tarif;
    private String daya;
    private String nama_penyedia;


    public Pelanggan() {
        this.id_penerbit = id_penerbit;
        this.no_pendaftaran = no_pendaftaran;
        this.nama = nama;
        this.alamat = alamat;
        this.jenis_tarif = jenis_tarif;
        this.daya = daya;
        this.nama_penyedia = nama_penyedia;
    }

    public String getId_penerbit() {
        return id_penerbit;
    }

    public void setId_penerbit(String id_penerbit) {
        this.id_penerbit = id_penerbit;
    }

    public String getNo_pendaftaran() {
        return no_pendaftaran;
    }

    public void setNo_pendaftaran(String no_pendaftaran) {
        this.no_pendaftaran = no_pendaftaran;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJenis_tarif() {
        return jenis_tarif;
    }

    public void setJenis_tarif(String jenis_tarif) {
        this.jenis_tarif = jenis_tarif;
    }

    public String getDaya() {
        return daya;
    }

    public void setDaya(String daya) {
        this.daya = daya;
    }

    public String getNama_penyedia() {
        return nama_penyedia;
    }

    public void setNama_penyedia(String nama_penyedia) {
        this.nama_penyedia = nama_penyedia;
    }

}
