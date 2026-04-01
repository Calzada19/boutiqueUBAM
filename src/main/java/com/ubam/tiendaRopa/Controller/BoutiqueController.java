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

    // 🔹 PRODUCTOS POR CATEGORIA
    @GetMapping("/productos/{categoria}")
    public String productos(@PathVariable Integer categoria, Model model) {

        List<Producto> productos = productoService.buscarPorCategoria(categoria);

        var cat = categoriaService.obtener(categoria);
        if (cat != null) {
            model.addAttribute("categoria", cat.getCategoriaNombre());
        }

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

        if (p.getImagenes() != null && !p.getImagenes().isEmpty()) {
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

        if (carrito == null || carrito.getDetalles() == null) {
            model.addAttribute("carrito", new ArrayList<>());
            model.addAttribute("total", 0);
            return "carrito";
        }

        model.addAttribute("carrito", carrito.getDetalles());

        double total = carrito.getDetalles().stream()
                .mapToDouble(d -> d.getProducto().getProductoPrecio() * d.getCDetalleCantidad())
                .sum();

        model.addAttribute("total", total);

        return "carrito";
    }

    // 🔹 AGREGAR
    @GetMapping("/carrito/agregar/{id}")
    public String agregar(@PathVariable int id, HttpSession session) {
        Usuario u = (Usuario) session.getAttribute("usuario");
        if (u == null) return "redirect:/login";

        carritoService.agregarProducto(u.getUsuarioId(), id);
        return "redirect:/carrito";
    }

    // 🔹 ELIMINAR
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
    public String loginPost(@RequestParam String correo,
                            @RequestParam String contra,
                            HttpSession session) {

        Usuario u = usuarioService.login(correo, contra);

        if (u != null) {
            session.setAttribute("usuario", u);
            return (u.getUsuarioTipo() == 1)
                    ? "redirect:/admin/dashboard"
                    : "redirect:/";
        }

        return "login";
    }

    // 🔐 REGISTRO
    @PostMapping("/registro")
    public String registrar(@RequestParam String Usuario_Nombre,
                            @RequestParam String Usuario_ApePat,
                            @RequestParam String Usuario_ApeMat,
                            @RequestParam String Usuario_Correo,
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

    // 🔐 LOGOUT
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    // 🔹 CHECKOUT
    @GetMapping("/checkout")
    public String checkout(HttpSession session, Model model) {

        Usuario u = (Usuario) session.getAttribute("usuario");
        if (u == null) return "redirect:/login";

        Carrito carrito = carritoService.verCarrito(u.getUsuarioId());

        if (carrito == null || carrito.getDetalles().isEmpty()) {
            return "redirect:/carrito";
        }

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

        if (carrito == null || carrito.getDetalles().isEmpty()) {
            return "redirect:/carrito";
        }

        for (CDetalle d : carrito.getDetalles()) {
            if (d.getProducto().getProductoStock() < d.getCDetalleCantidad()) {
                flash.addFlashAttribute("error", "Stock insuficiente");
                return "redirect:/carrito";
            }
        }

        for (CDetalle d : carrito.getDetalles()) {
            Producto p = d.getProducto();
            p.setProductoStock(p.getProductoStock() - d.getCDetalleCantidad());
            productoService.guardar(p);
        }

        carritoService.vaciarCarrito(carrito);

        flash.addFlashAttribute("success", "Compra realizada");
        return "redirect:/checkout";
    }
}