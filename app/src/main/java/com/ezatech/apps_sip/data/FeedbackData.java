package com.ezatech.apps_sip.data;

/**
 * Created by HIM on 08/02/2018.
 */

public class FeedbackData {

    String id, nama, konfirm, waktu;
    private String id_riwayat;
    private String no_surat_rwt;
    private String id_pemeriksa1_rwt;
    private String id_pemeriksa2_rwt;
    private String id_staff_rwt;
    private String created_at_rwt;
    private String updated_at_rwt;
    private String nama_pemeriksa1_rwt;
    private String nama_pemeriksa2_rwt;
    private String status_rwt;


    public FeedbackData(String id_riwayat, String no_surat_rwt, String id_pemeriksa1_rwt, String id_pemeriksa2_rwt, String id_staff_rwt, String created_at_rwt, String updated_at_rwt, String nama_pemeriksa1_rwt, String nama_pemeriksa2_rwt, String status_rwt) {
        this.id_riwayat = id_riwayat;
        this.no_surat_rwt = no_surat_rwt;
        this.id_pemeriksa1_rwt = id_pemeriksa1_rwt;
        this.id_pemeriksa2_rwt = id_pemeriksa2_rwt;
        this.id_staff_rwt = id_staff_rwt;
        this.created_at_rwt = created_at_rwt;
        this.updated_at_rwt = updated_at_rwt;
        this.nama_pemeriksa1_rwt = nama_pemeriksa1_rwt;
        this.nama_pemeriksa2_rwt = nama_pemeriksa2_rwt;
        this.status_rwt = status_rwt;
    }

    public String getId_riwayat() {
        return id_riwayat;
    }

    public void setId_riwayat(String id_riwayat) {
        this.id_riwayat = id_riwayat;
    }

    public String getNo_surat_rwt() {
        return no_surat_rwt;
    }

    public void setNo_surat_rwt(String no_surat_rwt) {
        this.no_surat_rwt = no_surat_rwt;
    }

    public String getId_pemeriksa1_rwt() {
        return id_pemeriksa1_rwt;
    }

    public void setId_pemeriksa1_rwt(String id_pemeriksa1_rwt) {
        this.id_pemeriksa1_rwt = id_pemeriksa1_rwt;
    }

    public String getId_pemeriksa2_rwt() {
        return id_pemeriksa2_rwt;
    }

    public void setId_pemeriksa2_rwt(String id_pemeriksa2_rwt) {
        this.id_pemeriksa2_rwt = id_pemeriksa2_rwt;
    }

    public String getId_staff_rwt() {
        return id_staff_rwt;
    }

    public void setId_staff_rwt(String id_staff_rwt) {
        this.id_staff_rwt = id_staff_rwt;
    }

    public String getCreated_at_rwt() {
        return created_at_rwt;
    }

    public void setCreated_at_rwt(String created_at_rwt) {
        this.created_at_rwt = created_at_rwt;
    }

    public String getUpdated_at_rwt() {
        return updated_at_rwt;
    }

    public void setUpdated_at_rwt(String updated_at_rwt) {
        this.updated_at_rwt = updated_at_rwt;
    }

    public String getNama_pemeriksa1_rwt() {
        return nama_pemeriksa1_rwt;
    }

    public void setNama_pemeriksa1_rwt(String nama_pemeriksa1_rwt) {
        this.nama_pemeriksa1_rwt = nama_pemeriksa1_rwt;
    }

    public String getNama_pemeriksa2_rwt() {
        return nama_pemeriksa2_rwt;
    }

    public void setNama_pemeriksa2_rwt(String nama_pemeriksa2_rwt) {
        this.nama_pemeriksa2_rwt = nama_pemeriksa2_rwt;
    }

    public String getStatus_rwt() {
        return status_rwt;
    }

    public void setStatus_rwt(String status_rwt) {
        this.status_rwt = status_rwt;
    }



    public FeedbackData() {
    }

    public FeedbackData(String id, String nama, String konfirm, String waktu) {
        this.id = id;
        this.nama = nama;
        this.konfirm = konfirm;
        this.waktu = waktu;
    }

    public String getId() {return id;
    }

    public void setId(String id) {this.id = id;
    }

    public String getNama() {return nama;
    }

    public void setNama(String nama) {this.nama = nama;
    }

    public String getKonfirm() {return konfirm;
    }

    public void setKonfirm(String konfirm) {this.konfirm = konfirm;
    }

    public String getWaktu() {return waktu;
    }

    public void setWaktu(String waktu) {this.waktu = waktu;
    }

}
