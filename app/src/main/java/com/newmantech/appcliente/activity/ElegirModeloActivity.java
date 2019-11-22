package com.newmantech.appcliente.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;

import com.newmantech.appcliente.R;
import com.newmantech.appcliente.adapter.AdapterElegirModelo;
import com.newmantech.appcliente.model.EntityElegirModelo;

import java.util.ArrayList;

public class ElegirModeloActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView lvPhotos;
    private ArrayList<EntityElegirModelo> arrayList;
    private AdapterElegirModelo adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elegir_modelo);
        toolbar = findViewById(R.id.toolbar);
        lvPhotos = findViewById(R.id.lvPhotos);

        configureToolbar();
        setModelos();
    }

    public void setModelos(){
        arrayList = new ArrayList<>();
        arrayList.add(new EntityElegirModelo("https://i.linio.com/p/940faec658e586eec1bb289889e78a2e-product.jpg", "BODAS ORO G1", 1));
        arrayList.add(new EntityElegirModelo("https://i.linio.com/p/940faec658e586eec1bb289889e78a2e-product.jpg", "BODAS ORO G2", 2));
        adapter = new AdapterElegirModelo(arrayList, this);
        lvPhotos.setAdapter(adapter);
    }

    public void configureToolbar(){
        setupUI(getWindow().getDecorView().getRootView());
        toolbar.setTitle(R.string.elegirModelo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_35dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
