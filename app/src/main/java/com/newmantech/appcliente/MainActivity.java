package com.newmantech.appcliente;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.newmantech.appcliente.utils.Utilitario;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private List<CatalogoProducto> items = new ArrayList();
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private Context contexto = this;

    List<CatalogoProducto> listaDistribucion = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FillPedidos();
        //finalizarPedido();
        //registrarIncidencia();
    }

    private void FillPedidos(){
       Retrofit retrofit = new Retrofit.Builder().baseUrl(Utilitario.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductoService productoService = retrofit.create(ProductoService.class);

        Call<List<CatalogoProducto>> lista = productoService.getListadoCatalogoProducto();
        lista.enqueue(new Callback<List<CatalogoProducto>>() {
            @Override
            public void onResponse(Call<List<CatalogoProducto>> call, Response<List<CatalogoProducto>> response) {
                Log.i("onResponse chamado", "onResponse: ");

                Log.i("onResponse chamado", "onResponse: " + response.isSuccessful());
                if(response.isSuccessful()) {

                    Log.i("SIZE ", "onResponse: " + listaDistribucion.size());

                    listaDistribucion = response.body();

                    for(CatalogoProducto dis : listaDistribucion ){
                        //Log.i("CLIENTE  ", "onResponse: " + dis.getCodigoProducto()+"-"+dis.getCodigoNetSuite());

                        items.add(new CatalogoProducto());
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

                    adapter = new CatalogoProductoAdapter(items);
                    recycler.setAdapter(adapter);

                }

            }

            @Override
            public void onFailure(Call<List<CatalogoProducto>> call, Throwable t) {
                Log.e("onFaillure chamado ", t.getMessage());
            }
        });
    }


}

