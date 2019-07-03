package com.newmantech.appcliente;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Producto {
    

@SerializedName("idProducto")
@Expose
private Integer idProducto;

        @SerializedName("nombre")
@Expose
private String nombre;

        @SerializedName("descripcion")
@Expose
private String descripcion;

        @SerializedName("descripcionMarca")
@Expose
private String descripcionMarca;

        @SerializedName("titulo")
@Expose
private String titulo;

        @SerializedName("tituloLargo")
@Expose
private String tituloLargo;

        @SerializedName("precioCompra")
@Expose
private Double precioCompra;

        @SerializedName("precioRegular")
@Expose
private Double precioRegular;

        @SerializedName("precioVenta")
@Expose
private Double precioVenta;

        @SerializedName("tipo")
@Expose
private int tipo;

        @SerializedName("descripcionTipo")
@Expose
private String descripcionTipo;

        @SerializedName("imagen1")
@Expose
private String imagen1;

        @SerializedName("imagen2")
@Expose
private String imagen2;

        @SerializedName("imagen3")
@Expose
private String imagen3;

        @SerializedName("imagen4")
@Expose
private String imagen4;

        @SerializedName("codigoNetSuite")
@Expose
private String codigoNetSuite;

        @SerializedName("estado")
@Expose
private Integer estado;

        @SerializedName("codigoProducto")
@Expose
private String codigoProducto;

        @SerializedName("codigoProveedor")
@Expose
private String codigoProveedor;

        @SerializedName("proveedor")
@Expose
private String proveedor;

        @SerializedName("descripcionVenta")
@Expose
private String descripcionVenta;

        @SerializedName("stock")
@Expose
private Integer stock;

        @SerializedName("stockReservado")
@Expose
private Integer stockReservado;

        @SerializedName("verificarStockNetsuite")
@Expose
private Boolean verificarStockNetsuite;

        @SerializedName("codigoAlmacen")
@Expose
private String codigoAlmacen;

        @SerializedName("stockDisponible")
@Expose
private Integer stockDisponible;

        @SerializedName("stockDisponibleActual")
@Expose
private Integer stockDisponibleActual;

        @SerializedName("formatPrecioCompra")
@Expose
private String formatPrecioCompra;

        @SerializedName("pesoVolumetrico")
@Expose
private Double pesoVolumetrico;

        @SerializedName("pesoKilo")
@Expose
private Double pesoKilo;

        @SerializedName("medidaAncho")
@Expose
private Double medidaAncho;

        @SerializedName("medidaAlto")
@Expose
private Double medidaAlto;

        @SerializedName("medidaLargo")
@Expose
private Double medidaLargo;

        @SerializedName("tagBusqueda")
@Expose
private String tagBusqueda;

        @SerializedName("afectoIgv")
@Expose
private Boolean afectoIgv;

        @SerializedName("estadoSincronizacion")
@Expose
private Integer estadoSincronizacion;

        @SerializedName("informacionProducto")
@Expose
private String informacionProducto;

        @SerializedName("especificacionesProducto")
@Expose
private String especificacionesProducto;

        @SerializedName("modoUsoProducto")
@Expose
private String modoUsoProducto;

        @SerializedName("videoProducto")
@Expose
private String videoProducto;

        @SerializedName("totalComentariosPendientes")
@Expose
private Integer totalComentariosPendientes;

        @SerializedName("totalComentariosAprobados")
@Expose
private Integer totalComentariosAprobados;

        public Integer getIdProducto() {
            return idProducto;
        }

        public void setIdProducto(Integer idProducto) {
            this.idProducto = idProducto;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcionMarca() {
            return descripcionMarca;
        }

        public void setDescripcionMarca(String descripcionMarca) {
            this.descripcionMarca = descripcionMarca;
        }

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public String getTituloLargo() {
            return tituloLargo;
        }

        public void setTituloLargo(String tituloLargo) {
            this.tituloLargo = tituloLargo;
        }

        public Double getPrecioCompra() {
            return precioCompra;
        }

        public void setPrecioCompra(Double precioCompra) {
            this.precioCompra = precioCompra;
        }

        public Double getPrecioRegular() {
            return precioRegular;
        }

        public void setPrecioRegular(Double precioRegular) {
            this.precioRegular = precioRegular;
        }

        public Double getPrecioVenta() {
            return precioVenta;
        }

        public void setPrecioVenta(Double precioVenta) {
            this.precioVenta = precioVenta;
        }

        public int getTipo() {
            return tipo;
        }

        public void setTipo(int tipo) {
            this.tipo = tipo;
        }

        public String getDescripcionTipo() {
            return descripcionTipo;
        }

        public void setDescripcionTipo(String descripcionTipo) {
            this.descripcionTipo = descripcionTipo;
        }

        public String getImagen1() {
            return imagen1;
        }

        public void setImagen1(String imagen1) {
            this.imagen1 = imagen1;
        }

        public String getImagen2() {
            return imagen2;
        }

        public void setImagen2(String imagen2) {
            this.imagen2 = imagen2;
        }

        public String getImagen3() {
            return imagen3;
        }

        public void setImagen3(String imagen3) {
            this.imagen3 = imagen3;
        }

        public String getImagen4() {
            return imagen4;
        }

        public void setImagen4(String imagen4) {
            this.imagen4 = imagen4;
        }

        public String getCodigoNetSuite() {
            return codigoNetSuite;
        }

        public void setCodigoNetSuite(String codigoNetSuite) {
            this.codigoNetSuite = codigoNetSuite;
        }

        public Integer getEstado() {
            return estado;
        }

        public void setEstado(Integer estado) {
            this.estado = estado;
        }

        public String getCodigoProducto() {
            return codigoProducto;
        }

        public void setCodigoProducto(String codigoProducto) {
            this.codigoProducto = codigoProducto;
        }

        public String getCodigoProveedor() {
            return codigoProveedor;
        }

        public void setCodigoProveedor(String codigoProveedor) {
            this.codigoProveedor = codigoProveedor;
        }

        public String getProveedor() {
            return proveedor;
        }

        public void setProveedor(String proveedor) {
            this.proveedor = proveedor;
        }

        public String getDescripcionVenta() {
            return descripcionVenta;
        }

        public void setDescripcionVenta(String descripcionVenta) {
            this.descripcionVenta = descripcionVenta;
        }

        public Integer getStock() {
            return stock;
        }

        public void setStock(Integer stock) {
            this.stock = stock;
        }

        public Integer getStockReservado() {
            return stockReservado;
        }

        public void setStockReservado(Integer stockReservado) {
            this.stockReservado = stockReservado;
        }

        public Boolean getVerificarStockNetsuite() {
            return verificarStockNetsuite;
        }

        public void setVerificarStockNetsuite(Boolean verificarStockNetsuite) {
            this.verificarStockNetsuite = verificarStockNetsuite;
        }

        public String getCodigoAlmacen() {
            return codigoAlmacen;
        }

        public void setCodigoAlmacen(String codigoAlmacen) {
            this.codigoAlmacen = codigoAlmacen;
        }

        public Integer getStockDisponible() {
            return stockDisponible;
        }

        public void setStockDisponible(Integer stockDisponible) {
            this.stockDisponible = stockDisponible;
        }

        public Integer getStockDisponibleActual() {
            return stockDisponibleActual;
        }

        public void setStockDisponibleActual(Integer stockDisponibleActual) {
            this.stockDisponibleActual = stockDisponibleActual;
        }

        public String getFormatPrecioCompra() {
            return formatPrecioCompra;
        }

        public void setFormatPrecioCompra(String formatPrecioCompra) {
            this.formatPrecioCompra = formatPrecioCompra;
        }

        public Double getPesoVolumetrico() {
            return pesoVolumetrico;
        }

        public void setPesoVolumetrico(Double pesoVolumetrico) {
            this.pesoVolumetrico = pesoVolumetrico;
        }

        public Double getPesoKilo() {
            return pesoKilo;
        }

        public void setPesoKilo(Double pesoKilo) {
            this.pesoKilo = pesoKilo;
        }

        public Double getMedidaAncho() {
            return medidaAncho;
        }

        public void setMedidaAncho(Double medidaAncho) {
            this.medidaAncho = medidaAncho;
        }

        public Double getMedidaAlto() {
            return medidaAlto;
        }

        public void setMedidaAlto(Double medidaAlto) {
            this.medidaAlto = medidaAlto;
        }

        public Double getMedidaLargo() {
            return medidaLargo;
        }

        public void setMedidaLargo(Double medidaLargo) {
            this.medidaLargo = medidaLargo;
        }

        public String getTagBusqueda() {
            return tagBusqueda;
        }

        public void setTagBusqueda(String tagBusqueda) {
            this.tagBusqueda = tagBusqueda;
        }

        public Boolean getAfectoIgv() {
            return afectoIgv;
        }

        public void setAfectoIgv(Boolean afectoIgv) {
            this.afectoIgv = afectoIgv;
        }

        public Integer getEstadoSincronizacion() {
            return estadoSincronizacion;
        }

        public void setEstadoSincronizacion(Integer estadoSincronizacion) {
            this.estadoSincronizacion = estadoSincronizacion;
        }

        public String getInformacionProducto() {
            return informacionProducto;
        }

        public void setInformacionProducto(String informacionProducto) {
            this.informacionProducto = informacionProducto;
        }

        public String getEspecificacionesProducto() {
            return especificacionesProducto;
        }

        public void setEspecificacionesProducto(String especificacionesProducto) {
            this.especificacionesProducto = especificacionesProducto;
        }

        public String getModoUsoProducto() {
            return modoUsoProducto;
        }

        public void setModoUsoProducto(String modoUsoProducto) {
            this.modoUsoProducto = modoUsoProducto;
        }

        public String getVideoProducto() {
            return videoProducto;
        }

        public void setVideoProducto(String videoProducto) {
            this.videoProducto = videoProducto;
        }

        public Integer getTotalComentariosPendientes() {
            return totalComentariosPendientes;
        }

        public void setTotalComentariosPendientes(Integer totalComentariosPendientes) {
            this.totalComentariosPendientes = totalComentariosPendientes;
        }

        public Integer getTotalComentariosAprobados() {
            return totalComentariosAprobados;
        }

        public void setTotalComentariosAprobados(Integer totalComentariosAprobados) {
            this.totalComentariosAprobados = totalComentariosAprobados;
        }    
}
