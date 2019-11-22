package com.newmantech.appcliente.model;

public class EntityProductoPersonalizado {
    int Codigo;
    String Descripcion;
    Boolean vigencia;
    String imagenmenuurl;
    String imagenDetalleurl;

    public EntityProductoPersonalizado(int codigo, String descripcion, Boolean vigencia, String imagenmenuurl, String imagenDetalleurl) {
        Codigo = codigo;
        Descripcion = descripcion;
        this.vigencia = vigencia;
        this.imagenmenuurl = imagenmenuurl;
        this.imagenDetalleurl = imagenDetalleurl;
    }

    public int getCodigo() {
        return Codigo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public Boolean getVigencia() {
        return vigencia;
    }

    public String getImagenmenuurl() {
        return imagenmenuurl;
    }

    public String getImagenDetalleurl() {
        return imagenDetalleurl;
    }
}
