package com.newmantech.appcliente;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CarritoCompraActivity extends AppCompatActivity {
    private RecyclerView recycler;
    private RecyclerView.LayoutManager lManager;
    private List<ItemCarritoCompra> items = new ArrayList();
    private RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito_compra);

        recycler = findViewById(R.id.reciclador_productosc);
        recycler.setHasFixedSize(true);
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);
        FillItemCarritoCompra();
        adapter = new ItemCarritoCompraAdapter(items);
        recycler.setAdapter(adapter);
        // Obtener el Recycler
       // recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        //lManager = new LinearLayoutManager(this);



    }
    private void FillItemCarritoCompra() {

        items.add(new ItemCarritoCompra(1, 1,"Pisco Tabernero Quebranta","","Tabernero","https://i.linio.com/p/940faec658e586eec1bb289889e78a2e-product.jpg",37.00,1));
        items.add(new ItemCarritoCompra(1, 2,"Pisco La Botija","","Tabernero","https://i.linio.com/p/0f6f58efe48abf6cb6d5d7a2ae9ab200-product.jpg",37.00,2));
        items.add(new ItemCarritoCompra(1, 3,"Pisco Demonio de los Andes","","Tacama","https://i.linio.com/p/4a2ebcba614aaab74e43a0a3a07a6cf5-product.jpg",37.00,1));
        items.add(new ItemCarritoCompra(1, 4,"Pisco Tres Generaciones Acholado","","Tres Generaciones","https://i.linio.com/p/739031b2bfe811c11557813af97f2562-product.jpg",37.00,1));
        items.add(new ItemCarritoCompra(1, 5,"Pisco Porton","","Pisco Porton","https://i.linio.com/p/7d119d0a78732017bfd7b928c25f9c6a-product.jpg",37.00,1));
    }

}
