package com.newmantech.appcliente.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.newmantech.appcliente.R;
import com.newmantech.appcliente.model.EntityHeader;
import com.newmantech.appcliente.model.EntityTarifarioProductoPersonalizado;

import java.util.ArrayList;

public class AdapterPiscoPersonalizado extends RecyclerView.Adapter<AdapterPiscoPersonalizado.ViewHolder>  {
    ArrayList<EntityHeader> arrayList;
    Context ctx;

    public AdapterPiscoPersonalizado(ArrayList<EntityHeader> arrayList, Context ctx) {
        this.arrayList = arrayList;
        this.ctx = ctx;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content_piscos_personalizados , viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final @NonNull ViewHolder v, int i) {

        final EntityHeader obj = arrayList.get(i);
        ArrayList<EntityTarifarioProductoPersonalizado> aObjTari = obj.getArrayList();
        EntityTarifarioProductoPersonalizado objTari=null;
        if(aObjTari.size()>0){
            objTari = aObjTari.get(0);

            v.tvDescripcion.setText(objTari.getDescripcion());
            v.tvTipoPedido.setText("Pedidos " + objTari.getTipoVenta());
        }

        switch (obj.getGrupo()){
            case 1:
                v.tvCantidad.setText("1");
                if(aObjTari.size()>0){
                    v.tvTitulo1.setText(objTari.getVariedad());
                    for(int j=0; j<aObjTari.size(); j++){
                        objTari = aObjTari.get(j);
                        switch (j){
                            case 0:
                                v.tvPrecio1.setText("S/" +String.valueOf(objTari.getPrecio()));
                                break;
                            case 1:
                                v.tvPrecio2.setText("S/" +String.valueOf(objTari.getPrecio()));
                                break;
                            case 2:
                                v.tvPrecio3.setText("S/" +String.valueOf(objTari.getPrecio()));
                                break;
                        }
                    }
                    v.view1.setVisibility(View.VISIBLE);
                }

                break;
            case 2:
            case 3:
                v.tvCantidad.setText("25");
                if(aObjTari.size()>0){
                    v.tvTitulo2.setText(objTari.getVariedad());
                    for(int j=0; j<aObjTari.size(); j++){
                        objTari = aObjTari.get(j);
                        switch (objTari.getTamaño()){
                            case "50":
                                switch (objTari.getCantidad() ){
                                    case 25:
                                        v.tvPrecio1_1.setText("S/" +String.valueOf(objTari.getPrecio()));
                                        break;
                                    case 50:
                                        v.tvPrecio1_2.setText("S/" +String.valueOf(objTari.getPrecio()));
                                        break;
                                    case 100:
                                        v.tvPrecio1_3.setText("S/" +String.valueOf(objTari.getPrecio()));
                                        break;
                                }
                                break;
                            case "187":
                                switch (objTari.getCantidad() ){
                                    case 25:
                                        v.tvPrecio2_1.setText("S/" +String.valueOf(objTari.getPrecio()));
                                        break;
                                    case 50:
                                        v.tvPrecio2_2.setText("S/" +String.valueOf(objTari.getPrecio()));
                                        break;
                                    case 100:
                                        v.tvPrecio2_3.setText("S/" +String.valueOf(objTari.getPrecio()));
                                        break;
                                }
                                break;
                            case "375":
                                switch (objTari.getCantidad() ){
                                    case 25:
                                        v.tvPrecio3_1.setText("S/" +String.valueOf(objTari.getPrecio()));
                                        break;
                                    case 50:
                                        v.tvPrecio3_2.setText("S/" +String.valueOf(objTari.getPrecio()));
                                        break;
                                    case 100:
                                        v.tvPrecio3_3.setText("S/" +String.valueOf(objTari.getPrecio()));
                                        break;
                                }
                                break;
                            case "500":
                                switch (objTari.getCantidad() ){
                                    case 25:
                                        v.tvPrecio4_1.setText("S/" +String.valueOf(objTari.getPrecio()));
                                        break;
                                    case 50:
                                        v.tvPrecio4_2.setText("S/" +String.valueOf(objTari.getPrecio()));
                                        break;
                                    case 100:
                                        v.tvPrecio4_3.setText("S/" +String.valueOf(objTari.getPrecio()));
                                        break;
                                }
                                break;
                            case "750":
                                switch (objTari.getCantidad() ){
                                    case 25:
                                        v.tvPrecio5_1.setText("S/" +String.valueOf(objTari.getPrecio()));
                                        break;
                                    case 50:
                                        v.tvPrecio5_2.setText("S/" +String.valueOf(objTari.getPrecio()));
                                        break;
                                    case 100:
                                        v.tvPrecio5_3.setText("S/" +String.valueOf(objTari.getPrecio()));
                                        break;
                                }
                                break;
                        }
                    }
                    v.view2.setVisibility(View.VISIBLE);
                }
                break;
            case 4:
                v.tvCantidad.setText("25");
                if(aObjTari.size()>0){
                    v.tvTitulo3.setText(objTari.getVariedad());
                    for(int j=0; j<aObjTari.size(); j++){
                        objTari = aObjTari.get(j);
                        switch (objTari.getTamaño()){
                            case "50":
                                switch (objTari.getCantidad() ){
                                    case 25:
                                        v.tvPrec1_1.setText("S/" +String.valueOf(objTari.getPrecio()));
                                        break;
                                    case 50:
                                        v.tvPrec1_2.setText("S/" +String.valueOf(objTari.getPrecio()));
                                        break;
                                    case 100:
                                        v.tvPrec1_3.setText("S/" +String.valueOf(objTari.getPrecio()));
                                        break;
                                }
                                break;
                            case "500":
                                switch (objTari.getCantidad() ){
                                    case 25:
                                        v.tvPrec2_1.setText("S/" +String.valueOf(objTari.getPrecio()));
                                        break;
                                    case 50:
                                        v.tvPrec2_2.setText("S/" +String.valueOf(objTari.getPrecio()));
                                        break;
                                    case 100:
                                        v.tvPrec2_3.setText("S/" +String.valueOf(objTari.getPrecio()));
                                        break;
                                }
                                break;

                        }
                    }
                    v.view3.setVisibility(View.VISIBLE);
                }
                break;
        }

        v.ivAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v_) {
                int n = Integer.parseInt(v.tvCantidad.getText().toString());
                if(obj.getGrupo() == 1){
                    v.tvCantidad.setText(String.valueOf(n+1));
                }else{
                    v.tvCantidad.setText(String.valueOf(n+25));
                }
            }
        });
        v.ivRestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v_) {
                int n = Integer.parseInt(v.tvCantidad.getText().toString());
                if(obj.getGrupo() == 1){
                    if(n-1>=0)
                        v.tvCantidad.setText(String.valueOf(n-1));
                }else{
                    if(n-25>=0)
                        v.tvCantidad.setText(String.valueOf(n-25));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    /*@Override
    public View getView(int position, View v, ViewGroup parent) {


        return v;
    }*/

    public static class ViewHolder extends RecyclerView.ViewHolder{
        View view1, view2, view3;
        TextView tvTitulo1, tvTitulo2, tvTitulo3;
        TextView tvPrecio1, tvPrecio2, tvPrecio3;
        TextView tvPrecio1_1, tvPrecio2_1, tvPrecio3_1, tvPrecio4_1, tvPrecio5_1;
        TextView tvPrecio1_2, tvPrecio2_2, tvPrecio3_2, tvPrecio4_2, tvPrecio5_2;
        TextView tvPrecio1_3, tvPrecio2_3, tvPrecio3_3, tvPrecio4_3, tvPrecio5_3;

        TextView tvPrec1_1, tvPrec2_1, tvPrec1_2, tvPrec2_2, tvPrec1_3, tvPrec2_3;
        ImageView ivFoto, ivAgregar, ivRestar;
        TextView tvCantidad, tvDescripcion, tvTipoPedido;
        Button btnAgregarCarrito;

        public ViewHolder(@NonNull View v) {
            super(v);
             view1 = v.findViewById(R.id.include1);
             view2 = v.findViewById(R.id.include2);
             view3 = v.findViewById(R.id.include3);

             tvTitulo1 = v.findViewById(R.id.tvTitulo1);
             tvTitulo2 = v.findViewById(R.id.tvTitulo2);
             tvTitulo3 = v.findViewById(R.id.tvTitulo3);
            tvPrecio1 = v.findViewById(R.id.tvPrecio1);
            tvPrecio2 = v.findViewById(R.id.tvPrecio2);
            tvPrecio3 = v.findViewById(R.id.tvPrecio3);

            tvPrecio1_1 = v.findViewById(R.id.tvPrecio1_1);
            tvPrecio2_1 = v.findViewById(R.id.tvPrecio2_1);
            tvPrecio3_1 = v.findViewById(R.id.tvPrecio3_1);
            tvPrecio4_1 = v.findViewById(R.id.tvPrecio4_1);
            tvPrecio5_1 = v.findViewById(R.id.tvPrecio5_1);
            tvPrecio1_2 = v.findViewById(R.id.tvPrecio1_2);
            tvPrecio2_2 = v.findViewById(R.id.tvPrecio2_2);
            tvPrecio3_2 = v.findViewById(R.id.tvPrecio3_2);
            tvPrecio4_2 = v.findViewById(R.id.tvPrecio4_2);
            tvPrecio5_2 = v.findViewById(R.id.tvPrecio5_2);
            tvPrecio1_3 = v.findViewById(R.id.tvPrecio1_3);
            tvPrecio2_3 = v.findViewById(R.id.tvPrecio2_3);
            tvPrecio3_3 = v.findViewById(R.id.tvPrecio3_3);
            tvPrecio4_3 = v.findViewById(R.id.tvPrecio4_3);
            tvPrecio5_3 = v.findViewById(R.id.tvPrecio5_3);

            tvPrec1_1 = v.findViewById(R.id.tvPrec1_1);
            tvPrec2_1 = v.findViewById(R.id.tvPrec2_1);
            tvPrec1_2 = v.findViewById(R.id.tvPrec1_2);
            tvPrec2_2 = v.findViewById(R.id.tvPrec2_2);
            tvPrec1_3 = v.findViewById(R.id.tvPrec1_3);
            tvPrec2_3 = v.findViewById(R.id.tvPrec2_3);

            ivFoto = v.findViewById(R.id.ivFoto);
            ivAgregar = v.findViewById(R.id.ivAgregar);
            ivRestar = v.findViewById(R.id.ivRestar);
            tvCantidad = v.findViewById(R.id.tvCantidad);
            btnAgregarCarrito = v.findViewById(R.id.btnAgregarCarrito);
            tvDescripcion = v.findViewById(R.id.tvDescripcion);
            tvTipoPedido = v.findViewById(R.id.tvTipoPedido);
        }
    }
}
