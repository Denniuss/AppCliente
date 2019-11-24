package com.newmantech.appcliente.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.newmantech.appcliente.R;

public class DetalleCarritoActivity extends AppCompatActivity {

    Toolbar toolbar;
    private Button btnPagar, btnElegirModelo;
    private double mSubTotal = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_carrito);

        toolbar = findViewById(R.id.toolbar);
        btnPagar = findViewById(R.id.btnPagar);
        btnElegirModelo = findViewById(R.id.btnElegirModelo);

        setupUI(getWindow().getDecorView().getRootView());
        toolbar.setTitle(R.string.piscosElegidos);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_35dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent paymentIntent = new Intent(DetalleCarritoActivity.this, ComfirmaCompraActivity.class);
                paymentIntent.putExtra("TOTAL_PRICE", mSubTotal/Double.valueOf(getString(R.string.tipo_cambio)));
                startActivity(paymentIntent);
            }
        });
        btnElegirModelo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetalleCarritoActivity.this, ElegirModeloActivity.class);
                startActivity(i);
            }
        });
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
