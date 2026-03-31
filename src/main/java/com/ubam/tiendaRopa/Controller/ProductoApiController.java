/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ubam.tiendaRopa.Controller;

import com.ubam.tiendaRopa.Entity.Producto;
import com.ubam.tiendaRopa.Service.ProductoService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author carlos
 */
//http://localhost:8080/app

//direccion para la app http://192.168.1.76:8080/app (la ip cambia)

@RestController
@RequestMapping("/")
public class ProductoApiController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/app")
    public ArrayList<Producto> obtenerProducto(){
        return productoService.obtenerProducto();
    }
}
    

