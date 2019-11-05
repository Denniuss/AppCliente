package com.newmantech.appcliente.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WorkFlow {

    @SerializedName("idWorflow")
    @Expose
    private int idWorflow;

    @SerializedName("idPedido")
    @Expose
    private int idPedido;

    @SerializedName("fase")
    @Expose
    private int fase;

    @SerializedName("estado")
    @Expose
    private int estado;

    @SerializedName("nombreEstado")
    @Expose
    private String nombreEstado;

    @SerializedName("fechaFormat")
    @Expose
    private String fechaFormat;

    @SerializedName("idUsuario")
    @Expose
    private Integer idUsuario;

    @SerializedName("pedido")
    @Expose
    private Pedido pedido;

    public int getIdWorflow() {
        return idWorflow;
    }

    public void setIdWorflow(int idWorflow) {
        this.idWorflow = idWorflow;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getFase() {
        return fase;
    }

    public void setFase(int fase) {
        this.fase = fase;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public String getFechaFormat() {
        return fechaFormat;
    }

    public void setFechaFormat(String fechaFormat) {
        this.fechaFormat = fechaFormat;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public String toString() {
        return "WorkFlow{" +
                "idWorflow=" + idWorflow +
                ", idPedido=" + idPedido +
                ", fase=" + fase +
                ", estado=" + estado +
                ", nombreEstado='" + nombreEstado + '\'' +
                ", fecha=" + fechaFormat +
                ", idUsuario=" + idUsuario +
                ", pedido=" + pedido +
                '}';
    }
}
