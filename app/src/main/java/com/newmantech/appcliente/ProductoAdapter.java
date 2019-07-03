package com.newmantech.appcliente;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {

    private List<Producto> items;

    public static class ProductoViewHolder extends RecyclerView.ViewHolder {

        public CardView ProductoCardView;
        public ImageView imagen;
        public TextView cliente;
        public TextView direccion;
        public TextView distrito;
        public TextView estado;
        public TextView idProducto;

        public ProductoViewHolder(View v) {

            super(v);
            ProductoCardView = (CardView) v.findViewById(R.id.pedido_card);
            imagen = (ImageView) v.findViewById(R.id.imagen);
            cliente = (TextView) v.findViewById(R.id.cliente);
            direccion = (TextView) v.findViewById(R.id.direccion);
            distrito = (TextView) v.findViewById(R.id.distrito);
            estado = (TextView) v.findViewById(R.id.estado);
            idProducto = (TextView) v.findViewById(R.id.idpedido);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public ProductoAdapter(List<Producto> items) {
        this.items = items;
    }

    public List<Producto> getItems(){
        return this.items;
    }

    @Override
    public ProductoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.pedido_card, viewGroup, false);
        return new ProductoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProductoViewHolder viewHolder, final int i) {
        //viewHolder.imagen.setImageResource(items.get(i).getImagen1());
        //Picasso.with(viewHolder.imagen.getContext())
               // .load(items.get(i).getImagenurl()).into(viewHolder.imagen);

        //viewHolder.cliente.setText(items.get(i).getCliente());
        //viewHolder.direccion.setText("Dirección: " + items.get(i).getDireccion());
        //viewHolder.distrito.setText("Distrito: " + String.valueOf(items.get(i).getDistrito()));
        viewHolder.estado.setText("Estado: " + String.valueOf(items.get(i).getEstado()));
        viewHolder.idProducto.setText(String.valueOf(items.get(i).getIdProducto()));

        viewHolder.ProductoCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                //bundle.putInt("curImagen", items.get(i).getImagen());
                //bundle.putString("curImagen", items.get(i).getImagenurl());
                //bundle.putString("curCliente", items.get(i).getCliente());
                //bundle.putString("curDireccion", items.get(i).getDireccion());
                //bundle.putString("curDistrito", items.get(i).getDistrito());
                //bundle.putString("curEstado", items.get(i).getEstado());
                bundle.putInt("curIdProducto", items.get(i).getIdProducto());
                //bundle.putString("curLatitud", items.get(i).getLatitud());
                //bundle.putString("curLongitud", items.get(i).getLongitud());

                Intent iconIntent = new Intent(view.getContext(), DetalleDireccionActivity.class);
                iconIntent.putExtras(bundle);
                view.getContext().startActivity(iconIntent);
            }
        });
    }

}
