package com.newmantech.appcliente;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.newmantech.appcliente.fragment.DetalleFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CatalogoProductoAdapter extends RecyclerView.Adapter<CatalogoProductoAdapter.ProductoViewHolder> {

    private List<CatalogoProducto> items;
    private CatalogoProductoView catalogoProductoView;

    public static class ProductoViewHolder extends RecyclerView.ViewHolder {

        public CardView ProductoCardView;
        public ImageView foto;
        public TextView nombre;
        public TextView descripcion;
        public TextView precio;
        public TextView marca;
        public TextView idProducto;
        public TextView idCatalogoProducto;
        float v1,v4,v5,v2,v3,v6;

        public ProductoViewHolder(View v) {

            super(v);
            ProductoCardView = (CardView) v.findViewById(R.id.producton_card);
            foto = (ImageView) v.findViewById(R.id.foto);
            marca = (TextView) v.findViewById(R.id.marca);
            nombre = (TextView) v.findViewById(R.id.nombre);
            descripcion = (TextView) v.findViewById(R.id.descripcion);
            precio = (TextView) v.findViewById(R.id.precio);
            idCatalogoProducto = (TextView) v.findViewById(R.id.idCatalogoProducto);
            //estado = (TextView) v.findViewById(R.id.estado);
            //idProducto = (TextView) v.findViewById(R.id.idpedido);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public CatalogoProductoAdapter(List<CatalogoProducto> items) {
        this.items = items;

    }

    public CatalogoProductoAdapter(List<CatalogoProducto> items,CatalogoProductoView catalogoProductoView) {
        this.items = items;
        this.catalogoProductoView = catalogoProductoView;
    }

    public List<CatalogoProducto> getItems(){
        return this.items;
    }


    @Override
    public ProductoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.grid_normal_item, viewGroup, false);
        return new ProductoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProductoViewHolder viewHolder, final int i) {

/*        Glide.with(viewHolder.foto.getContext())
                .load(items.get(i).getFoto())
                .centerCrop()
                .into(viewHolder.foto);*/
        Picasso.with(viewHolder.foto.getContext())
                .load(items.get(i).getFoto()).into(viewHolder.foto);

        viewHolder.nombre.setText(items.get(i).getNombre());
        viewHolder.marca.setText(items.get(i).getDescripcionMarca());
        viewHolder.descripcion.setText("S/ " + items.get(i).getPrecioCompra().toString() + "   " + items.get(i).getPorcentajeDescuento());
        viewHolder.precio.setText("S/ " + items.get(i).getPrecioCatalogo().toString());
        viewHolder.idCatalogoProducto.setText(items.get(i).getIdCatalogoProducto()+"");


        Log.i("URL ", "onResponse: " + items.get(i).getFoto());
        Log.i("View ", "onResponse: " + viewHolder.foto.getContext());

        viewHolder.ProductoCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("curFoto", items.get(i).getFoto());
                //bundle.putString("curImagen", items.get(i).getImagenurl());
                bundle.putString("curMarca", items.get(i).getDescripcionMarca());
                bundle.putString("curNombre", items.get(i).getNombre());
                bundle.putString("curPrecioCompra", items.get(i).getPrecioCompra().toString());
                bundle.putString("curPorcentajeDescuento", items.get(i).getPorcentajeDescuento());
                bundle.putString("curPrecioCatalogo", items.get(i).getPrecioCatalogo().toString());
                bundle.putInt("curIdCatalogoProducto", items.get(i).getIdCatalogoProducto());
                bundle.putString("curkeyItemCanje", items.get(i).getKeyItemCanje());

                bundle.putInt("curStockDisponible", items.get(i).getStockDisponible());

                bundle.putString("curCodigoNetsuite", items.get(i).getCodigoNetsuite());

                Log.i("URL ", "CatalogoProductoAdapter curCodigoNetsuite: " + items.get(i).getCodigoNetsuite());

                Log.i("URL ", "CatalogoProductoAdapter curMarca: " + items.get(i).getDescripcionMarca());

                catalogoProductoView.iniciarDetalle(bundle);

                //Intent iconIntent = new Intent(view.getContext(), DetalleActivity.class);
                //iconIntent.putExtras(bundle);
                //view.getContext().startActivity(iconIntent);
            }
        });

    }

    public interface CatalogoProductoView {
        void iniciarDetalle(Bundle bundle);

    }



}
