package com.ezatech.apps_sip.pengaturan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.ezatech.apps_sip.R;

public class InfoActivity extends AppCompatActivity {

    private Toolbar mActionToolbar;
    private ImageView ivInfotttt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        mActionToolbar = (Toolbar) findViewById(R.id.tabs_info);
        setSupportActionBar(mActionToolbar);
        getSupportActionBar().setTitle("Info Aplikasi");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        initView();
    }

    //button back toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        ivInfotttt = (ImageView) findViewById(R.id.iv_infotttt);
    }
}
