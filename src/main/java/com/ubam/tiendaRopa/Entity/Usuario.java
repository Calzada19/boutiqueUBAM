/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ubam.tiendaRopa.Entity;

import jakarta.persistence.*;

/**
 *
 * @author carlo
 */
@Entity
@Table(name="usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="UsuarioId")
    private int usuarioId;

    @Column(name="Usuario_Nombre")
    private String usuarioNombre;

    @Column(name="Usuario_ApePat")
    private String usuarioApePat;

    @Column(name="Usuario_ApeMat")
    private String usuarioApeMat;

    @Column(name="Usuario_Correo")
    private String usuarioCorreo;

    @Column(name="Usuario_Contra")
    private String usuarioContra;

    @Column(name="Usuario_Tipo")
    private int usuarioTipo;

    @OneToOne(mappedBy="usuario")
    private Carrito carrito;
    
    /**/

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getUsuarioApePat() {
        return usuarioApePat;
    }

    public void setUsuarioApePat(String usuarioApePat) {
        this.usuarioApePat = usuarioApePat;
    }

    public String getUsuarioApeMat() {
        return usuarioApeMat;
    }

    public void setUsuarioApeMat(String usuarioApeMat) {
        this.usuarioApeMat = usuarioApeMat;
    }

    public String getUsuarioCorreo() {
        return usuarioCorreo;
    }

    public void setUsuarioCorreo(String usuarioCorreo) {
        this.usuarioCorreo = usuarioCorreo;
    }

    public String getUsuarioContra() {
        return usuarioContra;
    }

    public void setUsuarioContra(String usuarioContra) {
        this.usuarioContra = usuarioContra;
    }

    public int getUsuarioTipo() {
        return usuarioTipo;
    }

    public void setUsuarioTipo(int usuarioTipo) {
        this.usuarioTipo = usuarioTipo;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    
}
