/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ubam.tiendaRopa.Controller;

import com.ubam.tiendaRopa.Entity.Carrito;
import com.ubam.tiendaRopa.Entity.Producto;
import com.ubam.tiendaRopa.Entity.Usuario;
import com.ubam.tiendaRopa.Service.CarritoService;
import com.ubam.tiendaRopa.Service.CategoriaService;
import com.ubam.tiendaRopa.Service.ProductoService;
import com.ubam.tiendaRopa.Service.SubcategoriaService;
import com.ubam.tiendaRopa.Service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author carlos
 */
@Controller
public class BoutiqueController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private SubcategoriaService subcategoriaService;

    @Autowired
    private CarritoService carritoService;

    // 🔹 INICIO
    @GetMapping("/")
    public String inicio(Model model) {
        return "index";
    }

    // 🔹 LISTAR PRODUCTOS
    @GetMapping("/productos")
    public String productos(Model model){

        model.addAttribute("productos", productoService.listar());

        return "productos";
    }

    // 🔹 DETALLE PRODUCTO
    @GetMapping("/producto/{id}")
    public String producto(@PathVariable int id, Model model){

        Producto producto = productoService.obtener(id);

        model.addAttribute("producto", producto);

        return "producto";
    }

    // 🔹 IMAGEN DESDE BD
    @GetMapping("/producto/imagen/{id}")
    @ResponseBody
    public byte[] imagenProducto(@PathVariable int id) {

        Producto producto = productoService.obtener(id);

        if (producto.getImagenes() != null && !producto.getImagenes().isEmpty()) {
            return producto.getImagenes().get(0).getImagen();
        }

        return new byte[0];
    }

    // 🔹 CARRITO
    @GetMapping("/carrito")
    public String carrito(Model model, HttpSession session){

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if(usuario == null){
            return "redirect:/login";
        }

        Carrito carrito = carritoService.verCarrito(usuario.getUsuarioId());

        model.addAttribute("carrito", carrito.getDetalles());

        return "carrito";
    }

    // 🔹 AGREGAR AL CARRITO
    @GetMapping("/carrito/agregar/{id}")
    public String agregar(@PathVariable int id) {

        int usuarioId = 1; // 🔥 temporal

        carritoService.agregarProducto(usuarioId, id);

        return "redirect:/carrito";
    }

    // 🔹 ELIMINAR DEL CARRITO
    @GetMapping("/carrito/eliminar/{id}")
    public String eliminar(@PathVariable int id) {

        carritoService.eliminarProducto(id);

        return "redirect:/carrito";
    }

    // 🔐 LOGIN
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(
            @RequestParam String correo,
            @RequestParam String contra,
            HttpSession session){

        Usuario usuario = usuarioService.login(correo, contra);

        if(usuario != null){

            session.setAttribute("usuario", usuario);

            // 🔥 REDIRECCIÓN POR ROL
            if(usuario.getUsuarioTipo() == 1){
                return "redirect:/admin/dashboard";
            }else{
                return "redirect:/";
            }
        }

        return "login";
    }

    // 🔐 REGISTRO
    @GetMapping("/registro")
    public String registro() {
        return "registro";
    }

    @PostMapping("/registro")
    public String registrar(
            @RequestParam String Usuario_Nombre,
            @RequestParam String Usuario_ApePat,
            @RequestParam String Usuario_ApeMat,
            @RequestParam String Usuario_Correo,
            @RequestParam String Usuario_Contra){

            Usuario u = new Usuario();

        u.setUsuarioNombre(Usuario_Nombre);
        u.setUsuarioApePat(Usuario_ApePat);
        u.setUsuarioApeMat(Usuario_ApeMat);
        u.setUsuarioCorreo(Usuario_Correo);
        u.setUsuarioContra(Usuario_Contra);

        // 🔥 CLAVE
        u.setUsuarioTipo(2); // usuario normal

        usuarioService.registrar(u);

        return "redirect:/login";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){

        session.invalidate(); // 🔥 destruye toda la sesión

        return "redirect:/";
    }

    
}