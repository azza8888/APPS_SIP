package com.ezatech.apps_sip.uploadLaporan;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.ezatech.apps_sip.MainActivity;
import com.ezatech.apps_sip.R;
import com.ezatech.apps_sip.api.BaseApi;
import com.ezatech.apps_sip.api.RetrofitClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ezatech.apps_sip.logRes.LoginActivity.my_shared_preferences;

public class FormActivity extends AppCompatActivity {

    private Toolbar mActionToolbar;
    private ImageView ivFotodepan;
    private Button btnGallery;
    private Button btnUpload1;
    private ImageView ivFotokanan;
    private Button btnGallery2;
    private Button btnUpload2;
    private ImageView ivFotokiri;
    private Button btnGallery3;
    private Button btnUpload3;
    private ImageView ivFotobelakang;
    private Button btnGallery4;
    private Button btnUpload4;
    private Button btnSimpanupload;
    private Button btnBatal;
    private int REQUEST_GALLERY = 9544;
    private int CAMERA_REQUEST = 7777;
    private int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 1;
    private String part_image;
    private EditText etEd;
    private EditText etNopendaftaran;
    private EditText etNosurat;
    private EditText etNopemeriksa;
    private EditText etNolhpp;
    private EditText etTgllhpp;
    private EditText etGambarins;
    private EditText etDiagramgt;
    private EditText etPeutama;
    private EditText etPecabang;
    private EditText etPeakhir;
    private EditText etPekotakkontak;
    private EditText etJenispengut;
    private EditText etJenispengcab;
    private EditText etJenispengakhir;
    private EditText etPenghantarbj;
    private EditText etPenghantarbp;
    private EditText etPenghantarbs;
    private EditText etSaklarutm;
    private EditText etSaklarcb1;
    private EditText etSaklarcb2;
    private EditText etPhbkcb1;
    private EditText etPhbkcb2;
    private EditText etPenghantarutm;
    private EditText etPenghantarcbang;
    private EditText etPenghantarakhir;
    private EditText etPenghantar3fs;
    private EditText etFittinglmp;
    private EditText etPpkotakk;
    private EditText etPpsakelar;
    private EditText etTinggikk;
    private EditText etTinggiphbk;
    private EditText etJeniskk;
    private EditText etTandakmp;
    private EditText etPengujipbbn;
    private EditText etJumlahphbutm;
    private EditText etJumlahphb1fs;
    private EditText etJumlahphb3fs;
    private EditText etJumlahphbcb;
    private EditText etJmlsalurancb;
    private EditText etJmlsaluranakhir;
    private EditText etJmltitiklmp;
    private EditText etJmlsakelar;
    private EditText etKkb;
    private EditText etKkk;
    private EditText etThnislp;
    private EditText etResistanpmb;
    private EditText etJmlmlu;
    private EditText etJmlmlk;
    private EditText etCatatan;
    private EditText etLocation;
    private EditText etLat;
    private EditText etLng;
    private Button btnFoto1;
    private Button btnFoto2;
    private Button btnFoto3;
    private Button btnFoto4;
    private Button btnFoto5;
    private EditText etNamapen;
    private EditText etAlmtpen;
    private EditText etTarif;
    private EditText etDaya;
    private EditText etBtl;
    private EditText etNmpemeriksa1;
    private EditText etNmpemeriksa2;
    private String no_pend;
    private SharedPreferences sharedpreferences;
    private String token;
    private String id_penerbit;
    private int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        Bundle bundle = getIntent().getExtras();
        no_pend     = bundle.getString("no_pendaftaran");

        sharedpreferences = getSharedPreferences(my_shared_preferences, MODE_PRIVATE);
        token = sharedpreferences.getString("acces_token","");
        id_penerbit = sharedpreferences.getString("id","");
//        no_pend = sharedpreferences.getString("no_pendaftaran","");

