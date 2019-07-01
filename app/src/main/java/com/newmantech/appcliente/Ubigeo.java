package com.newmantech.appcliente;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Ubigeo {

    @SerializedName("idUbigeo")
    @Expose
    private Integer idUbigeo;

    @SerializedName("codigoPais")
    @Expose
    private String codigoPais;

    @SerializedName("codigoDepartamento")
    @Expose
    private String codigoDepartamento;

    @SerializedName("codigoProvincia")
    @Expose
    private String codigoProvincia;

    @SerializedName("codigoDistrito")
    @Expose
    private String codigoDistrito;

    @SerializedName("nombreUbigeo")
    @Expose
    private String nombreUbigeo;

    @SerializedName("estado")
    @Expose
    private Integer estado;

    public Integer getIdUbigeo() {
        return idUbigeo;
    }

    public void setIdUbigeo(Integer idUbigeo) {
        this.idUbigeo = idUbigeo;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    public String getCodigoDepartamento() {
        return codigoDepartamento;
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

    public String getNombreUbigeo() {
        return nombreUbigeo;
    }

    public void setNombreUbigeo(String nombreUbigeo) {
        this.nombreUbigeo = nombreUbigeo;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return nombreUbigeo;
    }
}