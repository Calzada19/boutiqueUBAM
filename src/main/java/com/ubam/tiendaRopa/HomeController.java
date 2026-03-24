/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ubam.tiendaRopa;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Eljoj
 */
@Controller
public class HomeController {

// http://localhost:8080/  
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
}
