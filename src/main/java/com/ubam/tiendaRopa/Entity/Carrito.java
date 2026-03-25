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
    private int CarritoId;

    private int Carrito_Estado;

    @OneToOne
    @JoinColumn(name="Carrito_UsuarioId")
    private Usuario usuario;

    @OneToMany(mappedBy="carrito", fetch = FetchType.LAZY)
    private List<CDetalle> detalles = new ArrayList<>();
    
    /**/

    public int getCarritoId() {
        return CarritoId;
    }

    public void setCarritoId(int CarritoId) {
        this.CarritoId = CarritoId;
    }

    public int getCarrito_Estado() {
        return Carrito_Estado;
    }

    public void setCarrito_Estado(int Carrito_Estado) {
        this.Carrito_Estado = Carrito_Estado;
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
