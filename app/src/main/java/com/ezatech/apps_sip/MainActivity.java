package com.ezatech.apps_sip;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ezatech.apps_sip.notifLaporan.ListLaporanActivity;
import com.ezatech.apps_sip.pengaturan.PengaturanActivity;
import com.ezatech.apps_sip.pengguna.ProfilActivity;
import com.ezatech.apps_sip.riwayatNotif.RiwayatActivity;
import com.ezatech.apps_sip.uploadLaporan.FormActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnLapor;
    private TextView feedback2;
    private LinearLayout riwayatNotifikasi;
    private LinearLayout konfirmasiPelapor;
    private LinearLayout pengguna;
    private LinearLayout pengaturan;
    private Toolbar mActionToolbar;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActionToolbar = (Toolbar) findViewById(R.id.tabs_MainActivity);
        setSupportActionBar(mActionToolbar);
        getSupportActionBar().setTitle("Menu");

//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//            getSupportActionBar().setDisplayShowHomeEnabled(false);
//        }

        btnLapor = (Button) findViewById(R.id.btn_lapor);
        feedback2 = (TextView) findViewById(R.id.feedback2);
        riwayatNotifikasi = (LinearLayout) findViewById(R.id.riwayat_notifikasi);
        konfirmasiPelapor = (LinearLayout) findViewById(R.id.konfirmasi_pelapor);
        pengguna = (LinearLayout) findViewById(R.id.pengguna);
        pengaturan = (LinearLayout) findViewById(R.id.pengaturan);

        pengguna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfilActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        pengaturan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PengaturanActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        riwayatNotifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RiwayatActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        konfirmasiPelapor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListLaporanActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        btnLapor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(MainActivity.this, FormActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }


//     //button exit
    @Override
    public void onBackPressed() {
    if (doubleBackToExitPressedOnce) {
        super.onBackPressed();
        finishAffinity();
        return;
    }

    this.doubleBackToExitPressedOnce = true;
    Toast.makeText(this, "Tekan kembali lagi untuk keluar.", Toast.LENGTH_SHORT).show();

    new Handler().postDelayed(new Runnable() {

        @Override
        public void run() {
            doubleBackToExitPressedOnce = false;
        }
    }, 2000);
}

}
