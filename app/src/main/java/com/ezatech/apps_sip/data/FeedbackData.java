package com.ezatech.apps_sip.data;

/**
 * Created by HIM on 08/02/2018.
 */

public class FeedbackData {

    String id, nama, konfirm, waktu;

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
