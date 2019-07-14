package com.newmantech.appcliente;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class CarritoCompra {
    private static final long serialVersionUID = 1L;

    @SerializedName("idCarritoCompra")
    @Expose
    private Long idCarritoCompra;

    @SerializedName("codigoSesion")
    @Expose
    private String codigoSesion;

    @SerializedName("fecha")
    @Expose
    private String fecha;

    @SerializedName("importeTotalPuntos")
    @Expose
    private Integer importeTotalPuntos;

    @SerializedName("detalles")
    @Expose
    private List<CarritoDetalle> detalles;

    @SerializedName("cliente")
    @Expose
    private Cliente cliente;

    @SerializedName("estado")
    @Expose
    private Integer estado;

    @SerializedName("direccionIP")
    @Expose
    private String direccionIP;

    @SerializedName("importeTotalSoles")
    @Expose
    private Double importeTotalSoles;

    @SerializedName("importeDescuentoCupon")
    @Expose
    private Double importeDescuentoCupon;

    @SerializedName("importeCostoDelivery")
    @Expose
    private Double importeCostoDelivery;

    @SerializedName("importePuntosDelivery")
    @Expose
    private Integer importePuntosDelivery;

    @SerializedName("tipoCarrito")
    @Expose
    private Integer tipoCarrito;

    public Long getIdCarritoCompra() {
        return idCarritoCompra;
    }

    public void setIdCarritoCompra(Long idCarritoCompra) {
        this.idCarritoCompra = idCarritoCompra;
    }

    public String getCodigoSesion() {
        return codigoSesion;
    }

    public void setCodigoSesion(String codigoSesion) {
        this.codigoSesion = codigoSesion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getImporteTotalPuntos() {
        return importeTotalPuntos;
    }

    public void setImporteTotalPuntos(Integer importeTotalPuntos) {
        this.importeTotalPuntos = importeTotalPuntos;
    }

    public List<CarritoDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<CarritoDetalle> detalles) {
        this.detalles = detalles;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getDireccionIP() {
        return direccionIP;
    }

    public void setDireccionIP(String direccionIP) {
        this.direccionIP = direccionIP;
    }

    public Double getImporteTotalSoles() {
        return importeTotalSoles;
    }

    public void setImporteTotalSoles(Double importeTotalSoles) {
        this.importeTotalSoles = importeTotalSoles;
    }

    public Double getImporteDescuentoCupon() {
        return importeDescuentoCupon;
    }

    public void setImporteDescuentoCupon(Double importeDescuentoCupon) {
        this.importeDescuentoCupon = importeDescuentoCupon;
    }

    public Double getImporteCostoDelivery() {
        return importeCostoDelivery;
    }

    public void setImporteCostoDelivery(Double importeCostoDelivery) {
        this.importeCostoDelivery = importeCostoDelivery;
    }

    public Integer getImportePuntosDelivery() {
        return importePuntosDelivery;
    }

    public void setImportePuntosDelivery(Integer importePuntosDelivery) {
        this.importePuntosDelivery = importePuntosDelivery;
    }

    public Integer getTipoCarrito() {
        return tipoCarrito;
    }

    public void setTipoCarrito(Integer tipoCarrito) {
        this.tipoCarrito = tipoCarrito;
    }
}