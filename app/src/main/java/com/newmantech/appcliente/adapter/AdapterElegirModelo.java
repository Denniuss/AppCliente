package com.newmantech.appcliente.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.newmantech.appcliente.R;
import com.newmantech.appcliente.model.EntityElegirModelo;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterElegirModelo extends BaseAdapter {
    ArrayList<EntityElegirModelo> arrayList;
    private Context ctx;

    public AdapterElegirModelo(ArrayList<EntityElegirModelo> arrayList, Context ctx) {
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
        if(v == null){
            LayoutInflater layoutInflater = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
            v = layoutInflater.inflate(R.layout.content_elegir_modelo,null);
        }
        final EntityElegirModelo obj = arrayList.get(position);

        final ImageView ivFoto = v.findViewById(R.id.ivFoto);
        Button btnModelo = v.findViewById(R.id.btnModelo);
        btnModelo.setText(obj.getDescripcion());
        btnModelo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx, " ID_MODELO = " + String.valueOf(obj.getId()), Toast.LENGTH_LONG).show();
            }
        });

        Picasso.with(ctx)
                .load(obj.getUrl())
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

        return v;
    }
}
