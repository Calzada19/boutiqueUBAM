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
@Table(name="subcategoria")
public class Subcategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="SubcategoriaId")
    private int subcategoriaId;

    @Column(name="Subcategoria_Nombre")
    private String subcategoriaNombre;

    @ManyToOne
    @JoinColumn(name="Subcategoria_Categoria")
    private Categoria categoria;

    @OneToMany(mappedBy="subcategoria")
    private List<Producto> productos;
    
    /**/

    public int getSubcategoriaId() {
        return subcategoriaId;
    }

    public void setSubcategoriaId(int subcategoriaId) {
        this.subcategoriaId = subcategoriaId;
    }

    public String getSubcategoriaNombre() {
        return subcategoriaNombre;
    }

    public void setSubcategoriaNombre(String subcategoriaNombre) {
        this.subcategoriaNombre = subcategoriaNombre;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    
}
