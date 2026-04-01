/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ubam.tiendaRopa.Service;

import com.ubam.tiendaRopa.Entity.Producto;
import com.ubam.tiendaRopa.Repository.ProductoRepository;
import java.util.ArrayList;
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
    
    //obtener los datos para la app
    public ArrayList<Producto> obtenerProducto(){
        return (ArrayList<Producto>) productoRepo.findAll();
    }
    
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
    

    public List<Producto> buscarPorCategoria(int categoriaId) {
    return productoRepo.findBySubcategoria_Categoria_CategoriaId(categoriaId);}

    public List<Producto> buscarPorCategoriaNombre(String nombre) {
    return productoRepo.findBySubcategoria_SubcategoriaNombre(nombre);
}
    public List<Producto> buscarPorCategoria(Integer Subcategoria_Categoria) {
    return productoRepo.findBySubcategoria_Categoria_CategoriaId(Subcategoria_Categoria);

}
}
