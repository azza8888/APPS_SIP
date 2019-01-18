package com.ezatech.apps_sip.pengaturan;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ezatech.apps_sip.MainActivity;
import com.ezatech.apps_sip.R;
import com.ezatech.apps_sip.logRes.LoginActivity;

public class PengaturanActivity extends AppCompatActivity {

    private Toolbar mActionToolbar;
    private TextView tvTentang;
    private TextView tvInfo;
    private TextView tvKeluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan);

        mActionToolbar = (Toolbar) findViewById(R.id.tabs_pengaturan);
        setSupportActionBar(mActionToolbar);
        getSupportActionBar().setTitle("Pengaturan");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        tvTentang = (TextView) findViewById(R.id.tv_tentang);
        tvInfo = (TextView) findViewById(R.id.tv_info);
        tvKeluar = (TextView) findViewById(R.id.tv_keluar);

        tvKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PengaturanActivity.this);
                builder.setTitle("Aplikasi SIP");
                builder.setMessage("Jika anda ingin keluar takan (YA) !!!")
                        .setIcon(android.R.drawable.ic_lock_power_off)
                        .setCancelable(false)
                        .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                    Intent intent = new Intent(PengaturanActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                }

                            }
                        }).setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    //button back toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

}