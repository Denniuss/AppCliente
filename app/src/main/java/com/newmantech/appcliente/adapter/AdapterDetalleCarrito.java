package com.newmantech.appcliente.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.newmantech.appcliente.R;
import com.newmantech.appcliente.activity.DetalleCarritoActivity;
import com.newmantech.appcliente.activity.ElegirModeloActivity;
import com.newmantech.appcliente.activity.PersonalizarModeloActivity;
import com.newmantech.appcliente.model.EntityDetalleCarrito;

import java.util.ArrayList;

public class AdapterDetalleCarrito extends RecyclerView.Adapter<AdapterDetalleCarrito.ViewHolder> {
    ArrayList<EntityDetalleCarrito> arrayList;
    Context context;
    public onClickEvent onclick;

    public interface onClickEvent{
        public void onClickDelete(int i, int idProducto);
    }

    public AdapterDetalleCarrito(ArrayList<EntityDetalleCarrito> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    public void setOnclickEvent(onClickEvent onclick ){
        this.onclick = onclick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content_detalle_carrito , viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder v, final int i) {

        v.btnElegirModelo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ElegirModeloActivity.class);
                context.startActivity(i);
            }
        });
        v.btnPersonalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, PersonalizarModeloActivity.class);
                context.startActivity(i);
            }
        });
        v.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclick.onClickDelete(i, 2);

            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvSubtotal, tvPrecioUnidad, tvCantidad, tvTitulo;
        ImageView ivDelete, ivFoto;
        Button btnElegirModelo, btnPersonalizar;
        public ViewHolder(@NonNull View v) {
            super(v);
            tvSubtotal = v.findViewById(R.id.tvSubtotal);
            tvPrecioUnidad = v.findViewById(R.id.tvPrecioUnidad);
            tvCantidad = v.findViewById(R.id.tvCantidad);
            tvTitulo = v.findViewById(R.id.tvTitulo);
            ivDelete = v.findViewById(R.id.ivDelete);
            ivFoto = v.findViewById(R.id.ivFoto);
            btnElegirModelo = v.findViewById(R.id.btnElegirModelo);
            btnPersonalizar = v.findViewById(R.id.btnPersonalizar);
        }
    }
}
