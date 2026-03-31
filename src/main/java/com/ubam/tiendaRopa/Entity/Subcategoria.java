/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ubam.tiendaRopa.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private int SubcategoriaId;

    private String Subcategoria_Nombre;

    @ManyToOne
    @JoinColumn(name="Subcategoria_Categoria")
    private Categoria categoria;

    @OneToMany(mappedBy="subcategoria")
    @JsonIgnore
    private List<Producto> productos;
    
    /**/

    public int getSubcategoriaId() {
        return SubcategoriaId;
    }

    public void setSubcategoriaId(int SubcategoriaId) {
        this.SubcategoriaId = SubcategoriaId;
    }

    public String getSubcategoria_Nombre() {
        return Subcategoria_Nombre;
    }

    public void setSubcategoria_Nombre(String Subcategoria_Nombre) {
        this.Subcategoria_Nombre = Subcategoria_Nombre;
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
