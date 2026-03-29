/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ubam.tiendaRopa.Service;

import com.ubam.tiendaRopa.Entity.Producto;
import com.ubam.tiendaRopa.Repository.ProductoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlo
 */
@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepo;

    public List<Producto> listar(){
        return productoRepo.findAll();
    }

    public Producto obtener(int id){
        return productoRepo.findById(id).orElse(null);
    }

    public void guardar(Producto producto){
        productoRepo.save(producto);
    }

    public void eliminar(int id){

    if(!productoRepo.existsById(id)){
        throw new RuntimeException("Producto no existe");
    }

    productoRepo.deleteById(id);

    }
}
