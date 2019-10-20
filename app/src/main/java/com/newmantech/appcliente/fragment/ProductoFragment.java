package com.newmantech.appcliente.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.newmantech.appcliente.CarritoCompraActivity;
import com.newmantech.appcliente.CatalogoProducto;
import com.newmantech.appcliente.CatalogoProductoAdapter;
import com.newmantech.appcliente.CatalogoProductoService;
import com.newmantech.appcliente.ListadoProductosNActivity;
import com.newmantech.appcliente.R;
import com.newmantech.appcliente.utils.Utilitario;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProductoFragment extends Fragment implements CatalogoProductoAdapter.CatalogoProductoView {

    private List<CatalogoProducto> items = new ArrayList();
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private FloatingActionButton my_cart;
    protected View mView;


    List<CatalogoProducto> listaCatalogo= new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_producto, container, false);
        this.my_cart = view.findViewById(R.id.my_cart);
        this.recycler = view.findViewById(R.id.reciclador_productos_n);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Obtener el Recycler
        recycler.setHasFixedSize(true);
        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(getContext());
        //recycler.setLayoutManager(lManager);


        recycler.setLayoutManager(new GridLayoutManager(getContext(),2));
        FillProductos(this);

        //adapter = new CatalogoProductoAdapter(items,this);
        //recycler.setAdapter(adapter);

        my_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent newActivity = new Intent(ListadoProductosNActivity.this, CarritoCompraActivity.class);
                //ListadoProductosNActivity.this.startActivity(newActivity);
                CarroFragment fr=new CarroFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contenedor,fr)
                        .commit();
                Snackbar.make(view, "Tocaste el Carrito", Snackbar.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void FillProductos(final CatalogoProductoAdapter.CatalogoProductoView view){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Utilitario.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CatalogoProductoService ProductoService = retrofit.create(CatalogoProductoService.class);

        Call<List<CatalogoProducto>> lista = ProductoService.getlistadoCatalogoProducto();
        lista.enqueue(new Callback<List<CatalogoProducto>>() {
            @Override
            public void onResponse(Call<List<CatalogoProducto>> call, Response<List<CatalogoProducto>> response) {
                Log.i("onResponse chamado", "onResponse: ");

                if(response.isSuccessful()) {

                    Log.i("SIZE ", "onResponse: " + listaCatalogo.size());

                    listaCatalogo = response.body();

                    //.add(new CatalogoProducto(1,"Pisco Tabernero Quebranta","","Tabernero","Vigente","https://i.linio.com/p/940faec658e586eec1bb289889e78a2e-product.jpg",37.00,99.00,"-28%"));

                    for(CatalogoProducto dis : listaCatalogo ){
                        Log.i("CatalogoProducto  ", "onResponse: " + dis.getNombre()+"-"+dis.getDescripcion());

                        //items.add(new Pedido(dis.getIdPedido(), R.drawable.face01, dis.getCliente(), dis.getDireccion(), dis.getDistrito(), dis.getDescripcion(),dis.getEstado(),dis.getLatitud(),dis.getLongitud(),""));
                    }

                    items.addAll(listaCatalogo);
                    //listaCatalogo.add(new CatalogoProducto(1,"Pisco Tabernero Quebranta","","Tabernero","Vigente","https://i.linio.com/p/940faec658e586eec1bb289889e78a2e-product.jpg",37.00,99.00,"-28%"))

                    /*
                    items.add(new CatalogoProducto(1,"Pisco Tabernero Quebranta","","Tabernero","Vigente","Imagen",37.00,99.00,"-28%"));
                    items.add(new CatalogoProducto(1,"Pisco Tabernero Quebranta","","Tabernero","Vigente","Imagen",37.00,99.00,"-28%"));
                    items.add(new CatalogoProducto(1,"Pisco Tabernero Quebranta","","Tabernero","Vigente","Imagen",37.00,99.00,"-28%"));
                    items.add(new CatalogoProducto(1,"Pisco Tabernero Quebranta","","Tabernero","Vigente","Imagen",37.00,99.00,"-28%"));
                    items.add(new CatalogoProducto(1,"Pisco Tabernero Quebranta","","Tabernero","Vigente","Imagen",37.00,99.00,"-28%"));


                    //listado de prueba ok
                    items.add(new CatalogoProducto(1,"Pisco Tabernero Quebranta","","Tabernero","Vigente","https://i.linio.com/p/940faec658e586eec1bb289889e78a2e-product.jpg",37.00,99.00,"-28%"));
                    items.add(new CatalogoProducto(2,"Pisco La Botija","","Tabernero","Vigente","https://i.linio.com/p/0f6f58efe48abf6cb6d5d7a2ae9ab200-product.jpg",37.00,99.00,"-28%"));
                    items.add(new CatalogoProducto(3,"Pisco Demonio de los Andes","","Tacama","Vigente","https://i.linio.com/p/4a2ebcba614aaab74e43a0a3a07a6cf5-product.jpg",37.00,99.00,"-28%"));
                    items.add(new CatalogoProducto(4,"Pisco Tres Generaciones 192Acholado","","Tres Generaciones","Vigente","https://i.linio.com/p/739031b2bfe811c11557813af97f2562-product.jpg",37.00,99.00,"-28%"));
                    items.add(new CatalogoProducto(5,"Pisco Porton","","Pisco Porton","Vigente","https://i.linio.com/p/7d119d0a78732017bfd7b928c25f9c6a-product.jpg",37.00,99.00,"-28%"));
                    */

                    Log.i("CatalogoProducto  ", "onResponse:items size " + items.size());

                    recycler.setLayoutManager(new GridLayoutManager(getContext(),2));
                    //FillProductos();
                    adapter = new CatalogoProductoAdapter(items,view);
                    recycler.setAdapter(adapter);

                    /*
                    // Obtener el Recycler
                    recycler = (RecyclerView) findViewById(R.id.recicladorProductosN);
                    recycler.setHasFixedSize(true);

                    // Usar un administrador para LinearLayout
                    lManager = new LinearLayoutManager(contexto);
                    recycler.setLayoutManager(lManager);

                    adapter = new PedidosAdapter(items);
                    recycler.setAdapter(adapter);*/

                }

            }

            @Override
            public void onFailure(Call<List<CatalogoProducto>> call, Throwable t) {
                Log.e("onFaillure chamado ", t.getMessage());
            }
        });



/*
        items.add(new CatalogoProducto(1,"Pisco Tabernero Quebranta","","Tabernero","Vigente","Imagen",37.00,99.00,"-28%"));
        items.add(new CatalogoProducto(2,"Pisco La Botija","","Tabernero","Vigente","Imagen",37.00,99.00,"-28%"));
        items.add(new CatalogoProducto(3,"Pisco Demonio de los Andes","","Tabernero","Vigente","Imagen",37.00,99.00,"-28%"));
        items.add(new CatalogoProducto(4,"Pisco Tres Generaciones Acholado","","Tabernero","Vigente","Imagen",37.00,99.00,"-28%"));
        items.add(new CatalogoProducto(5,"Pisco Porton","","Tabernero","Vigente","Imagen",37.00,99.00,"-28%"));
*/




    }

    @Override
    public void iniciarDetalle(Bundle bundle) {
        DetalleFragment fr=new DetalleFragment();
        fr.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedor,fr)
                .commit();
    }
}
