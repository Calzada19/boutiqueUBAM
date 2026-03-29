/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ubam.tiendaRopa.Entity;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carlo
 */
@Entity
@Table(name="carrito")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CarritoId")
    private int carritoId;

    @Column(name="Carrito_Estado")
    private int carritoEstado;

    @OneToOne
    @JoinColumn(name="Carrito_UsuarioId")
    private Usuario usuario;

    @OneToMany(mappedBy="carrito", fetch = FetchType.LAZY)
    private List<CDetalle> detalles = new ArrayList<>();
    
    /**/

    public int getCarritoId() {
        return carritoId;
    }

    public void setCarritoId(int carritoId) {
        this.carritoId = carritoId;
    }

    public int getCarritoEstado() {
        return carritoEstado;
    }

    public void setCarritoEstado(int carritoEstado) {
        this.carritoEstado = carritoEstado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<CDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<CDetalle> detalles) {
        this.detalles = detalles;
    }

    
}
