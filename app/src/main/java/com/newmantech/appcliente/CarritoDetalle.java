package com.newmantech.appcliente;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CarritoDetalle {

    private static final long serialVersionUID = 1L;

    @SerializedName("idCarritoDetalle")
    @Expose
    private Long idCarritoDetalle;

    @SerializedName("cantidad")
    @Expose
    private Integer cantidad;

    @SerializedName("estado")
    @Expose
    private Integer estado;

    @SerializedName("precio")
    @Expose
    private Double precio;

    @SerializedName("precioVenta")
    @Expose
    private Double precioVenta;

    @SerializedName("puntosUsados")
    @Expose
    private Integer puntosUsados;

    @SerializedName("catalogoProducto")
    @Expose
    private CatalogoProducto catalogoProducto;

    @SerializedName("complementos")
    @Expose
    private List<CatalogoProducto> complementos;

    @SerializedName("carritoCompra")
    @Expose
    private CarritoCompra carritoCompra;

    @SerializedName("idCatalogoProductoComplemento")
    @Expose
    private Integer idCatalogoProductoComplemento;

    @SerializedName("formatSubTotalImportePuntos")
    @Expose
    private String formatSubTotalImportePuntos;

    @SerializedName("formatSubTotalImporteSoles")
    @Expose
    private String formatSubTotalImporteSoles;

    @SerializedName("formatSubTotalImporteDolares")
    @Expose
    private String formatSubTotalImporteDolares;

    @SerializedName("importeSubTotalPuntos")
    @Expose
    private Integer importeSubTotalPuntos;

    @SerializedName("importeSubTotalSoles")
    @Expose
    private Double importeSubTotalSoles;

    @SerializedName("importeSubTotalDolares")
    @Expose
    private Double importeSubTotalDolares;

    @SerializedName("estadoValidacionStock")
    @Expose
    private Integer estadoValidacionStock;

    @SerializedName("stockDisponibleVisible")
    @Expose
    private Integer stockDisponibleVisible;

    @SerializedName("cantidadUltimaReserva")
    @Expose
    private Integer cantidadUltimaReserva;

    public Long getIdCarritoDetalle() {
        return idCarritoDetalle;
    }

    public void setIdCarritoDetalle(Long idCarritoDetalle) {
        this.idCarritoDetalle = idCarritoDetalle;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getPuntosUsados() {
        return puntosUsados;
    }

    public void setPuntosUsados(Integer puntosUsados) {
        this.puntosUsados = puntosUsados;
    }

    public CatalogoProducto getCatalogoProducto() {
        return catalogoProducto;
    }

    public void setCatalogoProducto(CatalogoProducto catalogoProducto) {
        this.catalogoProducto = catalogoProducto;
    }

    public List<CatalogoProducto> getComplementos() {
        return complementos;
    }

    public void setComplementos(List<CatalogoProducto> complementos) {
        this.complementos = complementos;
    }

    public CarritoCompra getCarritoCompra() {
        return carritoCompra;
    }

    public void setCarritoCompra(CarritoCompra carritoCompra) {
        this.carritoCompra = carritoCompra;
    }

    public Integer getIdCatalogoProductoComplemento() {
        return idCatalogoProductoComplemento;
    }

    public void setIdCatalogoProductoComplemento(Integer idCatalogoProductoComplemento) {
        this.idCatalogoProductoComplemento = idCatalogoProductoComplemento;
    }

    public String getFormatSubTotalImportePuntos() {
        return formatSubTotalImportePuntos;
    }

    public void setFormatSubTotalImportePuntos(String formatSubTotalImportePuntos) {
        this.formatSubTotalImportePuntos = formatSubTotalImportePuntos;
    }

    public String getFormatSubTotalImporteSoles() {
        return formatSubTotalImporteSoles;
    }

    public void setFormatSubTotalImporteSoles(String formatSubTotalImporteSoles) {
        this.formatSubTotalImporteSoles = formatSubTotalImporteSoles;
    }

    public String getFormatSubTotalImporteDolares() {
        return formatSubTotalImporteDolares;
    }

    public void setFormatSubTotalImporteDolares(String formatSubTotalImporteDolares) {
        this.formatSubTotalImporteDolares = formatSubTotalImporteDolares;
    }

    public Integer getImporteSubTotalPuntos() {
        return importeSubTotalPuntos;
    }

    public void setImporteSubTotalPuntos(Integer importeSubTotalPuntos) {
        this.importeSubTotalPuntos = importeSubTotalPuntos;
    }

    public Double getImporteSubTotalSoles() {
        return importeSubTotalSoles;
    }

    public void setImporteSubTotalSoles(Double importeSubTotalSoles) {
        this.importeSubTotalSoles = importeSubTotalSoles;
    }

    public Double getImporteSubTotalDolares() {
        return importeSubTotalDolares;
    }

    public void setImporteSubTotalDolares(Double importeSubTotalDolares) {
        this.importeSubTotalDolares = importeSubTotalDolares;
    }

    public Integer getEstadoValidacionStock() {
        return estadoValidacionStock;
    }

    public void setEstadoValidacionStock(Integer estadoValidacionStock) {
        this.estadoValidacionStock = estadoValidacionStock;
    }

    public Integer getStockDisponibleVisible() {
        return stockDisponibleVisible;
    }

    public void setStockDisponibleVisible(Integer stockDisponibleVisible) {
        this.stockDisponibleVisible = stockDisponibleVisible;
    }

    public Integer getCantidadUltimaReserva() {
        return cantidadUltimaReserva;
    }

    public void setCantidadUltimaReserva(Integer cantidadUltimaReserva) {
        this.cantidadUltimaReserva = cantidadUltimaReserva;
    }
}