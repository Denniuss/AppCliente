package com.newmantech.appcliente;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

public class DetalleDireccionActivity extends AppCompatActivity {
    //public ImageView imagen;
    //public TextView cliente;
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
    public TextView iddirecciondelivery;
    public Button btnMapa;

    List<Ubigeo> listaDepartamentoUbigeo = new ArrayList<>();
    List<Ubigeo> listaProvinciaUbigeo = new ArrayList<>();
    List<Ubigeo> listaDistritoUbigeo = new ArrayList<>();

    Map<String,Ubigeo> departamentoMap = new HashMap<>();
    Map<String,Ubigeo> provinciaMap = new HashMap<>();
    Map<String,Ubigeo> distitoMap = new HashMap<>();


    Retrofit retrofit = new Retrofit.Builder().baseUrl(Utilitario.baseUrlServio).addConverterFactory(GsonConverterFactory.create()).build();
    DireccionService direccionService = retrofit.create(DireccionService.class);

    Fragment currentFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direccion_detalle);

/*
        currentFragment = new DetallePedidoFragment();
        changeFragment(currentFragment);

        Toolbar toolbar = (Toolbar) currentFragment.getView().findViewById(R.id.toolbar);
        ((AppCompatActivity)currentFragment.getActivity()).setSupportActionBar(toolbar);
 */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinnerDepartamento = (Spinner) findViewById(R.id.spinnerDep);
        spinnerProvincia = (Spinner) findViewById(R.id.spinnerProv);
        spinnerDistrito = (Spinner) findViewById(R.id.spinnerDistr);

        iddirecciondelivery = (TextView) findViewById(R.id.idDireccionDelivery);
        departamento = (TextView) findViewById(R.id.departamento);
        provincia = (TextView) findViewById(R.id.provincia);
        distrito = (TextView) findViewById(R.id.distrito);
        direccion = (TextView) findViewById(R.id.direccion);
        nombreDireccion = (TextView) findViewById(R.id.nombreDireccion);
        btnMapa = (Button) findViewById(R.id.btnMapa);

        iddepartamento = (TextView) findViewById(R.id.iddepartamento);
        idprovincia = (TextView) findViewById(R.id.idprovincia);
        iddistrito = (TextView) findViewById(R.id.iddistrito);

        nombreDireccion.setText( getIntent().getExtras().getString("curNombreDireccion"));
        departamento.setText(getIntent().getExtras().getString("curDepartamento"));
        provincia.setText(getIntent().getExtras().getString("curProvincia"));
        distrito.setText(getIntent().getExtras().getString("curDistrito"));
        direccion.setText(getIntent().getExtras().getString("curDireccion"));

        Log.i("curidDepartamento", "body: " + getIntent().getExtras().getInt("curidDepartamento"));

        iddepartamento.setText(getIntent().getExtras().getInt("curidDepartamento")+"");
        idprovincia.setText(getIntent().getExtras().getInt("curidProvincia")+"");
        iddistrito.setText(getIntent().getExtras().getInt("curidDistrito")+"");

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

                    for(Ubigeo x: listaDepartamentoUbigeo) {
                        Log.i("onResponse chamado", "body: " + x);
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


        /*bundle.putLong("curIdDireccionDelivery", items.get(i).getIdDireccionDelivery());
        bundle.putString("curNombreDireccion", items.get(i).getNombreDireccion());
        bundle.putString("curDepartamento", items.get(i).getDepartamento());
        bundle.putString("curCiudad", items.get(i).getCiudad());
        bundle.putString("curDistrito", items.get(i).getDistrito());
        bundle.putString("curDireccion", items.get(i).getDireccion());*/

                //cliente.setText(getIntent().getExtras().getString("curIdDireccionDelivery"));
                //direccion.setText("Nombre Dirección: " + getIntent().getExtras().getString("curNombreDireccion"));

        //distrito.setText("Distrito: " + getIntent().getExtras().getString("curDistrito"));
        //estado.setText("Estado: " + getIntent().getExtras().getString("curEstado"));
        //imagen.setImageResource(getIntent().getExtras().getInt("curImagen"));
        //idpedido.setText(String.valueOf(getIntent().getExtras().getInt("curIdpedido")));
        //latitud.setText(getIntent().getExtras().getString("curLatitud"));
        //longitud.setText(getIntent().getExtras().getString("curLongitud"));

        btnMapa.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                MostrarMapa();
            }
        });

        //Picasso.with(imagen.getContext())
          //      .load(getIntent().getExtras().getString("curImagen")).into(imagen);
    }

    public void cargarComboDepartamento() {

        Log.i("onResponse ", "ArrayAdapter: ");

        final String[] itemSeleecionadoDept = {""};
        final Integer[] posicionEncontradaDept = {0};
        ArrayAdapter<Ubigeo> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,listaDepartamentoUbigeo);
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
        //Log.i("SET2 ", "onResponse: ubigeo " + departamentoMap.get(iddepartamento.getText()).getNombreUbigeo());
        //Integer posicionDep = adapter.getPosition(departamentoMap.get(iddepartamento.getText()).getNombreUbigeo());
        //Log.i("SET3 ", "onResponse: adapter " + posicionDep);
        //spinnerDepartamento.setSelection(posicionDep);
    }

    public void cargarComboProvincia() {
        Log.i("onResponse ", "ArrayAdapter: ");

        ArrayAdapter<Ubigeo> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,listaProvinciaUbigeo);
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


        Log.i("onResponse ", "idprovincia " + idprovincia.getText());

        //spinnerProvincia.setSelection(Integer.parseInt(idprovincia.getText()+""));
        spinnerProvincia.setSelection(getIndex(spinnerProvincia, "Lima"));
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

        ArrayAdapter<Ubigeo> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,listaDistritoUbigeo);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDistrito.setAdapter(arrayAdapter);

        Log.e("Log", "spinnerDistrito ");

        spinnerDistrito.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Ubigeo ubigeo = (Ubigeo) parent.getSelectedItem();
                //displayUbigeoData(ubigeo);
                //Toast.makeText(this,list1.get(position)+"",Toast.LENGTH_SHORT).show();
                Integer ids = listaDistritoUbigeo.get(position).getIdUbigeo();
                String desc = listaDistritoUbigeo.get(position).getNombreUbigeo();
                //Toast.makeText(this,desc,Toast.LENGTH_SHORT).show();
                Log.e("Log", "id " + ids + " desc " + desc);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //spinnerDistrito.setSelection(Integer.parseInt(iddistrito.getText()+""));
    }



    public void geteSelectedUbigeo() {
        Ubigeo ubigeo = (Ubigeo) spinnerDepartamento.getSelectedItem();
        displayUbigeoData(ubigeo);
    }

    private void displayUbigeoData (Ubigeo ubigeo) {
        String name = ubigeo.getNombreUbigeo();
        int idUbigeo = ubigeo.getIdUbigeo();

        Toast.makeText(this,name,Toast.LENGTH_LONG).show();
    }

    private void MostrarMapa(){

    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_detalle:
                currentFragment = new DetallePedidoFragment();
                break;
            case R.id.menu_maps:
                currentFragment = new PedidoMapFragment();
                break;
        }
        changeFragment(currentFragment);
        return super.onOptionsItemSelected(item);
    }
     private  void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,fragment).commit();
     }
     */
}
