package com.newmantech.appcliente.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
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
import com.newmantech.appcliente.adapter.AdapterPiscoPersonalizado;
import com.newmantech.appcliente.model.EntityPiscoPersonalizado;

import java.util.ArrayList;

public class PiscosPersonalizadosActivity extends AppCompatActivity {

    Toolbar toolbar;
    FloatingActionButton fabCar;
    private ListView lvPiscos;
    AdapterPiscoPersonalizado adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piscos_personalizados);

        toolbar = findViewById(R.id.toolbar);
        fabCar = findViewById(R.id.fabCar);
        lvPiscos = findViewById(R.id.lvPiscos);


        setupUI(getWindow().getDecorView().getRootView());
        toolbar.setTitle(R.string.piscosPerson);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_35dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fabCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PiscosPersonalizadosActivity.this, DetalleCarritoActivity.class);
                startActivity(i);
            }
        });
        generateList();
    }

    public void generateList(){
        ArrayList<EntityPiscoPersonalizado> arrayList = new ArrayList<>();
        arrayList.add(new EntityPiscoPersonalizado(1, "500 ml."));
        arrayList.add(new EntityPiscoPersonalizado(3, "Pisco Italia / Torontel / Acholado"));
        arrayList.add(new EntityPiscoPersonalizado(2, "Pisco Italia / Torontel"));
        arrayList.add(new EntityPiscoPersonalizado(3, "Pisco Mosto Verde"));
        arrayList.add(new EntityPiscoPersonalizado(3, "Pisco "));
        arrayList.add(new EntityPiscoPersonalizado(1, "900 ml."));
        arrayList.add(new EntityPiscoPersonalizado(2, "Pisco Quebranta"));
        adapter = new AdapterPiscoPersonalizado(arrayList, this);
        lvPiscos.setAdapter(adapter);

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
