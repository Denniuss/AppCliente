package com.newmantech.appcliente.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.newmantech.appcliente.model.CatalogoProducto;
import com.newmantech.appcliente.service.ProductoService;
import com.newmantech.appcliente.R;
import com.newmantech.appcliente.utils.Utilitario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetalleProductoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetalleProductoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleProductoFragment extends Fragment {
    public TextView caracteristicas;
    public TextView descripcion;
    public TextView detalleProducto;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_producto, container, false);
        caracteristicas = (TextView) view.findViewById(R.id.caracteristicas);
        descripcion = (TextView) view.findViewById(R.id.descripcion);
        detalleProducto = (TextView) view.findViewById(R.id.detalles);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String codigoNetsuite = "";
        if (getArguments() != null) {
            codigoNetsuite = getArguments().getString("CodigoNetsuite");
        }

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
                        Toast.makeText(getContext(),
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
    }
}
