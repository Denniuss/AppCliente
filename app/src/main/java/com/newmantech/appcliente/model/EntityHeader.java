package com.newmantech.appcliente.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class EntityHeader {
    int Grupo;
    ArrayList<EntityTarifarioProductoPersonalizado> arrayList;

    public EntityHeader(int Grupo, ArrayList<EntityTarifarioProductoPersonalizado> arrayList) {
        this.Grupo = Grupo;
        this.arrayList = arrayList;
    }

    public int getGrupo() {
        return Grupo;
    }

    public ArrayList<EntityTarifarioProductoPersonalizado> getArrayList() {
        return arrayList;
    }
}
