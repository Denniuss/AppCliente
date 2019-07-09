package com.newmantech.appcliente;

public class ItemCarritoCompra {

    private int idcliente;
    private int idCatalogoProducto;
    private String nombre;
    private String descripcion;
    private String marca;
    private String foto;
    private Double precio;
    private Integer cantidad;

    public ItemCarritoCompra() {
    }

    public ItemCarritoCompra(int idcliente, int idCatalogoProducto, String nombre, String descripcion, String marca, String foto, Double precio, Integer cantidad)
    {
        this.idcliente = idcliente;
        this.idCatalogoProducto = idCatalogoProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.marca = marca;
        this.foto = foto;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public int getIdcliente(){return idcliente;}
    public void setIdcliente(int idcliente){this.idcliente = idcliente;}
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
    public Double getPrecio(){ return precio;}
    public void setPrecio(Double precio){ this.precio = precio;}
    public Integer getCantidad(){ return cantidad;}
    public void setCantidad(Integer cantidad){ this.cantidad = cantidad;}


}
