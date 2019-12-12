package com.newmantech.appcliente.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.newmantech.appcliente.R;
import com.newmantech.appcliente.adapter.ItemCarritoCompraAdapter;
import com.newmantech.appcliente.model.CarritoCompra;
import com.newmantech.appcliente.model.CarritoDetalle;
import com.newmantech.appcliente.model.ItemCarritoCompra;
import com.newmantech.appcliente.service.ProductoService;
import com.newmantech.appcliente.utils.PersistentCookieStore;
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

public class CarritoCompraActivity extends AppCompatActivity {
    private List<ItemCarritoCompra> items = new ArrayList();
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    private Context contexto = this;

    private double mSubTotal = 0;
    private TextView subTotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito_compra);
        subTotal = (TextView)findViewById(R.id.sub_total);

                     //Obtener el Recycler
                    recycler = (RecyclerView) findViewById(R.id.reciclador_productosc);
                    recycler.setHasFixedSize(true);

                    Log.i("FillCarritoCompras", "Contexto): " + contexto);

                    // Usar un administrador para LinearLayout
                    lManager = new LinearLayoutManager(contexto);
                    recycler.setLayoutManager(lManager);
        FillItemCarritoCompra();
        /*
        items.add(new ItemCarritoCompra(1, 1,"Pisco Tabernero Quebranta","","Tabernero","https://i.linio.com/p/940faec658e586eec1bb289889e78a2e-product.jpg",37.00,1));
        items.add(new ItemCarritoCompra(1, 2,"Pisco La Botija","","Tabernero","https://i.linio.com/p/0f6f58efe48abf6cb6d5d7a2ae9ab200-product.jpg",37.00,2));
        items.add(new ItemCarritoCompra(1, 3,"Pisco Demonio de los Andes","","Tacama","https://i.linio.com/p/4a2ebcba614aaab74e43a0a3a07a6cf5-product.jpg",37.00,1));
        items.add(new ItemCarritoCompra(1, 4,"Pisco Tres Generaciones Acholado","","Tres Generaciones","https://i.linio.com/p/739031b2bfe811c11557813af97f2562-product.jpg",37.00,1));
        items.add(new ItemCarritoCompra(1, 5,"Pisco Porton","","Pisco Porton","https://i.linio.com/p/7d119d0a78732017bfd7b928c25f9c6a-product.jpg",37.00,1));
        */

        //adapter = new ItemCarritoCompraAdapter(items);
        //recycler.setAdapter(adapter);


        //setContentView(R.layout.activity_carrito_compra);

        // Obtener el Recycler
       // recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        //lManager = new LinearLayoutManager(this);

        Button btnPagar = (Button)findViewById(R.id.btnPagar);
        assert btnPagar != null;
        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent paymentIntent = new Intent(CarritoCompraActivity.this, ComfirmaCompraActivity.class);
                paymentIntent.putExtra("TOTAL_PRICE", mSubTotal/Double.valueOf(getString(R.string.tipo_cambio)));
                startActivity(paymentIntent);
            }
        });

        fn_LoadRecyclerView();

        Button btnComprar = (Button)findViewById(R.id.btnComprar);
        assert btnComprar != null;
        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent comprarIntent = new Intent(CarritoCompraActivity.this, ListadoProductosNActivity.class);
                startActivity(comprarIntent);
            }
        });


    }

    public void fn_LoadRecyclerView(){
        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
    }

    private void FillItemCarritoCompra() {

        CookieManager cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        CookieHandler cookieHandler = new CookieManager(new PersistentCookieStore(getApplicationContext()), CookiePolicy.ACCEPT_ALL);

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
                            Toast.makeText(getApplicationContext(),
                                    "Error al listar el carrito", Toast.LENGTH_SHORT).show();
                        }

                    recycler = findViewById(R.id.reciclador_productosc);
                    recycler.setHasFixedSize(true);
                    lManager = new LinearLayoutManager(contexto);
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
