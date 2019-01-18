package com.ezatech.apps_sip.notifLaporan;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ezatech.apps_sip.R;
import com.ezatech.apps_sip.adapter.AdapterLLaporan;
import com.ezatech.apps_sip.adapter.RiwayatAdapter;
import com.ezatech.apps_sip.data.FeedbackData;
import com.ezatech.apps_sip.data.ListLaporan;
import com.ezatech.apps_sip.riwayatNotif.RiwayatActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListLaporanActivity extends AppCompatActivity {

    private ArrayList<ListLaporan> listlaporan;
    private RecyclerView recyclerView;
    private Toolbar mActionToolbar;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_laporan);

        mActionToolbar = (Toolbar) findViewById(R.id.tabs_listlaporan);
        setSupportActionBar(mActionToolbar);
        getSupportActionBar().setTitle("Laporan Anda");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        recyclerView = findViewById(R.id.rv_listlaporan);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager mLayoutManager;
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        listlaporan = new ArrayList<>();

        getData();


    }

    private void getData() {
        ListLaporan list = new ListLaporan();
        list.setNama("EzaTech");
        list.setAlamat("Gemah Raya");
        list.setWaktu("15:00");
        list.setImage("Foto Gak Muncul");

        listlaporan.add(list);
        AdapterLLaporan adapter = new AdapterLLaporan(ListLaporanActivity.this, listlaporan);
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
