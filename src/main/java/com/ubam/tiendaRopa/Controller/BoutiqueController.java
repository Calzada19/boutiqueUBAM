/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ubam.tiendaRopa.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author carlos
 */
@Controller
public class BoutiqueController {

    // PAGINAS DE LA TIENDA

    @GetMapping("/")
    public String inicio(Model model) {
        return "index";
    }

    @GetMapping("/productos")
    public String productos() {
        return "productos";
    }

    @GetMapping("/producto")
    public String producto() {
        return "producto";
    }

    @GetMapping("/carrito")
    public String carrito() {
        return "carrito";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registro")
    public String registro() {
        return "registro";
    }


    // PANEL ADMIN

    @GetMapping("/admin/dashboard")
    public String dashboard(){
        return "admin/dashboard";
    }

    @GetMapping("/admin/categorias")
    public String categorias(){
        return "admin/categorias";
    }

    @GetMapping("/admin/productos")
    public String adminProductos(){
        return "admin/productos";
    }

    @GetMapping("/admin/agregarProducto")
    public String agregarProducto(){
        return "admin/agregar_producto";
    }

}
