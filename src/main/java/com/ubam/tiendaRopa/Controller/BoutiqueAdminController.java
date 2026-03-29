/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ubam.tiendaRopa.Controller;

import com.ubam.tiendaRopa.Entity.Categoria;
import com.ubam.tiendaRopa.Entity.Imagen;
import com.ubam.tiendaRopa.Entity.Producto;
import com.ubam.tiendaRopa.Entity.Usuario;
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
    private CategoriaService categoriaService;

    @Autowired
    private SubcategoriaService subcategoriaService;

    @GetMapping("/admin/dashboard")
    public String dashboard(HttpSession session){

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if(usuario == null || usuario.getUsuarioTipo() != 1){
            return "redirect:/login";
        }

        return "admin/dashboard";
    }

    // 🔹 DASHBOARD
    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        model.addAttribute("totalProductos", productoService.listar().size());
        model.addAttribute("totalCategorias", categoriaService.listar().size());

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

        return "admin/agregar";
    }

    // 🔹 GUARDAR PRODUCTO
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Producto producto,
                          @RequestParam("imagen") MultipartFile file) {

        try {
            productoService.guardar(producto);

            if (!file.isEmpty()) {
                Imagen img = new Imagen();
                img.setImagen(file.getBytes());
                img.setProducto(producto);

                producto.getImagenes().add(img);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/admin/productos";
    }

    // 🔹 EDITAR
    @GetMapping("/editarProducto/{id}")
    public String editar(@PathVariable int id, Model model) {

        model.addAttribute("producto", productoService.obtener(id));
        model.addAttribute("subcategorias", subcategoriaService.listar());

        return "admin/editar";
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
