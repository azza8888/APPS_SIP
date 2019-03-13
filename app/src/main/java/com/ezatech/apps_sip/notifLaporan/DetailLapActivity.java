package com.ezatech.apps_sip.notifLaporan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_lap);

        mActionToolbar = (Toolbar) findViewById(R.id.tabs_detaill);
        setSupportActionBar(mActionToolbar);
        getSupportActionBar().setTitle("Detail Laporan");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Bundle bundle = getIntent().getExtras();
        id_surat     = bundle.getString("id");
        Log.d("", "3333333333: "+id_surat);

        sharedpreferences = getSharedPreferences(my_shared_preferences, MODE_PRIVATE);
        token = sharedpreferences.getString("acces_token","");
        no_pend = sharedpreferences.getString("no_pendaftaran","");

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
        api.detailPendaftar(token,id_surat).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonObject1 = new JSONObject(response.body().string());
                        JSONArray jsonObject3 = jsonObject1.optJSONArray("pelanggan");

//                            JSONArray jsonArray = new JSONArray(response.body().string());
                        for (int i = 0 ; i<jsonObject3.length(); i++){
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
        if (item.getItemId()== android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }


//    public void goToPerksa() {
//        Intent intent = new Intent(DetailLapActivity.this, FormActivity.class);
////        intent.putExtra("no_pendaftaran", no_pend.trim());
////        Log.d("", "goToPerksa1111111: "+no_pend);
//        startActivity(intent);
//    }
}