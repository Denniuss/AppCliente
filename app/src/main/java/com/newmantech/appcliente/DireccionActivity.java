package com.newmantech.appcliente;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.newmantech.appcliente.utils.Utilitario;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DireccionActivity extends AppCompatActivity {
    private List<Direccion> items = new ArrayList();
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private Context contexto = this;

    private FloatingActionButton botonFlotanteDireccion;

    List<Direccion> listaDirecciones = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_direccion);
        FillDirecciones();

        botonFlotanteDireccion = (FloatingActionButton) findViewById(R.id.botonFlotanteDireccion);

        botonFlotanteDireccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent newActivity = new Intent(DetalleActivity.this,CarritoCompraActivity.class);
                //DetalleActivity.this.startActivity(newActivity);
                //Snackbar.make(view, "Tocaste el Carrito", Snackbar.LENGTH_LONG).show();
                //Intent intent = new Intent(DetalleDireccionActivity.this, MainActivity.class);
                //startActivity(intent);

                //Log.i("DireccionActivity", "getContext: "+view.getContext());
                //Intent iconIntent = new Intent(view.getContext(), DetalleDireccionActivity.class);
                //startActivity(iconIntent);


                Bundle bundle = new Bundle();
                //bundle.putInt("curImagen", items.get(i).getImagen());
                //bundle.putString("curImagen", items.get(i).getImagenurl());
                bundle.putLong("curIdDireccion", 0);
                bundle.putString("curNombreDireccion", "");
                bundle.putString("curDepartamento", "");
                bundle.putString("curProvincia", "");
                bundle.putString("curDistrito", "");
                bundle.putString("curDireccion", "");

                bundle.putString("curReferencia", "");
                bundle.putString("curTelefonoContacto", "");
                bundle.putString("curNombreContacto", "");
                bundle.putBoolean("curEstablecerDireccion", false);

                bundle.putString("curidDepartamento", "");
                bundle.putString("curidProvincia", "");
                bundle.putString("curidDistrito", "");

                bundle.putLong("curidDireccion", 0);

                Intent iconIntent = new Intent(view.getContext(), DetalleDireccionActivity.class);
                iconIntent.putExtras(bundle);
                view.getContext().startActivity(iconIntent);

            }
        });
    }

    private static void registrarIncidencia(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Utilitario.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductoService distribucionService = retrofit.create(ProductoService.class);
        PedidoPost pedidoTemP = new PedidoPost();
        pedidoTemP.setIdPedido(11);
        pedidoTemP.setObservacion("Pedido con incidencia Android");

        Call<Integer> resultado = distribucionService.registrarIncidencia(pedidoTemP);
        resultado.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Log.i("registrarIncidencia ", "onResponse: "+response.code());
                Log.i("Incidencia message", "onResponse: "+response.message());

                if(response.isSuccessful()) {
                    Log.i("INCIDENCIA", "onResponse: " + response.body());
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.e("onFaillure chamado ", t.getMessage());
            }
        });
    }

    private static void finalizarPedido(){
       Retrofit retrofit = new Retrofit.Builder().baseUrl(Utilitario.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductoService distribucionService = retrofit.create(ProductoService.class);
        PedidoPost pedidoTemP = new PedidoPost();
        pedidoTemP.setIdPedido(11);
        pedidoTemP.setObservacion("Pedido exitoso Android");

        Call<Integer> resultado = distribucionService.finalizarPedido(pedidoTemP);
        resultado.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Log.i("finalizarPedido ", "onResponse: "+response.code());
                Log.i("finalizarPedido message", "onResponse: "+response.message());

                if(response.isSuccessful()) {
                    Log.i("FINALIZA_PEDIDO", "onResponse: " + response.body());
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.e("onFaillure chamado ", t.getMessage());
            }
        });
    }

    private void FillDirecciones(){
       Retrofit retrofit = new Retrofit.Builder().baseUrl(Utilitario.baseUrlServio)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Log.i("----> FillDirecciones", "onResponse: ");

        DireccionService direccionService = retrofit.create(DireccionService.class);

        Call<List<Direccion>> lista = direccionService.getListadoDireccionesXCliente();

        Log.i("----> url"+ direccionService.getListadoDireccionesXCliente(), "onResponse: ");

        Log.i("----> lista" + lista, "onResponse: ");

        lista.enqueue(new Callback<List<Direccion>>() {
            @Override
            public void onResponse(Call<List<Direccion>> call, Response<List<Direccion>> response) {
                Log.i("onResponse chamado", "onResponse: ");

                Log.i("onResponse chamado", "onResponse: " + response.isSuccessful());
                if(response.isSuccessful()) {

                    Log.i("SIZE ", "onResponse: " + listaDirecciones.size());

                    listaDirecciones = response.body();

                    for(Direccion dis : listaDirecciones){
                        Log.i("DIRECCION  ", "onResponse: " + dis.getNombreDireccion()+"-"+dis.getDireccion());

                        //items.add(new Direccion(dis.getIdDireccionDelivery(), R.drawable.face01, dis.getNombreDireccion(), dis.getDireccion(), dis.getDepartamento(), dis.getCiudad(),dis.getDistrito(),dis.getTelefono(),dis.getDiasEntrega(),""));
                        items.add(dis);
                    }
                    /*
                    items.add(new Pedido(1,R.drawable.face01, "Marlon Leandro", "Av. Chimu 412 Urb. Zarate", "SJL", "Aniversario","Pendiente","-12.02456","-77.00057",""));
                    items.add(new Pedido(2,R.drawable.face02, "Juan Perez", "Av. Chimu 413 Urb. Zarate", "SJL", "Cumpleaños","Pendiente","-12.070118","-77.029274",""));
                    items.add(new Pedido(3,R.drawable.face03, "Carlos Gonzales", "Av. Chimu 414 Urb. Zarate", "SJL", "Boda","Pendiente","-12.058746","-77.12736",""));
                    items.add(new Pedido(4,R.drawable.face04, "Pedro Miranda", "Av. Chimu 415 Urb. Zarate", "SJL", "Aniversario","Pendiente","-11.986429","-77.090034",""));
                    items.add(new Pedido(5,R.drawable.face05, "Mateo Montero", "Av. Chimu 416 Urb. Zarate", "SJL", "Dia del Padre","Pendiente","-11.9229765","-77.04199",""));
                    items.add(new Pedido(6,R.drawable.face06, "Hugo Santana", "Av. Chimu 417 Urb. Zarate", "SJL", "Dia de la empresa","Pendiente","-12.05393","-76.97475",""));
                    items.add(new Pedido(7,R.drawable.face07, "Pedro Quijandria", "Av. Chimu 418 Urb. Zarate", "SJL", "Boda","Pendiente","-12.067931","-77.01171",""));
                    items.add(new Pedido(8,R.drawable.face08, "Ruben cartagena", "Av. Chimu 419 Urb. Zarate", "SJL", "Aniversario","Pendiente","-12.003887","-77.06022",""));
                    */

                    // Obtener el Recycler
                    recycler = (RecyclerView) findViewById(R.id.reciclador);
                    recycler.setHasFixedSize(true);

                    // Usar un administrador para LinearLayout
                    lManager = new LinearLayoutManager(contexto);
                    recycler.setLayoutManager(lManager);

                    adapter = new DireccionAdapter(items);
                    recycler.setAdapter(adapter);

                }

            }

            @Override
            public void onFailure(Call<List<Direccion>> call, Throwable t) {
                Log.e("onFaillure chamado ", t.getMessage());
            }
        });
    }


}

