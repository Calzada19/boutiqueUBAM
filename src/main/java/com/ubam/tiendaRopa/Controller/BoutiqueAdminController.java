/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ubam.tiendaRopa.Controller;

import com.ubam.tiendaRopa.Entity.Categoria;
import com.ubam.tiendaRopa.Entity.Imagen;
import com.ubam.tiendaRopa.Entity.Producto;
import com.ubam.tiendaRopa.Entity.Usuario;
import com.ubam.tiendaRopa.Repository.ImagenRepository;
import com.ubam.tiendaRopa.Service.CategoriaService;
import com.ubam.tiendaRopa.Service.ProductoService;
import com.ubam.tiendaRopa.Service.SubcategoriaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author carlo
 */
@Controller
@RequestMapping("/admin")
public class BoutiqueAdminController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ImagenRepository imagenRepository;
    
    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private SubcategoriaService subcategoriaService;

    // 🔹 DASHBOARD
    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {

        model.addAttribute("totalProductos", productoService.listar().size());
        model.addAttribute("totalCategorias", categoriaService.listar().size());

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if(usuario == null || usuario.getUsuarioTipo() != 1){
            return "redirect:/login";
        }

        return "admin/dashboard";
    }

    // 🔹 LISTAR PRODUCTOS
    @GetMapping("/productos")
    public String productos(Model model) {

        model.addAttribute("productos", productoService.listar());

        return "admin/productos";
    }
    
    // 🔹 FORM AGREGAR
    @GetMapping("/agregar")
    public String agregar(Model model) {

        model.addAttribute("subcategorias", subcategoriaService.listar());
        model.addAttribute("producto", new Producto());

        return "admin/agregar";
    }

    // 🔹 GUARDAR PRODUCTO
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Producto producto,
                          @RequestParam("imagen") MultipartFile file) {

        try {
            // 🔥 1. guardar producto primero
            productoService.guardar(producto);

            // 🔥 2. guardar imagen
            if (!file.isEmpty()) {

                Imagen img = new Imagen();
                img.setImagen(file.getBytes());
                img.setImagenTipo(3);
                img.setProducto(producto);

                imagenRepository.save(img); 
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/admin/productos";
    }

    // 🔹 EDITAR
@GetMapping("/editarProducto/{id}")
public String editar(@PathVariable int id, Model model) {

    Producto producto = productoService.obtener(id);

    // Si el producto no existe
    if (producto == null) {
        return "redirect:/admin/productos?error=notfound";
    }

    model.addAttribute("producto", producto);
    model.addAttribute("subcategorias", subcategoriaService.listar());

    return "admin/editar"; // 👈 SOLO el nombre de la vista
}

    // 🔹 ACTUALIZAR
    @PostMapping("/actualizarProducto")
    public String actualizar(@ModelAttribute Producto producto) {

        productoService.guardar(producto);

        return "redirect:/admin/productos";
    }

    // 🔹 ELIMINAR
    @GetMapping("/eliminarProducto/{id}")
    public String eliminar(@PathVariable int id) {

        productoService.eliminar(id);

        return "redirect:/admin/productos";
    }

    // 🔹 CATEGORIAS
    @GetMapping("/categorias")
    public String categorias(Model model) {

        model.addAttribute("categorias", categoriaService.listar());

        return "admin/categorias";
    }

    // 🔹 GUARDAR CATEGORIA
    @PostMapping("/guardarCategoria")
    public String guardarCategoria(Categoria categoria) {

        categoriaService.guardar(categoria);

        return "redirect:/admin/categorias";
    }
}
