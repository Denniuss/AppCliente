package com.newmantech.appcliente.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.newmantech.appcliente.CarritoCompra;
import com.newmantech.appcliente.CarritoCompraActivity;
import com.newmantech.appcliente.CarritoDetalle;
import com.newmantech.appcliente.ComfirmaCompraActivity;
import com.newmantech.appcliente.ItemCarritoCompra;
import com.newmantech.appcliente.ItemCarritoCompraAdapter;
import com.newmantech.appcliente.ListadoProductosNActivity;
import com.newmantech.appcliente.PersistentCookieStore;
import com.newmantech.appcliente.ProductoService;
import com.newmantech.appcliente.R;
import com.newmantech.appcliente.utils.Utilitario;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.ArrayList;
import java.util.List;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CarroFragment extends Fragment {

    private List<ItemCarritoCompra> items = new ArrayList();
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;


    private double mSubTotal = 0;
    private TextView subTotal;
    private Button btnPagar;
    Button btnComprar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_carro, container, false);
        subTotal = (TextView)view.findViewById(R.id.sub_total);
        recycler = (RecyclerView) view.findViewById(R.id.reciclador_productosc);
        btnPagar = (Button)view.findViewById(R.id.btnPagar);
        btnComprar= (Button)view.findViewById(R.id.btnComprar);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Obtener el Recycler
        recycler.setHasFixedSize(true);

        Log.i("FillCarritoCompras", "Contexto): " + getContext());

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(lManager);
        FillItemCarritoCompra();


        assert btnPagar != null;
        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /* Intent paymentIntent = new Intent(getActivity(), ComfirmaCompraActivity.class);
                paymentIntent.putExtra("TOTAL_PRICE", mSubTotal/Double.valueOf(getString(R.string.tipo_cambio)));
                startActivity(paymentIntent);*/
                CompraFragment fr=new CompraFragment();
                Bundle args = new Bundle();
                Double tipoCambio = Double.valueOf(getString(R.string.tipo_cambio));
                args.putDouble("TOTAL_PRICE", mSubTotal/tipoCambio);
                fr.setArguments(args);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contenedor,fr)
                        .commit();
            }
        });


         assert btnComprar != null;
        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent comprarIntent = new Intent(getActivity(), ListadoProductosNActivity.class);
                //startActivity(comprarIntent);
                ProductoFragment fr=new ProductoFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contenedor,fr)
                        .commit();


            }
        });

    }

    private void FillItemCarritoCompra() {

        CookieManager cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        CookieHandler cookieHandler = new CookieManager(new PersistentCookieStore(getContext()), CookiePolicy.ACCEPT_ALL);

        OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(interceptor)
                .cookieJar(new JavaNetCookieJar(cookieHandler))
                .build();

        Log.i("DetalleActivity", "body: client " + client.toString());

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Utilitario.baseUrlSWeb)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        android.util.Log.i("FillCargetTotalPrice", "onResponse: ");

        ProductoService productoService = retrofit.create(ProductoService.class);

        Call<CarritoCompra> lista = productoService.listarCarritoCompras();

        lista.enqueue(new Callback<CarritoCompra>() {
            @Override
            public void onResponse(Call<CarritoCompra> call, Response<CarritoCompra> response) {
                Log.e("FillCarritoCompras ", "onResponse isSuccessful" + response.isSuccessful());
                if(response.isSuccessful()) {
                    Log.i("FillCarritoCompras", "onResponse body: " + response.body());
                    //finish();
                    CarritoCompra respuesta = response.body();
                    Log.i("FillCarritoCompras", "respuesta: " + respuesta);
                    if(respuesta!=null){//respuesta.getEstado()==0
                        CarritoCompra carritoCompra = (CarritoCompra) respuesta;
                        Log.i("FillCarritoCompras", "carritoCompra: " + carritoCompra);
                        //Gson gson = new GsonBuilder().create();
                        //CarritoCompra s2 = gson.fromJson(respuesta.getResult().toString(), CarritoCompra.class);
                        // Log.i("FillCarritoCompras", "carritoCompra Object: " + s2);
                        if(null!=carritoCompra) {
                            if(null!=carritoCompra.getDetalles()) {
                                Log.i("FillCarritoCompras", "carritoCompra size: " + carritoCompra.getDetalles().size());
                                for (CarritoDetalle temp: carritoCompra.getDetalles()) {
                                    //items.add(new ItemCarritoCompra(1, 1,"Pisco Tabernero Quebranta","","Tabernero","https://i.linio.com/p/940faec658e586eec1bb289889e78a2e-product.jpg",37.00,1));
                                    items.add(new ItemCarritoCompra(carritoCompra.getCliente().getIdCliente(), temp.getCatalogoProducto().getIdCatalogoProducto(),temp.getCatalogoProducto().getNombre(),"",temp.getCatalogoProducto().getProducto().getDescripcionMarca(),temp.getCatalogoProducto().getProducto().getImagen1(),temp.getCatalogoProducto().getPrecioCatalogo(),temp.getCantidad()));
                                }

                                mSubTotal = carritoCompra.getImporteTotalSoles();
                                subTotal.setText("Sub Total: S/ " + carritoCompra.getImporteTotalSoles());

                                    /*Toast.makeText(getApplicationContext(),
                                            "Listado del carrito ", Toast.LENGTH_SHORT).show();*/

                            }
                        }

                    } else {
                        //Toast.makeText(this,,Toast.LENGTH_SHORT).show();
                        Toast.makeText(getContext(),
                                "Error al listar el carrito", Toast.LENGTH_SHORT).show();
                    }

                    recycler.setHasFixedSize(true);
                    lManager = new LinearLayoutManager(getContext());
                    recycler.setLayoutManager(lManager);

                    Log.i("FillCarritoCompras", "items size: " + items.size());

                    adapter = new ItemCarritoCompraAdapter(items);
                    recycler.setAdapter(adapter);

                    /*Intent iconIntent = new Intent(view.getContext(), DetalleDireccionActivity.class);
                    iconIntent.putExtras(bundle);
                    view.getContext().startActivity(iconIntent);*/

                    //adapter = new ItemCarritoCompraAdapter(items);
                    //recycler.setAdapter(adapter);

                }

            }

            @Override
            public void onFailure(Call<CarritoCompra> call, Throwable t) {
                Log.e("FillCarritoCompras ", "onFailure "+t.getMessage());
                System.out.println("onFailure"+call);
                t.printStackTrace();
            }
        });

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
