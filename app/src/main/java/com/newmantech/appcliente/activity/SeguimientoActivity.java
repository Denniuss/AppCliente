package com.newmantech.appcliente.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.newmantech.appcliente.R;
import com.newmantech.appcliente.adapter.SeguimientoAdapter;
import com.newmantech.appcliente.model.Pedido;
import com.newmantech.appcliente.model.PedidoPost;
import com.newmantech.appcliente.service.ClienteService;
import com.newmantech.appcliente.service.ProductoService;
import com.newmantech.appcliente.utils.Utilitario;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SeguimientoActivity extends AppCompatActivity {
    private List<Pedido> items = new ArrayList();
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private Context contexto = this;

    private FloatingActionButton botonFlotanteDireccion;

    List<Pedido> listadoPedidosSeguimiento = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_seguimiento);
        FillPedidoSeguimiento();

        botonFlotanteDireccion = (FloatingActionButton) findViewById(R.id.botonFlotanteDireccion);

        botonFlotanteDireccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putLong("curIdPedido", 0);
                bundle.putString("curEstado", "");
                bundle.putString("curCantidadPedido", "");

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

    private void FillPedidoSeguimiento(){
       Retrofit retrofit = new Retrofit.Builder().baseUrl(Utilitario.baseUrlPis)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Log.i("----> FillPedidoSeg", "onResponse: ");

        ClienteService clienteService = retrofit.create(ClienteService.class);

        Call<List<Pedido>> lista = clienteService.listarPedidosPorIdCliente(Utilitario.idCliente);

        Log.i("----> lista" + lista, "onResponse: ");

        lista.enqueue(new Callback<List<Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {
                Log.i("onResponse chamado", "onResponse: ");

                Log.i("onResponse chamado", "onResponse: " + response.isSuccessful());
                if(response.isSuccessful()) {

                    Log.i("SIZE ", "onResponse: " + listadoPedidosSeguimiento.size());

                    listadoPedidosSeguimiento = response.body();

                    for(Pedido dis : listadoPedidosSeguimiento){
                        Log.i("DIRECCION  ", "onResponse: " + dis.getIdPedido()+"-"+dis.getEstado() +"-"+dis.getCantidad());

                        //items.add(new Direccion(dis.getIdDireccionDelivery(), R.drawable.face01, dis.getNombreDireccion(), dis.getDireccion(), dis.getDepartamento(), dis.getCiudad(),dis.getDistrito(),dis.getTelefono(),dis.getDiasEntrega(),""));
                        items.add(dis);
                    }

                    // Obtener el Recycler
                    recycler = (RecyclerView) findViewById(R.id.recicladorSeguimiento);
                    recycler.setHasFixedSize(true);

                    // Usar un administrador para LinearLayout
                    lManager = new LinearLayoutManager(contexto);
                    recycler.setLayoutManager(lManager);

                    adapter = new SeguimientoAdapter(items);
                    recycler.setAdapter(adapter);

                }

            }

            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable t) {
                Log.e("onFaillure chamado ", t.getMessage());
            }
        });
    }


}

