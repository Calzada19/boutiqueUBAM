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
@Table(name="categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CategoriaId")
    private int categoriaId;

    @Column(name="Categoria_Nombre")
    private String categoriaNombre;

    @Column(name="Categoria_Orden")
    private Integer categoriaOrden;

    @OneToMany(mappedBy="categoria")
@JsonIgnore
private List<Subcategoria> subcategorias;

    /**/

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }

    public Integer getCategoriaOrden() {
        return categoriaOrden;
    }

    public void setCategoriaOrden(Integer categoriaOrden) {
        this.categoriaOrden = categoriaOrden;
    }

    public List<Subcategoria> getSubcategorias() {
        return subcategorias;
    }

    public void setSubcategorias(List<Subcategoria> subcategorias) {
        this.subcategorias = subcategorias;
    }

        
}
