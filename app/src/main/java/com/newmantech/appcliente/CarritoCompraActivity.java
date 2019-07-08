package com.newmantech.appcliente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class CarritoCompraActivity extends AppCompatActivity {
    private RecyclerView recycler;
    private RecyclerView.LayoutManager lManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito_compra);

        // Obtener el Recycler
        recycler = findViewById(R.id.reciclador_productosc);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);



    }
}
