package com.newmantech.appcliente.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ParametroDetalle {
    private static final long serialVersionUID = 1L;

    @SerializedName("texto")
    @Expose
    private String texto;

    @SerializedName("codigo")
    @Expose
    private Integer codigo;

    @SerializedName("textoSecundario")
    @Expose
    private String textoSecundario;

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getTextoSecundario() {
        return textoSecundario;
    }

    public void setTextoSecundario(String textoSecundario) {
        this.textoSecundario = textoSecundario;
    }

    @Override
    public String toString() {
        return "ParametroDetalle{" +
                "texto='" + texto + '\'' +
                ", codigo=" + codigo +
                ", textoSecundario='" + textoSecundario + '\'' +
                '}';
    }
}
