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
    private int UsuarioId;

    private String Usuario_Nombre;

    private String Usuario_ApePat;

    private String Usuario_ApeMat;

    private String Usuario_Correo;

    private String Usuario_Contra;

    private int Usuario_Tipo;
    
    @OneToOne(mappedBy="usuario")
    private Carrito carrito;
    
    /**/

    public int getUsuarioId() {
        return UsuarioId;
    }

    public void setUsuarioId(int UsuarioId) {
        this.UsuarioId = UsuarioId;
    }

    public String getUsuario_Nombre() {
        return Usuario_Nombre;
    }

    public void setUsuario_Nombre(String Usuario_Nombre) {
        this.Usuario_Nombre = Usuario_Nombre;
    }

    public String getUsuario_ApePat() {
        return Usuario_ApePat;
    }

    public void setUsuario_ApePat(String Usuario_ApePat) {
        this.Usuario_ApePat = Usuario_ApePat;
    }

    public String getUsuario_ApeMat() {
        return Usuario_ApeMat;
    }

    public void setUsuario_ApeMat(String Usuario_ApeMat) {
        this.Usuario_ApeMat = Usuario_ApeMat;
    }

    public String getUsuario_Correo() {
        return Usuario_Correo;
    }

    public void setUsuario_Correo(String Usuario_Correo) {
        this.Usuario_Correo = Usuario_Correo;
    }

    public String getUsuario_Contra() {
        return Usuario_Contra;
    }

    public void setUsuario_Contra(String Usuario_Contra) {
        this.Usuario_Contra = Usuario_Contra;
    }

    public int getUsuario_Tipo() {
        return Usuario_Tipo;
    }

    public void setUsuario_Tipo(int Usuario_Tipo) {
        this.Usuario_Tipo = Usuario_Tipo;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }
    
    
}
