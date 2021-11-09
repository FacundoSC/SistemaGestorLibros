package com.egg.web.library.entity;

import com.egg.web.library.copies.ClienteCopie;

import javax.persistence.*;

@Entity
@Table(name="Clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int IdCliente;

    @Column(name = "documento", unique = true)
    private Long documento;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "estado")
    private Boolean estado;

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

    public static ClienteCopie ObtenerClienteCopie (Cliente cliente){
          ClienteCopie clienteCopie = new ClienteCopie(cliente.IdCliente, cliente.documento, cliente.nombre,cliente.apellido,cliente.telefono,cliente.estado);
          return clienteCopie;
    }

    public Cliente (){

    }

    public Cliente(int idCliente, Long documento, String nombre, String apellido, String telefono, Boolean estado) {
        IdCliente = idCliente;
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.estado = estado;
    }


}
