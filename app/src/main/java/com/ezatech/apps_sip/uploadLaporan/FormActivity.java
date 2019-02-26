package com.ezatech.apps_sip.uploadLaporan;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ezatech.apps_sip.MainActivity;
import com.ezatech.apps_sip.R;
import com.ezatech.apps_sip.api.BaseApi;
import com.ezatech.apps_sip.api.RetrofitClient;
import com.ezatech.apps_sip.data.FormResultModel;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ezatech.apps_sip.logRes.LoginActivity.my_shared_preferences;

public class FormActivity extends AppCompatActivity {

    private Toolbar mActionToolbar;
    private Button btnUpload4;
    private Button btnSimpanupload;
    private Button btnBatal;
    private int REQUEST_GALLERY = 9544;
    private int CAMERA_REQUEST = 7777;
    private int CAMERA_REQUEST1 = 6666;
    private int CAMERA_REQUEST2 = 8888;
    private int CAMERA_REQUEST3 = 9955;
    private int CAMERA_REQUEST4 = 9954;
    private int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 1;
    private String part_image;
    private String part_image1;
    private String part_image2;
    private String part_image3;
    private String part_image4;
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
    private EditText etPhbkUtama;
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
    private EditText etNamapen;
    private EditText etAlmtpen;
    private EditText etTarif;
    private EditText etDaya;
    private EditText etBtl;
    private EditText etNmpemeriksa1;
    private EditText etNmpemeriksa2;
    private String no_pend;
    private File foto1 = null, foto2 = null, foto3=null, foto4=null, foto5=null;
    private SharedPreferences sharedpreferences;
    private String token;
    private String id_penerbit;
    private int i = 0;
    private ImageView ivFoto1;
    private ImageView ivFoto2;
    private Button btnCamera1;
    private ImageView ivFoto3;
    private ImageView ivFoto4;
    private ImageView ivFoto5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        Bundle bundle = getIntent().getExtras();
        no_pend = bundle.getString("no_pendaftaran");

        sharedpreferences = getSharedPreferences(my_shared_preferences, MODE_PRIVATE);
        token = sharedpreferences.getString("acces_token", "");
        id_penerbit = sharedpreferences.getString("id", "");
//        no_pend = sharedpreferences.getString("no_pendaftaran","");

        initView();
        mActionToolbar = (Toolbar) findViewById(R.id.tabs_upload);
        setSupportActionBar(mActionToolbar);
        getSupportActionBar().setTitle("Unggah Data Pemeriksa");

