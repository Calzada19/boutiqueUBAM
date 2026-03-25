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
@Table(name="imagen")
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ImagenId;

    @Lob
    private byte[] Imagen;

    private int Imagen_Tipo;

    @ManyToOne
    @JoinColumn(name="Imagen_ProductoId")
    private Producto producto;
    
    /**/

    public int getImagenId() {
        return ImagenId;
    }

    public void setImagenId(int ImagenId) {
        this.ImagenId = ImagenId;
    }

    public byte[] getImagen() {
        return Imagen;
    }

    public void setImagen(byte[] Imagen) {
        this.Imagen = Imagen;
    }

    public int getImagen_Tipo() {
        return Imagen_Tipo;
    }

    public void setImagen_Tipo(int Imagen_Tipo) {
        this.Imagen_Tipo = Imagen_Tipo;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    
}
