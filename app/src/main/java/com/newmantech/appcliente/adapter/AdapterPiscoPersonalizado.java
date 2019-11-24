package com.newmantech.appcliente.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.newmantech.appcliente.R;
import com.newmantech.appcliente.model.EntityPiscoPersonalizado;

import java.util.ArrayList;

public class AdapterPiscoPersonalizado extends BaseAdapter {
    ArrayList<EntityPiscoPersonalizado> arrayList;
    Context ctx;

    public AdapterPiscoPersonalizado(ArrayList<EntityPiscoPersonalizado> arrayList, Context ctx) {
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
        EntityPiscoPersonalizado obj = arrayList.get(position);
        if(v == null){
            LayoutInflater layoutInflater = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
            v = layoutInflater.inflate(R.layout.content_piscos_personalizados,null);
        }
        View view1 = v.findViewById(R.id.include1);
        View view2 = v.findViewById(R.id.include2);
        View view3 = v.findViewById(R.id.include3);

        TextView tvTitulo1 = v.findViewById(R.id.tvTitulo1);
        TextView tvTitulo2 = v.findViewById(R.id.tvTitulo2);
        TextView tvTitulo3 = v.findViewById(R.id.tvTitulo3);

        switch (obj.getTipo()){
            case 1:
                tvTitulo1.setText(obj.getTitulo());
                view1.setVisibility(View.VISIBLE);
                break;
            case 2:
                view2.setVisibility(View.VISIBLE);
                tvTitulo2.setText(obj.getTitulo());
                break;
            case 3:
                view3.setVisibility(View.VISIBLE);
                tvTitulo3.setText(obj.getTitulo());
                break;
        }

        return v;
    }
}
