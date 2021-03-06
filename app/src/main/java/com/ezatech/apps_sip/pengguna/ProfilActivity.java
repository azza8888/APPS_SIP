package com.ezatech.apps_sip.pengguna;

import android.app.Dialog;
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

import com.ezatech.apps_sip.R;
import com.ezatech.apps_sip.api.BaseApi;
import com.ezatech.apps_sip.api.RetrofitClient;
import com.ezatech.apps_sip.api.UtilsApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ezatech.apps_sip.logRes.LoginActivity.my_shared_preferences;

public class ProfilActivity extends AppCompatActivity {

    private Toolbar mActionToolbar;
    private ProgressDialog progressDialog;
    private SharedPreferences sharedpreferences;
    private EditText etTokenpsr;
    private EditText etIdprs;
    private EditText etNip;
    private EditText etNamap, etUsername;
    private EditText etEmailp;
    private EditText etJabatan;
    private EditText etDepartemen;
    private EditText etWewenang;
    private Button btnSimpanpsr, btnEdit;
    private BaseApi baseApi;
    private static String session_status;
    private String TAG;private String nip;
    private String nama;
    private String email;
    private String jabatan;
    private String departemen;
    private String wewenang;
    private String id_Kwilayah;
    private String id_Karea;
    private String id_Subarea;
    private String status;
    private String email_verified_at;
    private String created_at;
    private String update_at;
    private String access_token;
    private CircleImageView cvProfilsr;
    private BaseApi BaseApi;

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
        cvProfilsr = (CircleImageView) findViewById(R.id.cv_profilsr);
        etNip = (EditText) findViewById(R.id.et_nip);
        etNamap = (EditText) findViewById(R.id.et_namap);
        etUsername = (EditText) findViewById(R.id.et_username);
        etEmailp = (EditText) findViewById(R.id.et_emailp);
        etJabatan = (EditText) findViewById(R.id.et_jabatan);
        etDepartemen = (EditText) findViewById(R.id.et_departemen);
        etWewenang = (EditText) findViewById(R.id.et_wewenang);
        btnSimpanpsr = (Button) findViewById(R.id.btn_simpanpsr);
//        btnEdit = (Button) findViewById(R.id.btn_edit);

//        baseApi = UtilsApi.getAPIService();
        sharedpreferences = getSharedPreferences(my_shared_preferences, MODE_PRIVATE);
        String id = (sharedpreferences.getString("id", ""));
        String nip = (sharedpreferences.getString("nip", ""));
        String nama = (sharedpreferences.getString("nama", ""));
        String username = (sharedpreferences.getString("username", ""));
        String email = (sharedpreferences.getString("email", ""));
        String departemen = (sharedpreferences.getString("departemen", ""));
        String jabatan = (sharedpreferences.getString("jabatan", ""));
        String wewenang = (sharedpreferences.getString("wewenang", ""));
        access_token = (sharedpreferences.getString("acces_token", ""));
        tampilData();

        btnSimpanpsr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnSimpanpsr.getText().toString().contains("EDIT")){
                    etNip.setEnabled(true);
                    etNip.setError("Ubah NIP Anda ?");
                    etUsername.setEnabled(true);
                    etUsername.setError("Ubah Username Anda?");
                    etNamap.setEnabled(true);
                    etNamap.setError("Ubah Nama Anda?");
                    btnSimpanpsr.setText("SIMPAN");
                } else if (!btnSimpanpsr.getText().toString().contains("EDIT")){
                    btnSimpanpsr.setText("EDIT");
                    ubahData();
                }else {
                    btnSimpanpsr.getText().toString().contains("EDIT");
                    etNip.setEnabled(false);
                    etUsername.setEnabled(false);
                    etNamap.setEnabled(false);
                     btnSimpanpsr.setText("SIMPAN");

                }

            }
        });



    }

    private void ubahData() {
        BaseApi baseApi = RetrofitClient.getInstanceRetrofit();
        baseApi.editProfile(access_token,etNamap.getText().toString().trim(), etUsername.getText().toString().trim(), etNip.getText().toString().trim())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            try {
                                JSONObject jsonObject = new JSONObject(response.body().string());
                                String msg = jsonObject.optString("msg");
                                Toast.makeText(ProfilActivity.this, "" + msg, Toast.LENGTH_SHORT).show();
                                etNip.setEnabled(false);
                                etUsername.setEnabled(false);
                                etNamap.setEnabled(false);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(ProfilActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void tampilData() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon tunggu...");
        progressDialog.show();
        BaseApi baseApi = RetrofitClient.getInstanceRetrofit();
        baseApi.profile(access_token).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        progressDialog.dismiss();
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        String id = jsonObject.getString("id");
                        String nip = jsonObject.getString("nip");
                        String nama = jsonObject.getString("nama");
                        String email = jsonObject.getString("email");
                        String jabatan = jsonObject.getString("jabatan");
                        String departemen = jsonObject.getString("departemen");
                        String wewenang = jsonObject.getString("wewenang");
                        String id_kwilayah = jsonObject.optString("id_kwilayah");
                        String id_karea = jsonObject.optString("id_karea");
                        String id_subarea = jsonObject.optString("id_subarea");
                        String status = jsonObject.optString("status");
                        String email_verified_at = jsonObject.optString("email_verified_at");
                        String created_at = jsonObject.optString("created_at");
                        String updated_at = jsonObject.optString("updated_at");
                        String username = jsonObject.getString("username");


                        etIdprs.setText(id);
                        etNip.setText(nip);
                        etNamap.setText(nama);
                        etUsername.setText(username);
                        etEmailp.setText(email);
                        etDepartemen.setText(departemen);
                        etJabatan.setText(jabatan);
                        etWewenang.setText(wewenang);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(ProfilActivity.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(ProfilActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
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
