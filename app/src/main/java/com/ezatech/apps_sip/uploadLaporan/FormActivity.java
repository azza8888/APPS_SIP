package com.ezatech.apps_sip.uploadLaporan;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ezatech.apps_sip.MainActivity;
import com.ezatech.apps_sip.R;
import com.ezatech.apps_sip.logRes.LoginActivity;
import com.ezatech.apps_sip.pengaturan.PengaturanActivity;

import java.io.File;

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
    private int MY_PERMISSIONS_REQUEST_FINE_LOCATION= 1;
    private String part_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

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

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Buka Galeri"), REQUEST_GALLERY);
            }
        });

        btnUpload1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(Intent.createChooser(cameraIntent,"Ambil Foto"), CAMERA_REQUEST);
            }
        });

        btnGallery2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Buka Galeri"), REQUEST_GALLERY);
            }
        });
        btnUpload2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        btnGallery3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Buka Galeri"), REQUEST_GALLERY);
            }
        });

        btnUpload3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        btnGallery4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Buka Galeri"), REQUEST_GALLERY);
            }
        });

        btnUpload4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_GALLERY) {
                Uri dataimage = data.getData();
                String[] imageprojection = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(dataimage, imageprojection, null, null, null);
                if (requestCode == CAMERA_REQUEST) {
                    Uri dataimage1 = data.getData();
                    String[] imageprojection1 = {MediaStore.Images.Media.DATA};
                    Cursor cursor1 = getContentResolver().query(dataimage1, imageprojection1, null, null, null);
                }

                if (cursor != null) {
                    cursor.moveToFirst();
                    int indexImage = cursor.getColumnIndex(imageprojection[0]);
                    part_image = cursor.getString(indexImage);

                    if (part_image != null) {
                        File image = new File(part_image);
                        ivFotodepan.setImageBitmap(BitmapFactory.decodeFile(image.getAbsolutePath()));
                    }
                }
            }
        }
    }


    //button back toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

}
