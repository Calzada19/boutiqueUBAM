/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ubam.tiendaRopa.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

/**
 *
 * @author carlo
 */
@Entity
@Table(name="imagen")
public class Imagen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ImagenId")
    private int imagenId;

    @Lob
    @Column(name="Imagen")
    private byte[] imagen;

    @Column(name="Imagen_Tipo")
    private int imagenTipo;

    @JsonIgnore
@ManyToOne
@JoinColumn(name="Imagen_ProductoId")
private Producto producto;
    
    /**/

    public int getImagenId() {
        return imagenId;
    }

    public void setImagenId(int imagenId) {
        this.imagenId = imagenId;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public int getImagenTipo() {
        return imagenTipo;
    }

    public void setImagenTipo(int imagenTipo) {
        this.imagenTipo = imagenTipo;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

        
}
