package com.newmantech.appcliente.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.newmantech.appcliente.model.BResult;
import com.newmantech.appcliente.model.Cliente;
import com.newmantech.appcliente.model.Direccion;
import com.newmantech.appcliente.service.DireccionService;
import com.newmantech.appcliente.R;
import com.newmantech.appcliente.model.Ubigeo;
import com.newmantech.appcliente.utils.Utilitario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DetalleDireccionFragment extends Fragment {

    public Spinner spinnerDepartamento;
    public Spinner spinnerProvincia;
    public Spinner spinnerDistrito;

    //public List<Ubigeo> listaDeparamentos = new ArrayList<>();
    public TextView departamento;
    public TextView provincia;
    public TextView distrito;

    public TextView iddepartamento;
    public TextView idprovincia;
    public TextView iddistrito;

    public TextView direccion;
    public TextView nombreDireccion;


    public TextView nombreContacto;
    public TextView telefonoContacto;
    public TextView referencia;
    public CheckBox establecerDireccionCheck;

    public TextView iddireccion;
    public Button btnGuardarDireccion;

    List<Ubigeo> listaDepartamentoUbigeo = new ArrayList<>();
    List<Ubigeo> listaProvinciaUbigeo = new ArrayList<>();
    List<Ubigeo> listaDistritoUbigeo = new ArrayList<>();

    Map<String,Ubigeo> departamentoMap = new HashMap<>();
    Map<String,Ubigeo> provinciaMap = new HashMap<>();
    Map<String,Ubigeo> distitoMap = new HashMap<>();


    Retrofit retrofit = new Retrofit.Builder().baseUrl(Utilitario.baseUrlServio).addConverterFactory(GsonConverterFactory.create()).build();
    DireccionService direccionService = retrofit.create(DireccionService.class);


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_direccion, container, false);
        spinnerDepartamento = (Spinner) view.findViewById(R.id.spinnerDep);
        spinnerProvincia = (Spinner) view.findViewById(R.id.spinnerProv);
        spinnerDistrito = (Spinner) view.findViewById(R.id.spinnerDistr);

        iddireccion = (TextView) view.findViewById(R.id.idDireccion);
        departamento = (TextView) view.findViewById(R.id.departamento);
        provincia = (TextView) view.findViewById(R.id.provincia);
        distrito = (TextView) view.findViewById(R.id.distrito);
        direccion = (TextView) view.findViewById(R.id.direccion);
        nombreDireccion = (TextView) view.findViewById(R.id.nombreDireccion);
        btnGuardarDireccion = (Button) view.findViewById(R.id.btnGuardarDireccion);

        iddepartamento = (TextView) view.findViewById(R.id.iddepartamento);
        idprovincia = (TextView) view.findViewById(R.id.idprovincia);
        iddistrito = (TextView) view.findViewById(R.id.iddistrito);

        nombreContacto = (TextView) view.findViewById(R.id.nombreContacto);
        telefonoContacto = (TextView) view.findViewById(R.id.telefonoContacto);
        referencia = (TextView) view.findViewById(R.id.referencia);
        establecerDireccionCheck = (CheckBox) view.findViewById(R.id.establecerDireccionCheck);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getArguments() != null){
            iddireccion.setText(getArguments().getLong("curidDireccion")+"");

            Log.i("curidDireccion", "body: " + getArguments().getLong("curidDireccion")+"");

            nombreContacto.setText( getArguments().getString("curNombreContacto"));
            telefonoContacto.setText( getArguments().getString("curTelefonoContacto"));
            referencia.setText( getArguments().getString("curReferencia"));
            establecerDireccionCheck.setChecked( getArguments().getBoolean("curEstablecerDireccion"));

            nombreDireccion.setText( getArguments().getString("curNombreDireccion"));
            direccion.setText(getArguments().getString("curDireccion"));
            Log.i("curidDepartamento", "body: " + getArguments().getString("curidDepartamento"));

            iddepartamento.setText(getArguments().getString("curidDepartamento")+"");
            idprovincia.setText(getArguments().getString("curidProvincia")+"");
            iddistrito.setText(getArguments().getString("curidDistrito")+"");

            Call<List<Ubigeo>> lista = direccionService.getListadoDepartamento();

            lista.enqueue(new Callback<List<Ubigeo>>() {
                @Override
                public void onResponse(Call<List<Ubigeo>> call, Response<List<Ubigeo>> response) {
                    Log.i("onResponse chamado", "Response ubigeo: " + response.isSuccessful());
                    if(response.isSuccessful()) {
                        Log.i("onResponse chamado", "body: " + response.body());

                        Ubigeo temp = new Ubigeo();
                        temp.setNombreUbigeo("Seleccione");
                        temp.setCodigoDepartamento("-1");
                        listaDepartamentoUbigeo = new ArrayList<>();
                        listaDepartamentoUbigeo.add(temp);
                        listaDepartamentoUbigeo.addAll(response.body());

                        departamentoMap = new HashMap<>();
                        provinciaMap = new HashMap<>();
                        distitoMap = new HashMap<>();
                        for(Ubigeo x: listaDepartamentoUbigeo) {
                            Log.i("onResponse chamado", "body: " + x.getCodigoDepartamento() +"|"+x.getNombreUbigeo());
                            if(!departamentoMap.containsKey(x.getCodigoDepartamento())){
                                departamentoMap.put(x.getCodigoDepartamento(),x);
                            }
                        }


                        Log.i("SIZE ", "onResponse: listaDepartamentoUbigeo " + listaDepartamentoUbigeo.size());
                        cargarComboDepartamento();
                    }

                }

                @Override
                public void onFailure(Call<List<Ubigeo>> call, Throwable t) {
                    Log.e("onFaillure chamado ubig", t.getMessage());
                }
            });

            btnGuardarDireccion.setOnClickListener(new View.OnClickListener(){
                @Override
                public  void onClick(View v){
                    procesarRegistroDireccion();
                }
            });

        }
    }

    public void cargarComboDepartamento() {

        Log.i("onResponse ", "ArrayAdapter: ");

        final String[] itemSeleecionadoDept = {""};
        final Integer[] posicionEncontradaDept = {0};
        ArrayAdapter<Ubigeo> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,listaDepartamentoUbigeo);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDepartamento.setAdapter(arrayAdapter);
        spinnerDepartamento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Ubigeo ubigeo = (Ubigeo) parent.getSelectedItem();
                //displayUbigeoData(ubigeo);
                //Toast.makeText(this,list1.get(position)+"",Toast.LENGTH_SHORT).show();

                Log.e("Log", "iddepartamento " + iddepartamento.getText());
                Log.e("Log", "position " + listaDepartamentoUbigeo.get(position).getCodigoDepartamento());
                if(iddepartamento.getText().equals(listaDepartamentoUbigeo.get(position).getCodigoDepartamento())) {
                    //String itemSeleecionadoDept = parent.getItemAtPosition(position).toString();
                    //Toast.makeText(parent.getContext(),"Selected" + itemSeleecionadoDept, Toast.LENGTH_SHORT);
                    posicionEncontradaDept[0] = position;

                }

                String codigoDepartamento = listaDepartamentoUbigeo.get(position).getCodigoDepartamento();
                String codigoProvincia = listaDepartamentoUbigeo.get(position).getCodigoProvincia();
                String desc = listaDepartamentoUbigeo.get(position).getNombreUbigeo();
                Log.e("Log", "spinnerDepartamento ");

                //Toast.makeText(this,desc,Toast.LENGTH_SHORT).show();
                Log.e("Log", "codigoDepartamento " + codigoDepartamento + " desc " + desc);
                Log.e("Log", "codigoProvincia " + codigoProvincia + " desc " + desc);

                if(!codigoDepartamento.equals("-1")) {
                    Call<List<Ubigeo>> listaProv = direccionService.getListadoProvincia(codigoDepartamento,codigoProvincia);

                    listaProv.enqueue(new Callback<List<Ubigeo>>() {
                        @Override
                        public void onResponse(Call<List<Ubigeo>> call, Response<List<Ubigeo>> response) {
                            Log.i("onResponse chamado", "Response ubigeo: " + response.isSuccessful());
                            if(response.isSuccessful()) {
                                Log.i("onResponse chamado", "body: " + response.body());

                                //spinnerDepartamento.setSelection();

                                Ubigeo temp = new Ubigeo();
                                temp.setNombreUbigeo("Seleccione");
                                temp.setCodigoProvincia("-1");
                                listaProvinciaUbigeo = new ArrayList<>();
                                listaProvinciaUbigeo.add(temp);
                                listaProvinciaUbigeo.addAll(response.body());

                                provinciaMap = new HashMap<>();
                                distitoMap = new HashMap<>();

                                for(Ubigeo x: listaProvinciaUbigeo) {
                                    Log.i("onResponse chamado", "body: " + x.getCodigoDepartamento() +"|"+x.getNombreUbigeo());
                                    if(!provinciaMap.containsKey(x.getCodigoProvincia())){
                                        provinciaMap.put(x.getCodigoProvincia(),x);
                                    }
                                }

                                Log.i("SIZE ", "onResponse: listaProvinciaUbigeo " + listaProvinciaUbigeo.size());
                                cargarComboProvincia();
                            }

                        }

                        @Override
                        public void onFailure(Call<List<Ubigeo>> call, Throwable t) {
                            Log.e("onFaillure chamado ubig", t.getMessage());
                        }
                    });
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Integer posicionDep = Integer.parseInt( departamentoMap.get(iddepartamento).getNombreUbigeo()+"");

        ArrayAdapter adapter = (ArrayAdapter) spinnerDepartamento.getAdapter();
        Log.i("SET ", "onResponse: adapter " + adapter);
        Log.i("SET ", "onResponse: iddepartamento " + iddepartamento.getText());
        Log.i("SET2 ", "onResponse: MAP " + departamentoMap);
        Log.i("SET3 ", "onResponse: MAP2 " + departamentoMap.get(iddepartamento.getText()+""));

        //Log.i("SET2 ", "onResponse: ubigeo " + departamentoMap.get(iddepartamento.getText()+"").getNombreUbigeo());
        //Integer posicionDep = adapter.getPosition(departamentoMap.get(iddepartamento.getText()+"").getNombreUbigeo());
        //Log.i("SET3 ", "onResponse: adapter " + posicionDep);

        if(departamentoMap!=null) {
            Ubigeo existeDepartamento = departamentoMap.get(iddepartamento.getText()+"");
            Log.i("DetalleDireccionAct", "onResponse: existeDepartamento " + existeDepartamento);
            if(existeDepartamento!=null) {
                spinnerDepartamento.setSelection(obtenerPosicionItem(spinnerDepartamento,existeDepartamento.getNombreUbigeo()));
            }
        }
        //spinnerDepartamento.position
        //spinnerDepartamento.setSelection(posicionDep);
    }

    public static int obtenerPosicionItem(Spinner spinner, String fruta) {
        //Creamos la variable posicion y lo inicializamos en 0
        int posicion = 0;
        //Recorre el spinner en busca del ítem que coincida con el parametro `String fruta`
        //que lo pasaremos posteriormente
        for (int i = 0; i < spinner.getCount(); i++) {
            //Almacena la posición del ítem que coincida con la búsqueda
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(fruta)) {
                posicion = i;
            }
        }
        //Devuelve un valor entero (si encontro una coincidencia devuelve la
        // posición 0 o N, de lo contrario devuelve 0 = posición inicial)
        return posicion;
    }


    public void cargarComboProvincia() {
        Log.i("onResponse ", "ArrayAdapter: ");

        ArrayAdapter<Ubigeo> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,listaProvinciaUbigeo);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProvincia.setAdapter(arrayAdapter);

        spinnerProvincia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Ubigeo ubigeo = (Ubigeo) parent.getSelectedItem();
                //displayUbigeoData(ubigeo);
                //Toast.makeText(this,list1.get(position)+"",Toast.LENGTH_SHORT).show();
                //Integer ids = listaProvinciaUbigeo.get(position).getIdUbigeo();
                Log.e("Log", "spinnerProvincia ");
                String codigoDepartamento = listaProvinciaUbigeo.get(position).getCodigoDepartamento();
                String codigoProvincia = listaProvinciaUbigeo.get(position).getCodigoProvincia();
                String codigoDistrito = listaProvinciaUbigeo.get(position).getCodigoDistrito();
                String desc = listaProvinciaUbigeo.get(position).getNombreUbigeo();

                Log.e("Log", "codigoDepartamento " + codigoDepartamento + " desc " + desc);
                Log.e("Log", "codigoProvincia " + codigoProvincia + " desc " + desc);
                Log.e("Log", "codigoDistrito" + codigoDistrito + " desc " + desc);

                if(codigoProvincia!=null && !codigoProvincia.equals("-1")) {
                    //Toast.makeText(this,desc,Toast.LENGTH_SHORT).show();
                    //cargarComboDistrito(codigoProvincia);
                    Call<List<Ubigeo>> listaDistrito = direccionService.getListadoDistrito(codigoDepartamento,codigoProvincia,codigoDistrito);
                    listaDistrito.enqueue(new Callback<List<Ubigeo>>() {
                        @Override
                        public void onResponse(Call<List<Ubigeo>> call, Response<List<Ubigeo>> response) {
                            Log.i("onResponse chamado", "Response ubigeo: " + response.isSuccessful());
                            if(response.isSuccessful()) {
                                Log.i("onResponse chamado", "body: " + response.body());

                                Ubigeo temp = new Ubigeo();
                                temp.setNombreUbigeo("Seleccione");
                                temp.setCodigoDepartamento("-1");
                                listaDistritoUbigeo = new ArrayList<>();
                                listaDistritoUbigeo .add(temp);
                                listaDistritoUbigeo .addAll(response.body());

                                distitoMap = new HashMap<>();
                                for(Ubigeo x: listaDistritoUbigeo) {
                                    Log.i("onResponse chamado", "listaDistritoUbigeo body: " +"|"+x.getIdUbigeo() + x.getCodigoDistrito() +"|"+x.getNombreUbigeo());
                                    if(!distitoMap.containsKey(x.getCodigoDistrito()+"")){
                                        distitoMap.put(x.getCodigoDistrito()+"",x);
                                    }
                                }

                                Log.i("SIZE ", "onResponse: listaDistritoUbigeo " + listaDistritoUbigeo.size());
                                cargarComboDistrito();
                            }

                        }

                        @Override
                        public void onFailure(Call<List<Ubigeo>> call, Throwable t) {
                            Log.e("onFaillure chamado ubig", t.getMessage());
                        }
                    });
                }



            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Log.i("onResponse ", "idprovincia select" + idprovincia.getText());
        Log.i("onResponse ", "idprovincia provinciaMap" + provinciaMap);

        //spinnerProvincia.setSelection(Integer.parseInt(idprovincia.getText()+""));
        //spinnerProvincia.setSelection(getIndex(spinnerProvincia, "Lima"));

        if(provinciaMap!=null) {
            Ubigeo existeProvincia = provinciaMap.get(idprovincia.getText()+"");
            Log.i("DetalleDireccionAct", "onResponse: existeProvincia " + existeProvincia);
            if(existeProvincia!=null) {
                spinnerProvincia.setSelection(obtenerPosicionItem(spinnerProvincia,existeProvincia.getNombreUbigeo()));
            }
        }
        //.setSelection(obtenerPosicionItem(spinnerProvincia,provinciaMap.get(idprovincia.getText()+"").getNombreUbigeo()));

    }
    private int getIndex(Spinner spinner, String myString){
        Log.i("onResponse ", "getIndex " );

        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            Log.i("onResponse ", "getItemAtPosition " + spinner.getItemAtPosition(i));
            Log.i("onResponse ", "getSelectedItemId " + spinner.getSelectedItemId());
            if (spinner.getItemAtPosition(i).equals(myString)){
                index = i;
            }
        }
        return index;
    }


    public void cargarComboDistrito() {
        Log.i("onResponse ", "ArrayAdapter: ");

        ArrayAdapter<Ubigeo> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,listaDistritoUbigeo);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDistrito.setAdapter(arrayAdapter);

        Log.e("Log", "spinnerDistrito ");

        spinnerDistrito.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Ubigeo ubigeo = (Ubigeo) parent.getSelectedItem();
                //displayUbigeoData(ubigeo);
                //Toast.makeText(this,list1.get(position)+"",Toast.LENGTH_SHORT).show();
                String idDistrito = listaDistritoUbigeo.get(position).getCodigoDistrito();
                String desc = listaDistritoUbigeo.get(position).getNombreUbigeo();
                //Toast.makeText(this,desc,Toast.LENGTH_SHORT).show();
                Log.e("Log", "id " + idDistrito + " desc " + desc);
                //iddistrito.setText(idDistrito+"");
                Log.e("Log", "onItemSelected idDistrito " + idDistrito );
                iddistrito.setText(idDistrito);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Log.i("onResponse ", "iddistrito select " + iddistrito.getText());
        Log.i("onResponse ", "iddistrito distitoMap" + distitoMap);


        if(distitoMap!=null) {
            Ubigeo existeDistrito = distitoMap.get(iddistrito.getText()+"");
            Log.i("DetalleDireccionAct", "onResponse: existeDistrito " + existeDistrito);
            if(existeDistrito!=null) {
                spinnerDistrito.setSelection(obtenerPosicionItem(spinnerDistrito,existeDistrito.getNombreUbigeo()));
            }
        }
        //spinnerDistrito.setSelection(obtenerPosicionItem(spinnerDistrito,distitoMap.get(iddistrito.getText()+"").getNombreUbigeo()));

    }



    public void geteSelectedUbigeo() {
        Ubigeo ubigeo = (Ubigeo) spinnerDepartamento.getSelectedItem();
        displayUbigeoData(ubigeo);
    }

    private void displayUbigeoData (Ubigeo ubigeo) {
        String name = ubigeo.getNombreUbigeo();
        int idUbigeo = ubigeo.getIdUbigeo();

        Toast.makeText(getContext(),name,Toast.LENGTH_LONG).show();
    }

    private void procesarRegistroDireccion(){

        //Agregar Guardado de finalizacion de Atencion
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Utilitario.baseUrlServio)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DireccionService direccionService = retrofit.create(DireccionService.class);
        Direccion direccionTemp = new Direccion();
        direccionTemp.setDireccion(direccion.getText().toString());
        direccionTemp.setNombreDireccion(nombreDireccion.getText().toString());
        direccionTemp.setIdDireccion((Long.valueOf(iddireccion.getText()+"")));

        direccionTemp.setNombreContacto(nombreContacto.getText().toString());
        direccionTemp.setTelefonoContacto(telefonoContacto.getText().toString());
        direccionTemp.setReferenciaDireccion(referencia.getText().toString());
        direccionTemp.setEstablecerDireccion(establecerDireccionCheck.isChecked());

        Log.i("onResponse ", "actualizado referencia " + referencia.getText().toString());
        Log.i("onResponse ", "actualizado iddistrito " + iddistrito.getText().toString());


        Ubigeo ubigeoTemp =  distitoMap.get(iddistrito.getText()+"");
        Log.i("onResponse ", "Guardar Direccion ubigeoTemp " + ubigeoTemp);
        if(null!=ubigeoTemp) {
            Log.i("onResponse ", "Guardar Direccion ubigeoTemp existe");
            Integer idUbigeo = ubigeoTemp.getIdUbigeo();
            Log.i("onResponse ", "Guardar Direccion ubigeoTemp idUbigeo " + idUbigeo);
            direccionTemp.setIdUbigeo(idUbigeo);
        }
        Cliente cliente = new Cliente();
        cliente.setIdCliente(Utilitario.idCliente);
        cliente.setDireccionDelivery(direccionTemp);

        Log.i("onResponse ", "Guardar Direccion " + direccionTemp);

        Call<BResult> resultado = direccionService.registrarDireccion(cliente);

        resultado.enqueue(new Callback<BResult>() {
            @Override
            public void onResponse(Call<BResult> call, Response<BResult> response) {
                Log.i("registrarDireccion ", "onResponse: "+response.code());
                Log.i("Direccion message", "onResponse: "+response.message());

                if(response.isSuccessful()) {
                    Log.i("Direccion", "onResponse: " + response.body());

                    //finish();

                    BResult respuesta = response.body();

                    if(respuesta!=null) {
                        DireccionFragment fr=new DireccionFragment();
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.contenedor,fr)
                                .commit();
                        //Intent intent = new Intent(DetalleDireccionActivity.this, DireccionActivity.class);
                        //startActivity(intent);
                        if(respuesta.getCodigo()>0){
                            Toast.makeText(getContext(),
                                    "Se guardo la dirección correctamente", Toast.LENGTH_SHORT).show();
                        } else {
                            //Toast.makeText(this,,Toast.LENGTH_SHORT).show();
                            Toast.makeText(getContext(),
                                    "Error al guardar la dirección vuelva a intentar", Toast.LENGTH_SHORT).show();
                        }
                    }


                    /*Intent iconIntent = new Intent(view.getContext(), DetalleDireccionActivity.class);
                    iconIntent.putExtras(bundle);
                    view.getContext().startActivity(iconIntent);*/

                }
            }

            @Override
            public void onFailure(Call<BResult> call, Throwable t) {
                Log.e("error_savedireccion", t.getMessage());
            }
        });



    }
}
