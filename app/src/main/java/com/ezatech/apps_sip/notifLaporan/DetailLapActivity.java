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
    private String id;
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
        id     = bundle.getString("id");
//        no_pend = bundle.getString("no_pendaftaran");

        sharedpreferences = getSharedPreferences(my_shared_preferences, MODE_PRIVATE);
        token = sharedpreferences.getString("acces_token","");
        id_penerbit = sharedpreferences.getString("id","");
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
        api.detailPendaftar(token,id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONArray jsonArray = new JSONArray(response.body().string());
                        for (int i = 0 ; i<jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.optJSONObject(i);
                            String id_penerbit = jsonObject.getString("id");

                            JSONObject object = jsonObject.optJSONObject("pelanggan");
                            String no_pend = object.getString("no_pendaftaran");

                            String nama_pend = object.getString("nama");
                            String alamat_pend = object.getString("alamat");

                            JSONObject object1 = jsonObject.optJSONObject("tarif");
                            String tarif_pend = object1.getString("jenis_tarif");

                            JSONObject object2 = jsonObject.optJSONObject("daya");
                            String daya_pend = object2.getString("daya");

                            JSONObject object3 = jsonObject.optJSONObject("penyedia");
                            String btl_pend = object3.getString("nama_penyedia");

                            Pelanggan pelanggan = new Pelanggan();
                            pelanggan.setId_penerbit(id_penerbit);
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