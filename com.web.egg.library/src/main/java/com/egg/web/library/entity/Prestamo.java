package com.egg.web.library.entity;

import javax.persistence.*;

@Entity
@Table(name="Clientes")
public class Prestamo {

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
}
