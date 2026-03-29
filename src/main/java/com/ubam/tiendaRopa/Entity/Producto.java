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
@Table(name="producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ProductoId")
    private int productoId;

    @Column(name="Producto_Nombre")
    private String productoNombre;

    @Column(name="Producto_Descripcion")
    private String productoDescripcion;

    @Column(name="Producto_Precio")
    private double productoPrecio;

    @Column(name="Producto_Stock")
    private int productoStock;

    @Column(name="Producto_SKU")
    private String productoSKU;

    @ManyToOne
    @JoinColumn(name="Producto_SubcatId")
    private Subcategoria subcategoria;

    @OneToMany(mappedBy="producto", cascade = CascadeType.ALL)
    private List<Imagen> imagenes = new ArrayList<>();
    
    /**/

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public String getProductoNombre() {
        return productoNombre;
    }

    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }

    public String getProductoDescripcion() {
        return productoDescripcion;
    }

    public void setProductoDescripcion(String productoDescripcion) {
        this.productoDescripcion = productoDescripcion;
    }

    public double getProductoPrecio() {
        return productoPrecio;
    }

    public void setProductoPrecio(double productoPrecio) {
        this.productoPrecio = productoPrecio;
    }

    public int getProductoStock() {
        return productoStock;
    }

    public void setProductoStock(int productoStock) {
        this.productoStock = productoStock;
    }

    public String getProductoSKU() {
        return productoSKU;
    }

    public void setProductoSKU(String productoSKU) {
        this.productoSKU = productoSKU;
    }

    public Subcategoria getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(Subcategoria subcategoria) {
        this.subcategoria = subcategoria;
    }

    public List<Imagen> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<Imagen> imagenes) {
        this.imagenes = imagenes;
    }
    
    
}