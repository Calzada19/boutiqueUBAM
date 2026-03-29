/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ubam.tiendaRopa.Service;

import com.ubam.tiendaRopa.Entity.Subcategoria;
import com.ubam.tiendaRopa.Repository.SubcategoriaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlo
 */
@Service
public class SubcategoriaService {

    @Autowired
    private SubcategoriaRepository subcategoriaRepo;

    public List<Subcategoria> listar(){
        return subcategoriaRepo.findAll();
    }

    public void guardar(Subcategoria subcategoria){
        subcategoriaRepo.save(subcategoria);
    }
    public Subcategoria obtener(int id){
    return subcategoriaRepo.findById(id).orElse(null);
}
}
