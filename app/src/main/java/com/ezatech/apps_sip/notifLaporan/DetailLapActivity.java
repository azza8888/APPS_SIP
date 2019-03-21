package com.ezatech.apps_sip.notifLaporan;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ezatech.apps_sip.R;
import com.ezatech.apps_sip.adapter.AdapterDetailPel;
import com.ezatech.apps_sip.api.BaseApi;
import com.ezatech.apps_sip.api.RetrofitClient;
import com.ezatech.apps_sip.data.Pelanggan;
import com.ezatech.apps_sip.uploadLaporan.FormActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import pl.aprilapps.easyphotopicker.EasyImage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ezatech.apps_sip.logRes.LoginActivity.my_shared_preferences;

public class DetailLapActivity extends AppCompatActivity {

    private ArrayList<Pelanggan> pelanggans;
    private RecyclerView recyclerView;
    private Toolbar mActionToolbar;
    private Context context;
    private String id_penerbit;
    private String no_pend;
    private String nama_pend;
    private String alamat_pend;
    private String tarif_pend;
    private String daya_pend;
    private String btl_pend;
    private String id_surat;
    private SharedPreferences sharedpreferences;
    private String token;
    private String jumlah = "0";
    private Button btnSimpanDetail;
    private Button btnSelesaikan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_lap);


        btnSelesaikan = (Button) findViewById(R.id.btn_selesaikan);
        mActionToolbar = (Toolbar) findViewById(R.id.tabs_detaill);
        setSupportActionBar(mActionToolbar);
        getSupportActionBar().setTitle("Detail Pelanggan");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Bundle bundle = getIntent().getExtras();
        id_surat = bundle.getString("id");


        sharedpreferences = getSharedPreferences(my_shared_preferences, MODE_PRIVATE);
        token = sharedpreferences.getString("acces_token", "");
        no_pend = sharedpreferences.getString("no_pendaftaran", "");

        recyclerView = findViewById(R.id.rv_listdetailpel);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        pelanggans = new ArrayList<>();

        getDetail();

    }

    private void getDetail() {
        BaseApi api = RetrofitClient.getInstanceRetrofit();
        api.detailPendaftar(token, id_surat).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject1 = new JSONObject(response.body().string());
                        String jml = jsonObject1.getString("jml");
                        if (jml == jumlah) {
                            btnSelesaikan.setVisibility(View.VISIBLE);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                if (ContextCompat.checkSelfPermission(DetailLapActivity.this,
                                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                                        ContextCompat.checkSelfPermission(DetailLapActivity.this,
                                                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {


                                } else {
                                    Toast.makeText(DetailLapActivity.this, "Mengijinkan Permission", Toast.LENGTH_SHORT).show();
                                    EasyImage.openCamera(DetailLapActivity.this, 0);

                                }
                            }
                            btnSelesaikan.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    BaseApi api = RetrofitClient.getInstanceRetrofit();
                                    api.konformasiDone(token, id_surat).enqueue(new Callback<ResponseBody>() {
                                        @Override
                                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                            try {
                                                JSONObject object = new JSONObject(response.body().string());
                                                String message = object.getString("message");
                                                Intent intent = new Intent(DetailLapActivity.this, ListLaporanActivity.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(intent);
                                                Toast.makeText(DetailLapActivity.this, "" + message, Toast.LENGTH_SHORT).show();

                                            }

                                            catch (JSONException e) {
                                                e.printStackTrace();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                                        }
                                    });
                                }
                            });
                        }
                        JSONArray jsonObject3 = jsonObject1.optJSONArray("pelanggan");

                        for (int i = 0; i < jsonObject3.length(); i++) {
                            JSONObject object = jsonObject3.getJSONObject(i);
                            String id_surat = object.getString("id");
                            String pemeriksa1 = object.getString("pemeriksa1");
                            String pemeriksa2 = object.getString("pemeriksa2");
                            String no_pend = object.getString("no_pendaftaran");
                            String nama_pend = object.getString("nama");
                            String alamat_pend = object.getString("alamat");
                            String tarif_pend = object.getString("jenis_tarif");
                            String daya_pend = object.getString("daya");
                            String btl_pend = object.getString("nama_penyedia");
                            String nama_btl = object.getString("nama_btl");


                            Pelanggan pelanggan = new Pelanggan();
                            pelanggan.setId_penerbit(id_surat);
                            pelanggan.setNo_pendaftaran(no_pend);
                            pelanggan.setNama(nama_pend);
                            pelanggan.setAlamat(alamat_pend);
                            pelanggan.setJenis_tarif(tarif_pend);
                            pelanggan.setDaya(daya_pend);
                            pelanggan.setNama_penyedia(btl_pend);
                            pelanggans.add(pelanggan);
                            AdapterDetailPel adapterDetailPel = new AdapterDetailPel(DetailLapActivity.this, pelanggans);
                            recyclerView.setAdapter(adapterDetailPel);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    //button back toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

}