package com.newmantech.appcliente.model;

public class EntityTarifarioProductoPersonalizado {
    int IdVariedad, IdTamaño, Cantidad, IdUnidad, Grupo, IdTipoVenta, IdDetalleTarifario;
    String	TipoVenta, tamaño, unidadmedida, Descripcion, Variedad;
    double Precio;

    public EntityTarifarioProductoPersonalizado(
            int IdDetalleTarifario, int IdVariedad, String Variedad, double	Precio, int IdTipoVenta, String	TipoVenta, int Cantidad, int IdTamaño, String tamaño, int IdUnidad, String unidadmedida, String	Descripcion, int Grupo) {
        this.IdDetalleTarifario = IdDetalleTarifario;
        this.IdVariedad = IdVariedad;
        this.Variedad = Variedad;
        this.Precio = Precio;
        this.IdTipoVenta = IdTipoVenta;
        this.TipoVenta = TipoVenta;
        this.Cantidad = Cantidad;
        this.IdTamaño = IdTamaño;
        this.tamaño = tamaño;
        this.IdUnidad = IdUnidad;
        this.unidadmedida = unidadmedida;
        this.Descripcion = Descripcion;
        this.Grupo = Grupo;
    }

    public int getIdVariedad() {
        return IdVariedad;
    }

    public int getIdTamaño() {
        return IdTamaño;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public int getIdUnidad() {
        return IdUnidad;
    }

    public int getGrupo() {
        return Grupo;
    }

    public int getIdTipoVenta() {
        return IdTipoVenta;
    }

    public int getIdDetalleTarifario() {
        return IdDetalleTarifario;
    }

    public String getTipoVenta() {
        return TipoVenta;
    }

    public String getTamaño() {
        return tamaño;
    }

    public String getUnidadmedida() {
        return unidadmedida;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public String getVariedad() {
        return Variedad;
    }

    public double getPrecio() {
        return Precio;
    }
}
