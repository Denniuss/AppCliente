package com.newmantech.appcliente.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.newmantech.appcliente.R;
import com.newmantech.appcliente.activity.PiscosPersonalizadosActivity;
import com.newmantech.appcliente.model.EntityProductoPersonalizado;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterPedidoPersonalizado extends BaseAdapter {
    ArrayList<EntityProductoPersonalizado> arrayList;
    Context ctx;

    public AdapterPedidoPersonalizado(ArrayList<EntityProductoPersonalizado> arrayList, Context ctx) {
        this.arrayList = arrayList;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        EntityProductoPersonalizado obj = arrayList.get(position);
        if(v == null){
            LayoutInflater layoutInflater = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
            v = layoutInflater.inflate(R.layout.content_pedido_personalizado,null);
        }
        final ImageView ivFoto = v.findViewById(R.id.ivFoto);

        Picasso.with(ctx)
                .load(obj.getImagenmenuurl())
                .placeholder(R.drawable.loanding)
                .into(ivFoto, new Callback() {
                    @Override
                    public void onSuccess() {
                        ivFoto.setAnimation(AnimationUtils.loadAnimation(ctx, R.anim.btn_animation));
                    }
                    @Override
                    public void onError() {
                        Toast.makeText(ctx, "Ocurrió un error", Toast.LENGTH_SHORT);
                    }
                });

        LinearLayout llContent =  v.findViewById(R.id.llContent);
        TextView tvTitiuno = v.findViewById(R.id.tvTitiuno);
        tvTitiuno.setText(obj.getDescripcion());

        llContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ctx, PiscosPersonalizadosActivity.class);
                ctx.startActivity(i);
            }
        });


        return v;
    }
}
