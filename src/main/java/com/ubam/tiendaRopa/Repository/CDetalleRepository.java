/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ubam.tiendaRopa.Repository;

import com.ubam.tiendaRopa.Entity.CDetalle;
import com.ubam.tiendaRopa.Entity.Carrito;
import com.ubam.tiendaRopa.Entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author carlos
 */
public interface CDetalleRepository extends JpaRepository<CDetalle, Integer>{
    CDetalle findByCarritoAndProducto(Carrito carrito, Producto producto);
}
