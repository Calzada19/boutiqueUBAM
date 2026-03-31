package com.ubam.tiendaRopa.Controller;

<<<<<<< Updated upstream
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
=======
import com.ubam.tiendaRopa.Entity.CDetalle;
import com.ubam.tiendaRopa.Entity.Carrito;
import com.ubam.tiendaRopa.Entity.Producto;
import com.ubam.tiendaRopa.Entity.Usuario;
import com.ubam.tiendaRopa.Service.CarritoService;
import com.ubam.tiendaRopa.Service.CategoriaService;
import com.ubam.tiendaRopa.Service.ProductoService;
import com.ubam.tiendaRopa.Service.SubcategoriaService;
import com.ubam.tiendaRopa.Service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
>>>>>>> Stashed changes

@Controller
public class BoutiqueController {

    // PAGINAS DE LA TIENDA

<<<<<<< Updated upstream
=======
    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SubcategoriaService subcategoriaService;

    @Autowired
    private CarritoService carritoService;

    // 🔹 INICIO
>>>>>>> Stashed changes
    @GetMapping("/")
    public String inicio(Model model) {
        List<Producto> productos = productoService.listar();
        model.addAttribute("productos", productos);
        return "index";
    }

<<<<<<< Updated upstream
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
=======
    // 🔹 TODOS LOS PRODUCTOS
    @GetMapping("/productos")
    public String todosProductos(Model model) {

        List<Producto> productos = productoService.listar();

        model.addAttribute("categoria", "TODOS LOS PRODUCTOS");

        Map<String, List<Producto>> productosPorSubcategoria = productos.stream()
                .collect(Collectors.groupingBy(p ->
                        p.getSubcategoria().getSubcategoriaNombre()
                ));

        model.addAttribute("productosPorSubcategoria", productosPorSubcategoria);

        return "productos";
    }

    // 🔹 PRODUCTOS POR CATEGORIA
@GetMapping("/productos/{categoria}")
public String productos(
        @PathVariable Integer categoria,
        Model model) {

    List<Producto> productos = productoService.buscarPorCategoria(categoria);

    var cat = categoriaService.obtener(categoria);

    if (cat != null) {
        model.addAttribute("categoria", cat.getCategoriaNombre());
    }

    // 🔥 AGRUPAR POR SUBCATEGORIA
    Map<String, List<Producto>> productosPorSubcategoria = productos.stream()
        .collect(Collectors.groupingBy(p -> 
            p.getSubcategoria().getSubcategoriaNombre()
        ));

    model.addAttribute("productosPorSubcategoria", productosPorSubcategoria);

    return "productos";
}

    // 🔹 DETALLE PRODUCTO
    @GetMapping("/producto/{id}")
    public String producto(@PathVariable int id, Model model) {

        Producto producto = productoService.obtener(id);
        model.addAttribute("producto", producto);

        return "producto";
    }

    // 🔹 IMAGEN DESDE BD
    @GetMapping("/producto/imagen/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> imagenProducto(@PathVariable int id) {

        Producto producto = productoService.obtener(id);

        if (producto.getImagenes() != null && !producto.getImagenes().isEmpty()) {

            byte[] imagen = producto.getImagenes().get(0).getImagen();

            return ResponseEntity.ok()
                    .header("Content-Type", "image/jpeg")
                    .body(imagen);
        }

        return ResponseEntity.notFound().build();
    }

    // 🔹 CARRITO
    @GetMapping("/carrito")
    public String carrito(Model model, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/login";
        }

        Carrito carrito = carritoService.verCarrito(usuario.getUsuarioId());

        if (carrito == null || carrito.getDetalles() == null) {
            model.addAttribute("carrito", new ArrayList<>());
            model.addAttribute("total", 0);
            return "carrito";
        }

        model.addAttribute("carrito", carrito.getDetalles());

        double total = carrito.getDetalles()
                .stream()
                .mapToDouble(d -> d.getProducto().getProductoPrecio() * d.getCDetalleCantidad())
                .sum();

        model.addAttribute("total", total);

>>>>>>> Stashed changes
        return "carrito";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

<<<<<<< Updated upstream
=======
    @PostMapping("/login")
    public String loginPost(
            @RequestParam String correo,
            @RequestParam String contra,
            HttpSession session) {

        Usuario usuario = usuarioService.login(correo, contra);

        if (usuario != null) {

            session.setAttribute("usuario", usuario);

            if (usuario.getUsuarioTipo() == 1) {
                return "redirect:/admin/dashboard";
            } else {
                return "redirect:/";
            }
        }

        return "login";
    }

    // 🔐 REGISTRO
>>>>>>> Stashed changes
    @GetMapping("/registro")
    public String registro() {
        return "registro";
    }

<<<<<<< Updated upstream

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

    @GetMapping("/admin/agregar")
    public String agregarProducto(){
        return "admin/agregar";
    }

}
=======
    @PostMapping("/registro")
    public String registrar(
            @RequestParam String Usuario_Nombre,
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

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();
        return "redirect:/";
    }

    // 🔹 CHECKOUT
    @GetMapping("/checkout")
    public String checkout(HttpSession session, Model model) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/login";
        }

        Carrito carrito = carritoService.verCarrito(usuario.getUsuarioId());

        if (carrito == null || carrito.getDetalles().isEmpty()) {
            return "redirect:/carrito";
        }

        double total = carrito.getDetalles()
                .stream()
                .mapToDouble(d -> d.getProducto().getProductoPrecio() * d.getCDetalleCantidad())
                .sum();

        model.addAttribute("carrito", carrito.getDetalles());
        model.addAttribute("total", total);

        return "checkout";
    }

    @PostMapping("/checkout/confirmar")
    public String confirmarCompra(HttpSession session, RedirectAttributes flash) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/login";
        }

        Carrito carrito = carritoService.verCarrito(usuario.getUsuarioId());

        if (carrito == null || carrito.getDetalles().isEmpty()) {
            return "redirect:/carrito";
        }

        // VALIDAR STOCK
        for (CDetalle d : carrito.getDetalles()) {
            if (d.getProducto().getProductoStock() < d.getCDetalleCantidad()) {
                flash.addFlashAttribute("error", "Stock insuficiente");
                return "redirect:/carrito";
            }
        }

        // DESCONTAR STOCK
        for (CDetalle d : carrito.getDetalles()) {
            Producto p = d.getProducto();

            p.setProductoStock(
                    p.getProductoStock() - d.getCDetalleCantidad()
            );

            productoService.guardar(p);
        }

        // VACIAR CARRITO
        carritoService.vaciarCarrito(carrito);

        flash.addFlashAttribute("success", "Compra realizada con éxito");
        return "redirect:/checkout";
    }
}
>>>>>>> Stashed changes
