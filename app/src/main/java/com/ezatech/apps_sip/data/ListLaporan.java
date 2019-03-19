package com.ezatech.apps_sip.data;

public class ListLaporan {

    private String nama;
    private String nopel;
    private String alamat;
    private String waktu;
    private String image;

    private String id;
    private String no_surat;
    private String id_pemeriksa1;
    private String id_pemeriksa2;
    private String id_staff;
    private String created_at;
    private String updated_at;
    private String nama_pemeriksa1;
    private String nama_pemeriksa2;
    private String status;


    public ListLaporan(String id, String no_surat, String id_pemeriksa1, String id_pemeriksa2, String id_staff, String created_at, String updated_at
    ,String nama_pemeriksa1, String nama_pemeriksa2,String status) {
        this.id = id;
        this.no_surat = no_surat;
        this.id_pemeriksa1 = id_pemeriksa1;
        this.id_pemeriksa2 = id_pemeriksa2;
        this.id_staff = id_staff;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.nama_pemeriksa1= nama_pemeriksa1;
        this.nama_pemeriksa2= nama_pemeriksa2;
        this.status= status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNo_surat() {
        return no_surat;
    }

    public void setNo_surat(String no_surat) {
        this.no_surat = no_surat;
    }

    public String getId_pemeriksa1() {
        return id_pemeriksa1;
    }

    public void setId_pemeriksa1(String id_pemeriksa1) {
        this.id_pemeriksa1 = id_pemeriksa1;
    }

    public String getId_pemeriksa2() {
        return id_pemeriksa2;
    }

    public void setId_pemeriksa2(String id_pemeriksa2) {
        this.id_pemeriksa2 = id_pemeriksa2;
    }

    public String getId_staff() {
        return id_staff;
    }

    public void setId_staff(String id_staff) {
        this.id_staff = id_staff;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getNama_pemeriksa1() {
        return nama_pemeriksa1;
    }

    public void setNama_pemeriksa1(String nama_pemeriksa1) {
        this.nama_pemeriksa1 = nama_pemeriksa1;
    }

    public String getNama_pemeriksa2() {
        return nama_pemeriksa2;
    }

    public void setNama_pemeriksa2(String nama_pemeriksa2) {
        this.nama_pemeriksa2 = nama_pemeriksa2;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



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
