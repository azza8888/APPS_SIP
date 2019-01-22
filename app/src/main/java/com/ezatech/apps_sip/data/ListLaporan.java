package com.ezatech.apps_sip.data;

public class ListLaporan {

    private String nama;
    private String nopel;
    private String alamat;
    private String waktu;
    private String image;

    public ListLaporan() {
        this.nama = nama;
        this.nopel = nopel;
        this.alamat = alamat;
        this.waktu = waktu;
        this.image = image;
    }


    public String getNopel() {
        return nopel;
    }

    public void setNopel(String nopel) {
        this.nopel = nopel;
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

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
