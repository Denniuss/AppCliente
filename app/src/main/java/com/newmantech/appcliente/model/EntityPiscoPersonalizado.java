package com.newmantech.appcliente.model;

public class EntityPiscoPersonalizado {

    int tipo;

    public String getTitulo() {
        return titulo;
    }

    String titulo;

    public EntityPiscoPersonalizado(int tipo, String titulo) {
        this.tipo = tipo;
        this.titulo = titulo;
    }

    public int getTipo() {
        return tipo;
    }
}
