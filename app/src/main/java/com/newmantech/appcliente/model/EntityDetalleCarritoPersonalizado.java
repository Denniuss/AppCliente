package com.newmantech.appcliente.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EntityDetalleCarritoPersonalizado {

    private static final long serialVersionUID = 1L;

    @SerializedName("idProducto")
    @Expose
    private Integer idProducto;

    @SerializedName("tipoPiscoPersonalizado")
    @Expose
    private String tipoPiscoPersonalizado;

    @SerializedName("idDetalleTarifario")
    @Expose
    private Integer idDetalleTarifario;

    @SerializedName("variedad")
    @Expose
    private String variedad;

    @SerializedName("cantidad")
    @Expose
    private Integer cantidad;

    @SerializedName("precioUnitario")
    @Expose
    private Double precioUnitario;

    @SerializedName("subTotal")
    @Expose
    private Double subTotal;

    @SerializedName("tamanho")
    @Expose
    private String tamanho;

    @SerializedName("unidadMedida")
    @Expose
    private String unidadMedida;

    public EntityDetalleCarritoPersonalizado(Integer idProducto, String tipoPiscoPersonalizado, Integer idDetalleTarifario, String variedad, Integer cantidad, Double precioUnitario, Double subTotal, String tamanho, String unidadMedida) {
        this.idProducto = idProducto;
        this.tipoPiscoPersonalizado = tipoPiscoPersonalizado;
        this.idDetalleTarifario = idDetalleTarifario;
        this.variedad = variedad;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subTotal = subTotal;
        this.tamanho = tamanho;
        this.unidadMedida = unidadMedida;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getTipoPiscoPersonalizado() {
        return tipoPiscoPersonalizado;
    }

    public void setTipoPiscoPersonalizado(String tipoPiscoPersonalizado) {
        this.tipoPiscoPersonalizado = tipoPiscoPersonalizado;
    }

    public Integer getIdDetalleTarifario() {
        return idDetalleTarifario;
    }

    public void setIdDetalleTarifario(Integer idDetalleTarifario) {
        this.idDetalleTarifario = idDetalleTarifario;
    }

    public String getVariedad() {
        return variedad;
    }

    public void setVariedad(String variedad) {
        this.variedad = variedad;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    @Override
    public String toString() {
        return "EntityDetalleCarritoPersonalizado{" +
                "idProducto=" + idProducto +
                ", tipoPiscoPersonalizado='" + tipoPiscoPersonalizado + '\'' +
                ", idDetalleTarifario=" + idDetalleTarifario +
                ", variedad='" + variedad + '\'' +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                ", subTotal=" + subTotal +
                ", tamanho='" + tamanho + '\'' +
                ", unidadMedida='" + unidadMedida + '\'' +
                '}';
    }
}