        if (ContextCompat.checkSelfPermission(FormActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //
            if (ActivityCompat.shouldShowRequestPermissionRationale(FormActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {

                ActivityCompat.requestPermissions(FormActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_FINE_LOCATION);

            }
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        ivFoto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postCamera1();
            }
        });

        ivFoto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postCamera2();
            }
        });
        ivFoto3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postCamera3();
            }
        });
        ivFoto4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postCamera4();
            }
        });
        ivFoto5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postCamera5();
            }
        });

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

        btnSimpanupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                kirimData();
            }
        });
        getNoLhpp();
        getDataPendaftaran();

    }


    private void postCamera1() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUEST);
    }

    private void postCamera2() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUEST1);
    }
    private void postCamera3() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUEST2);
    }
    private void postCamera4() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUEST3);
    }
    private void postCamera5() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUEST4);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST) {
                Uri dataimage = data.getData();
                String[] imageprojection = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(dataimage, imageprojection, null, null, null);

                if (cursor != null) {
                    cursor.moveToFirst();
                    int indexImage = cursor.getColumnIndex(imageprojection[0]);
                    part_image = cursor.getString(indexImage);

                    Picasso.with(this).load(new File(part_image))
                            .fit()
                            .centerCrop()
                            .into(ivFoto1);
                    foto1 = new File(part_image);
                }

            }
            if (requestCode == CAMERA_REQUEST1) {
                Uri dataimage = data.getData();
                String[] imageprojection = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(dataimage, imageprojection, null, null, null);

                if (cursor != null) {
                    cursor.moveToFirst();
                    int indexImage = cursor.getColumnIndex(imageprojection[0]);
                    part_image1 = cursor.getString(indexImage);

                    Picasso.with(this).load(new File(part_image1))
                            .fit()
                            .centerCrop()
                            .into(ivFoto2);
                    foto2 = new File(part_image1);
                }

            }
            if (requestCode == CAMERA_REQUEST2) {
                Uri dataimage = data.getData();
                String[] imageprojection = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(dataimage, imageprojection, null, null, null);

                if (cursor != null) {
                    cursor.moveToFirst();
                    int indexImage = cursor.getColumnIndex(imageprojection[0]);
                    part_image2 = cursor.getString(indexImage);

                    Picasso.with(this).load(new File(part_image2))
                            .fit()
                            .centerCrop()
                            .into(ivFoto3);
                    foto3 = new File(part_image2);
                }

            }

            if (requestCode == CAMERA_REQUEST3) {
                Uri dataimage = data.getData();
                String[] imageprojection = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(dataimage, imageprojection, null, null, null);

                if (cursor != null) {
                    cursor.moveToFirst();
                    int indexImage = cursor.getColumnIndex(imageprojection[0]);
                    part_image3 = cursor.getString(indexImage);

                    Picasso.with(this).load(new File(part_image3))
                            .fit()
                            .centerCrop()
                            .into(ivFoto4);
                    foto4 = new File(part_image3);
                }

            }

            if (requestCode == CAMERA_REQUEST4) {
                Uri dataimage = data.getData();
                String[] imageprojection = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(dataimage, imageprojection, null, null, null);

                if (cursor != null) {
                    cursor.moveToFirst();
                    int indexImage = cursor.getColumnIndex(imageprojection[0]);
                    part_image4 = cursor.getString(indexImage);

                    Picasso.with(this).load(new File(part_image4))
                            .fit()
                            .centerCrop()
                            .into(ivFoto5);
                    foto5 = new File(part_image4);
                }

            }
        }
    }

    private void kirimData() {
        if (!vallidasi()) {
            return;
        }

        final ProgressDialog p;
        p = new ProgressDialog(this);
        p.setMessage("Proses Upload Foto");
        p.show();

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/from-data"), foto1);
        MultipartBody.Part body = MultipartBody.Part.createFormData("foto1", foto1.getName(), requestFile);

        RequestBody requestFile2 = RequestBody.create(MediaType.parse("multipart/from-data"), foto2);
        MultipartBody.Part body2 = MultipartBody.Part.createFormData("foto2", foto2.getName(), requestFile2);

        RequestBody requestFile3 = RequestBody.create(MediaType.parse("multipart/from-data"), foto3);
        MultipartBody.Part body3 = MultipartBody.Part.createFormData("foto3", foto3.getName(), requestFile3);

        RequestBody requestFile4 = RequestBody.create(MediaType.parse("multipart/from-data"), foto4);
        MultipartBody.Part body4 = MultipartBody.Part.createFormData("foto4", foto4.getName(), requestFile4);

        RequestBody requestFile5 = RequestBody.create(MediaType.parse("multipart/from-data"), foto5);
        MultipartBody.Part body5 = MultipartBody.Part.createFormData("foto5", foto5.getName(), requestFile5);

        RequestBody noPendaftaran = RequestBody.create(MediaType.parse("text/plain"), etNopendaftaran.getText().toString().trim());
        RequestBody noSuratTgs = RequestBody.create(MediaType.parse("text/plain"), etNosurat.getText().toString().trim());
        RequestBody noPemeriksaan = RequestBody.create(MediaType.parse("text/plain"), etNopemeriksa.getText().toString().trim());
        RequestBody noLhpp = RequestBody.create(MediaType.parse("text/plain"), etNolhpp.getText().toString().trim());
        RequestBody tglLhpp = RequestBody.create(MediaType.parse("text/plain"), etTgllhpp.getText().toString().trim());
        RequestBody gambarIns = RequestBody.create(MediaType.parse("text/plain"), etGambarins.getText().toString().trim());
        RequestBody diagramT = RequestBody.create(MediaType.parse("text/plain"), etDiagramgt.getText().toString().trim());
        RequestBody peUtama = RequestBody.create(MediaType.parse("text/plain"), etPeutama.getText().toString().trim());
        RequestBody peCabang = RequestBody.create(MediaType.parse("text/plain"), etPecabang.getText().toString().trim());
        RequestBody peAkhir = RequestBody.create(MediaType.parse("text/plain"), etPeakhir.getText().toString().trim());
        RequestBody peKotakKontak = RequestBody.create(MediaType.parse("text/plain"), etPekotakkontak.getText().toString().trim());
        RequestBody jenisPengut = RequestBody.create(MediaType.parse("text/plain"), etJenispengut.getText().toString().trim());
        RequestBody jenisPengeCab = RequestBody.create(MediaType.parse("text/plain"), etJenispengcab.getText().toString().trim());
        RequestBody jenisPengAkhir = RequestBody.create(MediaType.parse("text/plain"), etJenispengakhir.getText().toString().trim());
        RequestBody penghantarbj = RequestBody.create(MediaType.parse("text/plain"), etPenghantarbj.getText().toString().trim());
        RequestBody penghantarbp = RequestBody.create(MediaType.parse("text/plain"), etPenghantarbp.getText().toString().trim());
        RequestBody penghantarbs = RequestBody.create(MediaType.parse("text/plain"), etPenghantarbs.getText().toString().trim());
        RequestBody saklarUtm = RequestBody.create(MediaType.parse("text/plain"), etSaklarutm.getText().toString().trim());
        RequestBody saklarCb1 = RequestBody.create(MediaType.parse("text/plain"), etSaklarcb1.getText().toString().trim());
        RequestBody saklarCb2 = RequestBody.create(MediaType.parse("text/plain"), etSaklarcb2.getText().toString().trim());
        RequestBody phbkUtama = RequestBody.create(MediaType.parse("text/plain"), etPhbkUtama.getText().toString().trim());
        RequestBody phbkCb1 = RequestBody.create(MediaType.parse("text/plain"), etPhbkcb1.getText().toString().trim());
        RequestBody phbkCb2 = RequestBody.create(MediaType.parse("text/plain"), etPhbkcb2.getText().toString().trim());
        RequestBody penghantarUtm = RequestBody.create(MediaType.parse("text/plain"), etPenghantarutm.getText().toString().trim());
        RequestBody penghantarCb = RequestBody.create(MediaType.parse("text/plain"), etPenghantarcbang.getText().toString().trim());
        RequestBody penghantarAkhir = RequestBody.create(MediaType.parse("text/plain"), etPenghantarakhir.getText().toString().trim());
        RequestBody penghantar3fs = RequestBody.create(MediaType.parse("text/plain"), etPenghantar3fs.getText().toString().trim());
        RequestBody fittingLamp = RequestBody.create(MediaType.parse("text/plain"), etFittinglmp.getText().toString().trim());
        RequestBody ppKotakk = RequestBody.create(MediaType.parse("text/plain"), etPpkotakk.getText().toString().trim());
        RequestBody ppSakelar = RequestBody.create(MediaType.parse("text/plain"), etPpsakelar.getText().toString().trim());
        RequestBody tinggiKk = RequestBody.create(MediaType.parse("text/plain"), etTinggikk.getText().toString().trim());
        RequestBody tinggiPhbk = RequestBody.create(MediaType.parse("text/plain"), etTinggiphbk.getText().toString().trim());
        RequestBody jenisKk = RequestBody.create(MediaType.parse("text/plain"), etJeniskk.getText().toString().trim());
        RequestBody tandaKmp = RequestBody.create(MediaType.parse("text/plain"), etTandakmp.getText().toString().trim());
        RequestBody pengujiPbbn = RequestBody.create(MediaType.parse("text/plain"), etPengujipbbn.getText().toString().trim());
        RequestBody jumlahPhbUtm = RequestBody.create(MediaType.parse("text/plain"), etJumlahphbutm.getText().toString().trim());
        RequestBody jumlahPhb1fs = RequestBody.create(MediaType.parse("text/plain"), etJumlahphb1fs.getText().toString().trim());
        RequestBody jumlahPhb3fs = RequestBody.create(MediaType.parse("text/plain"), etJumlahphb3fs.getText().toString().trim());
        RequestBody jumlahPhbCb = RequestBody.create(MediaType.parse("text/plain"), etJumlahphbcb.getText().toString().trim());
        RequestBody jumlahSalurancb = RequestBody.create(MediaType.parse("text/plain"), etJmlsalurancb.getText().toString().trim());
        RequestBody jumlahSaluranAkhir = RequestBody.create(MediaType.parse("text/plain"), etJmlsaluranakhir.getText().toString().trim());
        RequestBody jmlTitiklmp = RequestBody.create(MediaType.parse("text/plain"), etJmltitiklmp.getText().toString().trim());
        RequestBody jmlSakelar = RequestBody.create(MediaType.parse("text/plain"), etJmlsakelar.getText().toString().trim());
        RequestBody kkb = RequestBody.create(MediaType.parse("text/plain"), etKkb.getText().toString().trim());
        RequestBody kkk = RequestBody.create(MediaType.parse("text/plain"), etKkk.getText().toString().trim());
        RequestBody tahananIsolasiP = RequestBody.create(MediaType.parse("text/plain"), etThnislp.getText().toString().trim());
        RequestBody resistanPmb = RequestBody.create(MediaType.parse("text/plain"), etResistanpmb.getText().toString().trim());
        RequestBody jmlmlu = RequestBody.create(MediaType.parse("text/plain"), etJmlmlu.getText().toString().trim());
        RequestBody jmlmlk = RequestBody.create(MediaType.parse("text/plain"), etJmlmlk.getText().toString().trim());
        RequestBody catatan = RequestBody.create(MediaType.parse("text/plain"), etCatatan.getText().toString().trim());
        RequestBody location = RequestBody.create(MediaType.parse("text/plain"), etLocation.getText().toString().trim());
        RequestBody lat = RequestBody.create(MediaType.parse("text/plain"), etLat.getText().toString().trim());
        RequestBody lng = RequestBody.create(MediaType.parse("text/plain"), etLng.getText().toString().trim());

        BaseApi api = RetrofitClient.getInstanceRetrofit();
        api.unggahPemeriksa(token, noPendaftaran,noSuratTgs,noPemeriksaan,
                noLhpp, tglLhpp, gambarIns, diagramT, peUtama, peCabang, peAkhir,
                peKotakKontak, jenisPengut, jenisPengeCab, jenisPengAkhir,
                penghantarbj, penghantarbp,
                penghantarbs, saklarUtm, saklarCb1, saklarCb2,phbkUtama, phbkCb1, phbkCb2, penghantarUtm,
                penghantarCb, penghantarAkhir, penghantar3fs, fittingLamp, ppKotakk, ppSakelar,
                tinggiKk, tinggiPhbk, jenisKk, tandaKmp, pengujiPbbn, jumlahPhbUtm, jumlahPhb1fs,
                jumlahPhb3fs, jumlahPhbCb, jumlahSalurancb, jumlahSaluranAkhir, jmlTitiklmp, jmlSakelar,
                kkb, kkk, tahananIsolasiP, resistanPmb, jmlmlu, jmlmlk, catatan,
                location, lat, lng, body,body2,body3,body4,body5
        )
                .enqueue(new Callback<FormResultModel>() {
                    @Override
                    public void onResponse(Call<FormResultModel> call, Response<FormResultModel> response) {
                        if (response.isSuccessful()) {
                            p.dismiss();
                            Intent intent = new Intent(FormActivity.this, MainActivity.class);
                            Toast.makeText(FormActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<FormResultModel> call, Throwable t) {
                        p.dismiss();
                        Log.d("FORM", "onFailure: " + t.getMessage());
                    }
                });
    }

    private boolean vallidasi() {
        if (etNolhpp == null) {
            Toast.makeText(this, "No Lhpp Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etTgllhpp == null) {
            Toast.makeText(this, "Tgl Lhpp Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etGambarins == null) {
            Toast.makeText(this, "Gambar Instalasi Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etDiagramgt == null) {
            Toast.makeText(this, "Diagram Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etPeutama == null) {
            Toast.makeText(this, "Penghantar Proteksi PE(Utama) Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etPecabang == null) {
            Toast.makeText(this, "Penghantar Proteksi PE(Cabang) Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etPeakhir == null) {
            Toast.makeText(this, "Penghantar Proteksi PE(Akhir) Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etPekotakkontak == null) {
            Toast.makeText(this, "Penghantar Proteksi PE(Kotak Kontak) Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etJenispengut == null) {
            Toast.makeText(this, "Jenis Penghantar Utama Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etJenispengcab == null) {
            Toast.makeText(this, "Jenis Penghantar Cabang Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etJenispengakhir == null) {
            Toast.makeText(this, "Jenis Penghantar Akhir Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (etPenghantarbj== null) {
            Toast.makeText(this, "Penghantar Pembumian(Jenis) Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etPenghantarbp== null) {
            Toast.makeText(this, "Penghantar Pembumian(Luas Penampang) Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etPenghantarbs== null) {
            Toast.makeText(this, "Penghantar Pembumian(Sistem pembumian) Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etSaklarutm== null) {
            Toast.makeText(this, "Sakelar (sakelar utama) Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etSaklarcb1== null) {
            Toast.makeText(this, "Sakelar (sakelar cabang1) Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etSaklarcb2== null) {
            Toast.makeText(this, "Sakelar (sakelar cabang2) Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etPhbkUtama==null){
            Toast.makeText(this, "Proteksi Sirkit Akhir (PHBK Utama) Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etPhbkcb1== null) {
            Toast.makeText(this, "Proteksi Sirkit Akhir (PHBK cabang1) Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etPhbkcb2== null) {
            Toast.makeText(this, "Proteksi Sirkit Akhir (PHBK cabang2) Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etPenghantarutm== null) {
            Toast.makeText(this, "Penampang penghantar Utama Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etPenghantarcbang== null) {
            Toast.makeText(this, "Penampang penghantar Cabang Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etPenghantarakhir== null) {
            Toast.makeText(this, "Penampang penghantar Akhir Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etPenghantar3fs== null) {
            Toast.makeText(this, "Penampang penghantar tiga Fasa Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etFittinglmp== null) {
            Toast.makeText(this, "Penguji Polaritas (Fitting Lampu) Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etPpkotakk== null) {
            Toast.makeText(this, "Penguji Polaritas (Kotak Kontak) Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etPpsakelar== null) {
            Toast.makeText(this, "Penguji Polaritas (Sakelar) Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etTinggikk== null) {
            Toast.makeText(this, "Pemasangan1 Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etTinggiphbk== null) {
            Toast.makeText(this, "Pemasangan2 Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etJeniskk== null) {
            Toast.makeText(this, "Jenis Kotak Kontak Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etTandakmp== null) {
            Toast.makeText(this, "Tanda Komponen SNI Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etPengujipbbn== null) {
            Toast.makeText(this, "Penguji Pembebanan Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etJumlahphbutm== null) {
            Toast.makeText(this, "Jumlah PHB utama Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etJumlahphb1fs== null) {
            Toast.makeText(this, "Jumlah PHB 1 phasa Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etJumlahphb3fs== null) {
            Toast.makeText(this, "Jumlah PHB 3 phasa Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etJumlahphbcb== null) {
            Toast.makeText(this, "Jumlah PHB Cabang Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etJmlsalurancb== null) {
            Toast.makeText(this, "Jumlah Saluran Cabang Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etJmlsaluranakhir== null) {
            Toast.makeText(this, "Jumlah Saluran Akhir Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etJmltitiklmp== null) {
            Toast.makeText(this, "Jumlah Titik Lampu Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etJmlsakelar== null) {
            Toast.makeText(this, "Jumlah Sakelar Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etKkb== null) {
            Toast.makeText(this, "KKB Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etKkk== null) {
            Toast.makeText(this, "KKK Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etThnislp== null) {
            Toast.makeText(this, "Tahanan Isolasi Penghantar Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etResistanpmb== null) {
            Toast.makeText(this, "Resisten Pembumian Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etJmlmlu== null) {
            Toast.makeText(this, "Jumlah Motor Listrik (Unit) Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etJmlmlk== null) {
            Toast.makeText(this, "Jumlah Motor Listrik (Kw) Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etCatatan== null) {
            Toast.makeText(this, "Catatan Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etLocation== null) {
            Toast.makeText(this, "Lokasi Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etLat== null) {
            Toast.makeText(this, "Latitude Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etLng== null) {
            Toast.makeText(this, "Longitude Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (foto1 == null) {
            Toast.makeText(this, "Foto 1 belum Di isi", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (foto2 == null) {
            Toast.makeText(this, "Foto 2 belum Di isi", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (foto3 == null) {
            Toast.makeText(this, "Foto 3 belum Di isi", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (foto4 == null) {
            Toast.makeText(this, "Foto 4 belum Di isi", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (foto5 == null) {
            Toast.makeText(this, "Foto 5 belum Di isi", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void getNoLhpp() {
        BaseApi api = RetrofitClient.getInstanceRetrofit();
        api.getNoLhpp(token).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject object = new JSONObject(response.body().string());
                    String noLhpp = object.optString("noLhpp");
                    etNolhpp.setText(noLhpp);
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



    private void getDataPendaftaran() {
        BaseApi api = RetrofitClient.getInstanceRetrofit();
        api.pemeriksaGetNopend(token, no_pend)
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
        etPhbkUtama = (EditText) findViewById(R.id.et_phbkUtama);
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

        btnSimpanupload = (Button) findViewById(R.id.btn_simpanupload);
        btnBatal = (Button) findViewById(R.id.btn_batal);
        ivFoto1 = (ImageView) findViewById(R.id.iv_foto1);
        ivFoto2 = (ImageView) findViewById(R.id.iv_foto2);
        ivFoto3 = (ImageView) findViewById(R.id.iv_foto3);
        ivFoto4 = (ImageView) findViewById(R.id.iv_foto4);
        ivFoto5 = (ImageView) findViewById(R.id.iv_foto5);
    }
}
