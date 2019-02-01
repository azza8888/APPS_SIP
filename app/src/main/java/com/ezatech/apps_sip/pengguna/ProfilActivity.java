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
    private EditText etNamap;
    private EditText etEmailp;
    private EditText etJabatan;
    private EditText etDepartemen;
    private EditText etWewenang;
    private Button btnSimpanpsr, btnEdit;
    private BaseApi baseApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        etTokenpsr = (EditText) findViewById(R.id.et_tokenpsr);
        etIdprs = (EditText) findViewById(R.id.et_idprs);
        etNip = (EditText) findViewById(R.id.et_nip);
        etNamap = (EditText) findViewById(R.id.et_namap);
        etEmailp = (EditText) findViewById(R.id.et_emailp);
        etJabatan = (EditText) findViewById(R.id.et_jabatan);
        etDepartemen = (EditText) findViewById(R.id.et_departemen);
        etWewenang = (EditText) findViewById(R.id.et_wewenang);
        btnSimpanpsr = (Button) findViewById(R.id.btn_simpanpsr);
        btnEdit = (Button) findViewById(R.id.btn_edit);

        mActionToolbar = (Toolbar) findViewById(R.id.tabs_profils);
        setSupportActionBar(mActionToolbar);
        getSupportActionBar().setTitle("Profil Anda");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        baseApi = UtilsApi.getAPIService();
        btnEdit.setVisibility(View.VISIBLE);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSimpanpsr.setVisibility(View.VISIBLE);
                btnEdit.setVisibility(View.INVISIBLE);
                etNip.setEnabled(true);
                etEmailp.setEnabled(true);
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
                etEmailp.setEnabled(false);
                etNamap.setEnabled(false);
//                UbahData();
                btnEdit.setEnabled(true);
            }
        });

        sharedpreferences = getSharedPreferences(my_shared_preferences, MODE_PRIVATE);
        String id =(sharedpreferences.getString("id",""));
        etIdprs.setText(id);
        String nip = (sharedpreferences.getString("nip", ""));
        etNip.setText(nip);
        String nama = (sharedpreferences.getString("nama", ""));
        etNamap.setText(nama);
        String email = (sharedpreferences.getString("email", ""));
        etEmailp.setText(email);
        String departemen = (sharedpreferences.getString("departemen", ""));
        etDepartemen.setText(departemen);
        String jabatan = (sharedpreferences.getString("jabatan", ""));
        etJabatan.setText(jabatan);
        String wewenang = (sharedpreferences.getString("wewenang",""));
        etWewenang.setText(wewenang);
        String token = (sharedpreferences.getString("access_token",""));
        etTokenpsr.setText(token);

    }

    private void UbahData() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Tunggu. . .");
        progressDialog.show();
        final String token = etTokenpsr.getText().toString();
        final String id = etIdprs.getText().toString();
        final String nip = etNip.getText().toString();
        final String nama = etNamap.getText().toString();
        final String email = etEmailp.getText().toString();
        final String jabatan = etJabatan.getText().toString();
        final String departemen = etDepartemen.getText().toString();
        final String wewenang = etWewenang.getText().toString();

        baseApi.profile(id,etNip.getText().toString(),
                etNamap.getText().toString(),
                etEmailp.getText().toString(),
                etJabatan.getText().toString(),
                etDepartemen.getText().toString(),
                etWewenang.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    progressDialog.dismiss();
                    SharedPreferences sp = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean(session_status, true);
                    editor.putString("nip", nip);
                    editor.putString("nama", nama);
                    editor.putString("email", email);
                    editor.putString("jabatan", jabatan);
                    editor.putString("departemen", departemen);
                    editor.putString("wewenang", wewenang);
                    editor.commit();
                    Toast.makeText(ProfilActivity.this, ("Edit Profil Berhasil"), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ProfilActivity.this, ProfilActivity.class);
                    startActivity(intent);
                }
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
