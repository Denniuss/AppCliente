package com.newmantech.appcliente;

public class Cliente {

    private Integer idCliente;
    private Integer estado;
    private String segmento;
    private String nombre;
    private String apellidos;
    private String email;
    private String docIdentidad;
    private Direccion direccionDelivery;

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getSegmento() {
        return segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocIdentidad() {
        return docIdentidad;
    }

    public void setDocIdentidad(String docIdentidad) {
        this.docIdentidad = docIdentidad;
    }

    public Direccion getDireccionDelivery() {
        return direccionDelivery;
    }

    public void setDireccionDelivery(Direccion direccionDelivery) {
        this.direccionDelivery = direccionDelivery;
    }
}