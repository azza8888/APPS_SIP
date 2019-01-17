package com.ezatech.apps_sip.logRes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ezatech.apps_sip.MainActivity;
import com.ezatech.apps_sip.R;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmaill;
    private EditText etPassword;
    private TextView tvResetpass;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmaill = (EditText) findViewById(R.id.et_emaill);
        etPassword = (EditText) findViewById(R.id.et_password);
        tvResetpass = (TextView) findViewById(R.id.tv_resetpass);
        btnLogin = (Button) findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
