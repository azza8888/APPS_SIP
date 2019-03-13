package com.ezatech.apps_sip.notifLaporan;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.ezatech.apps_sip.R;
import com.ezatech.apps_sip.adapter.AdapterLLaporan;
import com.ezatech.apps_sip.adapter.RiwayatAdapter;
import com.ezatech.apps_sip.api.BaseApi;
import com.ezatech.apps_sip.api.RetrofitClient;
import com.ezatech.apps_sip.data.FeedbackData;
import com.ezatech.apps_sip.data.ListLaporan;
import com.ezatech.apps_sip.logRes.LoginActivity;
import com.ezatech.apps_sip.riwayatNotif.RiwayatActivity;
import com.ezatech.apps_sip.uploadLaporan.FormActivity;
import com.squareup.picasso.Picasso;

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
import static com.ezatech.apps_sip.logRes.LoginActivity.session_status;

public class ListLaporanActivity extends AppCompatActivity {

    private ArrayList<ListLaporan> listlaporan;
    private RecyclerView recyclerView;
    private Toolbar mActionToolbar;
    private Context context;
    private SharedPreferences sharedpreferences;
    private String token;
    private String id_surat;
    private String nama1;
    private String nama2;
    private String no_surat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_laporan);

        mActionToolbar = (Toolbar) findViewById(R.id.tabs_listlaporan);
        setSupportActionBar(mActionToolbar);
        getSupportActionBar().setTitle("Surat Perintah");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }




        sharedpreferences = getSharedPreferences(my_shared_preferences, MODE_PRIVATE);
        token = sharedpreferences.getString("acces_token","");
        id_surat = sharedpreferences.getString("id","");


        recyclerView = findViewById(R.id.rv_listlaporan);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager mLayoutManager;
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        listlaporan = new ArrayList<>();

        getData();


    }

    private void getData() {
        BaseApi api = RetrofitClient.getInstanceRetrofit();
        api.suratPerintah(token).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONArray jsonArray = new JSONArray(response.body().string());

                        for (int i= 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.optJSONObject(i);
                            String id_surat = jsonObject.optString("id");
                            String no_surat = jsonObject.optString("no_surat");

                            JSONObject object1 = jsonObject.optJSONObject("pemeriksa1");
                            String nama1 = object1.getString("nama");

                            JSONObject object2 = jsonObject.optJSONObject("pemeriksa2");
                            String nama2 = object2.getString("nama");

                            SharedPreferences sharedPreferences = ListLaporanActivity.this.getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean(session_status, true);
                            editor.putString("id", id_surat);
                            editor.putString("nama", nama1);
                            editor.putString("nama", nama2);
                            editor.commit();

                            ListLaporan laporan = new ListLaporan();
                            laporan.setId(id_surat);
                            laporan.setNo_surat(no_surat);
                            laporan.setNama_pemeriksa1(nama1);
                            laporan.setNama_pemeriksa2(nama2);
                            listlaporan.add(laporan);
                            AdapterLLaporan adapterLLaporan = new AdapterLLaporan(ListLaporanActivity.this, listlaporan);
                            recyclerView.setAdapter(adapterLLaporan);

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

    public void functionToRun() {

//        Intent intent = new Intent(ListLaporanActivity.this, DetailLapActivity.class);
//        startActivity(intent);

        Uri uriUrl = Uri.parse("https://slo.sertifikasiinstalasiprima.co.id/pdf/surat-tugas/print/"+id_surat);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

//    public void intentPeriksa(){
//        Intent intent = new Intent(ListLaporanActivity.this, DetailLapActivity.class);
//        intent.putExtra("id", id.trim());
//        startActivity(intent);
////        startActivity(new Intent(ListLaporanActivity.this, FormActivity.class));
//    }

}
