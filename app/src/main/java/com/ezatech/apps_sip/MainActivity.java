package com.ezatech.apps_sip;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ezatech.apps_sip.notifLaporan.ListLaporanActivity;
import com.ezatech.apps_sip.pengaturan.PengaturanActivity;
import com.ezatech.apps_sip.pengguna.ProfilActivity;
import com.ezatech.apps_sip.riwayatNotif.RiwayatActivity;
import com.ezatech.apps_sip.slide.SlideScreenActivity;
import com.ezatech.apps_sip.uploadLaporan.FormActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnLapor;
    private TextView feedback2;
    private LinearLayout riwayatNotifikasi;
    private LinearLayout konfirmasiPelapor;
    private LinearLayout pengguna;
    private LinearLayout pengaturan;
    private EditText editTextKonfirm;
    private EditText editTextTitle;
    private EditText editTextMessage;
    private EditText editTextTelp;
    private EditText editTextWaktu;
    private EditText editTextJam;
    private Toolbar mActionToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActionToolbar = (Toolbar) findViewById(R.id.tabs_MainActivity);
        setSupportActionBar(mActionToolbar);
        getSupportActionBar().setTitle("Menu");

        btnLapor = (Button) findViewById(R.id.btn_lapor);
        feedback2 = (TextView) findViewById(R.id.feedback2);
        riwayatNotifikasi = (LinearLayout) findViewById(R.id.riwayat_notifikasi);
        konfirmasiPelapor = (LinearLayout) findViewById(R.id.konfirmasi_pelapor);
        pengguna = (LinearLayout) findViewById(R.id.pengguna);
        pengaturan = (LinearLayout) findViewById(R.id.pengaturan);
        editTextKonfirm = (EditText) findViewById(R.id.editTextKonfirm);
        editTextTitle = (EditText) findViewById(R.id.editTextTitle);
        editTextMessage = (EditText) findViewById(R.id.editTextMessage);
        editTextTelp = (EditText) findViewById(R.id.editTextTelp);
        editTextWaktu = (EditText) findViewById(R.id.editTextWaktu);
        editTextJam = (EditText) findViewById(R.id.editTextJam);

        pengguna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfilActivity.class);
                startActivity(intent);
            }
        });

        pengaturan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PengaturanActivity.class);
                startActivity(intent);
            }
        });

        riwayatNotifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RiwayatActivity.class);
                startActivity(intent);
            }
        });
        konfirmasiPelapor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListLaporanActivity.class);
                startActivity(intent);
            }
        });

        btnLapor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                startActivity(intent);
            }
        });
    }


}
