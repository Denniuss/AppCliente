package com.newmantech.appcliente.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;
import com.newmantech.appcliente.R;
import com.newmantech.appcliente.utils.TemplatePDF;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PersonalizarModeloActivity extends AppCompatActivity {
    Button btnAgregarImagen, btnGuardar;
    ImageView ivLogo;
    private TemplatePDF templatePDF;
    private static final int FILE_SELECT_CODE1 = 2;
    Image img;
    private final int RC_PERMISSION_WRITE_EXTERNAL_STORAGE = 100;

    Toolbar toolbar;
    private StorageReference mStorageRef;
    public static StorageReference riversRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalizar_modelo);

        mStorageRef = FirebaseStorage.getInstance().getReference();
        requestWriteExternalStoragePermission();
        requestReadExternalStoragePermission();
        btnAgregarImagen = findViewById(R.id.btnAgregarImagen);
        ivLogo = findViewById(R.id.ivLogo);
        toolbar = findViewById(R.id.toolbar);
        setupUI(getWindow().getDecorView().getRootView());
        toolbar.setTitle(R.string.piscosPerson);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_35dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnGuardar = findViewById(R.id.btnGuardar);
        btnAgregarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fn_SelecFile("Selecciona foto", FILE_SELECT_CODE1);
            }
        });
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                templatePDF = new TemplatePDF(getApplicationContext(), img);
                templatePDF.openDocument();
                templatePDF.addParagraph("Logo elegido");
                templatePDF.addImage();
                templatePDF.closeDocument();
                pdfView();
                guardarPdf();
            }
        });
    }
    public void pdfView(){
        templatePDF.appViewPdf(this);
        // leer y escribir en el dispositivo
    }

    public void guardarPdf(){
        //Uri file = Uri.fromFile(new File("path/to/images/rivers.jpg"));
        Uri file = Uri.fromFile(templatePDF.pdfFile);
        String s = String.valueOf(System.currentTimeMillis());
        riversRef = mStorageRef.child("PDF/Logo.pdf");

        riversRef.putFile(file)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Uri downloadUrl = taskSnapshot.getUploadSessionUri();

                        riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String url = uri.toString();

                            }
                        });
                        finish();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Log.e("Error", exception.toString());
                    }
                });
    }

    private void getImg(){

        //Bitmap bm = BitmapFactory.decodeResource(getResources(), getResources().getDrawable(ivIcono));
        Bitmap bitmap = ((BitmapDrawable)ivLogo.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        img= null;
        byte[] byteArray = stream.toByteArray();
        try {
            img = Image.getInstance(byteArray);
        } catch (BadElementException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fn_SelecFile(String msg, int file_select){

        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.setType("image/");
        //i.addCategory(Intent.CATEGORY_OPENABLE);

        /*Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        Log.d("DMA_LECTOR", " file_select " + file_select);
        i.setType("*");*/
        //i.addCategory(Intent.CATEGORY_OPENABLE);
        try{
            startActivityForResult(Intent.createChooser(i,msg), file_select);
        }catch (android.content.ActivityNotFoundException ex){
        }

    }

    private void requestWriteExternalStoragePermission() {
        // Should we show an explanation?
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            new AlertDialog.Builder(this)
                    .setTitle("Inform and request")
                    .setMessage("You need to enable permissions, bla bla bla")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(PersonalizarModeloActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, RC_PERMISSION_WRITE_EXTERNAL_STORAGE);
                        }
                    })
                    .show();
        } else {
            ActivityCompat.requestPermissions(PersonalizarModeloActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, RC_PERMISSION_WRITE_EXTERNAL_STORAGE);
        }
    }

    public void requestReadExternalStoragePermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            new AlertDialog.Builder(this)
                    .setTitle("Inform and request")
                    .setMessage("You need to enable permissions, bla bla bla")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(PersonalizarModeloActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, RC_PERMISSION_WRITE_EXTERNAL_STORAGE);
                        }
                    })
                    .show();
        } else {
            ActivityCompat.requestPermissions(PersonalizarModeloActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, RC_PERMISSION_WRITE_EXTERNAL_STORAGE);
        }
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        }
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == FILE_SELECT_CODE1){
            Uri uri = data.getData();
            //String ruta = Util.getPath(this, uri);
            //File file = new File(ruta);
            Bitmap bitmap = null;//= BitmapFactory.decodeFile(file.getAbsolutePath());// DMA_VALIDAR

            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                //bitmapBajaResolucion = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uriFotoBajeResolucion);
            } catch (IOException e) {
                e.printStackTrace();
            }

            bitmap = redimensionarImagenMaximo(bitmap, 500, 900);

            //Picasso.with(MisFormulasActivity.this).load(file).into(img_photo);
            //sTitulo = data.getStringExtra("titulo");
            //sDescripcion = data.getStringExtra("descripcion");
            //ByteArrayOutputStream stream = new ByteArrayOutputStream();
            //ByteArrayOutputStream stream = new ByteArrayOutputStream();
            if(bitmap != null){
                //bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                //imagenFOTO = stream.toByteArray();
                ivLogo.setImageBitmap(bitmap);
                getImg();
            }

        super.onActivityResult(requestCode, resultCode, data);
    }
    }


        public Bitmap redimensionarImagenMaximo(Bitmap mBitmap, float newWidth, float newHeigth){
            //Redimensionamos
            int width = mBitmap.getWidth();
            int height = mBitmap.getHeight();
            float scaleWidth = ((float) newWidth) / width;
            float scaleHeight = ((float) newHeigth) / height;
            // create a matrix for the manipulation
            Matrix matrix = new Matrix();
            // resize the bit map
            matrix.postScale(scaleWidth, scaleHeight);
            // recreate the new Bitmap
            return Bitmap.createBitmap(mBitmap, 0, 0, width, height, matrix, false);
        }

    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideKeyb();
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }
    private void hideKeyb() {
        try {
            InputMethodManager inputManager = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) { //No debe pasar nunca
            e.printStackTrace();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        goBack();
        return true;
    }

    private void goBack() {
        setResult(RESULT_CANCELED);
        finish();
    }

    }
