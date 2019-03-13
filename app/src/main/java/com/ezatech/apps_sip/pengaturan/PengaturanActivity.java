package com.ezatech.apps_sip.pengaturan;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ezatech.apps_sip.MainActivity;
import com.ezatech.apps_sip.R;
import com.ezatech.apps_sip.api.BaseApi;
import com.ezatech.apps_sip.api.RetrofitClient;
import com.ezatech.apps_sip.api.UtilsApi;
import com.ezatech.apps_sip.logRes.LoginActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ezatech.apps_sip.logRes.LoginActivity.my_shared_preferences;

public class PengaturanActivity extends AppCompatActivity {

    private Toolbar mActionToolbar;
    private CardView tvTentang;
    private CardView tvInfo, tvGantipass;
    private TextView tvKeluar;
    private String token;
    private EditText tv_tokenext;
    private BaseApi baseApi;
    private SharedPreferences sharedpreferences;

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

        tvTentang = (CardView) findViewById(R.id.tv_tentang);
        tv_tokenext =(EditText) findViewById(R.id.et_tokenext);
        tvInfo = (CardView) findViewById(R.id.tv_info);
        tvGantipass = (CardView) findViewById(R.id.tv_gantipass);
        tvKeluar = (TextView) findViewById(R.id.tv_keluar);
//        baseApi = UtilsApi.getAPIService();

        sharedpreferences = getSharedPreferences(my_shared_preferences, MODE_PRIVATE);
        String token = (sharedpreferences.getString("acces_token",""));
        tv_tokenext.setText(token);


        tvKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(PengaturanActivity.this);
                builder.setTitle("Ingin Keluar dari Akun ini?")
                        .setIcon(android.R.drawable.ic_lock_power_off)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                keluar();
                            }
                        }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
            }
        });

        tvTentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PengaturanActivity.this, TentangActivity.class);
                startActivity(intent);
            }
        });

        tvInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PengaturanActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        });
        tvGantipass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PengaturanActivity.this, GantiPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    private void keluar() {
        baseApi = RetrofitClient.getInstanceRetrofit();
        final String token = tv_tokenext.getText().toString();
        baseApi.logOut(token).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                SharedPreferences sharedPreferences = PengaturanActivity.this.getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);

                //Creating editor to store values to shared preferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(LoginActivity.session_status, false);
                editor.putString("id", null);
                editor.putString("nip", null);
                editor.putString("nama", null);
                editor.putString("email", null);
                editor.putString("jabatan", null);
                editor.putString("departemen", null);
                editor.putString("wewenang", null);
                editor.putString("id_Kwilayah", null);
                editor.putString("id_Karea", null);
                editor.putString("id_Subarea", null);
                editor.putString("status", null);
                editor.putString("email_verified_at", null);
                editor.putString("created_at", null);
                editor.putString("update_at", null);
                editor.putString("username", null);
                editor.putString("acccess_token", null);
                editor.clear();
                editor.commit();
                finish();

                Intent intent1 = new Intent(PengaturanActivity.this, LoginActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent1);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

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



}