package com.ubam.tiendaRopa.Controller;

import com.ubam.tiendaRopa.Entity.*;
import com.ubam.tiendaRopa.Service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class BoutiqueController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CarritoService carritoService;

    // 🔹 INICIO
    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("productos", productoService.listar());
        return "index";
    }

    // 🔹 LISTAR PRODUCTOS (Corregido y Unificado)
    @GetMapping("/productos")
    public String productos(@RequestParam(required = false) Integer categoria, Model model) {
        
        List<Producto> productos;

        if (categoria != null) {
            // Buscamos por ID de categoría
            productos = productoService.buscarPorCategoria(categoria);
            var cat = categoriaService.obtener(categoria);
            model.addAttribute("categoria", (cat != null) ? cat.getCategoriaNombre() : "CATEGORÍA");
        } else {
            // Si no hay parámetro, listamos todo
            productos = productoService.listar();
            model.addAttribute("categoria", "TODOS LOS PRODUCTOS");
        }

        // Agrupamos por subcategoría para la vista
        Map<String, List<Producto>> agrupados = productos.stream()
                .collect(Collectors.groupingBy(p ->
                        p.getSubcategoria().getSubcategoriaNombre()));

        model.addAttribute("productosPorSubcategoria", agrupados);
        return "productos";
    }

    // 🔹 DETALLE PRODUCTO
    @GetMapping("/producto/{id}")
    public String producto(@PathVariable int id, Model model) {
        model.addAttribute("producto", productoService.obtener(id));
        return "producto";
    }

    // 🔹 IMAGEN
    @GetMapping("/producto/imagen/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> imagen(@PathVariable int id) {
        Producto p = productoService.obtener(id);
        if (p != null && p.getImagenes() != null && !p.getImagenes().isEmpty()) {
            return ResponseEntity.ok()
                    .header("Content-Type", "image/jpeg")
                    .body(p.getImagenes().get(0).getImagen());
        }
        return ResponseEntity.notFound().build();
    }

    // 🔹 CARRITO
    @GetMapping("/carrito")
    public String carrito(Model model, HttpSession session) {
        Usuario u = (Usuario) session.getAttribute("usuario");
        if (u == null) return "redirect:/login";

        Carrito carrito = carritoService.verCarrito(u.getUsuarioId());
        List<CDetalle> detalles = (carrito != null && carrito.getDetalles() != null) 
                                  ? carrito.getDetalles() : new ArrayList<>();

        double total = detalles.stream()
                .mapToDouble(d -> d.getProducto().getProductoPrecio() * d.getCDetalleCantidad())
                .sum();

        model.addAttribute("carrito", detalles);
        model.addAttribute("total", total);
        return "carrito";
    }

    @GetMapping("/carrito/agregar/{id}")
    public String agregar(@PathVariable int id, HttpSession session) {
        Usuario u = (Usuario) session.getAttribute("usuario");
        if (u == null) return "redirect:/login";
        carritoService.agregarProducto(u.getUsuarioId(), id);
        return "redirect:/carrito";
    }

    @GetMapping("/carrito/eliminar/{id}")
    public String eliminar(@PathVariable int id) {
        carritoService.eliminarProducto(id);
        return "redirect:/carrito";
    }

    // 🔐 LOGIN / LOGOUT
    @GetMapping("/login")
    public String login() { return "login"; }

    @PostMapping("/login")
    public String loginPost(@RequestParam String correo, @RequestParam String contra, HttpSession session) {
        Usuario u = usuarioService.login(correo, contra);
        if (u != null) {
            session.setAttribute("usuario", u);
            return (u.getUsuarioTipo() == 1) ? "redirect:/admin/dashboard" : "redirect:/";
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    // 🔐 REGISTRO
    @PostMapping("/registro")
    public String registrar(@RequestParam String Usuario_Nombre, @RequestParam String Usuario_ApePat,
                            @RequestParam String Usuario_ApeMat, @RequestParam String Usuario_Correo,
                            @RequestParam String Usuario_Contra) {
        Usuario u = new Usuario();
        u.setUsuarioNombre(Usuario_Nombre);
        u.setUsuarioApePat(Usuario_ApePat);
        u.setUsuarioApeMat(Usuario_ApeMat);
        u.setUsuarioCorreo(Usuario_Correo);
        u.setUsuarioContra(Usuario_Contra);
        u.setUsuarioTipo(2);
        usuarioService.registrar(u);
        return "redirect:/login";
    }

    // 🔹 CHECKOUT
    @GetMapping("/checkout")
    public String checkout(HttpSession session, Model model) {
        Usuario u = (Usuario) session.getAttribute("usuario");
        if (u == null) return "redirect:/login";

        Carrito carrito = carritoService.verCarrito(u.getUsuarioId());
        if (carrito == null || carrito.getDetalles().isEmpty()) return "redirect:/carrito";

        double total = carrito.getDetalles().stream()
                .mapToDouble(d -> d.getProducto().getProductoPrecio() * d.getCDetalleCantidad())
                .sum();

        model.addAttribute("carrito", carrito.getDetalles());
        model.addAttribute("total", total);
        return "checkout";
    }

    @PostMapping("/checkout/confirmar")
    public String confirmar(HttpSession session, RedirectAttributes flash) {
        Usuario u = (Usuario) session.getAttribute("usuario");
        if (u == null) return "redirect:/login";

        Carrito carrito = carritoService.verCarrito(u.getUsuarioId());
        if (carrito == null || carrito.getDetalles().isEmpty()) return "redirect:/carrito";

        for (CDetalle d : carrito.getDetalles()) {
            if (d.getProducto().getProductoStock() < d.getCDetalleCantidad()) {
                flash.addFlashAttribute("error", "Stock insuficiente para: " + d.getProducto().getProductoNombre());
                return "redirect:/carrito";
            }
        }

        for (CDetalle d : carrito.getDetalles()) {
            Producto p = d.getProducto();
            p.setProductoStock(p.getProductoStock() - d.getCDetalleCantidad());
            productoService.guardar(p);
        }

        carritoService.vaciarCarrito(carrito);
        flash.addFlashAttribute("success", "Compra realizada con éxito");
        return "redirect:/";
    }
}