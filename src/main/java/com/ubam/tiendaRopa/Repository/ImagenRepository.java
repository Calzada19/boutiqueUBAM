/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ubam.tiendaRopa.Repository;

import com.ubam.tiendaRopa.Entity.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author carlos
 */
public interface ImagenRepository extends JpaRepository<Imagen, Integer>{
    Imagen findByProducto_ProductoId(int productoId);
}
