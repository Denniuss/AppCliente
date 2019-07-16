package com.newmantech.appcliente;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.newmantech.appcliente.utils.Utilitario;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetalleActivity extends AppCompatActivity {
    public ImageView foto;
    public TextView marca;
    public TextView codigoNetsuite;
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
    public TextView keyItemCanje;
    private double mSubTotal = 0;

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
        keyItemCanje = (TextView) findViewById(R.id.keyItemCanje);

        codigoNetsuite = (TextView) findViewById(R.id.codigoNetsuite);

        cant = findViewById(R.id.txt_cantidad);
        my_cart = (FloatingActionButton) findViewById(R.id.my_cart);

        marca.setText(getIntent().getExtras().getString("curMarca")+"");
        nombre.setText(getIntent().getExtras().getString("curNombre")+"");
        preciocompra.setText("Precio Ant. : S/ " + getIntent().getExtras().getString("curPrecioCompra")+"");
        porcentajedescuento.setText("Descuento : -" + getIntent().getExtras().getString("curPorcentajeDescuento")+"");

        //idpedido.setText(String.valueOf(getIntent().getExtras().getInt("curIdpedido")));
        preciocatalogo.setText("Precio : S/ " + getIntent().getExtras().getString("curPrecioCatalogo"));

        idCatalogoProducto.setText(getIntent().getExtras().getString("curIdCatalogoProducto"));

        keyItemCanje.setText(getIntent().getExtras().getString("curkeyItemCanje"));

        codigoNetsuite.setText(getIntent().getExtras().getString("curCodigoNetsuite")+"");

        //mSubTotal =  Double.valueOf(preciocatalogo.toString());
        //mSubTotal  //= Double.parseDouble(preciocatalogo.toString());

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

/*        Button btnComprar = (Button)findViewById(R.id.btnComprar);
        assert btnComprar != null;
        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent comprarIntent = new Intent(DetalleActivity.this, ComfirmaCompraActivity.class);
                startActivity(comprarIntent);
            }
        });*/

        Button btnComprar = (Button)findViewById(R.id.btnComprar);
        assert btnComprar != null;
        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent paymentIntent = new Intent(DetalleActivity.this, ComfirmaCompraActivity.class);

                Log.i("DetalleActivity", "btnComprar: precio " + (Double.valueOf(getIntent().getExtras().getString("curPrecioCatalogo"))));

                Log.i("DetalleActivity", "btnComprar: cantidad " + Double.valueOf(cant.getText().toString()));

                Log.i("DetalleActivity", "btnComprar: cantidad " + Double.valueOf("3.4"));

                Log.i("DetalleActivity", "btnComprar: calculo " + (Double.valueOf(getIntent().getExtras().getString("curPrecioCatalogo"))*Double.valueOf(cant.getText().toString()))/Double.valueOf("3.4"));

                paymentIntent.putExtra("TOTAL_PRICE", (Double.valueOf(getIntent().getExtras().getString("curPrecioCatalogo"))*Double.valueOf(cant.getText().toString()))/Double.valueOf("3.4"));
                startActivity(paymentIntent);
            }
        });

