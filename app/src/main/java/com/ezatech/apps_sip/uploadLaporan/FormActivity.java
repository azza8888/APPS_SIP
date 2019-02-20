package com.ezatech.apps_sip.uploadLaporan;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.ezatech.apps_sip.MainActivity;
import com.ezatech.apps_sip.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

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

        ivFotodepan = (ImageView) findViewById(R.id.iv_fotodepan);
        btnGallery = (Button) findViewById(R.id.btn_gallery);
        btnUpload1 = (Button) findViewById(R.id.btn_upload1);
        ivFotokanan = (ImageView) findViewById(R.id.iv_fotokanan);
        btnGallery2 = (Button) findViewById(R.id.btn_gallery2);
        btnUpload2 = (Button) findViewById(R.id.btn_upload2);
        ivFotokiri = (ImageView) findViewById(R.id.iv_fotokiri);
        btnGallery3 = (Button) findViewById(R.id.btn_gallery3);
        btnUpload3 = (Button) findViewById(R.id.btn_upload3);
        ivFotobelakang = (ImageView) findViewById(R.id.iv_fotobelakang);
        btnGallery4 = (Button) findViewById(R.id.btn_gallery4);
        btnUpload4 = (Button) findViewById(R.id.btn_upload4);
        btnSimpanupload = (Button) findViewById(R.id.btn_simpanupload);
        btnBatal = (Button) findViewById(R.id.btn_batal);

//        btnGallery.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_PICK);
//                startActivityForResult(Intent.createChooser(intent, "Buka Galeri"), REQUEST_GALLERY);
//            }
//        });
//
//        btnUpload1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(Intent.createChooser(cameraIntent,"Ambil Foto"), CAMERA_REQUEST);
//            }
//        });
//
//        btnGallery2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_PICK);
//                startActivityForResult(Intent.createChooser(intent, "Buka Galeri"), REQUEST_GALLERY);
//            }
//        });
//        btnUpload2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(cameraIntent, CAMERA_REQUEST);
//            }
//        });
//
//        btnGallery3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_PICK);
//                startActivityForResult(Intent.createChooser(intent, "Buka Galeri"), REQUEST_GALLERY);
//            }
//        });
//
//        btnUpload3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(cameraIntent, CAMERA_REQUEST);
//            }
//        });
//
//        btnGallery4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_PICK);
//                startActivityForResult(Intent.createChooser(intent, "Buka Galeri"), REQUEST_GALLERY);
//            }
//        });
//
//        btnUpload4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(cameraIntent, CAMERA_REQUEST);
//            }
//        });

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
        initView();
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            if (requestCode == REQUEST_GALLERY) {
//                Uri dataimage = data.getData();
//                String[] imageprojection = {MediaStore.Images.Media.DATA};
//                Cursor cursor = getContentResolver().query(dataimage, imageprojection, null, null, null);
//                if (requestCode == CAMERA_REQUEST) {
//                    Uri dataimage1 = data.getData();
//                    String[] imageprojection1 = {MediaStore.Images.Media.DATA};
//                    Cursor cursor1 = getContentResolver().query(dataimage1, imageprojection1, null, null, null);
//                }
//
//                if (cursor != null) {
//                    cursor.moveToFirst();
//                    int indexImage = cursor.getColumnIndex(imageprojection[0]);
//                    part_image = cursor.getString(indexImage);
//
//                    if (part_image != null) {
//                        File image = new File(part_image);
//                        ivFotodepan.setImageBitmap(BitmapFactory.decodeFile(image.getAbsolutePath()));
//                    }
//                }
//            }
//        }
//    }


    //button back toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    private void initView() {


    }
}
