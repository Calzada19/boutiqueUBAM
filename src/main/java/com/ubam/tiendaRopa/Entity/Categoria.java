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
@Table(name="categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CategoriaId;

    private String Categoria_Nombre;

    private Integer Categoria_Orden;

    @OneToMany(mappedBy="categoria")
    private List<Subcategoria> subcategorias;
    /**/

    public int getCategoriaId() {
        return CategoriaId;
    }

    public void setCategoriaId(int CategoriaId) {
        this.CategoriaId = CategoriaId;
    }

    public String getCategoria_Nombre() {
        return Categoria_Nombre;
    }

    public void setCategoria_Nombre(String Categoria_Nombre) {
        this.Categoria_Nombre = Categoria_Nombre;
    }

    public Integer getCategoria_Orden() {
        return Categoria_Orden;
    }

    public void setCategoria_Orden(Integer Categoria_Orden) {
        this.Categoria_Orden = Categoria_Orden;
    }

    public List<Subcategoria> getSubcategorias() {
        return subcategorias;
    }

    public void setSubcategorias(List<Subcategoria> subcategorias) {
        this.subcategorias = subcategorias;
    }
    
    
}
