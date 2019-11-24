package com.newmantech.appcliente.model;

public class EntityElegirModelo {
    private String url;
    private String descripcion;
    private int id;

    public EntityElegirModelo(String url, String descripcion, int id) {
        this.url = url;
        this.descripcion = descripcion;
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getId() {
        return id;
    }
}
