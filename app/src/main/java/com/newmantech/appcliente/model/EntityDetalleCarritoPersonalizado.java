package com.newmantech.appcliente.model;

public class EntityDetalleCarritoPersonalizado {
    int IdProducto;
    String TipoPiscoPersonalizado;
    int IdDetalleTarifario;
    String Variedad;
    int Cantidad;
    double PrecioUnitario;
    double Subtotal;
    String Tamaño;
    String unidadmedida;

    public EntityDetalleCarritoPersonalizado(int idProducto, String tipoPiscoPersonalizado, int idDetalleTarifario, String variedad, int cantidad, double precioUnitario, double subtotal, String tamaño, String unidadmedida) {
        IdProducto = idProducto;
        TipoPiscoPersonalizado = tipoPiscoPersonalizado;
        IdDetalleTarifario = idDetalleTarifario;
        Variedad = variedad;
        Cantidad = cantidad;
        PrecioUnitario = precioUnitario;
        Subtotal = subtotal;
        Tamaño = tamaño;
        this.unidadmedida = unidadmedida;
    }

    public EntityDetalleCarritoPersonalizado() {
    }

    public int getIdProducto() {
        return IdProducto;
    }

    public String getTipoPiscoPersonalizado() {
        return TipoPiscoPersonalizado;
    }

    public int getIdDetalleTarifario() {
        return IdDetalleTarifario;
    }

    public String getVariedad() {
        return Variedad;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public double getPrecioUnitario() {
        return PrecioUnitario;
    }

    public double getSubtotal() {
        return Subtotal;
    }

    public String getTamaño() {
        return Tamaño;
    }

    public String getUnidadmedida() {
        return unidadmedida;
    }
}
