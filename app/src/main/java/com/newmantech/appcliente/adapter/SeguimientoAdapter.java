package com.newmantech.appcliente.adapter;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.newmantech.appcliente.R;
import com.newmantech.appcliente.model.Direccion;
import com.newmantech.appcliente.model.Pedido;
import com.newmantech.appcliente.utils.Utilitario;

import java.util.List;

public class SeguimientoAdapter extends RecyclerView.Adapter<SeguimientoAdapter.SeguimientoViewHolder> {

    private List<Pedido> items;
    private SeguimientoView seguimientoView;

    public static class SeguimientoViewHolder extends RecyclerView.ViewHolder {

        public CardView SeguimientoCardView;
        //public ImageView imagen;
        public TextView idVenta;
        public TextView cantidadPedido;
        public TextView estado;
        public TextView fechaPedidoFormat;

        public SeguimientoViewHolder(View v) {

            super(v);
            SeguimientoCardView = (CardView) v.findViewById(R.id.seguimiento_card);
            //imagen = (ImageView) v.findViewById(R.id.imagen);
            idVenta = (TextView) v.findViewById(R.id.idVenta);
            cantidadPedido = (TextView) v.findViewById(R.id.cantidadPedido);
            estado = (TextView) v.findViewById(R.id.estado);
            fechaPedidoFormat = (TextView) v.findViewById(R.id.fechaPedidoFormat);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public SeguimientoAdapter(List<Pedido> items) {
        this.items = items;
    }

    public SeguimientoAdapter(List<Pedido> items, SeguimientoView seguimientoView) {
        this.items = items;
        this.seguimientoView = seguimientoView;
    }


    public List<Pedido> getItems(){
        return this.items;
    }

    @Override
    public SeguimientoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.seguimiento_card, viewGroup, false);
        return new SeguimientoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SeguimientoViewHolder viewHolder, final int i) {
        //viewHolder.imagen.setImageResource(items.get(i).getImagen1());
        //Picasso.with(viewHolder.imagen.getContext())
               // .load(items.get(i).getImagenurl()).into(viewHolder.imagen);

        Log.i("onResponse Adapter", "Response Cantidad: " + items.get(i).getCantidad()+"");
        Log.i("onResponse Adapter", "Response getEstado: " + items.get(i).getEstado()+"");
        Log.i("onResponse Adapter", "Response getIdPedido: " + items.get(i).getIdPedido()+"");
        Log.i("onResponse Adapter", "Response getEstadoParametro Codigo: " + items.get(i).getFlujo().getEstadoParametro().getCodigo()+"");


        viewHolder.cantidadPedido.setText("Cantidad " +items.get(i).getCantidad());
        viewHolder.estado.setText("Estado " + Utilitario.ESTADO_FLUJO_TRABAJO.getEstadoWorkFlowByID(items.get(i).getFlujo().getEstadoParametro().getCodigo()).getKeyMsg() +"");
        viewHolder.idVenta.setText("Pedido " + items.get(i).getIdPedido());
        viewHolder.fechaPedidoFormat.setText("Fecha Pedido " +items.get(i).getFechaCreacionFormat());

        //viewHolder.direccion.setText("Dirección: " + items.get(i).getDireccion());
        /*viewHolder.distrito.setText("Distrito: " + String.valueOf(items.get(i).getDistrito()));
        viewHolder.departamento.setText("Departamento: " + String.valueOf(items.get(i).getDepartamento()));
        viewHolder.provincia.setText("Provincia: " + String.valueOf(items.get(i).getCiudad()));
        viewHolder.distrito.setText("Distrito: " + String.valueOf(items.get(i).getDistrito()));*/

        viewHolder.SeguimientoCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putLong("curIdPedido", items.get(i).getIdPedido());
                bundle.putString("curEstado", items.get(i).getEstado()+"");
                bundle.putString("curCantidad", items.get(i).getCantidad() +"");
                bundle.putString("curFechaPedido", items.get(i).getFechaCreacionFormat() +"");

                seguimientoView.iniciarDetalleDireccion(bundle);

                //Intent iconIntent = new Intent(view.getContext(), DetalleDireccionActivity.class);
                //iconIntent.putExtras(bundle);
                //view.getContext().startActivity(iconIntent);
            }
        });
    }

    public interface SeguimientoView {
        void iniciarDetalleDireccion(Bundle bundle);

    }

}