/*        btnComprar.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                MostrarCompra();
            }
        });*/

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
        Integer cantidad = 0;
        if(null!=cant.getText()) {
            cantidad = Integer.parseInt(cant.getText().toString());
        }

        String Codigo = idCatalogoProducto.getText().toString();
        String key = keyItemCanje.getText().toString();

        //Agregar Producto al carrito del compra;

        Log.i("DetalleActivity", "body: cantidad " + cantidad);
        Log.i("DetalleActivity", "body: key " + key);

        //Bundle bundle = new Bundle();
       // bundle.putDouble("n",nLatitud);

        //Intent newActivity = new Intent(this,CarritoCompraActivity.class);
        //newActivity.putExtras(bundle);
        //this.startActivity(newActivity);

        //OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();
        //HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        //interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        //HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        //interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        //OkHttpClient session = RetrofitCached.getCacheEnabledRetrofit(getBaseContext());


        //cookieManager = AppController.getCookieManager();
        //client.setCookieHandler(cookieManager);
        //client.setFollowRedirects(true);
        //client.setFollowRedirects(true);
        //toSyncCookies();
        //request = request.newBuilder().header("Cache-Control", "public, max-age=" + 60).build();

        CookieManager cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        CookieHandler cookieHandler = new CookieManager(new PersistentCookieStore(getApplicationContext()), CookiePolicy.ACCEPT_ALL);

        //CookieHandler cookieHandler = new CookieManager();
        OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(interceptor)
                .cookieJar(new JavaNetCookieJar(cookieHandler))
                //.cookieJar(new JavaNetCookieJar(cookieManager))
                //.cookieJar(new CookieStore())
                /*.addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        final Request original = chain.request();

                        final Request authorized = original.newBuilder()
                                .addHeader("Cookie", "cookie-name=cookie-value")
                                .build();

                        return chain.proceed(authorized);
                    }
                })*/
                //.connectTimeout(100, TimeUnit.SECONDS)
                //.writeTimeout(100, TimeUnit.SECONDS)
                //.readTimeout(300, TimeUnit.SECONDS)
                //.addInterceptor(interceptor)
                /*.cookieJar(new CookieJar() {
                    private final HashMap<Object,List> cookieStore = new HashMap<>();

                    @Override
                    public void saveFromResponse(HttpUrl url, List cookies) {
                        cookieStore.put(url, cookies);
                    }

                    @Override
                    public List loadForRequest(HttpUrl url) {
                        List cookies =  cookieStore.get(url);
                        return cookies != null ? cookies : new ArrayList();
                    }
                })*/
                //.addNetworkInterceptor(new CacheInterceptor())
                .build();
        //client.newBuilder()
        Log.i("DetalleActivity", "body: client " + client.toString());

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Utilitario.baseUrlSWeb)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductoService productoService = retrofit.create(ProductoService.class);

        Call<BResult> lista = productoService.agregarCarritoCompras(key,cantidad);
        lista.enqueue(new Callback<BResult>() {
            @Override
            public void onResponse(Call<BResult> call, Response<BResult> response) {
                Log.e("DetalleActivity ", "onResponse " + response.isSuccessful());

                if(response.isSuccessful()) {


                    Log.i("Direccion", "onResponse: " + response.body());

                    finish();

                    BResult respuesta = response.body();

                    if(respuesta!=null) {
                        //Intent intent = new Intent(DetalleActivity.this, DireccionActivity.class);
                        //startActivity(intent);
                        if(respuesta.getEstado()==0){
                            Toast.makeText(getApplicationContext(),
                                    "Se guardo la dirección correctamente", Toast.LENGTH_SHORT).show();
                        } else {
                            //Toast.makeText(this,,Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(),
                                    "Error al guardar la dirección vuelva a intentar", Toast.LENGTH_SHORT).show();
                        }
                    }


                    /*Intent iconIntent = new Intent(view.getContext(), DetalleDireccionActivity.class);
                    iconIntent.putExtras(bundle);
                    view.getContext().startActivity(iconIntent);*/
                    /* Obtener el Recycler
                    recycler = (RecyclerView) findViewById(R.id.reciclador);
                    recycler.setHasFixedSize(true);

                    // Usar un administrador para LinearLayout
                    lManager = new LinearLayoutManager(contexto);
                    recycler.setLayoutManager(lManager);

                    adapter = new CatalogoProductoAdapter(items);
                    recycler.setAdapter(adapter);
                    */

                }

            }

            @Override
            public void onFailure(Call<BResult> call, Throwable t) {
                Log.e("DetalleActivity ", "onFailure "+t.getMessage());
            }
        });


        Toast.makeText(DetalleActivity.this, getString(R.string.add_carrito_ok), Toast.LENGTH_LONG).show();
    }


    private static void toSyncCookies() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            CookieSyncManager.getInstance().sync();
            return;
        }

        AsyncTask.THREAD_POOL_EXECUTOR.execute(new Runnable() {
            @Override
            public void run() {
                if (SysUtils.hasLollipop())
                    android.webkit.CookieManager.getInstance().flush();

            }
        });
    }



/*    private void MostrarCompra(){

        Intent newActivity = new Intent(this,ComfirmaCompraActivity.class);
        //newActivity.putExtras(bundle);
        this.startActivity(newActivity);


    }*/
    private void MostrarDetalles(){
        Intent intent = new Intent(this, DetalleProducto.class);
        //intent.putExtra("item", result);
        intent.putExtra("CodigoNetsuite", codigoNetsuite.getText());
        System.out.print("intent  " + intent);
        startActivity(intent);
    }
}
