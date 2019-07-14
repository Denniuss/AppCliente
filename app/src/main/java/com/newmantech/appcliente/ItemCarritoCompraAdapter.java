package com.newmantech.appcliente;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ItemCarritoCompraAdapter extends RecyclerView.Adapter<ItemCarritoCompraAdapter.ItemViewHolder> {

    private List<ItemCarritoCompra> items;
    private Context context;
    private AdapterView.OnItemClickListener mlistener;

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
            public void onClick(View view) {
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
}