        initView();
        mActionToolbar = (Toolbar) findViewById(R.id.tabs_upload);
        setSupportActionBar(mActionToolbar);
        getSupportActionBar().setTitle("Unggah Data Pemeriksa");

//        if (ContextCompat.checkSelfPermission(FormActivity.this,
//                Manifest.permission.READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            //
//            if (ActivityCompat.shouldShowRequestPermissionRationale(FormActivity.this,
//                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
//
//            } else {
//
//                ActivityCompat.requestPermissions(FormActivity.this,
//                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                        MY_PERMISSIONS_REQUEST_FINE_LOCATION);
//
//            }
//        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FormActivity.this);
                builder.setTitle("Aplikasi SIP");
                builder.setMessage("Jika anda ingin Membatalkan Penyimpanan takan (YA) !!!")
                        .setIcon(android.R.drawable.ic_lock_power_off)
                        .setCancelable(false)
                        .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                    Intent intent = new Intent(FormActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }

                            }
                        }).setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        getDataPendaftaran();
        postDataPemeriksa();

    }

    private void postDataPemeriksa() {
        BaseApi baseApi = RetrofitClient.getInstanceRetrofit();
        baseApi.unggahPemeriksa(token,
                etNolhpp.getText().toString(),
                etTgllhpp.getText().toString(),
                etGambarins.getText().toString(),
                etDiagramgt.getText().toString(),
                etPeutama.getText().toString(),
                etPecabang.getText().toString(),
                etPeakhir.getText().toString(),
                etPekotakkontak.getText().toString(),
                etJenispengut.getText().toString(),
                etJenispengcab.getText().toString(),
                etPenghantarbj.getText().toString(),
                etPenghantarbp.getText().toString(),
                etPenghantarbs.getText().toString(),
                etSaklarutm.getText().toString(),
                etSaklarcb1.getText().toString(),etSaklarcb2.getText().toString(),
                etPhbkcb1.getText().toString(), etPhbkcb2.getText().toString(),
                etPenghantarutm.getText().toString(), etPenghantarcbang.getText().toString(),
                etPenghantarakhir.getText().toString(), etPenghantar3fs.getText().toString(),
                etFittinglmp.getText().toString(), etPpkotakk.getText().toString(),
                etPpsakelar.getText().toString(), etTinggikk.getText().toString(),
                etTinggiphbk.getText().toString(), etJeniskk.getText().toString(),
                etTandakmp.getText().toString(), etPengujipbbn.getText().toString(),
                etJumlahphbutm.getText().toString(), etJumlahphb1fs.getText().toString(),
                etJumlahphb3fs.getText().toString(), etJumlahphbcb.getText().toString(),
                etJmlsalurancb.getText().toString(), etJmlsaluranakhir.getText().toString(),
                etJmltitiklmp.getText().toString(), etJmlsakelar.getText().toString(),
                etKkb.getText().toString(), etKkk.getText().toString(),
                etThnislp.getText().toString(), etResistanpmb.getText().toString(),
                etJmlmlu.getText().toString(), etJmlmlk.getText().toString(),
                etCatatan.getText().toString(), etLocation.getText().toString(),
                etLat.getText().toString(), etLng.getText().toString()
                ).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject object = new JSONObject(response.body().string());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void getDataPendaftaran() {
        BaseApi api = RetrofitClient.getInstanceRetrofit();
        api.pemeriksaGetNopend(token,no_pend)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            JSONArray array = new JSONArray(response.body().string());
                            JSONObject object = array.optJSONObject(i);
                            String no_pendaftaran = object.getString("no_pendaftaran");
                            String nama_pendaftaran = object.getString("nama");
                            String alamat_pendaftaran = object.getString("alamat");
                            String tarif_pendaftaran = object.getString("jenis_tarif");
                            String daya_pendaftaran = object.getString("daya");
                            String nama_btl = object.getString("nama_btl");
                            String no_suratt = object.getString("no_surat");
                            String namap1 = object.getString("pemeriksa1");
                            String namap2 = object.getString("pemeriksa2");

                            etNopendaftaran.setText(no_pendaftaran);
                            etNamapen.setText(nama_pendaftaran);
                            etAlmtpen.setText(alamat_pendaftaran);
                            etTarif.setText(tarif_pendaftaran);
                            etDaya.setText(daya_pendaftaran);
                            etBtl.setText(nama_btl);
                            etNosurat.setText(no_suratt);
                            etNmpemeriksa1.setText(namap1);
                            etNmpemeriksa2.setText(namap2);
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


    //button back toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        etNamapen = (EditText) findViewById(R.id.et_namapen);
        etAlmtpen = (EditText) findViewById(R.id.et_almtpen);
        etTarif = (EditText) findViewById(R.id.et_tarif);
        etDaya = (EditText) findViewById(R.id.et_daya);
        etBtl = (EditText) findViewById(R.id.et_btl);
        etNmpemeriksa1 = (EditText) findViewById(R.id.et_nmpemeriksa1);
        etNmpemeriksa2 = (EditText) findViewById(R.id.et_nmpemeriksa2);
        etEd = (EditText) findViewById(R.id.et_ed);
        etNopendaftaran = (EditText) findViewById(R.id.et_nopendaftaran);
        etNosurat = (EditText) findViewById(R.id.et_nosurat);
        etNopemeriksa = (EditText) findViewById(R.id.et_nopemeriksa);
        etNolhpp = (EditText) findViewById(R.id.et_nolhpp);
        etTgllhpp = (EditText) findViewById(R.id.et_tgllhpp);
        etGambarins = (EditText) findViewById(R.id.et_gambarins);
        etDiagramgt = (EditText) findViewById(R.id.et_diagramgt);
        etPeutama = (EditText) findViewById(R.id.et_peutama);
        etPecabang = (EditText) findViewById(R.id.et_pecabang);
        etPeakhir = (EditText) findViewById(R.id.et_peakhir);
        etPekotakkontak = (EditText) findViewById(R.id.et_pekotakkontak);
        etJenispengut = (EditText) findViewById(R.id.et_jenispengut);
        etJenispengcab = (EditText) findViewById(R.id.et_jenispengcab);
        etJenispengakhir = (EditText) findViewById(R.id.et_jenispengakhir);
        etPenghantarbj = (EditText) findViewById(R.id.et_penghantarbj);
        etPenghantarbp = (EditText) findViewById(R.id.et_penghantarbp);
        etPenghantarbs = (EditText) findViewById(R.id.et_penghantarbs);
        etSaklarutm = (EditText) findViewById(R.id.et_saklarutm);
        etSaklarcb1 = (EditText) findViewById(R.id.et_saklarcb1);
        etSaklarcb2 = (EditText) findViewById(R.id.et_saklarcb2);
        etPhbkcb1 = (EditText) findViewById(R.id.et_phbkcb1);
        etPhbkcb2 = (EditText) findViewById(R.id.et_phbkcb2);
        etPenghantarutm = (EditText) findViewById(R.id.et_penghantarutm);
        etPenghantarcbang = (EditText) findViewById(R.id.et_penghantarcbang);
        etPenghantarakhir = (EditText) findViewById(R.id.et_penghantarakhir);
        etPenghantar3fs = (EditText) findViewById(R.id.et_penghantar3fs);
        etFittinglmp = (EditText) findViewById(R.id.et_fittinglmp);
        etPpkotakk = (EditText) findViewById(R.id.et_ppkotakk);
        etPpsakelar = (EditText) findViewById(R.id.et_ppsakelar);
        etTinggikk = (EditText) findViewById(R.id.et_tinggikk);
        etTinggiphbk = (EditText) findViewById(R.id.et_tinggiphbk);
        etJeniskk = (EditText) findViewById(R.id.et_jeniskk);
        etTandakmp = (EditText) findViewById(R.id.et_tandakmp);
        etPengujipbbn = (EditText) findViewById(R.id.et_pengujipbbn);
        etJumlahphbutm = (EditText) findViewById(R.id.et_jumlahphbutm);
        etJumlahphb1fs = (EditText) findViewById(R.id.et_jumlahphb1fs);
        etJumlahphb3fs = (EditText) findViewById(R.id.et_jumlahphb3fs);
        etJumlahphbcb = (EditText) findViewById(R.id.et_jumlahphbcb);
        etJmlsalurancb = (EditText) findViewById(R.id.et_jmlsalurancb);
        etJmlsaluranakhir = (EditText) findViewById(R.id.et_jmlsaluranakhir);
        etJmltitiklmp = (EditText) findViewById(R.id.et_jmltitiklmp);
        etJmlsakelar = (EditText) findViewById(R.id.et_jmlsakelar);
        etKkb = (EditText) findViewById(R.id.et_kkb);
        etKkk = (EditText) findViewById(R.id.et_kkk);
        etThnislp = (EditText) findViewById(R.id.et_thnislp);
        etResistanpmb = (EditText) findViewById(R.id.et_resistanpmb);
        etJmlmlu = (EditText) findViewById(R.id.et_jmlmlu);
        etJmlmlk = (EditText) findViewById(R.id.et_jmlmlk);
        etCatatan = (EditText) findViewById(R.id.et_catatan);
        etLocation = (EditText) findViewById(R.id.et_location);
        etLat = (EditText) findViewById(R.id.et_lat);
        etLng = (EditText) findViewById(R.id.et_lng);
        btnFoto1 = (Button) findViewById(R.id.btn_foto1);
        btnFoto2 = (Button) findViewById(R.id.btn_foto2);
        btnFoto3 = (Button) findViewById(R.id.btn_foto3);
        btnFoto4 = (Button) findViewById(R.id.btn_foto4);
        btnFoto5 = (Button) findViewById(R.id.btn_foto5);

        btnSimpanupload = (Button) findViewById(R.id.btn_simpanupload);
        btnBatal = (Button) findViewById(R.id.btn_batal);
    }
}
