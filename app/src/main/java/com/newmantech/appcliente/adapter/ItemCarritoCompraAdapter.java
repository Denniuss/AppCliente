package com.newmantech.appcliente.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.newmantech.appcliente.utils.PersistentCookieStore;
import com.newmantech.appcliente.R;
import com.newmantech.appcliente.model.BResult;
import com.newmantech.appcliente.model.ItemCarritoCompra;
import com.newmantech.appcliente.service.ProductoService;
import com.newmantech.appcliente.utils.Utilitario;
import com.squareup.picasso.Picasso;

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

public class ItemCarritoCompraAdapter extends RecyclerView.Adapter<ItemCarritoCompraAdapter.ItemViewHolder> {

    private List<ItemCarritoCompra> items;
    //private Context context;
    private AdapterView.OnItemClickListener mlistener;
    private CarroCompraView carroCompraView;

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public CardView ItemCarritoCompraCardView;
        public ImageView foto;
        public TextView nombre;
        public TextView precio;
        public TextView marca;
        public TextView cantidad;
        public TextView idCatalogoProducto;
        public TextView idcliente;
        public ImageView btnremove;
        public TextView subtotalitem;
        private ArrayList<String> chores;


        public ItemViewHolder(View v) {

            super(v);
            //cv = (CardView)itemView.findViewById(R.id.);
            ItemCarritoCompraCardView = (CardView) v.findViewById(R.id.itemc_card);
            foto = (ImageView) v.findViewById(R.id.foto);
            nombre = (TextView) v.findViewById(R.id.nombre);
            marca = (TextView) v.findViewById(R.id.marca);
            cantidad = (TextView) v.findViewById(R.id.cantidad);
            precio = (TextView) v.findViewById(R.id.precio);
            subtotalitem = (TextView) v.findViewById(R.id.subtotalitem);
            idCatalogoProducto = (TextView) v.findViewById(R.id.idCatalogoProducto);
            idcliente = (TextView) v.findViewById(R.id.idcliente);
            btnremove =  (ImageView) v.findViewById(R.id.btnremove);
            //estado = (TextView) v.findViewById(R.id.estado);
            //idProducto = (TextView) v.findViewById(R.id.idpedido);

        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public ItemCarritoCompraAdapter(List<ItemCarritoCompra> items) {
        this.items = items;

    }

    public ItemCarritoCompraAdapter(List<ItemCarritoCompra> items,CarroCompraView carroCompraView) {
        this.items = items;
        this.carroCompraView = carroCompraView;

    }

    public List<ItemCarritoCompra> getItems(){
        return this.items;
    }

    public interface OnItenClickListener {
        void onItemClick(int position);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.grid_carrito_item, viewGroup, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ItemViewHolder pvh = new ItemViewHolder(v);
        return pvh;
    }


    @Override
    public void onBindViewHolder(final ItemCarritoCompraAdapter.ItemViewHolder viewHolder, final int i) {
        //viewHolder.choreName.setText(this.chores.get(position));
/*        Glide.with(viewHolder.foto.getContext())
                .load(items.get(i).getFoto())
                .centerCrop()
                .into(viewHolder.foto);*/
        Picasso.with(viewHolder.foto.getContext())
                .load(items.get(i).getFoto()).into(viewHolder.foto);

        viewHolder.nombre.setText(items.get(i).getNombre());
        viewHolder.marca.setText("Marca: " +items.get(i).getMarca());
        viewHolder.precio.setText("Precio Unidad: S/ " + items.get(i).getPrecio().toString());
        viewHolder.cantidad.setText("Cantidad: " + items.get(i).getCantidad().toString());
        viewHolder.subtotalitem.setText("Subtotal: S/ " + (items.get(i).getCantidad() * items.get(i).getPrecio()));
        viewHolder.idCatalogoProducto.setText(items.get(i).getIdCatalogoProducto()+"");


        Log.i("URL ", "onResponse: " + items.get(i).getFoto());
        Log.i("View ", "onResponse: " + viewHolder.foto.getContext());

        viewHolder.btnremove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                Log.i("CarritoAdapter ", "eliminar: " + items.get(i).getIdCatalogoProducto());

                Integer idCatalogoProducto = items.get(i).getIdCatalogoProducto();

                CookieManager cookieManager = new CookieManager();
                CookieHandler.setDefault(cookieManager);
                cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);

                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                CookieHandler cookieHandler = new CookieManager(new PersistentCookieStore(view.getContext()), CookiePolicy.ACCEPT_ALL);

                OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(interceptor)
                        .cookieJar(new JavaNetCookieJar(cookieHandler))
                        .build();

                Log.i("CarritoAdapter", "body: client " + client.toString());

                Retrofit retrofit = new Retrofit.Builder().baseUrl(Utilitario.baseUrlSWeb)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                android.util.Log.i("CarritoAdapter", "onResponse: ");

                ProductoService productoService = retrofit.create(ProductoService.class);

                Call<BResult> lista = productoService.eliminarCarritoCompras(idCatalogoProducto);

                lista.enqueue(new Callback<BResult>() {
                    @Override
                    public void onResponse(Call<BResult> call, Response<BResult> response) {
                        Log.e("CarritoAdapter ", "onResponse isSuccessful" + response.isSuccessful());
                        if(response.isSuccessful()) {
                            Log.i("CarritoAdapter", "onResponse body: " + response.body());
                            //finish();
                            BResult respuesta = response.body();
                            Log.i("FillCarritoCompras", "respuesta: " + respuesta);
                            if(respuesta!=null){//respuesta.getEstado()==0
                                if(respuesta.getEstado()==0) {
                                    Toast.makeText(view.getContext(),
                                            "Listado del carrito ", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } else {
                                //Toast.makeText(this,,Toast.LENGTH_SHORT).show();
                                Toast.makeText(view.getContext(),
                                        "Error al elimianr el item del carrito", Toast.LENGTH_SHORT).show();
                            }
                            /*
                            recycler = findViewById(R.id.reciclador_productosc);
                            recycler.setHasFixedSize(true);
                            lManager = new LinearLayoutManager(contexto);
                            recycler.setLayoutManager(lManager);

                            Log.i("FillCarritoCompras", "items size: " + items.size());

                            adapter = new ItemCarritoCompraAdapter(items);
                            recycler.setAdapter(adapter);*/

                    //Intent iconIntent = new Intent(view.getContext(), CarritoCompraActivity.class);
                    //view.getContext().startActivity(iconIntent);

                            //adapter = new ItemCarritoCompraAdapter(items);
                            //recycler.setAdapter(adapter);

                        //}
                           carroCompraView.iniciar();

                    }

                    @Override
                    public void onFailure(Call<BResult> call, Throwable t) {
                        Log.e("FillCarritoCompras ", "onFailure "+t.getMessage());
                        System.out.println("onFailure"+call);
                        t.printStackTrace();
                    }
                });









/*                int position = Integer.parseInt(position);
                ItemCarritoCompraAdapter adapter = new ItemCarritoCompraAdapter(items);
                items.remove(position);
                adapter.notifyDataSetChanged();*/

/*                Bundle bundle = new Bundle();
                bundle.putString("curFoto", items.get(i).getFoto());
                bundle.putString("curCantidad", items.get(i).getCantidad().toString());
                bundle.putString("curMarca", items.get(i).getMarca());
                bundle.putString("curNombre", items.get(i).getNombre());
                bundle.putString("curPrecio", items.get(i).getPrecio().toString());
                bundle.putInt("curIdCatalogoProducto", items.get(i).getIdCatalogoProducto());

                Log.i("URL ", "ItemCarritoCompraAdapter curMarca: " + items.get(i).getMarca());

                Intent iconIntent = new Intent(view.getContext(), CarritoCompraActivity.class);
                iconIntent.putExtras(bundle);
                view.getContext().startActivity(iconIntent);*/
            }
        });

    }

    public void removeAt(int position) {
        this.items.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public interface CarroCompraView {
        void iniciar();

    }
}
