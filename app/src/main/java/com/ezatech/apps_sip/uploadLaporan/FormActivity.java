package com.ezatech.apps_sip.uploadLaporan;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.ezatech.apps_sip.MainActivity;
import com.ezatech.apps_sip.R;
import com.ezatech.apps_sip.Utils.GpsTracker;
import com.ezatech.apps_sip.api.BaseApi;
import com.ezatech.apps_sip.api.RetrofitClient;
import com.ezatech.apps_sip.data.FormResultModel;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;
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
    private File foto1 = null, foto2 = null, foto3 = null, foto4 = null, foto5 = null;
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
    private Button btnOpenDatePicker;
    private Button btnGetLokasi;

    private Calendar myCalendar;
    private Context context;
    private static final int PERMISSION_REQUEST_CAMERA = 8;
    private EditText etIdPelanggan;
    private Spinner spGambarins;
    private Spinner spDiagramgt;
    private Spinner spPeutama;
    private Spinner spPecabang;
    private Spinner spPeakhir;
    private Spinner spPekotakkontak;
    private Spinner spPenghantarbs;
    private Spinner spJenispengut;
    private Spinner spJenispengcab;
    private Spinner spJenispengakhir;
    private Spinner spSaklarutm;
    private Spinner spSaklarcb1;
    private Spinner spSaklarcb2;
    private Spinner spPhbkUtama;
    private Spinner spPhbkcb1;
    private Spinner spPhbkcb2;
    private Spinner spPenghantarutm;
    private Spinner spPenghantarcbang;
    private Spinner spPenghantarakhir;
    private Spinner spPenghantar3fs;
    private Spinner spFittinglmp;
    private Spinner spPpkotakk;
    private Spinner spPpsakelar;
    private Spinner spJeniskk;
    private Spinner spTandakmp;
    private Spinner spPengujipbbn;
    private int hari, bulan ,tahun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        initView();
        Bundle bundle = getIntent().getExtras();
        no_pend = bundle.getString("no_pendaftaran");

        sharedpreferences = getSharedPreferences(my_shared_preferences, MODE_PRIVATE);
        token = sharedpreferences.getString("acces_token", "");
        id_penerbit = sharedpreferences.getString("id", "");
