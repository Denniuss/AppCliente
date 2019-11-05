package com.newmantech.appcliente.model;

import java.util.List;

public class BResult {

    private Integer estado;

    private String mensaje;

    private Long codigo;

    private Integer totalRegistros;
    private Integer nroPagina;
    private Integer totalFiltros;
    private Integer totalDiferencia;

    private Object result;
    private List<?> lista;

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Integer getTotalRegistros() {
        return totalRegistros;
    }

    public void setTotalRegistros(Integer totalRegistros) {
        this.totalRegistros = totalRegistros;
    }

    public Integer getNroPagina() {
        return nroPagina;
    }

    public void setNroPagina(Integer nroPagina) {
        this.nroPagina = nroPagina;
    }

    public Integer getTotalFiltros() {
        return totalFiltros;
    }

    public void setTotalFiltros(Integer totalFiltros) {
        this.totalFiltros = totalFiltros;
    }

    public Integer getTotalDiferencia() {
        return totalDiferencia;
    }

    public void setTotalDiferencia(Integer totalDiferencia) {
        this.totalDiferencia = totalDiferencia;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public List<?> getLista() {
        return lista;
    }

    public void setLista(List<?> lista) {
        this.lista = lista;
    }
}