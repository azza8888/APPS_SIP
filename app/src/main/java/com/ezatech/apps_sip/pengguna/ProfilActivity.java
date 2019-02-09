package com.ezatech.apps_sip.pengguna;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ezatech.apps_sip.MainActivity;
import com.ezatech.apps_sip.R;
import com.ezatech.apps_sip.api.BaseApi;
import com.ezatech.apps_sip.api.UtilsApi;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ezatech.apps_sip.logRes.LoginActivity.my_shared_preferences;
import static com.ezatech.apps_sip.logRes.LoginActivity.session_status;

public class ProfilActivity extends AppCompatActivity {

    private Toolbar mActionToolbar;
    private ProgressDialog progressDialog;
    private SharedPreferences sharedpreferences;
    private EditText etTokenpsr;
    private EditText etIdprs;
    private EditText etNip;
    private EditText etNamap,etUsername;
    private EditText etEmailp;
    private EditText etJabatan;
    private EditText etDepartemen;
    private EditText etWewenang;
    private Button btnSimpanpsr, btnEdit;
    private BaseApi baseApi;
    private String TAG;
    private String token;
    private String i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        mActionToolbar = (Toolbar) findViewById(R.id.tabs_profils);
        setSupportActionBar(mActionToolbar);
        getSupportActionBar().setTitle("Profil Anda");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        etTokenpsr = (EditText) findViewById(R.id.et_tokenpsr);
        etIdprs = (EditText) findViewById(R.id.et_idprs);
        etNip = (EditText) findViewById(R.id.et_nip);
        etNamap = (EditText) findViewById(R.id.et_namap);
        etUsername= (EditText) findViewById(R.id.et_username);
        etEmailp = (EditText) findViewById(R.id.et_emailp);
        etJabatan = (EditText) findViewById(R.id.et_jabatan);
        etDepartemen = (EditText) findViewById(R.id.et_departemen);
        etWewenang = (EditText) findViewById(R.id.et_wewenang);
        btnSimpanpsr = (Button) findViewById(R.id.btn_simpanpsr);
        btnEdit = (Button) findViewById(R.id.btn_edit);

        sharedpreferences = getSharedPreferences(my_shared_preferences, MODE_PRIVATE);
//        String id =(sharedpreferences.getString("id",""));
//        etIdprs.setText(id);
//        String nip = (sharedpreferences.getString("nip", ""));
//        etNip.setText(nip);
//        String nama = (sharedpreferences.getString("nama", ""));
//        etNamap.setText(nama);
//        String username = (sharedpreferences.getString("username",""));
//        etUsername.setText(username);
//        String email = (sharedpreferences.getString("email", ""));
//        etEmailp.setText(email);
//        String departemen = (sharedpreferences.getString("departemen", ""));
//        etDepartemen.setText(departemen);
//        String jabatan = (sharedpreferences.getString("jabatan", ""));
//        etJabatan.setText(jabatan);
//        String wewenang = (sharedpreferences.getString("wewenang",""));
//        etWewenang.setText(wewenang);
        String access_token = (sharedpreferences.getString("acces_token",""));
        etTokenpsr.setText(access_token);
//
//        Log.d(TAG, "APABBBB: "+access_token);



        baseApi = UtilsApi.getAPIService();
        TampilData();
        btnEdit.setVisibility(View.VISIBLE);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSimpanpsr.setVisibility(View.VISIBLE);
                btnEdit.setVisibility(View.INVISIBLE);
                etNip.setEnabled(true);
                etUsername.setEnabled(true);
                etNamap.setEnabled(true);

            }
        });
        btnSimpanpsr.setVisibility(View.INVISIBLE);
        btnSimpanpsr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEdit.setVisibility(View.VISIBLE);
                btnSimpanpsr.setVisibility(View.INVISIBLE);
                etNip.setEnabled(false);
                etUsername.setEnabled(false);
                etNamap.setEnabled(false);
                btnEdit.setEnabled(true);
                UbahData();
            }
        });



    }

    private void UbahData() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon tunggu...");
        progressDialog.show();
        final String access_token = etTokenpsr.getText().toString();
        final String nip = etNip.getText().toString();
        final String nama = etNamap.getText().toString();
        final String username = etUsername.getText().toString();
        baseApi.EditProfile(
                etNamap.getText().toString(),
                etNip.getText().toString(),
                etUsername.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        progressDialog.dismiss();
                        SharedPreferences sp = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);

                        SharedPreferences.Editor editor = sp.edit();
                        editor.putBoolean(session_status, true);
                        editor.putString("nip", nip);
                        editor.putString("nama", nama);
                        editor.putString("username", username);
                        editor.commit();
                        Toast.makeText(ProfilActivity.this, ("Edit Profil Berhasil"), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ProfilActivity.this, ProfilActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });

    }

    private void TampilData() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.show();
        final String token = etTokenpsr.getText().toString();
        baseApi.profile(token)
                .enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressDialog.dismiss();
//                if (response.isSuccessful()){
                    try {
                        JSONObject object1 = new JSONObject(response.body().string());
//                        JSONObject object1 = new JSONObject("user");
                        String id = object1.optString("id");
                        String nip = object1.optString("nip");
                        String nama = object1.optString("nama");
                        String email = object1.optString("email");
                        String jabatan = object1.optString("jabatan");
                        String departemen = object1.optString("departemen");
                        String wewenang = object1.optString("wewenang");
                        String id_kwilayah = object1.optString("id_kwilayah");
                        String id_karea = object1.optString("id_karea");
                        String id_subarea = object1.optString("id_subarea");
                        String status = object1.optString("status");
                        String email_verified_at = object1.optString("email_verified_at");
                        String created_at = object1.optString("created_at");
                        String updated_at = object1.optString("updated_at");
                        String username = object1.getString("username");

                        etIdprs.setText(id);
                        etNip.setText(nip);
                        etNamap.setText(nama);
                        etUsername.setText(username);
                        etEmailp.setText(email);
                        etDepartemen.setText(departemen);
                        etJabatan.setText(jabatan);
                        etWewenang.setText(wewenang);

                        progressDialog.dismiss();
                        Intent intent = new Intent(String.valueOf(ProfilActivity.this));
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);



                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//                    SharedPreferences sp = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sp.edit();
//                    editor.putBoolean(session_status, true);
//                    editor.putString("nip", nip);
//                    editor.putString("nama", nama);
//                    editor.putString("username", username);
//                    editor.putString("email", email);
//                    editor.putString("jabatan", jabatan);
//                    editor.putString("departemen", departemen);
//                    editor.putString("wewenang", wewenang);
//                    editor.commit();
//                    Toast.makeText(ProfilActivity.this, ("Edit Profil Berhasil"), Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(ProfilActivity.this, ProfilActivity.class);
//                    startActivity(intent);

//                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDialog.dismiss();
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
