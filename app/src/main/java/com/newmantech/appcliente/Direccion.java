package com.newmantech.appcliente;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Direccion {

    @SerializedName("establecerDireccion")
    @Expose
    private Boolean establecerDireccion;

    @SerializedName("idDireccionDelivery")
    @Expose
    private Long idDireccionDelivery;

    @SerializedName("nombre")
    @Expose
    private String nombre;

    @SerializedName("apellidos")
    @Expose
    private String apellidos;

    @SerializedName("direccion")
    @Expose
    private String direccion;

    @SerializedName("telefono")
    @Expose
    private String telefono;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("nroCelular")
    @Expose
    private String nroCelular;

    @SerializedName("nroCelularAuxiliar")
    @Expose
    private String nroCelularAuxiliar;

    @SerializedName("nombreContacto")
    @Expose
    private String nombreContacto;

    @SerializedName("emailContacto")
    @Expose
    private String emailContacto;

    @SerializedName("telefonoContacto")
    @Expose
    private String telefonoContacto;

    @SerializedName("referenciaDireccion")
    @Expose
    private String referenciaDireccion;

    @SerializedName("estado")
    @Expose
    private Integer estado;

    @SerializedName("distrito")
    @Expose
    private String distrito;

    @SerializedName("ciudad")
    @Expose
    private String ciudad;

    @SerializedName("departamento")
    @Expose
    private String departamento;

    @SerializedName("tipoDireccion")
    @Expose
    private String tipoDireccion;

    @SerializedName("docIdentidad")
    @Expose
    private String docIdentidad;

    @SerializedName("horarioEntrega")
    @Expose
    private String horarioEntrega;

    @SerializedName("fechaCanje")
    @Expose
    private Date fechaCanje;

    @SerializedName("fechaEntrega")
    @Expose
    private Date fechaEntrega;

    @SerializedName("preferenciaEntrega")
    @Expose
    private String preferenciaEntrega;

    @SerializedName("direccionNumero")
    @Expose
    private String direccionNumero;

    @SerializedName("direccionInterior")
    @Expose
    private String direccionInterior;

    @SerializedName("direccionLote")
    @Expose
    private String direccionLote;

    @SerializedName("direccionManzana")
    @Expose
    private String direccionManzana;

    @SerializedName("tipoEntrega")
    @Expose
    private String tipoEntrega;

    @SerializedName("idVenta")
    @Expose
    private Long idVenta;

    @SerializedName("urbanizacion")
    @Expose
    private String urbanizacion;

    @SerializedName("quienRecibeProducto")
    @Expose
    private Integer quienRecibeProducto;

    //private Ubigeo ubigeo;

    @SerializedName("nombreZonaDelivery")
    @Expose
    private String nombreZonaDelivery;
    //private DeliveryZona deliveryZona;

    //private ParametroDetalle tipoDocumento;
    @SerializedName("arrDistrito")
    @Expose
    private String[] arrDistrito;

    @SerializedName("arrProvincia")
    @Expose
    private String[] arrProvincia;

    @SerializedName("arrDepartamento")
    @Expose
    private String[] arrDepartamento;

    @SerializedName("arrTipoEntrega")
    @Expose
    private String[] arrTipoEntrega;

    @SerializedName("nombreDireccion")
    @Expose
    private String nombreDireccion;

    @SerializedName("diasEntrega")
    @Expose
    private Integer diasEntrega;

    @SerializedName("codigoDepartamento")
    @Expose
    private String codigoDepartamento;

    @SerializedName("codigoProvincia")
    @Expose
    private String codigoProvincia;

    @SerializedName("codigoDistrito")
    @Expose
    private String codigoDistrito;

    @SerializedName("idUbigeo")
    @Expose
    private Integer idUbigeo;

    @SerializedName("esFechaProgramada")
    @Expose
    private Boolean esFechaProgramada;

    @SerializedName("fechaEntregaF")
    @Expose
    private String fechaEntregaF;
    //private DeliveryDiasEntrega deliveryDiasEntrega;


    public Long getIdDireccionDelivery() {
        return idDireccionDelivery;
    }

    public void setIdDireccionDelivery(Long idDireccionDelivery) {
        this.idDireccionDelivery = idDireccionDelivery;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNroCelular() {
        return nroCelular;
    }

    public void setNroCelular(String nroCelular) {
        this.nroCelular = nroCelular;
    }

    public String getNroCelularAuxiliar() {
        return nroCelularAuxiliar;
    }

    public void setNroCelularAuxiliar(String nroCelularAuxiliar) {
        this.nroCelularAuxiliar = nroCelularAuxiliar;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getEmailContacto() {
        return emailContacto;
    }

    public void setEmailContacto(String emailContacto) {
        this.emailContacto = emailContacto;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getReferenciaDireccion() {
        return referenciaDireccion;
    }

    public void setReferenciaDireccion(String referenciaDireccion) {
        this.referenciaDireccion = referenciaDireccion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getTipoDireccion() {
        return tipoDireccion;
    }

    public void setTipoDireccion(String tipoDireccion) {
        this.tipoDireccion = tipoDireccion;
    }

    public String getDocIdentidad() {
        return docIdentidad;
    }

    public void setDocIdentidad(String docIdentidad) {
        this.docIdentidad = docIdentidad;
    }

    public String getHorarioEntrega() {
        return horarioEntrega;
    }

    public void setHorarioEntrega(String horarioEntrega) {
        this.horarioEntrega = horarioEntrega;
    }

    public Date getFechaCanje() {
        return fechaCanje;
    }

    public void setFechaCanje(Date fechaCanje) {
        this.fechaCanje = fechaCanje;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getPreferenciaEntrega() {
        return preferenciaEntrega;
    }

    public void setPreferenciaEntrega(String preferenciaEntrega) {
        this.preferenciaEntrega = preferenciaEntrega;
    }

    public String getDireccionNumero() {
        return direccionNumero;
    }

    public void setDireccionNumero(String direccionNumero) {
        this.direccionNumero = direccionNumero;
    }

    public String getDireccionInterior() {
        return direccionInterior;
    }

    public void setDireccionInterior(String direccionInterior) {
        this.direccionInterior = direccionInterior;
    }

    public String getDireccionLote() {
        return direccionLote;
    }

    public void setDireccionLote(String direccionLote) {
        this.direccionLote = direccionLote;
    }

    public String getDireccionManzana() {
        return direccionManzana;
    }

    public void setDireccionManzana(String direccionManzana) {
        this.direccionManzana = direccionManzana;
    }

    public String getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(String tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public String getUrbanizacion() {
        return urbanizacion;
    }

    public void setUrbanizacion(String urbanizacion) {
        this.urbanizacion = urbanizacion;
    }

    public Integer getQuienRecibeProducto() {
        return quienRecibeProducto;
    }

    public void setQuienRecibeProducto(Integer quienRecibeProducto) {
        this.quienRecibeProducto = quienRecibeProducto;
    }

    public String getNombreZonaDelivery() {
        return nombreZonaDelivery;
    }

    public void setNombreZonaDelivery(String nombreZonaDelivery) {
        this.nombreZonaDelivery = nombreZonaDelivery;
    }

    public String[] getArrDistrito() {
        return arrDistrito;
    }

    public void setArrDistrito(String[] arrDistrito) {
        this.arrDistrito = arrDistrito;
    }

    public String[] getArrProvincia() {
        return arrProvincia;
    }

    public void setArrProvincia(String[] arrProvincia) {
        this.arrProvincia = arrProvincia;
    }

    public String[] getArrDepartamento() {
        return arrDepartamento;
    }

    public void setArrDepartamento(String[] arrDepartamento) {
        this.arrDepartamento = arrDepartamento;
    }

    public String[] getArrTipoEntrega() {
        return arrTipoEntrega;
    }

    public void setArrTipoEntrega(String[] arrTipoEntrega) {
        this.arrTipoEntrega = arrTipoEntrega;
    }

    public String getNombreDireccion() {
        return nombreDireccion;
    }

    public void setNombreDireccion(String nombreDireccion) {
        this.nombreDireccion = nombreDireccion;
    }

    public Integer getDiasEntrega() {
        return diasEntrega;
    }

    public void setDiasEntrega(Integer diasEntrega) {
        this.diasEntrega = diasEntrega;
    }

    public Boolean getEsFechaProgramada() {
        return esFechaProgramada;
    }

    public void setEsFechaProgramada(Boolean esFechaProgramada) {
        this.esFechaProgramada = esFechaProgramada;
    }

    public String getFechaEntregaF() {
        return fechaEntregaF;
    }

    public void setFechaEntregaF(String fechaEntregaF) {
        this.fechaEntregaF = fechaEntregaF;
    }

    public void setCodigoDepartamento(String codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public String getCodigoProvincia() {
        return codigoProvincia;
    }

    public void setCodigoProvincia(String codigoProvincia) {
        this.codigoProvincia = codigoProvincia;
    }

    public String getCodigoDistrito() {
        return codigoDistrito;
    }

    public void setCodigoDistrito(String codigoDistrito) {
        this.codigoDistrito = codigoDistrito;
    }

    public String getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public Integer getIdUbigeo() {
        return idUbigeo;
    }

    public void setIdUbigeo(Integer idUbigeo) {
        this.idUbigeo = idUbigeo;
    }

    public Boolean getEstablecerDireccion() {
        return establecerDireccion;
    }

    public void setEstablecerDireccion(Boolean establecerDireccion) {
        this.establecerDireccion = establecerDireccion;
    }

    @Override
    public String toString() {
        return "Direccion{" +
                "establecerDireccion=" + establecerDireccion +
                ", idDireccionDelivery=" + idDireccionDelivery +
                ", direccion='" + direccion + '\'' +
                ", nombreContacto='" + nombreContacto + '\'' +
                ", telefonoContacto='" + telefonoContacto + '\'' +
                ", referenciaDireccion='" + referenciaDireccion + '\'' +
                ", nombreDireccion='" + nombreDireccion + '\'' +
                ", idUbigeo=" + idUbigeo +
                '}';
    }
}