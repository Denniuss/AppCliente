package com.newmantech.appcliente;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.newmantech.appcliente.utils.Utilitario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetalleProducto extends AppCompatActivity {

    public TextView caracteristicas;
    public TextView descripcion;
    public TextView detalleProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String codigoNetsuite = getIntent().getExtras().getString("CodigoNetsuite");
        Log.i("DetalleProducto", "CodigoNetsuitex: " + codigoNetsuite);

        caracteristicas = (TextView) findViewById(R.id.caracteristicas);
        descripcion = (TextView) findViewById(R.id.descripcion);
        detalleProducto = (TextView) findViewById(R.id.detalles);


        //Log.i("DetalleActivity", "body: client " + client.toString());

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Utilitario.baseUrlServio)
                //.client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        android.util.Log.i("DetalleProducto", "onResponse: ");

        ProductoService productoService = retrofit.create(ProductoService.class);

        Call<CatalogoProducto> lista = productoService.detalleProducto(codigoNetsuite);

        lista.enqueue(new Callback<CatalogoProducto>() {
            @Override
            public void onResponse(Call<CatalogoProducto> call, Response<CatalogoProducto> response) {
                Log.e("DetalleProducto ", "onResponse isSuccessful" + response.isSuccessful());
                if(response.isSuccessful()) {
                    Log.i("DetalleProducto", "onResponse body: " + response.body());
                    //finish();
                    CatalogoProducto catalogo = response.body();
                    Log.i("DetalleProducto", "catalogo: " + catalogo);
                    if(catalogo!=null){//respuesta.getEstado()==0


                        descripcion.setText(catalogo.getDescripcion());
                        detalleProducto.setText(catalogo.getEspecificacionesProducto());
                        caracteristicas.setText(catalogo.getInformacionProducto());

                        Log.i("DetalleProducto", "descripcion: " + catalogo.getDescripcion());
                        //Gson gson = new GsonBuilder().create();
                        //CarritoCompra s2 = gson.fromJson(respuesta.getResult().toString(), CarritoCompra.class);
                        // Log.i("FillCarritoCompras", "carritoCompra Object: " + s2);

                    } else {
                        //Toast.makeText(this,,Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(),
                                "Error al listar el detalle del producto", Toast.LENGTH_SHORT).show();
                    }
                    /*
                    recycler = findViewById(R.id.reciclador_productosc);
                    recycler.setHasFixedSize(true);
                    lManager = new LinearLayoutManager(contexto);
                    recycler.setLayoutManager(lManager);

                    Log.i("FillCarritoCompras", "items size: " + items.size());

                    adapter = new ItemCarritoCompraAdapter(items);
                    recycler.setAdapter(adapter);*/

                    /*Intent iconIntent = new Intent(view.getContext(), DetalleDireccionActivity.class);
                    iconIntent.putExtras(bundle);
                    view.getContext().startActivity(iconIntent);*/

                    //adapter = new ItemCarritoCompraAdapter(items);
                    //recycler.setAdapter(adapter);

                }

            }

            @Override
            public void onFailure(Call<CatalogoProducto> call, Throwable t) {
                Log.e("FillCarritoCompras ", "onFailure "+t.getMessage());
                System.out.println("onFailure"+call);
                t.printStackTrace();
            }
        });





        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

}
