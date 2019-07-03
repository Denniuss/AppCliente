package com.newmantech.appcliente;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetalleActivity extends AppCompatActivity {
    public ImageView foto;
    public TextView marca;
    public TextView nombre;
    public TextView preciocompra;
    public TextView porcentajedescuento;
    public TextView preciocatalogo;
    public Button btnDetalles;
    public Button btnCarrito;
    public Button btnComprar;


    Fragment currentFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

/*
        currentFragment = new DetallePedidoFragment();
        changeFragment(currentFragment);

        Toolbar toolbar = (Toolbar) currentFragment.getView().findViewById(R.id.toolbar);
        ((AppCompatActivity)currentFragment.getActivity()).setSupportActionBar(toolbar);
 */
       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        foto = (ImageView) findViewById(R.id.foto);
        marca = (TextView) findViewById(R.id.marca);
        nombre = (TextView) findViewById(R.id.nombre);
        preciocompra = (TextView) findViewById(R.id.preciocompra);
        porcentajedescuento = (TextView) findViewById(R.id.porcentajedescuento);
        //idpedido = (TextView) findViewById(R.id.idpedido);
        preciocatalogo = (TextView) findViewById(R.id.preciocatalogo);
        btnDetalles = (Button) findViewById(R.id.btnDetalles);
        btnCarrito = (Button) findViewById(R.id.btnCarrito);
        btnComprar = (Button) findViewById(R.id.btnComprar);

        Log.i("URL ", "DetalleActivity curMarca: " + getIntent().getExtras().getString("curMarca"));

        marca.setText(getIntent().getExtras().getString("curMarca")+"");
        nombre.setText(getIntent().getExtras().getString("curNombre")+"");
        preciocompra.setText("Precio Ant. : S/ " + getIntent().getExtras().getString("curPrecioCompra")+"");
        porcentajedescuento.setText("Descuento : -" + getIntent().getExtras().getString("curPorcentajeDescuento")+"");

        //idpedido.setText(String.valueOf(getIntent().getExtras().getInt("curIdpedido")));
        preciocatalogo.setText("Precio : S/ " + getIntent().getExtras().getString("curPrecioCatalogo"));

        Picasso.with(foto.getContext())
                .load(getIntent().getExtras().getString("curFoto")).into(foto);

        btnDetalles.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                MostrarDetalles();
            }
        });

        btnCarrito.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                MostrarCarrito();
            }
        });


        btnComprar.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                MostrarCompra();
            }
        });



    }

    private void MostrarCarrito(){
        //Bundle bundle = new Bundle();
       // bundle.putDouble("n",nLatitud);

        Intent newActivity = new Intent(this,CarritoCompraActivity.class);
        //newActivity.putExtras(bundle);
        this.startActivity(newActivity);
    }
    private void MostrarCompra(){

        Intent newActivity = new Intent(this,ComfirmaCompraActivity.class);
        //newActivity.putExtras(bundle);
        this.startActivity(newActivity);
    }
    private void MostrarDetalles(){
        Intent intent = new Intent(this, DetalleProducto.class);
        //intent.putExtra("item", result);
        System.out.print("intent  " + intent);
        startActivity(intent);
    }
}
