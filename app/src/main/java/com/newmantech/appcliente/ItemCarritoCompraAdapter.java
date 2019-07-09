package com.newmantech.appcliente;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemCarritoCompraAdapter extends RecyclerView.Adapter<ItemCarritoCompraAdapter.ItemViewHolder> {

    private List<ItemCarritoCompra> items;
    private Context context;

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public CardView ItemCarritoCompraCardView;
        public ImageView foto;
        public TextView nombre;
        public TextView precio;
        public TextView marca;
        public TextView cantidad;
        public TextView idCatalogoProducto;
        public TextView idcliente;

        public ItemViewHolder(View v) {

            super(v);
            ItemCarritoCompraCardView = (CardView) v.findViewById(R.id.itemc_card);
            foto = (ImageView) v.findViewById(R.id.foto);
            nombre = (TextView) v.findViewById(R.id.nombre);
            marca = (TextView) v.findViewById(R.id.marca);
            cantidad = (TextView) v.findViewById(R.id.cantidad);
            precio = (TextView) v.findViewById(R.id.precio);
            idCatalogoProducto = (TextView) v.findViewById(R.id.idCatalogoProducto);
            idcliente = (TextView) v.findViewById(R.id.idcliente);
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
    public List<ItemCarritoCompra> getItems(){
        return this.items;
    }

    @Override
    public ItemCarritoCompraAdapter.ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.grid_carrito_item, viewGroup, false);
        return new ItemCarritoCompraAdapter.ItemViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ItemCarritoCompraAdapter.ItemViewHolder viewHolder, final int i) {

/*        Glide.with(viewHolder.foto.getContext())
                .load(items.get(i).getFoto())
                .centerCrop()
                .into(viewHolder.foto);*/
        Picasso.with(viewHolder.foto.getContext())
                .load(items.get(i).getFoto()).into(viewHolder.foto);

        viewHolder.nombre.setText(items.get(i).getNombre());
        viewHolder.marca.setText(items.get(i).getMarca());
        viewHolder.precio.setText("S/ " + items.get(i).getPrecio().toString());
        viewHolder.cantidad.setText(items.get(i).getCantidad().toString());
        viewHolder.idCatalogoProducto.setText(items.get(i).getIdCatalogoProducto()+"");


        Log.i("URL ", "onResponse: " + items.get(i).getFoto());
        Log.i("View ", "onResponse: " + viewHolder.foto.getContext());

/*        viewHolder.ItemCarritoCompraCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("curFoto", items.get(i).getFoto());
                bundle.putString("curCantidad", items.get(i).getCantidad().toString());
                bundle.putString("curMarca", items.get(i).getMarca());
                bundle.putString("curNombre", items.get(i).getNombre());
                bundle.putString("curPrecio", items.get(i).getPrecio().toString());
                bundle.putInt("curIdCatalogoProducto", items.get(i).getIdCatalogoProducto());

                Log.i("URL ", "ItemCarritoCompraAdapter curMarca: " + items.get(i).getMarca());

                Intent iconIntent = new Intent(view.getContext(), CarritoCompraActivity.class);
                iconIntent.putExtras(bundle);
                view.getContext().startActivity(iconIntent);
            }
        });*/

    }

}
