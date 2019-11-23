package com.newmantech.appcliente.model;

public class EntityDetalleCarrito {
    String titulo;
    int cantidad;
    double precioUnitario;
    double subtotal;
    String urlFoto;
    int idProducto;

    public EntityDetalleCarrito(String titulo, int cantidad, double precioUnitario, double subtotal, String urlFoto, int idProducto) {
        this.titulo = titulo;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
        this.urlFoto = urlFoto;
        this.idProducto = idProducto;
    }

    public EntityDetalleCarrito() {
    }

    public String getTitulo() {
        return titulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public int getIdProducto() {
        return idProducto;
    }
}
