package com.sv.appDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sv.appDemo.model.Categoria;
import com.sv.appDemo.service.ICategoriaService;

@Controller
@RequestMapping("/categorias")
public class CategoriasTripController {

    @Autowired
    private ICategoriaService categoriaService;

    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "categoriasTrip/listCategoria";
    }

    @GetMapping("/create")
    public String crear(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categoriasTrip/formCategoria";
    }

    @PostMapping("/save")
    public String guardarCategoria(@RequestParam("nombre") String nombre,
                                   RedirectAttributes redirectAttributes) {
        Categoria nueva = new Categoria();
        nueva.setNombre(nombre);
        categoriaService.guardar(nueva);
        
        redirectAttributes.addFlashAttribute("mensajeExito", "Categoría '" + nombre + "' guardada correctamente");
        return "redirect:/categorias/index";
    }

    @GetMapping("/delete")
    public String eliminarCategoria(@RequestParam("id") Long id,
                                    RedirectAttributes redirectAttributes) {
        Categoria categoria = categoriaService.buscarPorId(id);
        if (categoria != null) {
            categoriaService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensajeExito", "Categoría '" + categoria.getNombre() + "' eliminada correctamente");
        } else {
            redirectAttributes.addFlashAttribute("mensajeError", "Categoría no encontrada");
        }
        return "redirect:/categorias/index";
    }
}