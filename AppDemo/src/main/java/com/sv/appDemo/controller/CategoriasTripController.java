package com.sv.appDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        Categoria categoria = categoriaService.buscarPorId(id);
        if (categoria != null) {
            model.addAttribute("categoria", categoria);
            return "categoriasTrip/formCategoria";
        } else {
            return "redirect:/categorias/index";
        }
    }

    @PostMapping("/save")
    public String guardarCategoria(@ModelAttribute Categoria categoria,
                                   RedirectAttributes redirectAttributes) {
        if (categoria.getId() != null) {
            redirectAttributes.addFlashAttribute("mensajeExito", "Categoría '" + categoria.getNombre() + "' actualizada correctamente");
        } else {
            redirectAttributes.addFlashAttribute("mensajeExito", "Categoría '" + categoria.getNombre() + "' guardada correctamente");
        }
        categoriaService.guardar(categoria);
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