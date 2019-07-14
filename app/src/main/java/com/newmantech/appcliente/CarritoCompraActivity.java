package com.newmantech.appcliente;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CarritoCompraActivity extends AppCompatActivity {
    private RecyclerView recycler;
    private RecyclerView.LayoutManager lManager;
    private List<ItemCarritoCompra> items = new ArrayList();
    private RecyclerView.Adapter adapter;
    private double mSubTotal = 0;
    private TextView subTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito_compra);
        subTotal = (TextView)findViewById(R.id.sub_total);

        recycler = findViewById(R.id.reciclador_productosc);
        recycler.setHasFixedSize(true);
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);
        FillItemCarritoCompra();
        mSubTotal = getTotalPrice(items);
        adapter = new ItemCarritoCompraAdapter(items);
        recycler.setAdapter(adapter);

        subTotal.setText("Sub Total: S/ " + String.valueOf(mSubTotal));

        // Obtener el Recycler
       // recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        //lManager = new LinearLayoutManager(this);

        Button btnPagar = (Button)findViewById(R.id.btnPagar);
        assert btnPagar != null;
        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent paymentIntent = new Intent(CarritoCompraActivity.this, ComfirmaCompraActivity.class);
                paymentIntent.putExtra("TOTAL_PRICE", mSubTotal);
                startActivity(paymentIntent);
            }
        });


        Button btnComprar = (Button)findViewById(R.id.btnComprar);
        assert btnComprar != null;
        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent comprarIntent = new Intent(CarritoCompraActivity.this, ListadoProductosNActivity.class);
                startActivity(comprarIntent);
            }
        });

    }
    private void FillItemCarritoCompra() {

        items.add(new ItemCarritoCompra(1, 1,"Pisco Tabernero Quebranta","","Tabernero","https://i.linio.com/p/940faec658e586eec1bb289889e78a2e-product.jpg",37.00,1));
        items.add(new ItemCarritoCompra(1, 2,"Pisco La Botija","","Tabernero","https://i.linio.com/p/0f6f58efe48abf6cb6d5d7a2ae9ab200-product.jpg",37.00,2));
        items.add(new ItemCarritoCompra(1, 3,"Pisco Demonio de los Andes","","Tacama","https://i.linio.com/p/4a2ebcba614aaab74e43a0a3a07a6cf5-product.jpg",37.00,1));
        items.add(new ItemCarritoCompra(1, 4,"Pisco Tres Generaciones Acholado","","Tres Generaciones","https://i.linio.com/p/739031b2bfe811c11557813af97f2562-product.jpg",37.00,1));
        items.add(new ItemCarritoCompra(1, 5,"Pisco Porton","","Pisco Porton","https://i.linio.com/p/7d119d0a78732017bfd7b928c25f9c6a-product.jpg",37.00,1));
    }

    private double getTotalPrice(List<ItemCarritoCompra> mProducts){
        double totalCost = 0;
        for(int i = 0; i < mProducts.size(); i++){
            ItemCarritoCompra pObject = mProducts.get(i);
            totalCost = totalCost + pObject.getCantidad() * pObject.getPrecio();
        }
        return totalCost;
    }

}
