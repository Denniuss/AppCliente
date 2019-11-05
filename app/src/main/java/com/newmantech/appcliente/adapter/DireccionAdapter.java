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

import java.util.List;

public class DireccionAdapter extends RecyclerView.Adapter<DireccionAdapter.DireccionViewHolder> {

    private List<Direccion> items;
    private DireccionView direccionView;

    public static class DireccionViewHolder extends RecyclerView.ViewHolder {

        public CardView DireccionCardView;
        public ImageView imagen;
        public TextView nombreDireccion;
        public TextView nombreContacto;
        public TextView direccion;

        public TextView departamento;
        public TextView provincia;
        public TextView distrito;

        public DireccionViewHolder(View v) {

            super(v);
            DireccionCardView = (CardView) v.findViewById(R.id.distrito_card);
            //imagen = (ImageView) v.findViewById(R.id.imagen);
            nombreDireccion = (TextView) v.findViewById(R.id.nombreDireccion);
            nombreContacto = (TextView) v.findViewById(R.id.nombreContacto);
            direccion = (TextView) v.findViewById(R.id.direccion);
            departamento = (TextView) v.findViewById(R.id.departamento);
            provincia = (TextView) v.findViewById(R.id.provincia);
            distrito = (TextView) v.findViewById(R.id.distrito);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public DireccionAdapter(List<Direccion> items) {
        this.items = items;
    }

    public DireccionAdapter(List<Direccion> items,DireccionView direccionView) {
        this.items = items;
        this.direccionView = direccionView;
    }


    public List<Direccion> getItems(){
        return this.items;
    }

    @Override
    public DireccionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.distrito_card, viewGroup, false);
        return new DireccionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DireccionViewHolder viewHolder, final int i) {
        //viewHolder.imagen.setImageResource(items.get(i).getImagen1());
        //Picasso.with(viewHolder.imagen.getContext())
               // .load(items.get(i).getImagenurl()).into(viewHolder.imagen);

        Log.i("onResponse Adapter", "Response ubigeo: " + items.get(i));

        viewHolder.nombreDireccion.setText(items.get(i).getNombreDireccion());
        viewHolder.nombreContacto.setText(items.get(i).getNombreContacto());
        viewHolder.direccion.setText(items.get(i).getDireccion());
        //viewHolder.direccion.setText("Dirección: " + items.get(i).getDireccion());
        viewHolder.distrito.setText("Distrito: " + String.valueOf(items.get(i).getDistrito()));
        viewHolder.departamento.setText("Departamento: " + String.valueOf(items.get(i).getDepartamento()));
        viewHolder.provincia.setText("Provincia: " + String.valueOf(items.get(i).getCiudad()));
        viewHolder.distrito.setText("Distrito: " + String.valueOf(items.get(i).getDistrito()));

        viewHolder.DireccionCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                //bundle.putInt("curImagen", items.get(i).getImagen());
                //bundle.putString("curImagen", items.get(i).getImagenurl());
                bundle.putLong("curIdDireccion", items.get(i).getIdDireccion());
                bundle.putString("curNombreDireccion", items.get(i).getNombreDireccion());
                bundle.putString("curDepartamento", items.get(i).getDepartamento());
                bundle.putString("curProvincia", items.get(i).getCiudad());
                bundle.putString("curDistrito", items.get(i).getDistrito());
                bundle.putString("curDireccion", items.get(i).getDireccion());

                bundle.putString("curReferencia", items.get(i).getReferenciaDireccion());
                bundle.putString("curTelefonoContacto", items.get(i).getTelefonoContacto());
                bundle.putString("curNombreContacto", items.get(i).getNombreContacto());
                bundle.putBoolean("curEstablecerDireccion", items.get(i).getEstablecerDireccion());

                bundle.putString("curidDepartamento", items.get(i).getCodigoDepartamento());
                bundle.putString("curidProvincia", items.get(i).getCodigoProvincia());
                bundle.putString("curidDistrito", items.get(i).getCodigoDistrito());
                Log.i("onResponse Adapter", "Response idDireccion: " + items.get(i).getIdDireccion());

                bundle.putLong("curidDireccion", items.get(i).getIdDireccion());

                direccionView.iniciarDetalleDireccion(bundle);

                //Intent iconIntent = new Intent(view.getContext(), DetalleDireccionActivity.class);
                //iconIntent.putExtras(bundle);
                //view.getContext().startActivity(iconIntent);
            }
        });
    }

    public interface DireccionView {
        void iniciarDetalleDireccion(Bundle bundle);

    }

}
