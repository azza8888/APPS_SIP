package com.ezatech.apps_sip.logRes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ezatech.apps_sip.R;
import com.ezatech.apps_sip.api.BaseApi;
import com.ezatech.apps_sip.api.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePassActivity extends AppCompatActivity {

    private Toolbar tabsCreatePass;
    private EditText etEmailCreate;
    private EditText etPasswordCreate;
    private EditText etPasswordConfirmCreate;
    private EditText etTokenCreate;
    private Button btnSimpanCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pass);
        initView();

        btnSimpanCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postPassword();
            }
        });
    }

    private void postPassword() {
        BaseApi api = RetrofitClient.getInstanceRetrofit();
        api.postPassword(etEmailCreate.getText().toString().trim(),
                etPasswordCreate.getText().toString().trim(),
                etPasswordConfirmCreate.getText().toString().trim(),
                etTokenCreate.getText().toString().trim()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String id = jsonObject.optString("id");
                    String nip = jsonObject.optString("nip");
                    String nama = jsonObject.optString("nama");
                    String email = jsonObject.optString("email");
                    String username = jsonObject.optString("username");
                    Intent intent = new Intent(CreatePassActivity.this,LoginActivity.class);
                    startActivity(intent);
                    Toast.makeText(CreatePassActivity.this, "Password Telah Diganti", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
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

    private void initView() {
        tabsCreatePass = (Toolbar) findViewById(R.id.tabs_createPass);
        etEmailCreate = (EditText) findViewById(R.id.et_emailCreate);
        etPasswordCreate = (EditText) findViewById(R.id.et_passwordCreate);
        etPasswordConfirmCreate = (EditText) findViewById(R.id.et_passwordConfirmCreate);
        etTokenCreate = (EditText) findViewById(R.id.et_tokenCreate);
        btnSimpanCreate = (Button) findViewById(R.id.btn_simpanCreate);
    }
}
