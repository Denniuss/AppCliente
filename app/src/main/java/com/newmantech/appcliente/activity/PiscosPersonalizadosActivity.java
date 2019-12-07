package com.newmantech.appcliente.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.newmantech.appcliente.R;
import com.newmantech.appcliente.adapter.AdapterPiscoPersonalizado;
import com.newmantech.appcliente.model.EntityHeader;
import com.newmantech.appcliente.model.EntityTarifarioProductoPersonalizado;

import java.util.ArrayList;

public class PiscosPersonalizadosActivity extends AppCompatActivity {

    Toolbar toolbar;
    FloatingActionButton fabCar;
    AdapterPiscoPersonalizado adapter;
    private RecyclerView recycler;
    int idProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piscos_personalizados);

        toolbar = findViewById(R.id.toolbar);
        fabCar = findViewById(R.id.fabCar);
        fn_LoadRecyclerView();

        idProducto = getIntent().getIntExtra("codigo",0);


        setupUI(getWindow().getDecorView().getRootView());
        toolbar.setTitle("");
        toolbar.setSubtitle(getIntent().getStringExtra("titulo"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_35dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fabCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PiscosPersonalizadosActivity.this, DetalleCarritoActivity.class);
                startActivity(i);
            }
        });
        setRecycler();
    }

    public void fn_LoadRecyclerView(){
        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
    }

    public void setRecycler(){
        ArrayList<EntityHeader> aHeader = new ArrayList<>();
        ArrayList<EntityTarifarioProductoPersonalizado> arrayList = generateList();
        ArrayList<EntityTarifarioProductoPersonalizado> aTabl1 = new ArrayList<>();
        ArrayList<EntityTarifarioProductoPersonalizado> aTabl2 = new ArrayList<>();
        ArrayList<EntityTarifarioProductoPersonalizado> aTabl3 = new ArrayList<>();
        ArrayList<EntityTarifarioProductoPersonalizado> aTabl4 = new ArrayList<>();
        for(int i=0; i<arrayList.size(); i++){
            EntityTarifarioProductoPersonalizado obj = arrayList.get(i);
            switch (obj.getGrupo()){
                case 1:
                    aTabl1.add(obj);
                    break;
                case 2:
                    aTabl2.add(obj);
                    break;
                case 3:
                    aTabl3.add(obj);
                    break;
                case 4:
                    aTabl4.add(obj);
                    break;
            }
        }
        switch (idProducto){
            case 1:
                aHeader.add(new EntityHeader(1, aTabl1));
                break;
            case 2:
                aHeader.add(new EntityHeader(1, aTabl1));
                break;
        }

        //aHeader.add(new EntityHeader(1, aTabl1));
        aHeader.add(new EntityHeader(2, aTabl2));
        aHeader.add(new EntityHeader(3, aTabl3));
        aHeader.add(new EntityHeader(4, aTabl4));

        adapter = new AdapterPiscoPersonalizado(aHeader, this, idProducto);
        recycler.setAdapter(adapter);
    }

    public ArrayList<EntityTarifarioProductoPersonalizado> generateList(){
        ArrayList<EntityTarifarioProductoPersonalizado> arrayList = new ArrayList<>();

        switch (idProducto){
            case 1:
                //Etiquetados
                arrayList.add(new EntityTarifarioProductoPersonalizado(1,1,"P. Quebranta",42.00,1,"Por Unidad",1,4,"500",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",1));
                arrayList.add(new EntityTarifarioProductoPersonalizado(2,2,"P. Italia/ Totrontel/ Acholado",55.00,1,"Por Unidad",1,4,"500",1,"ml","Pisco Italia: Medalla de Oro 2018 Pisco Torontel: Medalla de Oro 2017",1));
                arrayList.add(new EntityTarifarioProductoPersonalizado(3,3,"P. Mosto Verde",89.00,1,"Por Unidad",1,4,"500",1,"ml","Pisco Mosto Verde: Medalla de Plata 2017",1));

                arrayList.add(new EntityTarifarioProductoPersonalizado(4,1,"P. Quebranta",9.00,2,"Por Mayor",25,1,"50",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(5,1,"P. Quebranta",8.00,2,"Por Mayor",50,1,"50",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(6,1,"P. Quebranta",7.00,2,"Por Mayor",100,1,"50",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(7,1,"P. Quebranta",15.00,2,"Por Mayor",25,2,"187",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(8,1,"P. Quebranta",13.00,2,"Por Mayor",50,2,"187",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(9,1,"P. Quebranta",12.00,2,"Por Mayor",100,2,"187",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(10,1,"P. Quebranta",24.00,2,"Por Mayor",25,3,"375",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(11,1,"P. Quebranta",20.00,2,"Por Mayor",50,3,"375",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(12,1,"P. Quebranta",17.00,2,"Por Mayor",100,3,"375",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(13,1,"P. Quebranta",25.00,2,"Por Mayor",25,4,"500",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(14,1,"P. Quebranta",24.00,2,"Por Mayor",50,4,"500",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(15,1,"P. Quebranta",21.00,2,"Por Mayor",100,4,"500",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(16,1,"P. Quebranta",34.00,2,"Por Mayor",25,5,"750",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(17,1,"P. Quebranta",30.00,2,"Por Mayor",50,5,"750",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(18,1,"P. Quebranta",25.00,2,"Por Mayor",100,5,"750",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));

                arrayList.add(new EntityTarifarioProductoPersonalizado(19,2,"P. Italia/ Totrontel/ Acholado",10.00,2,"Por Mayor",25,1,"50",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2017",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(20,2,"P. Italia/ Totrontel/ Acholado",9.00,2,"Por Mayor",50,1,"50",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2018",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(21,2,"P. Italia/ Totrontel/ Acholado",8.00,2,"Por Mayor",100,1,"50",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2019",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(22,2,"P. Italia/ Totrontel/ Acholado",17.00,2,"Por Mayor",25,2,"187",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2020",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(23,2,"P. Italia/ Totrontel/ Acholado",15.00,2,"Por Mayor",50,2,"187",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2021",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(24,2,"P. Italia/ Totrontel/ Acholado",13.00,2,"Por Mayor",100,2,"187",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2022",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(25,2,"P. Italia/ Totrontel/ Acholado",25.00,2,"Por Mayor",25,3,"375",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2023",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(26,2,"P. Italia/ Totrontel/ Acholado",24.00,2,"Por Mayor",50,3,"375",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2024",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(27,2,"P. Italia/ Totrontel/ Acholado",20.00,2,"Por Mayor",100,3,"375",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2025",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(28,2,"P. Italia/ Totrontel/ Acholado",30.00,2,"Por Mayor",25,4,"500",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2026",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(29,2,"P. Italia/ Totrontel/ Acholado",28.00,2,"Por Mayor",50,4,"500",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2027",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(30,2,"P. Italia/ Totrontel/ Acholado",25.00,2,"Por Mayor",100,4,"500",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2028",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(31,2,"P. Italia/ Totrontel/ Acholado",38.00,2,"Por Mayor",25,5,"750",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2029",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(32,2,"P. Italia/ Totrontel/ Acholado",34.00,2,"Por Mayor",50,5,"750",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2030",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(33,2,"P. Italia/ Totrontel/ Acholado",30.00,2,"Por Mayor",100,5,"750",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2031",3));

                arrayList.add(new EntityTarifarioProductoPersonalizado(34,3,"P. Mosto Verde",11.00,2,"Por Mayor",25,1,"50",1,"ml","Pisco Mosto Verde: Medalla de Plata 2017",4));
                arrayList.add(new EntityTarifarioProductoPersonalizado(35,3,"P. Mosto Verde",10.00,2,"Por Mayor",50,1,"50",1,"ml","Pisco Mosto Verde: Medalla de Plata 2017",4));
                arrayList.add(new EntityTarifarioProductoPersonalizado(36,3,"P. Mosto Verde",9.00,2,"Por Mayor",100,1,"50",1,"ml","Pisco Mosto Verde: Medalla de Plata 2017",4));
                arrayList.add(new EntityTarifarioProductoPersonalizado(37,3,"P. Mosto Verde",51.00,2,"Por Mayor",25,4,"500",1,"ml","Pisco Mosto Verde: Medalla de Plata 2017",4));
                arrayList.add(new EntityTarifarioProductoPersonalizado(38,3,"P. Mosto Verde",42.00,2,"Por Mayor",50,4,"500",1,"ml","Pisco Mosto Verde: Medalla de Plata 2017",4));
                arrayList.add(new EntityTarifarioProductoPersonalizado(39,3,"P. Mosto Verde",38.00,2,"Por Mayor",100,4,"500",1,"ml","Pisco Mosto Verde: Medalla de Plata 2017",4));

                break;
            case 2:
                //Seragrifados
                arrayList.add(new EntityTarifarioProductoPersonalizado(1,1,"P. Quebranta",55.00,1,"Por Unidad",1,4,"500",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",1));
                arrayList.add(new EntityTarifarioProductoPersonalizado(2,2,"P. Italia/ Totrontel/ Acholado",65.00,1,"Por Unidad",1,4,"500",1,"ml","Pisco Italia: Medalla de Oro 2018 Pisco Torontel: Medalla de Oro 2017",1));
                arrayList.add(new EntityTarifarioProductoPersonalizado(3,3,"P. Mosto Verde",97.00,1,"Por Unidad",1,4,"500",1,"ml","Pisco Mosto Verde: Medalla de Plata 2017",1));

                arrayList.add(new EntityTarifarioProductoPersonalizado(4,1,"P. Quebranta",10.00,2,"Por Mayor",25,1,"50",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(5,1,"P. Quebranta",9.30,2,"Por Mayor",50,1,"50",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(6,1,"P. Quebranta",8.00,2,"Por Mayor",100,1,"50",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(7,1,"P. Quebranta",17.00,2,"Por Mayor",25,2,"187",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(8,1,"P. Quebranta",15.00,2,"Por Mayor",50,2,"187",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(9,1,"P. Quebranta",14.00,2,"Por Mayor",100,2,"187",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(10,1,"P. Quebranta",25.00,2,"Por Mayor",25,3,"375",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(11,1,"P. Quebranta",23.00,2,"Por Mayor",50,3,"375",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(12,1,"P. Quebranta",20.00,2,"Por Mayor",100,3,"375",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(13,1,"P. Quebranta",34.00,2,"Por Mayor",25,4,"500",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(14,1,"P. Quebranta",25.00,2,"Por Mayor",50,4,"500",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(15,1,"P. Quebranta",23.00,2,"Por Mayor",100,4,"500",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(16,1,"P. Quebranta",38.00,2,"Por Mayor",25,5,"750",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(17,1,"P. Quebranta",34.00,2,"Por Mayor",50,5,"750",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(18,1,"P. Quebranta",30.00,2,"Por Mayor",100,5,"750",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));

                arrayList.add(new EntityTarifarioProductoPersonalizado(19,2,"P. Italia/ Totrontel/ Acholado",11.00,2,"Por Mayor",25,1,"50",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2017",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(20,2,"P. Italia/ Totrontel/ Acholado",10.00,2,"Por Mayor",50,1,"50",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2018",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(21,2,"P. Italia/ Totrontel/ Acholado",9.00,2,"Por Mayor",100,1,"50",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2019",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(22,2,"P. Italia/ Totrontel/ Acholado",18.00,2,"Por Mayor",25,2,"187",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2020",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(23,2,"P. Italia/ Totrontel/ Acholado",16.00,2,"Por Mayor",50,2,"187",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2021",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(24,2,"P. Italia/ Totrontel/ Acholado",15.00,2,"Por Mayor",100,2,"187",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2022",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(25,2,"P. Italia/ Totrontel/ Acholado",28.00,2,"Por Mayor",25,3,"375",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2023",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(26,2,"P. Italia/ Totrontel/ Acholado",25.00,2,"Por Mayor",50,3,"375",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2024",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(27,2,"P. Italia/ Totrontel/ Acholado",23.00,2,"Por Mayor",100,3,"375",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2025",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(28,2,"P. Italia/ Totrontel/ Acholado",38.00,2,"Por Mayor",25,4,"500",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2026",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(29,2,"P. Italia/ Totrontel/ Acholado",30.00,2,"Por Mayor",50,4,"500",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2027",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(30,2,"P. Italia/ Totrontel/ Acholado",28.00,2,"Por Mayor",100,4,"500",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2028",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(31,2,"P. Italia/ Totrontel/ Acholado",42.00,2,"Por Mayor",25,5,"750",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2029",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(32,2,"P. Italia/ Totrontel/ Acholado",38.00,2,"Por Mayor",50,5,"750",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2030",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(33,2,"P. Italia/ Totrontel/ Acholado",34.00,2,"Por Mayor",100,5,"750",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2031",3));

                arrayList.add(new EntityTarifarioProductoPersonalizado(34,3,"P. Mosto Verde",13.00,2,"Por Mayor",25,1,"50",1,"ml","Pisco Mosto Verde: Medalla de Plata 2017",4));
                arrayList.add(new EntityTarifarioProductoPersonalizado(35,3,"P. Mosto Verde",11.00,2,"Por Mayor",50,1,"50",1,"ml","Pisco Mosto Verde: Medalla de Plata 2017",4));
                arrayList.add(new EntityTarifarioProductoPersonalizado(36,3,"P. Mosto Verde",10.00,2,"Por Mayor",100,1,"50",1,"ml","Pisco Mosto Verde: Medalla de Plata 2017",4));
                arrayList.add(new EntityTarifarioProductoPersonalizado(37,3,"P. Mosto Verde",55.00,2,"Por Mayor",25,4,"500",1,"ml","Pisco Mosto Verde: Medalla de Plata 2017",4));
                arrayList.add(new EntityTarifarioProductoPersonalizado(38,3,"P. Mosto Verde",47.00,2,"Por Mayor",50,4,"500",1,"ml","Pisco Mosto Verde: Medalla de Plata 2017",4));
                arrayList.add(new EntityTarifarioProductoPersonalizado(39,3,"P. Mosto Verde",42.00,2,"Por Mayor",100,4,"500",1,"ml","Pisco Mosto Verde: Medalla de Plata 2017",4));

                break;
            case 3:
//                arrayList.add(new EntityTarifarioProductoPersonalizado(1,1,"P. Quebranta",55.00,1,"Por Unidad",1,4,"500",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",1));
//                arrayList.add(new EntityTarifarioProductoPersonalizado(2,2,"P. Italia/ Totrontel/ Acholado",65.00,1,"Por Unidad",1,4,"500",1,"ml","Pisco Italia: Medalla de Oro 2018 Pisco Torontel: Medalla de Oro 2017",1));
//                arrayList.add(new EntityTarifarioProductoPersonalizado(3,3,"P. Mosto Verde",97.00,1,"Por Unidad",1,4,"500",1,"ml","Pisco Mosto Verde: Medalla de Plata 2017",1));

                arrayList.add(new EntityTarifarioProductoPersonalizado(4,1,"P. Quebranta",12.00,2,"Por Mayor",25,1,"50",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(5,1,"P. Quebranta",10.00,2,"Por Mayor",50,1,"50",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(6,1,"P. Quebranta",9.00,2,"Por Mayor",100,1,"50",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(7,1,"P. Quebranta",19.00,2,"Por Mayor",25,2,"187",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(8,1,"P. Quebranta",16.00,2,"Por Mayor",50,2,"187",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(9,1,"P. Quebranta",15.00,2,"Por Mayor",100,2,"187",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(10,1,"P. Quebranta",30.00,2,"Por Mayor",25,3,"375",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(11,1,"P. Quebranta",25.00,2,"Por Mayor",50,3,"375",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(12,1,"P. Quebranta",23.00,2,"Por Mayor",100,3,"375",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(13,1,"P. Quebranta",42.00,2,"Por Mayor",25,4,"500",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(14,1,"P. Quebranta",34.00,2,"Por Mayor",50,4,"500",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(15,1,"P. Quebranta",25.00,2,"Por Mayor",100,4,"500",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(16,1,"P. Quebranta",47.00,2,"Por Mayor",25,5,"750",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(17,1,"P. Quebranta",38.00,2,"Por Mayor",50,5,"750",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(18,1,"P. Quebranta",34.00,2,"Por Mayor",100,5,"750",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));

                arrayList.add(new EntityTarifarioProductoPersonalizado(19,2,"P. Italia/ Totrontel/ Acholado",13.00,2,"Por Mayor",25,1,"50",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2017",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(20,2,"P. Italia/ Totrontel/ Acholado",11.00,2,"Por Mayor",50,1,"50",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2018",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(21,2,"P. Italia/ Totrontel/ Acholado",10.00,2,"Por Mayor",100,1,"50",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2019",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(22,2,"P. Italia/ Totrontel/ Acholado",20.00,2,"Por Mayor",25,2,"187",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2020",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(23,2,"P. Italia/ Totrontel/ Acholado",18.00,2,"Por Mayor",50,2,"187",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2021",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(24,2,"P. Italia/ Totrontel/ Acholado",17.00,2,"Por Mayor",100,2,"187",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2022",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(25,2,"P. Italia/ Totrontel/ Acholado",34.00,2,"Por Mayor",25,3,"375",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2023",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(26,2,"P. Italia/ Totrontel/ Acholado",30.00,2,"Por Mayor",50,3,"375",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2024",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(27,2,"P. Italia/ Totrontel/ Acholado",25.00,2,"Por Mayor",100,3,"375",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2025",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(28,2,"P. Italia/ Totrontel/ Acholado",47.00,2,"Por Mayor",25,4,"500",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2026",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(29,2,"P. Italia/ Totrontel/ Acholado",38.00,2,"Por Mayor",50,4,"500",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2027",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(30,2,"P. Italia/ Totrontel/ Acholado",30.00,2,"Por Mayor",100,4,"500",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2028",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(31,2,"P. Italia/ Totrontel/ Acholado",51.00,2,"Por Mayor",25,5,"750",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2029",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(32,2,"P. Italia/ Totrontel/ Acholado",42.00,2,"Por Mayor",50,5,"750",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2030",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(33,2,"P. Italia/ Totrontel/ Acholado",38.00,2,"Por Mayor",100,5,"750",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2031",3));

                arrayList.add(new EntityTarifarioProductoPersonalizado(34,3,"P. Mosto Verde",14.00,2,"Por Mayor",25,1,"50",1,"ml","Pisco Mosto Verde: Medalla de Plata 2017",4));
                arrayList.add(new EntityTarifarioProductoPersonalizado(35,3,"P. Mosto Verde",12.00,2,"Por Mayor",50,1,"50",1,"ml","Pisco Mosto Verde: Medalla de Plata 2017",4));
                arrayList.add(new EntityTarifarioProductoPersonalizado(36,3,"P. Mosto Verde",11.00,2,"Por Mayor",100,1,"50",1,"ml","Pisco Mosto Verde: Medalla de Plata 2017",4));
                arrayList.add(new EntityTarifarioProductoPersonalizado(37,3,"P. Mosto Verde",59.00,2,"Por Mayor",25,4,"500",1,"ml","Pisco Mosto Verde: Medalla de Plata 2017",4));
                arrayList.add(new EntityTarifarioProductoPersonalizado(38,3,"P. Mosto Verde",55.00,2,"Por Mayor",50,4,"500",1,"ml","Pisco Mosto Verde: Medalla de Plata 2017",4));
                arrayList.add(new EntityTarifarioProductoPersonalizado(39,3,"P. Mosto Verde",51.00,2,"Por Mayor",100,4,"500",1,"ml","Pisco Mosto Verde: Medalla de Plata 2017",4));

                break;
            case 4:
                arrayList.add(new EntityTarifarioProductoPersonalizado(1,1,"P. Quebranta",34.00,1,"Por Unidad",1,4,"50",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",1));
                arrayList.add(new EntityTarifarioProductoPersonalizado(2,2,"P. Italia/ Totrontel/ Acholado",55.00,1,"Por Unidad",1,4,"50",1,"ml","Pisco Italia: Medalla de Oro 2018 Pisco Torontel: Medalla de Oro 2017",1));
                arrayList.add(new EntityTarifarioProductoPersonalizado(3,3,"P. Mosto Verde",89.00,1,"Por Unidad",1,4,"50",1,"ml","Pisco Mosto Verde: Medalla de Plata 2017",1));

                arrayList.add(new EntityTarifarioProductoPersonalizado(4,1,"P. Quebranta",9.00,2,"Por Mayor",25,1,"50",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(5,1,"P. Quebranta",8.00,2,"Por Mayor",50,1,"50",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(6,1,"P. Quebranta",7.00,2,"Por Mayor",100,1,"50",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(7,1,"P. Quebranta",15.00,2,"Por Mayor",25,2,"187",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(8,1,"P. Quebranta",13.00,2,"Por Mayor",50,2,"187",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(9,1,"P. Quebranta",12.00,2,"Por Mayor",100,2,"187",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(10,1,"P. Quebranta",24.00,2,"Por Mayor",25,3,"375",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(11,1,"P. Quebranta",20.00,2,"Por Mayor",50,3,"375",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(12,1,"P. Quebranta",17.00,2,"Por Mayor",100,3,"375",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(13,1,"P. Quebranta",25.00,2,"Por Mayor",25,4,"500",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(14,1,"P. Quebranta",24.00,2,"Por Mayor",50,4,"500",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(15,1,"P. Quebranta",21.00,2,"Por Mayor",100,4,"500",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(16,1,"P. Quebranta",34.00,2,"Por Mayor",25,5,"750",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(17,1,"P. Quebranta",30.00,2,"Por Mayor",50,5,"750",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
                arrayList.add(new EntityTarifarioProductoPersonalizado(18,1,"P. Quebranta",25.00,2,"Por Mayor",100,5,"750",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));

                arrayList.add(new EntityTarifarioProductoPersonalizado(19,2,"P. Italia/ Totrontel/ Acholado",10.00,2,"Por Mayor",25,1,"50",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2017",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(20,2,"P. Italia/ Totrontel/ Acholado",9.00,2,"Por Mayor",50,1,"50",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2018",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(21,2,"P. Italia/ Totrontel/ Acholado",8.00,2,"Por Mayor",100,1,"50",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2019",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(22,2,"P. Italia/ Totrontel/ Acholado",17.00,2,"Por Mayor",25,2,"187",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2020",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(23,2,"P. Italia/ Totrontel/ Acholado",15.00,2,"Por Mayor",50,2,"187",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2021",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(24,2,"P. Italia/ Totrontel/ Acholado",13.00,2,"Por Mayor",100,2,"187",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2022",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(25,2,"P. Italia/ Totrontel/ Acholado",25.00,2,"Por Mayor",25,3,"375",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2023",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(26,2,"P. Italia/ Totrontel/ Acholado",24.00,2,"Por Mayor",50,3,"375",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2024",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(27,2,"P. Italia/ Totrontel/ Acholado",20.00,2,"Por Mayor",100,3,"375",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2025",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(28,2,"P. Italia/ Totrontel/ Acholado",30.00,2,"Por Mayor",25,4,"500",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2026",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(29,2,"P. Italia/ Totrontel/ Acholado",28.00,2,"Por Mayor",50,4,"500",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2027",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(30,2,"P. Italia/ Totrontel/ Acholado",25.00,2,"Por Mayor",100,4,"500",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2028",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(31,2,"P. Italia/ Totrontel/ Acholado",38.00,2,"Por Mayor",25,5,"750",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2029",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(32,2,"P. Italia/ Totrontel/ Acholado",34.00,2,"Por Mayor",50,5,"750",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2030",3));
                arrayList.add(new EntityTarifarioProductoPersonalizado(33,2,"P. Italia/ Totrontel/ Acholado",30.00,2,"Por Mayor",100,5,"750",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2031",3));

                arrayList.add(new EntityTarifarioProductoPersonalizado(34,3,"P. Mosto Verde",11.00,2,"Por Mayor",25,1,"50",1,"ml","Pisco Mosto Verde: Medalla de Plata 2017",4));
                arrayList.add(new EntityTarifarioProductoPersonalizado(35,3,"P. Mosto Verde",10.00,2,"Por Mayor",50,1,"50",1,"ml","Pisco Mosto Verde: Medalla de Plata 2017",4));
                arrayList.add(new EntityTarifarioProductoPersonalizado(36,3,"P. Mosto Verde",9.00,2,"Por Mayor",100,1,"50",1,"ml","Pisco Mosto Verde: Medalla de Plata 2017",4));
                arrayList.add(new EntityTarifarioProductoPersonalizado(37,3,"P. Mosto Verde",51.00,2,"Por Mayor",25,4,"500",1,"ml","Pisco Mosto Verde: Medalla de Plata 2017",4));
                arrayList.add(new EntityTarifarioProductoPersonalizado(38,3,"P. Mosto Verde",42.00,2,"Por Mayor",50,4,"500",1,"ml","Pisco Mosto Verde: Medalla de Plata 2017",4));
                arrayList.add(new EntityTarifarioProductoPersonalizado(39,3,"P. Mosto Verde",38.00,2,"Por Mayor",100,4,"500",1,"ml","Pisco Mosto Verde: Medalla de Plata 2017",4));

                break;
        }

//        arrayList.add(new EntityTarifarioProductoPersonalizado(1,1,"P. Quebranta",42.00,1,"Por Unidad",1,4,"50",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",1));
//        arrayList.add(new EntityTarifarioProductoPersonalizado(2,2,"P. Italia/ Totrontel/ Acholado",55.00,1,"Por Unidad",1,4,"50",1,"ml","Pisco Italia: Medalla de Oro 2018 Pisco Torontel: Medalla de Oro 2017",1));
//        arrayList.add(new EntityTarifarioProductoPersonalizado(3,3,"P. Mosto Verde",89.00,1,"Por Unidad",1,4,"50",1,"ml","Pisco Mosto Verde: Medalla de Plata 2017",1));
//
//        arrayList.add(new EntityTarifarioProductoPersonalizado(4,1,"P. Quebranta",9.00,2,"Por Mayor",25,1,"50",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
//        arrayList.add(new EntityTarifarioProductoPersonalizado(5,1,"P. Quebranta",8.00,2,"Por Mayor",50,1,"50",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
//        arrayList.add(new EntityTarifarioProductoPersonalizado(6,1,"P. Quebranta",7.00,2,"Por Mayor",100,1,"50",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
//        arrayList.add(new EntityTarifarioProductoPersonalizado(7,1,"P. Quebranta",15.00,2,"Por Mayor",25,2,"187",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
//        arrayList.add(new EntityTarifarioProductoPersonalizado(8,1,"P. Quebranta",13.00,2,"Por Mayor",50,2,"187",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
//        arrayList.add(new EntityTarifarioProductoPersonalizado(9,1,"P. Quebranta",12.00,2,"Por Mayor",100,2,"187",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
//        arrayList.add(new EntityTarifarioProductoPersonalizado(10,1,"P. Quebranta",24.00,2,"Por Mayor",25,3,"375",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
//        arrayList.add(new EntityTarifarioProductoPersonalizado(11,1,"P. Quebranta",20.00,2,"Por Mayor",50,3,"375",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
//        arrayList.add(new EntityTarifarioProductoPersonalizado(12,1,"P. Quebranta",17.00,2,"Por Mayor",100,3,"375",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
//        arrayList.add(new EntityTarifarioProductoPersonalizado(13,1,"P. Quebranta",25.00,2,"Por Mayor",25,4,"500",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
//        arrayList.add(new EntityTarifarioProductoPersonalizado(14,1,"P. Quebranta",24.00,2,"Por Mayor",50,4,"500",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
//        arrayList.add(new EntityTarifarioProductoPersonalizado(15,1,"P. Quebranta",21.00,2,"Por Mayor",100,4,"500",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
//        arrayList.add(new EntityTarifarioProductoPersonalizado(16,1,"P. Quebranta",34.00,2,"Por Mayor",25,5,"750",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
//        arrayList.add(new EntityTarifarioProductoPersonalizado(17,1,"P. Quebranta",30.00,2,"Por Mayor",50,5,"750",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
//        arrayList.add(new EntityTarifarioProductoPersonalizado(18,1,"P. Quebranta",25.00,2,"Por Mayor",100,5,"750",1,"ml","Pisco Quebranta: Medalla de Oro 2016 - Certificación HACPP",2));
//
//        arrayList.add(new EntityTarifarioProductoPersonalizado(19,2,"P. Italia/ Totrontel/ Acholado",10.00,2,"Por Mayor",25,1,"50",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2017",3));
//        arrayList.add(new EntityTarifarioProductoPersonalizado(20,2,"P. Italia/ Totrontel/ Acholado",9.00,2,"Por Mayor",50,1,"50",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2018",3));
//        arrayList.add(new EntityTarifarioProductoPersonalizado(21,2,"P. Italia/ Totrontel/ Acholado",8.00,2,"Por Mayor",100,1,"50",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2019",3));
//        arrayList.add(new EntityTarifarioProductoPersonalizado(22,2,"P. Italia/ Totrontel/ Acholado",17.00,2,"Por Mayor",25,2,"187",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2020",3));
//        arrayList.add(new EntityTarifarioProductoPersonalizado(23,2,"P. Italia/ Totrontel/ Acholado",15.00,2,"Por Mayor",50,2,"187",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2021",3));
//        arrayList.add(new EntityTarifarioProductoPersonalizado(24,2,"P. Italia/ Totrontel/ Acholado",13.00,2,"Por Mayor",100,2,"187",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2022",3));
//        arrayList.add(new EntityTarifarioProductoPersonalizado(25,2,"P. Italia/ Totrontel/ Acholado",25.00,2,"Por Mayor",25,3,"375",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2023",3));
//        arrayList.add(new EntityTarifarioProductoPersonalizado(26,2,"P. Italia/ Totrontel/ Acholado",24.00,2,"Por Mayor",50,3,"375",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2024",3));
//        arrayList.add(new EntityTarifarioProductoPersonalizado(27,2,"P. Italia/ Totrontel/ Acholado",20.00,2,"Por Mayor",100,3,"375",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2025",3));
//        arrayList.add(new EntityTarifarioProductoPersonalizado(28,2,"P. Italia/ Totrontel/ Acholado",30.00,2,"Por Mayor",25,4,"500",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2026",3));
//        arrayList.add(new EntityTarifarioProductoPersonalizado(29,2,"P. Italia/ Totrontel/ Acholado",28.00,2,"Por Mayor",50,4,"500",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2027",3));
//        arrayList.add(new EntityTarifarioProductoPersonalizado(30,2,"P. Italia/ Totrontel/ Acholado",25.00,2,"Por Mayor",100,4,"500",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2028",3));
//        arrayList.add(new EntityTarifarioProductoPersonalizado(31,2,"P. Italia/ Totrontel/ Acholado",38.00,2,"Por Mayor",25,5,"750",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2029",3));
//        arrayList.add(new EntityTarifarioProductoPersonalizado(32,2,"P. Italia/ Totrontel/ Acholado",34.00,2,"Por Mayor",50,5,"750",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2030",3));
//        arrayList.add(new EntityTarifarioProductoPersonalizado(33,2,"P. Italia/ Totrontel/ Acholado",30.00,2,"Por Mayor",100,5,"750",1,"ml","Pisco Italia: Medalla de Oro 2018 \n Pisco Torontel: Medalla de Oro 2031",3));
//
//        arrayList.add(new EntityTarifarioProductoPersonalizado(34,3,"P. Mosto Verde",11.00,2,"Por Mayor",25,1,"50",1,"ml","Pisco Mosto Verde: Medalla de Plata 2017",4));
//        arrayList.add(new EntityTarifarioProductoPersonalizado(35,3,"P. Mosto Verde",10.00,2,"Por Mayor",50,1,"50",1,"ml","Pisco Mosto Verde: Medalla de Plata 2017",4));
//        arrayList.add(new EntityTarifarioProductoPersonalizado(36,3,"P. Mosto Verde",9.00,2,"Por Mayor",100,1,"50",1,"ml","Pisco Mosto Verde: Medalla de Plata 2017",4));
//        arrayList.add(new EntityTarifarioProductoPersonalizado(37,3,"P. Mosto Verde",51.00,2,"Por Mayor",25,4,"500",1,"ml","Pisco Mosto Verde: Medalla de Plata 2017",4));
//        arrayList.add(new EntityTarifarioProductoPersonalizado(38,3,"P. Mosto Verde",42.00,2,"Por Mayor",50,4,"500",1,"ml","Pisco Mosto Verde: Medalla de Plata 2017",4));
//        arrayList.add(new EntityTarifarioProductoPersonalizado(39,3,"P. Mosto Verde",38.00,2,"Por Mayor",100,4,"500",1,"ml","Pisco Mosto Verde: Medalla de Plata 2017",4));

        return arrayList;
    }

    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideKeyb();
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }
    private void hideKeyb() {
        try {
            InputMethodManager inputManager = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) { //No debe pasar nunca
            e.printStackTrace();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        goBack();
        return true;
    }

    private void goBack() {
        setResult(RESULT_CANCELED);
        finish();
    }
}
