package com.newmantech.appcliente.model;

public class Pedido {
    private String cliente;
    private String direccion;
    private String distrito;
    private String descripcion;
    private Integer estado;
    //private String imagenurl;
    private int imagen;
    private String latitud;
    private String longitud;
    private Integer idPedido;
    private String observacion;
    private Integer cantidad;
    private String fechaCreacionFormat;

    public Pedido() {

    }

    public Pedido(/*String imagenurl*/Integer idPedido, int imagen, String cliente, String direccion, String distrito, String descripcion, Integer estado, String latitud, String longitud, String observacion,Integer cantidad)
    {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.direccion = direccion;
        this.distrito = distrito;
        this.descripcion = descripcion;
        this.estado = estado;
        this.imagen = imagen;
        //this.imagenurl = imagenurl;
        this.latitud = latitud;
        this.longitud = longitud;
        this.observacion = observacion;
        this.cantidad = cantidad;
    }
    /*
    public String getImagenurl() {
        return imagenurl;
    }
    public void setImagenurl(String imagenurl) {
        this.imagenurl = imagenurl;
    }*/
    public Integer getIdPedido(){return idPedido;}
    public void setIdPedido(Integer idPedido){this.idPedido = idPedido;}
    public int getImagen(){return imagen;}
    public void setImagen(int imagen){this.imagen = imagen;}
    public String getCliente(){ return cliente; }
    public void setCliente(String cliente){ this.cliente = cliente;}
    public String getDireccion(){ return direccion; }
    public void setDireccion(String direccion){ this.direccion = direccion;}
    public String getDistrito(){ return distrito; }
    public void setDistrito(String distrito){ this.distrito = distrito;}
    public String getDescripcion(){ return descripcion; }
    public void setDescripcion(String descripcion){ this.descripcion = descripcion;}
    public Integer getEstado(){ return estado; }
    public void setEstado(Integer estado){ this.estado = estado;}
    public String getLatitud(){ return latitud;}
    public void setLatitud(String latitud){ this.latitud = latitud;}
    public String getLongitud(){ return longitud;}
    public void setLongitud(String longitud){ this.longitud = longitud;}
    public String getObservacion(){ return observacion;}
    public void setObservacion(String observacion){ this.observacion = observacion;}

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getFechaCreacionFormat() {
        return fechaCreacionFormat;
    }

    public void setFechaCreacionFormat(String fechaCreacionFormat) {
        this.fechaCreacionFormat = fechaCreacionFormat;
    }
}
