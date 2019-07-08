package com.newmantech.appcliente;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView cant;
    private FloatingActionButton my_cart;
    public TextView idCatalogoProducto;

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
        idCatalogoProducto = (TextView) findViewById(R.id.idCatalogoProducto);

        cant = findViewById(R.id.txt_cantidad);
        my_cart = (FloatingActionButton) findViewById(R.id.my_cart);

        marca.setText(getIntent().getExtras().getString("curMarca")+"");
        nombre.setText(getIntent().getExtras().getString("curNombre")+"");
        preciocompra.setText("Precio Ant. : S/ " + getIntent().getExtras().getString("curPrecioCompra")+"");
        porcentajedescuento.setText("Descuento : -" + getIntent().getExtras().getString("curPorcentajeDescuento")+"");

        //idpedido.setText(String.valueOf(getIntent().getExtras().getInt("curIdpedido")));
        preciocatalogo.setText("Precio : S/ " + getIntent().getExtras().getString("curPrecioCatalogo"));

        idCatalogoProducto.setText(getIntent().getExtras().getString("curIdCatalogoProducto"));

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

       my_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newActivity = new Intent(DetalleActivity.this,CarritoCompraActivity.class);
                DetalleActivity.this.startActivity(newActivity);
                Snackbar.make(view, "Tocaste el Carrito", Snackbar.LENGTH_LONG).show();
            }
        });

    }

    public void agregar(View view){
        //Obtiene el valor del TextView
        String valor = cant.getText().toString();
        //Se convierte  Integer
        int aux = Integer.parseInt(valor);
        //Se define el valor de una suma de + 1 en el TextView
        cant.setText(""+(aux+1));
    }//Fin agregar()

    public void restar(View view){
        //Obtiene el valor del TextView
        String valor = cant.getText().toString();
        //Se convierte  Integer
        int aux = Integer.parseInt(valor);
        //Se define el valor de una resta de - 1 en el TextView, y en el caso de que el valor
        //sea igual a 1, se mantiene
        if (aux == 1){
            cant.setText(""+1);
        }else {
            cant.setText("" + (aux - 1));
        }//Fin If
    }//Fin restar()

    private void MostrarCarrito(){
        String cantidad = cant.getText().toString();
        String Codigo = idCatalogoProducto.getText().toString();

        //Agregar Producto al carrito del compra;


        //Bundle bundle = new Bundle();
       // bundle.putDouble("n",nLatitud);

        //Intent newActivity = new Intent(this,CarritoCompraActivity.class);
        //newActivity.putExtras(bundle);
        //this.startActivity(newActivity);
        Toast.makeText(DetalleActivity.this, getString(R.string.add_carrito_ok), Toast.LENGTH_LONG).show();
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
