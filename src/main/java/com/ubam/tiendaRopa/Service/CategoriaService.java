/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ubam.tiendaRopa.Service;

import com.ubam.tiendaRopa.Entity.Categoria;
import com.ubam.tiendaRopa.Repository.CategoriaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepo;

    public List<Categoria> listar(){
        return categoriaRepo.findAll();
    }

    public void guardar(Categoria categoria){
        categoriaRepo.save(categoria);
    }
}