package com.ezatech.apps_sip;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.ezatech.apps_sip.notifLaporan.ListLaporanActivity;
import com.ezatech.apps_sip.pengaturan.PengaturanActivity;
import com.ezatech.apps_sip.pengguna.ProfilActivity;
import com.ezatech.apps_sip.riwayatNotif.RiwayatActivity;
import com.ezatech.apps_sip.uploadLaporan.FormActivity;

import java.util.HashMap;

import static com.ezatech.apps_sip.logRes.LoginActivity.my_shared_preferences;

public class MainActivity extends AppCompatActivity {


    private LinearLayout riwayatNotifikasi;
    private SliderLayout sliderLayout;
    private LinearLayout konfirmasiPelapor;
    private LinearLayout pengguna;
    private LinearLayout pengaturan;
    private Toolbar mActionToolbar;
    boolean doubleBackToExitPressedOnce = false;
    private SharedPreferences sharedpreferences;
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActionToolbar = (Toolbar) findViewById(R.id.tabs_MainActivity);
        setSupportActionBar(mActionToolbar);
        getSupportActionBar().setTitle("Aplikasi SIP");

//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//            getSupportActionBar().setDisplayShowHomeEnabled(false);
//        }

        sharedpreferences = getSharedPreferences(my_shared_preferences, MODE_PRIVATE);
        String id =(sharedpreferences.getString("id",""));

        String nip = (sharedpreferences.getString("nip", ""));

        String nama = (sharedpreferences.getString("nama", ""));
        String username = (sharedpreferences.getString("username",""));
        String email = (sharedpreferences.getString("email", ""));
        String departemen = (sharedpreferences.getString("departemen", ""));
        String jabatan = (sharedpreferences.getString("jabatan", ""));
        String wewenang = (sharedpreferences.getString("wewenang",""));
        String access_token = (sharedpreferences.getString("acces_token",""));

        Log.d(TAG, "TTTTTT: "+access_token);


//        btnLapor = (Button) findViewById(R.id.btn_lapor);
//        feedback2 = (TextView) findViewById(R.id.feedback2);
        riwayatNotifikasi = (LinearLayout) findViewById(R.id.riwayat_notifikasi);
        konfirmasiPelapor = (LinearLayout) findViewById(R.id.konfirmasi_pelapor);
        pengguna = (LinearLayout) findViewById(R.id.pengguna);
        pengaturan = (LinearLayout) findViewById(R.id.pengaturan);
        sliderLayout = (SliderLayout) findViewById(R.id.slider);

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Aplikasi SIP",R.drawable.logosiiip);
        file_maps.put("Icon Badai",R.drawable.storm1);
        file_maps.put("Icon Petir",R.drawable.thunder);
        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Left_Top);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());

        sliderLayout.setDuration(4000);

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

//        btnLapor.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Intent intent = new Intent(MainActivity.this, FormActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent);
//            }
//        });
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
