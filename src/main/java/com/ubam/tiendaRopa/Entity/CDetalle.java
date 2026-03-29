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
@Table(name="cdetalle")
public class CDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CDetalleId")
    private int CDetalleId;

    @Column(name="CDetalle_Cantidad")
    private int CDetalleCantidad;

    @ManyToOne
    @JoinColumn(name="CDetalle_CarritoId")
    private Carrito carrito;

    @ManyToOne
    @JoinColumn(name="CDetalle_ProductoId")
    private Producto producto;
    
    /**/

    public int getCDetalleId() {
        return CDetalleId;
    }

    public void setCDetalleId(int CDetalleId) {
        this.CDetalleId = CDetalleId;
    }

    public int getCDetalleCantidad() {
        return CDetalleCantidad;
    }

    public void setCDetalleCantidad(int CDetalleCantidad) {
        this.CDetalleCantidad = CDetalleCantidad;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    
}
