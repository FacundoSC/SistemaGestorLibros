package com.egg.web.library.copies;


import com.egg.web.library.entity.Cliente;

public class ClienteCopie {

    private int IdCliente;
    private Long documento;
    private String nombre;
    private String apellido;
    private String telefono;
    private boolean estado;

    public ClienteCopie(){

    }
    public ClienteCopie(int idCliente, Long documento, String nombre, String apellido, String telefono, Boolean estado) {
        IdCliente = idCliente;
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.estado = estado;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int idCliente) {
        IdCliente = idCliente;
    }

    public Long getDocumento() {
        return documento;
    }

    public void setDocumento(Long documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public static Cliente ObtenerCliente(ClienteCopie clienteCopie){
        Cliente cliente = new Cliente(clienteCopie.IdCliente, clienteCopie.documento, clienteCopie.nombre,clienteCopie.apellido,clienteCopie.telefono,clienteCopie.estado);
        return cliente;
    }

}
