package com.newmantech.appcliente.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.newmantech.appcliente.R;
import com.newmantech.appcliente.adapter.SeguimientoAdapter;
import com.newmantech.appcliente.model.CarritoDetalle;
import com.newmantech.appcliente.model.Pedido;
import com.newmantech.appcliente.model.PedidoPost;
import com.newmantech.appcliente.service.ClienteService;
import com.newmantech.appcliente.service.ProductoService;
import com.newmantech.appcliente.utils.Utilitario;

import java.util.ArrayList;
import java.util.List;

import moe.feng.common.stepperview.demo.fragment.VerticalStepperDemoFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SeguimientoFragment extends Fragment implements SeguimientoAdapter.SeguimientoView {

    private List<Pedido> items = new ArrayList();
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    private FloatingActionButton botonFlotanteDireccion;

    List<Pedido> listaDirecciones = new ArrayList<>();
    List<CarritoDetalle> carritoDetalles = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seguimiento, container, false);
        this.botonFlotanteDireccion = (FloatingActionButton) view.findViewById(R.id.botonFlotanteDireccion);
        this.recycler = view.findViewById(R.id.reciclador);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FillPedidosSeguimiento(this);
        botonFlotanteDireccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putLong("curIdPedido", 0);
                bundle.putString("curEstado", "");
                bundle.putString("curCantidad", "");
                bundle.putString("curFechaPedido","");

                VerticalStepperDemoFragment fr=new VerticalStepperDemoFragment();
                fr.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contenedor,fr)
                        .commit();

                /*
                DetalleDireccionFragment fr=new DetalleDireccionFragment();
                fr.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contenedor,fr)
                        .commit();*/
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

    private void FillPedidosSeguimiento(final SeguimientoAdapter.SeguimientoView view){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Utilitario.baseUrlPis)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ClienteService clienteService = retrofit.create(ClienteService.class);
        Log.i("----> FillPedidosSeg", "Inicio listarPedidosPorIdCliente: ");
        Call<List<Pedido>> lista = clienteService.listarPedidosPorIdCliente(Utilitario.idCliente);

        Log.i("----> FillPedidosSeg", "Inicio listarPedidosPorIdCliente: " +
                clienteService.listarPedidosPorIdCliente(Utilitario.idCliente));

        lista.enqueue(new Callback<List<Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {
                Log.i("onResponse chamado", "onResponse: ");

                Log.i("onResponse chamado", "onResponse: " + response.isSuccessful());
                if(response.isSuccessful()) {

                    Log.i("SIZE ", "onResponse: " + listaDirecciones.size());

                    listaDirecciones = response.body();

                    for(Pedido dis : listaDirecciones){
                        //Log.i("DIRECCION  ", "onResponse: " + dis.getCantidad()+"-"+dis.getEstado() + dis.getEstadoParametro());
                        Log.i("dis tostring  ", "onResponse: " + dis.toString());

                        //items.add(new Direccion(dis.getIdDireccion(), R.drawable.face01, dis.getNombreDireccion(), dis.getDireccion(), dis.getDepartamento(), dis.getCiudad(),dis.getDistrito(),dis.getTelefono(),dis.getDiasEntrega(),""));
                        items.add(dis);
                    }
                    // Obtener el Recycler
                    recycler.setHasFixedSize(true);

                    // Usar un administrador para LinearLayout
                    lManager = new LinearLayoutManager(getContext());
                    recycler.setLayoutManager(lManager);

                    adapter = new SeguimientoAdapter(items,view);
                    recycler.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable t) {
                Log.e("onFaillure chamado ", t.getMessage());
            }
        });
        Log.i("----> FillPedidosSeg" + lista, "Fin listarPedidosPorIdCliente: ");

    }

    @Override
    public void iniciarDetalleDireccion(Bundle bundle) {
        VerticalStepperDemoFragment fr=new VerticalStepperDemoFragment();
        fr.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedor,fr)
                .commit();
    }
}