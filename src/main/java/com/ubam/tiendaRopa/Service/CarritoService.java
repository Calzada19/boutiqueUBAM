/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ubam.tiendaRopa.Service;

import com.ubam.tiendaRopa.Entity.CDetalle;
import com.ubam.tiendaRopa.Entity.Carrito;
import com.ubam.tiendaRopa.Entity.Producto;
import com.ubam.tiendaRopa.Entity.Usuario;
import com.ubam.tiendaRopa.Repository.CDetalleRepository;
import com.ubam.tiendaRopa.Repository.CarritoRepository;
import com.ubam.tiendaRopa.Repository.ProductoRepository;
import com.ubam.tiendaRopa.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlo
 */
@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepo;

    @Autowired
    private CDetalleRepository detalleRepo;

    @Autowired
    private ProductoRepository productoRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    // 🔹 Obtener o crear carrito
    public Carrito obtenerCarrito(int usuarioId){

    Usuario usuario = usuarioRepo.findById(usuarioId).orElse(null);

    Carrito carrito = carritoRepo.findByUsuario(usuario);

    if(carrito == null){

        carrito = new Carrito();
        carrito.setUsuario(usuario);
        carrito.setCarritoEstado(1);

        carritoRepo.save(carrito);
    }

    return carrito;
}

    // 🔥 AGREGAR PRODUCTO
    public void agregarProducto(int usuarioId, int productoId){

    Carrito carrito = obtenerCarrito(usuarioId);

    Producto producto = productoRepo.findById(productoId).orElse(null);

    if(producto == null){
        throw new RuntimeException("Producto no encontrado");
    }

    CDetalle detalle = detalleRepo.findByCarritoAndProducto(carrito, producto);

    if(detalle != null){
        detalle.setCDetalleCantidad(detalle.getCDetalleCantidad() + 1);
    }else{
        detalle = new CDetalle();
        detalle.setCarrito(carrito);
        detalle.setProducto(producto);
        detalle.setCDetalleCantidad(1);
    }

    detalleRepo.save(detalle);
}

    // 🔹 Obtener carrito completo
    public Carrito verCarrito(int usuarioId){
        return obtenerCarrito(usuarioId);
    }

    // 🔹 Eliminar producto del carrito
    public void eliminarProducto(int detalleId){

    CDetalle detalle = detalleRepo.findById(detalleId).orElse(null);

    if(detalle == null){
        throw new RuntimeException("Detalle no encontrado");
    }

    detalleRepo.delete(detalle);
}

    public void guardar(Carrito carrito){
    carritoRepo.save(carrito);
    
}
    public void vaciarCarrito(Carrito carrito){
    carrito.getDetalles().clear();
    carritoRepo.save(carrito);
}
    
}

