package com.newmantech.appcliente;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CatalogoProducto {
    private int idCatalogoProducto;
    private String nombre;
    private String descripcion;
    private String marca;
    private String foto;
    private String estado;
    private Double precioCompra;
    private Double precioCatalogo;
    private String porcentajeDescuento;
    private boolean esOferta;
    private String keyItemCanje;

    public CatalogoProducto() {
    }

    public CatalogoProducto(int idCatalogoProducto, String nombre, String descripcion, String marca, String estado, String foto, Double precioCatalogo, Double precioCompra, String porcentajeDescuento)
    {
        this.idCatalogoProducto = idCatalogoProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.marca = marca;
        this.estado = estado;
        this.foto = foto;
        this.precioCompra = precioCompra;
        this.precioCatalogo = precioCatalogo;
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public int getIdCatalogoProducto(){return idCatalogoProducto;}
    public void setIdCatalogoProducto(int idCatalogoProducto){this.idCatalogoProducto = idCatalogoProducto;}
    public String getFoto(){return foto;}
    public void setFoto(String foto){this.foto = foto;}
    public String getNombre(){ return nombre; }
    public void setNombre(String nombre){ this.nombre = nombre;}
    public String getDescripcion(){ return descripcion; }
    public void setDescripcion(String descripcion){ this.descripcion = descripcion;}
    public String getMarca(){ return marca; }
    public void setMarca(String marca){ this.marca = marca;}
    public String getEstado(){ return estado; }
    public void setEstado(String estado){ this.estado = estado;}
    public Double getPrecioCompra(){ return precioCompra;}
    public void setPrecioCompra(Double precioCompra){ this.precioCompra = precioCompra;}
    public Double getPrecioCatalogo(){ return precioCatalogo;}
    public void setPrecioCatalogo(Double precioCatalogo){ this.precioCatalogo = precioCatalogo;}
    public String getPorcentajeDescuento(){ return porcentajeDescuento;}
    public void setPorcentajeDescuento(String porcentajeDescuento){ this.porcentajeDescuento = porcentajeDescuento;}

    public boolean isEsOferta() {
        return esOferta;
    }

    public void setEsOferta(boolean esOferta) {
        this.esOferta = esOferta;
    }

    public String getKeyItemCanje() {
        return keyItemCanje;
    }

    public void setKeyItemCanje(String keyItemCanje) {
        this.keyItemCanje = keyItemCanje;
    }
}