package com.ezatech.apps_sip.logRes;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ezatech.apps_sip.MainActivity;
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

public class LoginActivity extends AppCompatActivity {

    private EditText etEmaill;
    private EditText etPassword;
    private TextView tvResetpass, tvrememberme, tvtoken;
    private Button btnLogin;
    private Context mContext;
    private BaseApi baseApi;
    Boolean session = false;
    private String username;
    private String password;
    private ProgressDialog loading;
    public static final String my_shared_preferences = "login";
    public static final String session_status = "session_status";
    private String TAG;
    private SharedPreferences sharedpreferences;
    private String nip;
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
//    private String token_type;
    private String expires_at;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmaill = (EditText) findViewById(R.id.et_emaill);
        etPassword = (EditText) findViewById(R.id.et_password);
        tvResetpass = (TextView) findViewById(R.id.tv_resetpass);
        tvrememberme = (TextView) findViewById(R.id.tv_remeberme);
        tvtoken = (TextView) findViewById(R.id.tv_token);
        btnLogin = (Button) findViewById(R.id.btn_login);
        mContext = this;
        baseApi = UtilsApi.getAPIService();


        sharedpreferences = getSharedPreferences(my_shared_preferences, MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        String id = sharedpreferences.getString("id", null);
        nip = sharedpreferences.getString("nip",null);
        nama = sharedpreferences.getString("nama", null);
        email = sharedpreferences.getString("email",null);
        jabatan = sharedpreferences.getString("jabatan", null);
        departemen = sharedpreferences.getString("departemen",null);
        wewenang = sharedpreferences.getString("wewenang", null);
        id_Kwilayah = sharedpreferences.getString("id_Kwilayah",null);
        id_Karea = sharedpreferences.getString("id_Karea", null);
        id_Subarea = sharedpreferences.getString("id_Subarea",null);
        status = sharedpreferences.getString("status", null);
        email_verified_at = sharedpreferences.getString("email_verified_at",null);
        created_at = sharedpreferences.getString("created_at", null);
        update_at = sharedpreferences.getString("update_at",null);
        username = sharedpreferences.getString("username", null);

        access_token = sharedpreferences.getString("acces_token",null);
//        token_type = sharedpreferences.getString("token_type",null);
        expires_at = sharedpreferences.getString("expires_at",null);


        if (session){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra(username,"username");
            intent.putExtra(password, "password");
            finish();
            startActivity(intent);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplication(), MainActivity.class);
//                startActivity(intent);
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true,true);
                requestLogin();
            }
        });

        tvResetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, LupaPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    private void requestLogin() {
        BaseApi api = RetrofitClient.getInstanceRetrofit();
        api.login(
//                tvtoken.getText().toString().
                etEmaill.getText().toString(), etPassword.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();
                            try {
                                JSONObject JsonResult = new JSONObject(response.body().string());


//                                    String  success = JsonResult.getString("message");
//                                    Toast.makeText(mContext, success, Toast.LENGTH_SHORT).show();
                                    String id = JsonResult.getJSONObject("user").getString("id");
                                    String nip = JsonResult.getJSONObject("user").getString("nip");
                                    String nama = JsonResult.getJSONObject("user").getString("nama");
                                    String email = JsonResult.getJSONObject("user").getString("email");
                                    String jabatan = JsonResult.getJSONObject("user").getString("jabatan");
                                    String departemen = JsonResult.getJSONObject("user").getString("departemen");
                                    String wewenang = JsonResult.getJSONObject("user").getString("wewenang");
                                    String id_kwilayah = JsonResult.getJSONObject("user").getString("id_kwilayah");
                                    String id_karea = JsonResult.getJSONObject("user").getString("id_karea");
                                    String id_subarea = JsonResult.getJSONObject("user").getString("id_subarea");
                                    String status = JsonResult.getJSONObject("user").getString("status");
                                    String email_verified_at = JsonResult.getJSONObject("user").getString("email_verified_at");
                                    String created_at = JsonResult.getJSONObject("user").getString("created_at");
                                    String updated_at = JsonResult.getJSONObject("user").getString("updated_at");
                                    String username = JsonResult.getJSONObject("user").getString("username");
                                    String access_token =JsonResult.getString("acces_token");
                                    Log.d(TAG, "AAAAAAAAAA: "+access_token);
//                                    String token_type = JsonResult.getString("token_type");
                                    String expires_at = JsonResult.getString("expires_at");

                                    SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);

                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putBoolean(session_status, true);
                                    editor.putString("id", id);
                                    editor.putString("nip", nip);
                                    editor.putString("nama", nama);
                                    editor.putString("email", email);
                                    editor.putString("jabatan", jabatan);
                                    editor.putString("departemen", departemen);
                                    editor.putString("wewenang", wewenang);
                                    editor.putString("id_Kwilayah", id_kwilayah);
                                    editor.putString("id_Karea", id_karea);
                                    editor.putString("id_Subarea", id_subarea);
                                    editor.putString("status", status);
                                    editor.putString("email_verified_at", email_verified_at);
                                    editor.putString("created_at",created_at);
                                    editor.putString("updated_at", updated_at);
                                    editor.putString("username", username);
                                    editor.putString("acces_token", access_token);
                                    editor.commit();
                                    loading.dismiss();

                                    Intent intent = new Intent(mContext, MainActivity.class);
//                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    finish();
                                    startActivity(intent);



                                Toast.makeText(mContext, "Berhasil Login", Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                Toast.makeText(mContext, "Password atau Email Salah", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            } catch (IOException e) {
                                Toast.makeText(mContext, "Password atau Email Salah", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }else {
                            loading.dismiss();
                            Toast.makeText(mContext, "Password atau Email Salah", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        loading.dismiss();
                        Toast.makeText(mContext, "Password atau Email Salah", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
