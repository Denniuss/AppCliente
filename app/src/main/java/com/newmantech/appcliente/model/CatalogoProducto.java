package com.newmantech.appcliente.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CatalogoProducto {
    private int idCatalogoProducto;
    private String nombre;
    private String descripcion;
    private String descripcionMarca;
    private String foto;
    private String estado;
    private Double precioCompra;
    private Double precioCatalogo;
    private String porcentajeDescuento;
    private boolean esOferta;
    private String keyItemCanje;
    private Integer stockDisponible;

    private Producto producto;

    private String codigoNetsuite;

    private String especificacionesProducto;
    private String informacionProducto;

    public CatalogoProducto() {
    }

    public CatalogoProducto(int idCatalogoProducto, String nombre, String descripcion, String descripcionMarca, String estado, String foto, Double precioCatalogo, Double precioCompra, String porcentajeDescuento)
    {
        this.idCatalogoProducto = idCatalogoProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.descripcionMarca = descripcionMarca;
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

    public String getDescripcionMarca() {
        return descripcionMarca;
    }

    public void setDescripcionMarca(String descripcionMarca) {
        this.descripcionMarca = descripcionMarca;
    }

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

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getStockDisponible() {
        return stockDisponible;
    }

    public void setStockDisponible(Integer stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    public String getCodigoNetsuite() {
        return codigoNetsuite;
    }

    public void setCodigoNetsuite(String codigoNetsuite) {
        this.codigoNetsuite = codigoNetsuite;
    }

    public String getEspecificacionesProducto() {
        return especificacionesProducto;
    }

    public void setEspecificacionesProducto(String especificacionesProducto) {
        this.especificacionesProducto = especificacionesProducto;
    }

    public String getInformacionProducto() {
        return informacionProducto;
    }

    public void setInformacionProducto(String informacionProducto) {
        this.informacionProducto = informacionProducto;
    }
}