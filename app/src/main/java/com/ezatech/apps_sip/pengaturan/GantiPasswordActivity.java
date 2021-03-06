package com.ezatech.apps_sip.pengaturan;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ezatech.apps_sip.R;
import com.ezatech.apps_sip.api.BaseApi;
import com.ezatech.apps_sip.api.RetrofitClient;
import com.ezatech.apps_sip.api.UtilsApi;
import com.ezatech.apps_sip.logRes.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ezatech.apps_sip.logRes.LoginActivity.my_shared_preferences;
import static com.ezatech.apps_sip.logRes.LoginActivity.session_status;

public class GantiPasswordActivity extends AppCompatActivity {

    private EditText etTokencpsr;
    private EditText etIdcpsr;
    private EditText etPasswordsr;
    private EditText etNewpasssr;
    private EditText etConfrimpassr;
    private Button btnSimpanpasssr;
    private Toolbar mActionToolbar;
    private SharedPreferences sharedpreferences;
    private ProgressDialog progressDialog;
    private BaseApi baseApi;
    private String token;
    private String current_password;
    private String new_password;
    private String new_password_confirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganti_password);

//        baseApi = UtilsApi.getAPIService();
        mActionToolbar = (Toolbar) findViewById(R.id.tabs_changepasssr);
        setSupportActionBar(mActionToolbar);
        getSupportActionBar().setTitle("Change Password");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        etTokencpsr = (EditText) findViewById(R.id.et_tokencpsr);
        etIdcpsr = (EditText) findViewById(R.id.et_idcpsr);
        etPasswordsr = (EditText) findViewById(R.id.et_passwordsr);
        etNewpasssr = (EditText) findViewById(R.id.et_newpasssr);
        etConfrimpassr = (EditText) findViewById(R.id.et_confrimpassr);
        btnSimpanpasssr = (Button) findViewById(R.id.btn_simpanpasssr);

        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        token = sharedpreferences.getString("acces_token", null);
        current_password = sharedpreferences.getString("current_password", null);
        new_password = sharedpreferences.getString("new_password", null);
        new_password_confirmation = sharedpreferences.getString("new_password_confirmation", null);


        String id = (sharedpreferences.getString("id", ""));
        etIdcpsr.setText(id);
        String token = (sharedpreferences.getString("acces_token",""));
        etTokencpsr.setText(token);

        btnSimpanpasssr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UbahPassword();
            }
        });
    }

    private void UbahPassword() {
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Mohon Tunggu...");
//        progressDialog.show();
        final String token = etTokencpsr.getText().toString();
        final String current_password = etPasswordsr.getText().toString();
        final String new_password = etNewpasssr.getText().toString();
        final String new_password_confirmation = etConfrimpassr.getText().toString();

        if (!validasipas()){
            return;
        }
        baseApi = RetrofitClient.getInstanceRetrofit();
        baseApi.ubahPassword(token,
                etPasswordsr.getText().toString().trim(),
                etNewpasssr.getText().toString(),
                etConfrimpassr.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            try {
//                                progressDialog.dismiss();
                                JSONObject object = new JSONObject(response.body().string());
                                String msg = object.optString("msg");
                                Toast.makeText(GantiPasswordActivity.this, ""+msg, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(GantiPasswordActivity.this, LoginActivity.class);
                                startActivity(intent);
//                                progressDialog.dismiss();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


//                            SharedPreferences sp = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
//
//                            SharedPreferences.Editor editor = sp.edit();
//                            editor.putBoolean(LoginActivity.session_status, true);
//                            editor.putString("acces_token", token);
//                            editor.putString("current_password", current_password);
//                            editor.putString("new_password", new_password);
//                            editor.putString("new_password_confirmation", new_password_confirmation);
//                            editor.commit();

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(GantiPasswordActivity.this, "Ada yang belum di isi !!!", Toast.LENGTH_SHORT).show();

                    }

                });
    }

    private boolean validasipas() {
        if (etPasswordsr.getText().toString().isEmpty()) {
            etPasswordsr.setError("Password tidak boleh kosong");
            etPasswordsr.requestFocus();
            return false;
        }
        if (etNewpasssr.getText().toString().isEmpty()) {
            etNewpasssr.setError("Password Baru tidak boleh kosong");
            etNewpasssr.requestFocus();
            return false;
        }
        if (etConfrimpassr.getText().toString().isEmpty()) {
            etConfrimpassr.setError("Confirmation Password tidak boleh kosong");
            etConfrimpassr.requestFocus();
            return false;
        }
        return true;
    }

    //button back toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

}
