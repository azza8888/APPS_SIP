package com.ezatech.apps_sip.pengguna;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ezatech.apps_sip.logRes.LoginActivity.my_shared_preferences;
import static com.ezatech.apps_sip.logRes.LoginActivity.session_status;

public class EditProfilActivity extends AppCompatActivity {

    private Toolbar tabsEdtprofile;
    private BaseApi api;
    private EditText edtToken;
    private EditText edtId;
    private EditText edtNama;
    private EditText edtUsername;
    private EditText edtNip;
    private Button btnEdtsimpan;
    private Toolbar mActionToolbar;
    private SharedPreferences sharedpreferences;
    private ProgressDialog progressDialog;
    private String token;
    private int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);

        edtToken = (EditText) findViewById(R.id.edt_token);
        edtId = (EditText) findViewById(R.id.edt_id);
        edtNama = (EditText) findViewById(R.id.edt_nama);
        edtUsername = (EditText) findViewById(R.id.edt_username);
        edtNip = (EditText) findViewById(R.id.edt_nip);
        btnEdtsimpan = (Button) findViewById(R.id.btn_edtsimpan);



        mActionToolbar = (Toolbar) findViewById(R.id.tabs_edtprofile);
        setSupportActionBar(mActionToolbar);
        getSupportActionBar().setTitle("Edit Profil Anda");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        if (ContextCompat.checkSelfPermission(EditProfilActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //
            if (ActivityCompat.shouldShowRequestPermissionRationale(EditProfilActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {

                ActivityCompat.requestPermissions(EditProfilActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_FINE_LOCATION);

            }
        }



        sharedpreferences = getSharedPreferences(my_shared_preferences, MODE_PRIVATE);
        String id =(sharedpreferences.getString("id",""));
        edtId.setText(id);
        String nip = (sharedpreferences.getString("nip", ""));
        edtNip.setText(nip);
        String nama = (sharedpreferences.getString("nama", ""));
        edtNama.setText(nama);
        String username = (sharedpreferences.getString("username",""));
        edtUsername.setText(username);
        token = (sharedpreferences.getString("acces_token", ""));
        edtToken.setText(token);


//        api = UtilsApi.getAPIService();

        btnEdtsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                UbahData();
                ubahData();
            }
        });
    }

    private void ubahData() {
        BaseApi baseApi = RetrofitClient.getInstanceRetrofit();
        baseApi.editProfile(token,edtNama.getText().toString().trim(), edtUsername.getText().toString().trim(), edtNip.getText().toString().trim())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            try {
                                JSONObject jsonObject = new JSONObject(response.body().string());
                                String msg = jsonObject.optString("msg");
                                Toast.makeText(EditProfilActivity.this, "" + msg, Toast.LENGTH_SHORT).show();

                                Toast.makeText(EditProfilActivity.this, "Edit Profil Berhasil", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(EditProfilActivity.this, ProfilActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(EditProfilActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void UbahData() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon tunggu...");
        progressDialog.show();
        final String token = edtToken.getText().toString();
        final String nama = edtNama.getText().toString();
        final String username = edtUsername.getText().toString();
        final String nip = edtNip.getText().toString();

        api.editProfile(token,
                edtNama.getText().toString(),
                edtUsername.getText().toString(),
                edtNip.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                        String token = response.headers().get("acces_token");
                        if (response.isSuccessful()){
                            progressDialog.dismiss();

                                SharedPreferences sp = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putBoolean(session_status, true);
                                editor.putString("nama", nama);
                                editor.putString("username", username);
                                editor.putString("nip", nip);
                                editor.commit();

                                Toast.makeText(EditProfilActivity.this, "Edit Profil Berhasil", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(EditProfilActivity.this, ProfilActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);

                                Log.d("HAHAHAH", "HAHAHAHHA: "+token);


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
