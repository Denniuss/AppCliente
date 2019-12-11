package com.newmantech.appcliente.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.newmantech.appcliente.R;
import com.newmantech.appcliente.adapter.AdapterDetalleCarrito;
import com.newmantech.appcliente.model.BResult;
import com.newmantech.appcliente.model.EntityDetalleCarritoPersonalizado;
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

public class DetalleCarritoActivity extends AppCompatActivity {

    Toolbar toolbar;
    private Button btnPagar;
    private TextView tvTotal;
    private double mSubTotal = 0;
    private RecyclerView recycler;
    AdapterDetalleCarrito adapterDetalleCarrito;
    ArrayList<EntityDetalleCarritoPersonalizado> arrayList;
    Context contexto = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_carrito);

        toolbar = findViewById(R.id.toolbar);
        btnPagar = findViewById(R.id.btnPagar);
        recycler = findViewById(R.id.recycler);
        tvTotal = findViewById(R.id.tvTotal);

        setupUI(getWindow().getDecorView().getRootView());
        toolbar.setTitle(R.string.piscosElegidos);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_35dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent paymentIntent = new Intent(DetalleCarritoActivity.this, ComfirmaCompraActivity.class);
                paymentIntent.putExtra("TOTAL_PRICE", mSubTotal/Double.valueOf(getString(R.string.tipo_cambio)));
                startActivity(paymentIntent);
            }
        });
        fn_LoadRecyclerView();
        setRecycler();
    }

    public void setRecycler(){
        arrayList = new ArrayList<>();
        /*
        arrayList.add(new EntityDetalleCarritoPersonalizado(1,"Piscos Etiquetados",7, "P. Quebranta", 25, 10.00, 250.00, "500", "ml"));
        arrayList.add(new EntityDetalleCarritoPersonalizado(1,"Piscos Etiquetados",26, "P. Italia/ Totrontel/ Acholado", 50, 24.00, 1200.00, "375", "ml"));
        arrayList.add(new EntityDetalleCarritoPersonalizado(2,"Piscos Seragrifados",10, "P. Quebranta", 25, 25.00, 625.00, "375", "ml"));


*/
        arrayList.add(new EntityDetalleCarritoPersonalizado(3,"Piscos Grabados",13, "P. Quebranta", 25, 42.00, 1050.00, "500", "ml"));
        adapterDetalleCarrito = new AdapterDetalleCarrito(arrayList, this );
        //recycler.setAdapter(adapterDetalleCarrito);
        /*Inicio llamada servicio obtenerCarritoPersonalizado*/
/*
        CookieManager cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        CookieHandler cookieHandler = new CookieManager(new PersistentCookieStore(getApplicationContext()), CookiePolicy.ACCEPT_ALL);

        OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(interceptor)
                .cookieJar(new JavaNetCookieJar(cookieHandler))
                .build();
        Log.i("DetalleCarritoActivity", "body: client " + client.toString());

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Utilitario.baseUrlSWeb)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductoService productoService = retrofit.create(ProductoService.class);

        Call<List<EntityDetalleCarritoPersonalizado>> lista = productoService.obtenerCarritoCompraPersonalizado();
        lista.enqueue(new Callback<List<EntityDetalleCarritoPersonalizado>>() {
            @Override
            public void onResponse(Call<List<EntityDetalleCarritoPersonalizado>> call, Response<List<EntityDetalleCarritoPersonalizado>> response) {
                Log.e("DetalleCarritoActivity ", "onResponse " + response.isSuccessful());

                if(response.isSuccessful()) {
                    Log.i("DetalleCarritoActivity", "onResponse: " + response.body());

                    finish();

                    List<EntityDetalleCarritoPersonalizado> respuesta = response.body();

                    if(respuesta!=null) {
                        //Intent intent = new Intent(DetalleActivity.this, DireccionActivity.class);
                        //startActivity(intent);
                        if(respuesta.size()==0){
                            Toast.makeText(getApplicationContext(),
                                    "No tiene datos en el carrto personalizado", Toast.LENGTH_SHORT).show();
                        } else {

                            //arrayList = respuesta;
                            //ArrayList<EntityDetalleCarritoPersonalizado> arrayListTemp = new ArrayList<>(respuesta);
                            //arrayList = arrayListTemp;

                            //Log.i("DetalleCarritoActivity", "arrayListTemp: " + arrayListTemp.size());
                            Log.i("DetalleCarritoActivity", "arrayList: " + arrayList.size());

                            //Fin llamada servicio obtenerCarritoPersonalizado
                            //adapterDetalleCarrito = new AdapterDetalleCarrito(arrayList, contexto );

                            //calcularTotal();
                            //recycler.setAdapter(adapterDetalleCarrito);

                            //Toast.makeText(this,,Toast.LENGTH_SHORT).show();
                            //Toast.makeText(getApplicationContext(), "Datos cargados correctamente", Toast.LENGTH_SHORT).show();
                        }
                    }

                }

            }

            @Override
            public void onFailure(Call<List<EntityDetalleCarritoPersonalizado>> call, Throwable t) {
                Log.e("DetalleActivity ", "onFailure "+t.getMessage());
            }
        });*/

        adapterDetalleCarrito.setOnclickEvent(new AdapterDetalleCarrito.onClickEvent() {
            @Override
            public void onClickDelete(int i, int idProducto) {
                arrayList.remove(i);
                adapterDetalleCarrito.notifyDataSetChanged();
                calcularTotal();
            }
        });
        calcularTotal();
        recycler.setAdapter(adapterDetalleCarrito);
    }

    public void calcularTotal(){
        double precio = 0;
        for(EntityDetalleCarritoPersonalizado obj: arrayList){
            Log.i("EntityDetalleCarritoPer", "precio: " + obj.getSubTotal());
            precio += obj.getSubTotal();
        }
        Log.i("EntityDetalleCarritoPer", "precioT: " + precio);
        tvTotal.setText(String.valueOf(precio));

    }

    public void fn_LoadRecyclerView(){
        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
    }

    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideKeyb();
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }
    private void hideKeyb() {
        try {
            InputMethodManager inputManager = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) { //No debe pasar nunca
            e.printStackTrace();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        goBack();
        return true;
    }

    private void goBack() {
        setResult(RESULT_CANCELED);
        finish();
    }
}