//        no_pend = sharedpreferences.getString("no_pendaftaran","");

        //untuk membuat list sp1
        List<String> item = new ArrayList<>();
        item.add("");
        item.add("ada");
        item.add("tidak ada");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(FormActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGambarins.setAdapter(adapter);
        spDiagramgt.setAdapter(adapter);
        spPeutama.setAdapter(adapter);
        spPecabang.setAdapter(adapter);
        spPeakhir.setAdapter(adapter);
        spPekotakkontak.setAdapter(adapter);
        /////////////////////////////////////

        //untuk membuat list spPenghantar pembumian
        List<String> item1 = new ArrayList<>();
        item1.add("");
        item1.add("TT");
        item1.add("TTN-C-S");
        item1.add("TN-C");
        item1.add("TN-S");
        item1.add("lainnya");

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(FormActivity.this,
                android.R.layout.simple_spinner_dropdown_item, item1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPenghantarbs.setAdapter(adapter1);
        ////////////////////////////////////
        //untuk membuat list jenisPenghantar
        List<String> item2 = new ArrayList<>();
        item2.add("");
        item2.add("NYM");
        item2.add("NYA");
        item2.add("NYY");

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(FormActivity.this,
                android.R.layout.simple_spinner_dropdown_item, item2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spJenispengut.setAdapter(adapter2);
        spJenispengcab.setAdapter(adapter2);
        spJenispengakhir.setAdapter(adapter2);
        ///////////////////////////////////
        //untuk membuat list sakelar
        List<String> item3 = new ArrayList<>();
        item3.add("");
        item3.add("MCB 10 A");
        item3.add("Sakelar 25 A");
        item3.add("NFB 50 A");
        item3.add("NFB 3 x 5 A");
        item3.add("MCB 3 x 10 A");

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(FormActivity.this,
                android.R.layout.simple_spinner_dropdown_item, item3);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSaklarutm.setAdapter(adapter3);
        spSaklarcb1.setAdapter(adapter3);
        spSaklarcb2.setAdapter(adapter3);
        ///////////////////////////////////
        //untuk membuat list phbk
        List<String> item4 = new ArrayList<>();
        item4.add("");
        item4.add("MCB 2 A");
        item4.add("MCB 4 A");
        item4.add("MCB 6 A");
        item4.add("MCCB 10 A");
        item4.add("MCCB 50 A");
        item4.add("NFB 3 x 5 A");
        item4.add("MCB 3 x 10 A");
        item4.add("Fuse 4A");
        item4.add("Fuse 6A");

        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(FormActivity.this,
                android.R.layout.simple_spinner_dropdown_item, item4);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPhbkUtama.setAdapter(adapter4);
        spPhbkcb1.setAdapter(adapter4);
        spPhbkcb2.setAdapter(adapter4);
        ///////////////////////////////////
        //untuk membuat list penampang
        List<String> item5 = new ArrayList<>();
        item5.add("");
        item5.add("3x2.5mm2");
        item5.add("3x4 mm2");
        item5.add("3x6 mm2");
        item5.add("3x10 mm2");
        item5.add("3x16 mm2");
        item5.add("4x6 mm2");
        item5.add("4x10 mm2");
        item5.add("4x16 mm2");

        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(FormActivity.this,
                android.R.layout.simple_spinner_dropdown_item, item5);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPenghantarutm.setAdapter(adapter5);
        spPenghantarcbang.setAdapter(adapter5);
        spPenghantarakhir.setAdapter(adapter5);
        spPenghantar3fs.setAdapter(adapter5);
        ///////////////////////////////////
        //untuk membuat list penguji
        List<String> item6 = new ArrayList<>();
        item6.add("");
        item6.add("Sesuai");
        item6.add("Tidak Sesuai");
        item6.add("Tidak Ada");

        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(FormActivity.this,
                android.R.layout.simple_spinner_dropdown_item, item6);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFittinglmp.setAdapter(adapter6);
        spPpkotakk.setAdapter(adapter6);
        spPpsakelar.setAdapter(adapter6);
        ///////////////////////////////////
        //untuk membuat list perlengkapan
        List<String> item7 = new ArrayList<>();
        item7.add("");
        item7.add("SNI");
        item7.add("Tidak SNI");

        ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(FormActivity.this,
                android.R.layout.simple_spinner_dropdown_item, item7);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTandakmp.setAdapter(adapter7);
        ///////////////////////////////////
        //untuk membuat list perlengkapan
        List<String> item8 = new ArrayList<>();
        item8.add("");
        item8.add("Baik");
        item8.add("Tidak Baik");

        ArrayAdapter<String> adapter8 = new ArrayAdapter<String>(FormActivity.this,
                android.R.layout.simple_spinner_dropdown_item, item8);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPengujipbbn.setAdapter(adapter8);
        ///////////////////////////////////
        //untuk membuat list kotakkontak
        List<String> item9 = new ArrayList<>();
        item9.add("");
        item9.add("Biasa");
        item9.add("Putar");
        item9.add("Tutup");
        item9.add("Tidak Ada");
        item9.add("Lainnya");

        ArrayAdapter<String> adapter9 = new ArrayAdapter<String>(FormActivity.this,
                android.R.layout.simple_spinner_dropdown_item, item9);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spJeniskk.setAdapter(adapter9);
        ///////////////////////////////////

        ButterKnife.bind(this);
        myCalendar = Calendar.getInstance();


        mActionToolbar = (Toolbar) findViewById(R.id.tabs_upload);
        setSupportActionBar(mActionToolbar);
        getSupportActionBar().setTitle("Form Pemeriksa");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (ContextCompat.checkSelfPermission(FormActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(FormActivity.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(FormActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_FINE_LOCATION);

            } else {

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
//        btnOpenDatePicker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new DatePickerDialog(FormActivity.this, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        myCalendar.get(Calendar.YEAR);
//                        myCalendar.get(Calendar.MONTH);
//                        myCalendar.get(Calendar.DAY_OF_MONTH);
//
//                        String formatTanggal = "yyyy-MM-dd";
//                        SimpleDateFormat sdf = new SimpleDateFormat(formatTanggal);
//                        etTgllhpp.setText(sdf.format(myCalendar.getTime()));
//                    }
//                },
//                        myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
//                        myCalendar.get(Calendar.DAY_OF_MONTH)
//                ).show();
//            }
//        });
        btnOpenDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               tahun = myCalendar.get(Calendar.YEAR);
               bulan = myCalendar.get(Calendar.MONTH)+1;
               hari  =  myCalendar.get(Calendar.DAY_OF_MONTH);

                String formatTgl = tahun +"-"+bulan+"-"+hari;
                etTgllhpp.setText(formatTgl);
            }
        });
        btnGetLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GpsTracker tracker = new GpsTracker(FormActivity.this);
                double latitude = tracker.getLatitude();
                double longitude = tracker.getLongitude();
                etLat.setText("" + latitude);
                etLng.setText("" + longitude);
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

    private void permission(Integer type) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (ContextCompat.checkSelfPermission(FormActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(FormActivity.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(FormActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_FINE_LOCATION);

            } else {
                Toast.makeText(FormActivity.this, "Mengijinkan Permission", Toast.LENGTH_SHORT).show();
                EasyImage.openCamera(FormActivity.this, 0);

            }
        }
    }


    private void postCamera1() {
        permission(CAMERA_REQUEST);
//        EasyImage.openCamera(FormActivity.this, CAMERA_REQUEST);
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intent, CAMERA_REQUEST);
    }

    private void postCamera2() {
        permission(CAMERA_REQUEST1);
    }

    private void postCamera3() {
        permission(CAMERA_REQUEST2);
    }

    private void postCamera4() {
        permission(CAMERA_REQUEST3);
    }

    private void postCamera5() {
        permission(CAMERA_REQUEST4);
    }


    @Override
    protected void onActivityResult(final int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                if (foto1 == null) {
                    foto1 = imageFile;
                    Picasso.with(FormActivity.this)
                            .load(foto1)
                            .into(ivFoto1);
                    return;
//                        }
                }

                if (foto2 == null) {
                    foto2 = imageFile;
                    Picasso.with(FormActivity.this)
                            .load(foto2)
                            .into(ivFoto2);
                    return;
                }

                if (foto3 == null) {
                    foto3 = imageFile;
                    Picasso.with(FormActivity.this)
                            .load(foto3)
                            .into(ivFoto3);
                    return;
                }
                if (foto4 == null) {
                    foto4 = imageFile;
                    Picasso.with(FormActivity.this)
                            .load(foto4)
                            .into(ivFoto4);
                    return;
                }
                if (foto5 == null) {
                    foto5 = imageFile;
                    Picasso.with(FormActivity.this)
                            .load(foto5)
                            .into(ivFoto5);
                    return;
                } else {
                    Toast.makeText(FormActivity.this, "Kosong 5", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
//                        super.onImagePickerError(e, source, type);
                Toast.makeText(FormActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });
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

        RequestBody idPelanggan = RequestBody.create(MediaType.parse("text/plain"), etIdPelanggan.getText().toString().trim());
        RequestBody noPendaftaran = RequestBody.create(MediaType.parse("text/plain"), etNopendaftaran.getText().toString().trim());
        RequestBody noSuratTgs = RequestBody.create(MediaType.parse("text/plain"), etNosurat.getText().toString().trim());
//        RequestBody noPemeriksaan = RequestBody.create(MediaType.parse("text/plain"), etNopemeriksa.getText().toString().trim());
        RequestBody noLhpp = RequestBody.create(MediaType.parse("text/plain"), etNolhpp.getText().toString().trim());
        RequestBody tglLhpp = RequestBody.create(MediaType.parse("text/plain"), etTgllhpp.getText().toString().trim());

//        RequestBody gambarIns = RequestBody.create(MediaType.parse("text/plain"), etGambarins.getText().toString().trim());
        RequestBody gambarIns1 = RequestBody.create(MediaType.parse("text/plain"), spGambarins.getSelectedItem().toString().trim());
//        RequestBody diagramT = RequestBody.create(MediaType.parse("text/plain"), etDiagramgt.getText().toString().trim());
        RequestBody diagramT1 = RequestBody.create(MediaType.parse("text/plain"), spDiagramgt.getSelectedItem().toString().trim());
//        RequestBody peUtama = RequestBody.create(MediaType.parse("text/plain"), etPeutama.getText().toString().trim());
        RequestBody peUtama1 = RequestBody.create(MediaType.parse("text/plain"), spPeutama.getSelectedItem().toString().trim());
//        RequestBody peCabang = RequestBody.create(MediaType.parse("text/plain"), etPecabang.getText().toString().trim());
        RequestBody peCabang1 = RequestBody.create(MediaType.parse("text/plain"), spPecabang.getSelectedItem().toString().trim());
//        RequestBody peAkhir = RequestBody.create(MediaType.parse("text/plain"), etPeakhir.getText().toString().trim());
        RequestBody peAkhir1 = RequestBody.create(MediaType.parse("text/plain"), spPeakhir.getSelectedItem().toString().trim());
//        RequestBody peKotakKontak = RequestBody.create(MediaType.parse("text/plain"), etPekotakkontak.getText().toString().trim());
        RequestBody peKotakKontak1 = RequestBody.create(MediaType.parse("text/plain"), spPekotakkontak.getSelectedItem().toString().trim());


//        RequestBody jenisPengut = RequestBody.create(MediaType.parse("text/plain"), etJenispengut.getText().toString().trim());
//        RequestBody jenisPengeCab = RequestBody.create(MediaType.parse("text/plain"), etJenispengcab.getText().toString().trim());
//        RequestBody jenisPengAkhir = RequestBody.create(MediaType.parse("text/plain"), etJenispengakhir.getText().toString().trim());
        RequestBody jenisPengut1 = RequestBody.create(MediaType.parse("text/plain"), spJenispengut.getSelectedItem().toString().trim());
        RequestBody jenisPengeCab1 = RequestBody.create(MediaType.parse("text/plain"), spJenispengcab.getSelectedItem().toString().trim());
        RequestBody jenisPengAkhir1 = RequestBody.create(MediaType.parse("text/plain"), spJenispengakhir.getSelectedItem().toString().trim());

        RequestBody penghantarbj = RequestBody.create(MediaType.parse("text/plain"), etPenghantarbj.getText().toString().trim());
        RequestBody penghantarbp = RequestBody.create(MediaType.parse("text/plain"), etPenghantarbp.getText().toString().trim());

//        RequestBody penghantarbs = RequestBody.create(MediaType.parse("text/plain"), etPenghantarbs.getText().toString().trim());
        RequestBody penghantarbs1 = RequestBody.create(MediaType.parse("text/plain"), spPenghantarbs.getSelectedItem().toString().trim());

//        RequestBody saklarUtm = RequestBody.create(MediaType.parse("text/plain"), etSaklarutm.getText().toString().trim());
//        RequestBody saklarCb1 = RequestBody.create(MediaType.parse("text/plain"), etSaklarcb1.getText().toString().trim());
//        RequestBody saklarCb2 = RequestBody.create(MediaType.parse("text/plain"), etSaklarcb2.getText().toString().trim());
        RequestBody saklarUtm1 = RequestBody.create(MediaType.parse("text/plain"), spSaklarutm.getSelectedItem().toString().trim());
        RequestBody saklarCb11 = RequestBody.create(MediaType.parse("text/plain"), spSaklarcb1.getSelectedItem().toString().trim());
        RequestBody saklarCb21 = RequestBody.create(MediaType.parse("text/plain"), spSaklarcb2.getSelectedItem().toString().trim());

//        RequestBody phbkUtama = RequestBody.create(MediaType.parse("text/plain"), etPhbkUtama.getText().toString().trim());
        RequestBody phbkUtama1 = RequestBody.create(MediaType.parse("text/plain"), spPhbkUtama.getSelectedItem().toString().trim());
//        RequestBody phbkCb1 = RequestBody.create(MediaType.parse("text/plain"), etPhbkcb1.getText().toString().trim());
        RequestBody phbkCb11 = RequestBody.create(MediaType.parse("text/plain"), spPhbkcb1.getSelectedItem().toString().trim());
//        RequestBody phbkCb2 = RequestBody.create(MediaType.parse("text/plain"), etPhbkcb2.getText().toString().trim());
        RequestBody phbkCb21 = RequestBody.create(MediaType.parse("text/plain"), spPhbkcb2.getSelectedItem().toString().trim());
//        RequestBody penghantarUtm = RequestBody.create(MediaType.parse("text/plain"), etPenghantarutm.getText().toString().trim());
        RequestBody penghantarUtm1 = RequestBody.create(MediaType.parse("text/plain"), spPenghantarutm.getSelectedItem().toString().trim());
//        RequestBody penghantarCb = RequestBody.create(MediaType.parse("text/plain"), etPenghantarcbang.getText().toString().trim());
        RequestBody penghantarCb1 = RequestBody.create(MediaType.parse("text/plain"), spPenghantarcbang.getSelectedItem().toString().trim());
//        RequestBody penghantarAkhir = RequestBody.create(MediaType.parse("text/plain"), etPenghantarakhir.getText().toString().trim());
        RequestBody penghantarAkhir1 = RequestBody.create(MediaType.parse("text/plain"), spPenghantarakhir.getSelectedItem().toString().trim());
//        RequestBody penghantar3fs = RequestBody.create(MediaType.parse("text/plain"), etPenghantar3fs.getText().toString().trim());
        RequestBody penghantar3fs1 = RequestBody.create(MediaType.parse("text/plain"), spPenghantar3fs.getSelectedItem().toString().trim());
//        RequestBody fittingLamp = RequestBody.create(MediaType.parse("text/plain"), etFittinglmp.getText().toString().trim());
        RequestBody fittingLamp1 = RequestBody.create(MediaType.parse("text/plain"), spFittinglmp.getSelectedItem().toString().trim());
//        RequestBody ppKotakk = RequestBody.create(MediaType.parse("text/plain"), etPpkotakk.getText().toString().trim());
        RequestBody ppKotakk1 = RequestBody.create(MediaType.parse("text/plain"), spPpkotakk.getSelectedItem().toString().trim());
//        RequestBody ppSakelar = RequestBody.create(MediaType.parse("text/plain"), etPpsakelar.getText().toString().trim());
        RequestBody ppSakelar1 = RequestBody.create(MediaType.parse("text/plain"), spPpsakelar.getSelectedItem().toString().trim());
        RequestBody tinggiKk = RequestBody.create(MediaType.parse("text/plain"), etTinggikk.getText().toString().trim());
        RequestBody tinggiPhbk = RequestBody.create(MediaType.parse("text/plain"), etTinggiphbk.getText().toString().trim());
//        RequestBody jenisKk = RequestBody.create(MediaType.parse("text/plain"), etJeniskk.getText().toString().trim());
        RequestBody jenisKk1 = RequestBody.create(MediaType.parse("text/plain"), spJeniskk.getSelectedItem().toString().trim());
//        RequestBody tandaKmp = RequestBody.create(MediaType.parse("text/plain"), etTandakmp.getText().toString().trim());
        RequestBody tandaKmp1 = RequestBody.create(MediaType.parse("text/plain"), spTandakmp.getSelectedItem().toString().trim());
//        RequestBody pengujiPbbn = RequestBody.create(MediaType.parse("text/plain"), etPengujipbbn.getText().toString().trim());
        RequestBody pengujiPbbn1 = RequestBody.create(MediaType.parse("text/plain"), spPengujipbbn.getSelectedItem().toString().trim());

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
//        RequestBody alamat_pendaftaran = RequestBody.create(MediaType.parse("text/plain"), etAlmtpen.getText().toString().trim());
        RequestBody lat = RequestBody.create(MediaType.parse("text/plain"), etLat.getText().toString().trim());
        RequestBody lng = RequestBody.create(MediaType.parse("text/plain"), etLng.getText().toString().trim());

        BaseApi api = RetrofitClient.getInstanceRetrofit();
        api.unggahPemeriksa(token, idPelanggan, noPendaftaran, noSuratTgs,
                noLhpp, tglLhpp, gambarIns1, diagramT1, peUtama1, peCabang1, peAkhir1,
                peKotakKontak1, jenisPengut1, jenisPengeCab1, jenisPengAkhir1,
                penghantarbj, penghantarbp, penghantarbs1, saklarUtm1, saklarCb11, saklarCb21
                , phbkUtama1, phbkCb11, phbkCb21, penghantarUtm1,
                penghantarCb1, penghantarAkhir1, penghantar3fs1, fittingLamp1, ppKotakk1, ppSakelar1,
                tinggiKk, tinggiPhbk, jenisKk1, tandaKmp1, pengujiPbbn1
                , jumlahPhbUtm, jumlahPhb1fs,
                jumlahPhb3fs, jumlahPhbCb, jumlahSalurancb, jumlahSaluranAkhir, jmlTitiklmp, jmlSakelar,
                kkb, kkk, tahananIsolasiP, resistanPmb, jmlmlu, jmlmlk, catatan
                ,location, lat, lng
                , body, body2, body3, body4, body5
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
                        Toast.makeText(FormActivity.this, "Gagal Menyimpan Data Belum Lengkap", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private boolean vallidasi() {
        if (etNolhpp.getText().toString().isEmpty()) {
            etNolhpp.setError("No LHPP tidak boleh kosong");
            etNolhpp.requestFocus();
            return false;
        }
        if (etTgllhpp.getText().toString().isEmpty()) {
            etTgllhpp.setError("Tanggal LHPP tidak boleh kosong");
            etTgllhpp.requestFocus();
            return false;
        }
//        if (etGambarins.getText().toString().isEmpty()) {
//            etGambarins.setError("Gambar instalasi tidak boleh kosong");
//            etGambarins.requestFocus();
//            return false;
//        }
//        if (etDiagramgt.getText().toString().isEmpty()) {
//            etDiagramgt.setError("Diagram tidak boleh kosong");
//            etDiagramgt.requestFocus();
//            return false;
//        }
//        if (etPeutama.getText().toString().isEmpty()) {
//            etPeutama.setError("Penghantar Proteksi PE(Utama) tidak boleh Kosong");
//            etPeutama.requestFocus();
//            return false;
//        }
//        if (etPecabang.getText().toString().isEmpty()) {
//            etPecabang.setError("Penghantar Proteksi PE(Cabang) tidak boleh Kosong");
//            etPecabang.requestFocus();
//            return false;
//        }
//        if (etPeakhir.getText().toString().isEmpty()) {
//            etPeakhir.setError("Penghantar Proteksi PE(Akhir) tidak boleh Kosong");
//            etPeakhir.requestFocus();
//            return false;
//        }
//        if (etPekotakkontak.getText().toString().isEmpty()) {
//            etPekotakkontak.setError("Penghantar Proteksi PE(Kotak Kontak) tidak boleh Kosong");
//            etPekotakkontak.requestFocus();
//            return false;
//        }
//        if (etJenispengut.getText().toString().isEmpty()) {
//            etJenispengut.setError("Jenis Penghantar Utama tidak boleh Kosong");
//            etJenispengut.requestFocus();
//            return false;
//        }
//        if (etJenispengcab.getText().toString().isEmpty()) {
//            etJenispengcab.setError("Jenis Penghantar Cabang tidak boleh Kosong");
//            etJenispengcab.requestFocus();
//            return false;
//        }
//        if (etJenispengakhir.getText().toString().isEmpty()) {
//            etJenispengakhir.setError("Jenis Penghantar Akhir tidak boleh Kosong");
//            etJenispengakhir.requestFocus();
//            return false;
//        }

        if (etPenghantarbj.getText().toString().isEmpty()) {
            etPenghantarbj.setError("Penghantar Pembumian(Jenis) tidak boleh Kosong");
            etPenghantarbj.requestFocus();
            return false;
        }
        if (etPenghantarbp.getText().toString().isEmpty()) {
            etPenghantarbp.setError("Penghantar Pembumian(Luas Penampang) tidak boleh Kosong");
            etPenghantarbp.requestFocus();
            return false;
        }
//        if (etPenghantarbs.getText().toString().isEmpty()) {
//            etPenghantarbs.setError("Penghantar Pembumian(Sistem pembumian) tidak boleh Kosong");
//            etPenghantarbs.requestFocus();
//            return false;
//        }
//        if (etSaklarutm.getText().toString().isEmpty()) {
//            etSaklarutm.setError("Sakelar (sakelar utama) tidak boleh Kosong");
//            etSaklarutm.requestFocus();
//            return false;
//        }
//        if (etSaklarcb1.getText().toString().isEmpty()) {
//            etSaklarcb1.setError("Sakelar (sakelar cabang1) tidak boleh Kosong");
//            etSaklarcb1.requestFocus();
//            return false;
//        }
//        if (etSaklarcb2.getText().toString().isEmpty()) {
//            etSaklarcb2.setError("Sakelar (sakelar cabang2) tidak boleh Kosong");
//            etSaklarcb2.requestFocus();
//            return false;
//        }
//        if (etPhbkUtama.getText().toString().isEmpty()) {
//            etPhbkUtama.setError("Proteksi Sirkit Akhir (PHBK Utama) tidak boleh Kosong");
//            etPhbkUtama.requestFocus();
//            return false;
//        }
//        if (etPhbkcb1.getText().toString().isEmpty()) {
//            etPhbkcb1.setError("Proteksi Sirkit Akhir (PHBK cabang1) tidak boleh Kosong");
//            etPhbkcb1.requestFocus();
//            return false;
//        }
//        if (etPhbkcb2.getText().toString().isEmpty()) {
//            etPhbkcb2.setError("Proteksi Sirkit Akhir (PHBK cabang2)tidak boleh Kosong");
//            etPhbkcb2.requestFocus();
//            return false;
//        }
//        if (etPenghantarutm.getText().toString().isEmpty()) {
//            etPenghantarutm.setError("Penampang penghantar Utama tidak boleh Kosong");
//            etPenghantarutm.requestFocus();
//            return false;
//        }
//        if (etPenghantarcbang.getText().toString().isEmpty()) {
//            etPenghantarcbang.setError("Penampang penghantar Cabang tidak boleh Kosong");
//            etPenghantarcbang.requestFocus();
//            return false;
//        }
//        if (etPenghantarakhir.getText().toString().isEmpty()) {
//            etPenghantarakhir.setError("Penampang penghantar Akhir tidak boleh Kosong");
//            etPenghantarakhir.requestFocus();
//            return false;
//        }
//        if (etPenghantar3fs.getText().toString().isEmpty()) {
//            etPenghantar3fs.setError("Penampang penghantar tiga Fasa tidak boleh Kosong");
//            etPenghantar3fs.requestFocus();
//            return false;
//        }
//        if (etFittinglmp.getText().toString().isEmpty()) {
//            etFittinglmp.setError("Penguji Polaritas (Fitting Lampu) tidak boleh Kosong");
//            etFittinglmp.requestFocus();
//            return false;
//        }
//        if (etPpkotakk.getText().toString().isEmpty()) {
//            etPpkotakk.setError("Penguji Polaritas (Kotak Kontak) tidak boleh Kosong");
//            etPpkotakk.requestFocus();
//            return false;
//        }
//        if (etPpsakelar.getText().toString().isEmpty()) {
//            etPpsakelar.setError("Penguji Polaritas (Sakelar) tidak boleh Kosong");
//            etPpsakelar.requestFocus();
//            return false;
//        }
        if (etTinggikk.getText().toString().isEmpty()) {
            etTinggikk.setError("Pemasangan1 tidak boleh Kosong");
            etTinggikk.requestFocus();
            return false;
        }
        if (etTinggiphbk.getText().toString().isEmpty()) {
            etTinggiphbk.setError("Pemasangan2 tidak boleh Kosong");
            etTinggiphbk.requestFocus();
            return false;
        }
//        if (etJeniskk.getText().toString().isEmpty()) {
//            etJeniskk.setError("Jenis Kotak Kontak tidak boleh Kosong");
//            etJeniskk.requestFocus();
//            return false;
//        }
//        if (etTandakmp.getText().toString().isEmpty()) {
//            etTandakmp.setError("Tanda Komponen SNI tidak boleh Kosong");
//            etTandakmp.requestFocus();
//            return false;
//        }
//        if (etPengujipbbn.getText().toString().isEmpty()) {
//            etPengujipbbn.setError("Penguji Pembebanan tidak boleh Kosong");
//            etPengujipbbn.requestFocus();
//            return false;
//        }
        if (etJumlahphbutm.getText().toString().isEmpty()) {
            etJumlahphbutm.setError("Jumlah PHB utama tidak boleh Kosong");
            etJumlahphbutm.requestFocus();
            return false;
        }
        if (etJumlahphb1fs.getText().toString().isEmpty()) {
            etJumlahphb1fs.setError("Jumlah PHB 1 phasa tidak boleh Kosong");
            etJumlahphb1fs.requestFocus();
            return false;
        }
        if (etJumlahphb3fs.getText().toString().isEmpty()) {
            etJumlahphb3fs.setError("Jumlah PHB 3 phasa tidak boleh Kosong");
            etJumlahphb3fs.requestFocus();
            return false;
        }
        if (etJumlahphbcb.getText().toString().isEmpty()) {
            etJumlahphbcb.setError("Jumlah PHB Cabang tidak boleh Kosong");
            etJumlahphbcb.requestFocus();
            return false;
        }
        if (etJmlsalurancb.getText().toString().isEmpty()) {
            etJmlsalurancb.setError("Jumlah Saluran Cabang tidak boleh Kosong");
            etJmlsalurancb.requestFocus();
            return false;
        }
        if (etJmlsaluranakhir.getText().toString().isEmpty()) {
            etJmlsaluranakhir.setError("Jumlah Saluran Akhir tidak boleh Kosong");
            etJmlsaluranakhir.requestFocus();
            return false;
        }
        if (etJmltitiklmp.getText().toString().isEmpty()) {
            etJmltitiklmp.setError("Jumlah Titik Lampu tidak boleh Kosong");
            etJmltitiklmp.requestFocus();
            return false;
        }
        if (etJmlsakelar.getText().toString().isEmpty()) {
            etJmlsakelar.setError("Jumlah Sakelar tidak boleh Kosong");
            etJmlsakelar.requestFocus();
            return false;
        }
        if (etKkb.getText().toString().isEmpty()) {
            etKkb.setError("KKB tidak boleh Kosong");
            etKkb.requestFocus();
            return false;
        }
        if (etKkk.getText().toString().isEmpty()) {
            etKkk.setError("KKK tidak boleh Kosong");
            etKkk.requestFocus();
            return false;
        }
        if (etThnislp.getText().toString().isEmpty()) {
            etThnislp.setError("Tahanan Isolasi Penghantar tidak boleh Kosong");
            etThnislp.requestFocus();
            return false;
        }
        if (etResistanpmb.getText().toString().isEmpty()) {
            etResistanpmb.setError("Resisten Pembumian tidak boleh Kosong");
            etResistanpmb.requestFocus();
            return false;
        }
        if (etJmlmlu.getText().toString().isEmpty()) {
            etJmlmlu.setError("Jumlah Motor Listrik (Unit) tidak boleh Kosong");
            etJmlmlu.requestFocus();
            return false;
        }
        if (etJmlmlk.getText().toString().isEmpty()) {
            etJmlmlk.setError("Jumlah Motor Listrik (Kw) tidak boleh Kosong");
            etJmlmlk.requestFocus();
            return false;
        }
        if (etCatatan.getText().toString().isEmpty()) {
            etCatatan.setError("Catatan tidak boleh Kosong");
            etCatatan.requestFocus();
            return false;
        }
        if (etLocation.getText().toString().isEmpty()) {
            etLocation.setError("Lokasi tidak boleh Kosong");
            etLocation.requestFocus();
            return false;
        }
        if (etLat.getText().toString().isEmpty()) {
            etLat.setError("Latitude tidak boleh Kosong");
            return false;
        }
        if (etLng.getText().toString().isEmpty()) {
            etLng.setError("Longitude tidak boleh Kosong");
            etLng.requestFocus();
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
                            String id_pelanggan = object.getString("id");
                            String no_pendaftaran = object.getString("no_pendaftaran");
                            String nama_pendaftaran = object.getString("nama");
                            String alamat_pendaftaran = object.getString("alamat");
                            String tarif_pendaftaran = object.getString("jenis_tarif");
                            String daya_pendaftaran = object.getString("daya");
                            String nama_btl = object.getString("nama_btl");
                            String no_suratt = object.getString("no_surat");
                            String namap1 = object.getString("pemeriksa1");
                            String namap2 = object.getString("pemeriksa2");

                            etIdPelanggan.setText(id_pelanggan);
                            etNopendaftaran.setText(no_pendaftaran);
                            etNamapen.setText(nama_pendaftaran);
                            etAlmtpen.setText(alamat_pendaftaran);
                            etLocation.setText(alamat_pendaftaran);
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
        etIdPelanggan = (EditText) findViewById(R.id.et_idPelanggan);
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
//        etNopemeriksa = (EditText) findViewById(R.id.et_nopemeriksa);
        etNolhpp = (EditText) findViewById(R.id.et_nolhpp);
        etTgllhpp = (EditText) findViewById(R.id.et_tgllhpp);
//        etGambarins = (EditText) findViewById(R.id.et_gambarins);
//
//        etDiagramgt = (EditText) findViewById(R.id.et_diagramgt);
//        etPeutama = (EditText) findViewById(R.id.et_peutama);
//        etPecabang = (EditText) findViewById(R.id.et_pecabang);
//        etPeakhir = (EditText) findViewById(R.id.et_peakhir);
//        etPekotakkontak = (EditText) findViewById(R.id.et_pekotakkontak);
//        etJenispengut = (EditText) findViewById(R.id.et_jenispengut);
//        etJenispengcab = (EditText) findViewById(R.id.et_jenispengcab);
//        etJenispengakhir = (EditText) findViewById(R.id.et_jenispengakhir);
        etPenghantarbj = (EditText) findViewById(R.id.et_penghantarbj);
        etPenghantarbp = (EditText) findViewById(R.id.et_penghantarbp);
//        etPenghantarbs = (EditText) findViewById(R.id.et_penghantarbs);
//        etSaklarutm = (EditText) findViewById(R.id.et_saklarutm);
//        etSaklarcb1 = (EditText) findViewById(R.id.et_saklarcb1);
//        etSaklarcb2 = (EditText) findViewById(R.id.et_saklarcb2);
//        etPhbkUtama = (EditText) findViewById(R.id.et_phbkUtama);
//        etPhbkcb1 = (EditText) findViewById(R.id.et_phbkcb1);
//        etPhbkcb2 = (EditText) findViewById(R.id.et_phbkcb2);
//        etPenghantarutm = (EditText) findViewById(R.id.et_penghantarutm);
//        etPenghantarcbang = (EditText) findViewById(R.id.et_penghantarcbang);
//        etPenghantarakhir = (EditText) findViewById(R.id.et_penghantarakhir);
//        etPenghantar3fs = (EditText) findViewById(R.id.et_penghantar3fs);
//        etFittinglmp = (EditText) findViewById(R.id.et_fittinglmp);
//        etPpkotakk = (EditText) findViewById(R.id.et_ppkotakk);
//        etPpsakelar = (EditText) findViewById(R.id.et_ppsakelar);
        etTinggikk = (EditText) findViewById(R.id.et_tinggikk);
        etTinggiphbk = (EditText) findViewById(R.id.et_tinggiphbk);
//        etJeniskk = (EditText) findViewById(R.id.et_jeniskk);
//        etTandakmp = (EditText) findViewById(R.id.et_tandakmp);
//        etPengujipbbn = (EditText) findViewById(R.id.et_pengujipbbn);
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

        btnGetLokasi = (Button) findViewById(R.id.btn_lokasi);
        btnOpenDatePicker = (Button) findViewById(R.id.btnOpenDatePicker);
        btnSimpanupload = (Button) findViewById(R.id.btn_simpanupload);
        btnBatal = (Button) findViewById(R.id.btn_batal);
        ivFoto1 = (ImageView) findViewById(R.id.iv_foto1);
        ivFoto2 = (ImageView) findViewById(R.id.iv_foto2);
        ivFoto3 = (ImageView) findViewById(R.id.iv_foto3);
        ivFoto4 = (ImageView) findViewById(R.id.iv_foto4);
        ivFoto5 = (ImageView) findViewById(R.id.iv_foto5);
        spGambarins = (Spinner) findViewById(R.id.sp_gambarins);
        spDiagramgt = (Spinner) findViewById(R.id.sp_diagramgt);
        spPeutama = (Spinner) findViewById(R.id.sp_peutama);
        spPecabang = (Spinner) findViewById(R.id.sp_pecabang);
        spPeakhir = (Spinner) findViewById(R.id.sp_peakhir);
        spPekotakkontak = (Spinner) findViewById(R.id.sp_pekotakkontak);
        spPenghantarbs = (Spinner) findViewById(R.id.sp_penghantarbs);
        spJenispengut = (Spinner) findViewById(R.id.sp_jenispengut);
        spJenispengcab = (Spinner) findViewById(R.id.sp_jenispengcab);
        spJenispengakhir = (Spinner) findViewById(R.id.sp_jenispengakhir);
        spSaklarutm = (Spinner) findViewById(R.id.sp_saklarutm);
        spSaklarcb1 = (Spinner) findViewById(R.id.sp_saklarcb1);
        spSaklarcb2 = (Spinner) findViewById(R.id.sp_saklarcb2);

        spPhbkUtama = (Spinner) findViewById(R.id.sp_phbkUtama);
        spPhbkcb1 = (Spinner) findViewById(R.id.sp_phbkcb1);
        spPhbkcb2 = (Spinner) findViewById(R.id.sp_phbkcb2);
        spPenghantarutm = (Spinner) findViewById(R.id.sp_penghantarutm);
        spPenghantarcbang = (Spinner) findViewById(R.id.sp_penghantarcbang);
        spPenghantarakhir = (Spinner) findViewById(R.id.sp_penghantarakhir);
        spPenghantar3fs = (Spinner) findViewById(R.id.sp_penghantar3fs);
        spFittinglmp = (Spinner) findViewById(R.id.sp_fittinglmp);
        spPpkotakk = (Spinner) findViewById(R.id.sp_ppkotakk);
        spPpsakelar = (Spinner) findViewById(R.id.sp_ppsakelar);
        spJeniskk = (Spinner) findViewById(R.id.sp_jeniskk);
        spTandakmp = (Spinner) findViewById(R.id.sp_tandakmp);
        spPengujipbbn = (Spinner) findViewById(R.id.sp_pengujipbbn);
    }
}
