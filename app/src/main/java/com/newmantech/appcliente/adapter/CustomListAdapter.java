package com.newmantech.appcliente.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.newmantech.appcliente.R;
import com.newmantech.appcliente.utils.ListItemPedidoSeguimiento;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter{
    private ArrayList<ListItemPedidoSeguimiento> listData;
    private LayoutInflater layoutInflater;
    public CustomListAdapter(Context aContext, ArrayList<ListItemPedidoSeguimiento> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }
    @Override
    public int getCount() {
        return listData.size();
    }
    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    public View getView(int position, View v, ViewGroup vg) {
        ViewHolder holder;
        if (v == null) {
            v = layoutInflater.inflate(R.layout.list_row, null);
            holder = new ViewHolder();
            holder.uId = (TextView) v.findViewById(R.id.idDetallePedido);
            holder.UNombre = (TextView) v.findViewById(R.id.nombreDet);
            holder.UCantidad = (TextView) v.findViewById(R.id.cantidaDet);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.uId.setText("ID " +listData.get(position).getId());
        holder.UNombre.setText(listData.get(position).getNombre());
        holder.UCantidad.setText("#"+listData.get(position).getCantidad());
        return v;
    }
    static class ViewHolder {
        TextView uId;
        TextView UNombre;
        TextView UCantidad;
    }
}
