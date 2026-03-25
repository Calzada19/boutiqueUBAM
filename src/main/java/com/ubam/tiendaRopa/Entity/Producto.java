/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ubam.tiendaRopa.Entity;

import jakarta.persistence.*;
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
    private int ProductoId;

    private String Producto_Nombre;

    private String Producto_Descripcion;

    private double Producto_Precio;

    private int Producto_Stock;

    private String Producto_SKU;

    @ManyToOne
    @JoinColumn(name="Producto_SubcatId")
    private Subcategoria subcategoria;
    
    @OneToMany(mappedBy="producto")
    private List<Imagen> imagenes;
    
    /**/

    public int getProductoId() {
        return ProductoId;
    }

    public void setProductoId(int ProductoId) {
        this.ProductoId = ProductoId;
    }

    public String getProducto_Nombre() {
        return Producto_Nombre;
    }

    public void setProducto_Nombre(String Producto_Nombre) {
        this.Producto_Nombre = Producto_Nombre;
    }

    public String getProducto_Descripcion() {
        return Producto_Descripcion;
    }

    public void setProducto_Descripcion(String Producto_Descripcion) {
        this.Producto_Descripcion = Producto_Descripcion;
    }

    public double getProducto_Precio() {
        return Producto_Precio;
    }

    public void setProducto_Precio(double Producto_Precio) {
        this.Producto_Precio = Producto_Precio;
    }

    public int getProducto_Stock() {
        return Producto_Stock;
    }

    public void setProducto_Stock(int Producto_Stock) {
        this.Producto_Stock = Producto_Stock;
    }

    public String getProducto_SKU() {
        return Producto_SKU;
    }

    public void setProducto_SKU(String Producto_SKU) {
        this.Producto_SKU = Producto_SKU;
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
