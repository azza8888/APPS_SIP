package com.ezatech.apps_sip.riwayatNotif;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ezatech.apps_sip.R;
import com.ezatech.apps_sip.adapter.RiwayatAdapter;
import com.ezatech.apps_sip.data.FeedbackData;

import java.util.ArrayList;
import java.util.Date;

public class RiwayatActivity extends AppCompatActivity {

    private ArrayList<FeedbackData> riwayatl;
    private RecyclerView recyclerView;
    private Toolbar mActionToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);

        mActionToolbar = (Toolbar) findViewById(R.id.tabs_riwayat);
        setSupportActionBar(mActionToolbar);
        getSupportActionBar().setTitle("Riwayat Anda");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        recyclerView = findViewById(R.id.rv_riwayat);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager mLayoutManager;
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        riwayatl = new ArrayList<>();

        getData();


    }

    private void getData() {
        FeedbackData data = new FeedbackData();
        data.setId("1");
        data.setNama("azza");
        data.setWaktu("15:00");
        data.setKonfirm("accept");

        riwayatl.add(data);
        RiwayatAdapter adapter = new RiwayatAdapter(RiwayatActivity.this, riwayatl);
        recyclerView.setAdapter(adapter);
    }

    //button back toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }


}
