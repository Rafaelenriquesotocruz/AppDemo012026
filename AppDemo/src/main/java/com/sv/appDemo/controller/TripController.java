package com.sv.appDemo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sv.appDemo.model.Trip;
import com.sv.appDemo.model.Categoria;
import com.sv.appDemo.service.ITripService;
import com.sv.appDemo.service.ICategoriaService;

@Controller
@RequestMapping("/trips")
public class TripController {

    @Autowired
    private ITripService tripService;

    @Autowired
    private ICategoriaService categoriaService;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/view/{id}")
    public String verDetalle(@PathVariable("id") Long idTrip, Model model) {
        Trip trip = tripService.buscarPorId(idTrip);
        if (trip != null) {
            model.addAttribute("trip", trip);
            return "trips/detalle";
        } else {
            return "redirect:/trips/list";
        }
    }

    @GetMapping("/create")
    public String crear(Model model) {
        model.addAttribute("trip", new Trip());
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "trips/formTrip";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        Trip trip = tripService.buscarPorId(id);
        if (trip != null) {
            model.addAttribute("trip", trip);
            model.addAttribute("categorias", categoriaService.listarTodas());
            return "trips/formTrip";
        } else {
            return "redirect:/trips/list";
        }
    }

    @PostMapping("/save")
    public String guardarTrip(@ModelAttribute Trip trip,
                              BindingResult result,
                              @RequestParam(value = "imagenFile", required = false) MultipartFile imagenFile,
                              RedirectAttributes redirectAttributes,
                              Model model) {
        

        if (trip.getNombre() == null || trip.getNombre().trim().isEmpty()) {
            result.rejectValue("nombre", "error.nombre", "El nombre del viaje es obligatorio");
        }
        

        if (trip.getDescripcion() == null || trip.getDescripcion().trim().isEmpty()) {
            result.rejectValue("descripcion", "error.descripcion", "La descripción es obligatoria");
        }
        

        if (trip.getFecha() == null) {
            result.rejectValue("fecha", "error.fecha", "La fecha es obligatoria. Use formato dd-MM-yyyy");
        }
        

        if (trip.getCosto() == null || trip.getCosto() <= 0) {
            result.rejectValue("costo", "error.costo", "El costo debe ser un número positivo mayor a 0");
        }
        

        if (trip.getCategoria() == null || trip.getCategoria().getId() == null) {
            result.rejectValue("categoria", "error.categoria", "Debe seleccionar una categoría");
        }
        

        if (trip.getDestacado() == null) {
            trip.setDestacado(0);
        }
        

        if (result.hasErrors()) {
            System.out.println("=== ERRORES DE VALIDACIÓN ===");
            result.getAllErrors().forEach(error -> 
                System.out.println("  - " + error.getDefaultMessage()));
            

            model.addAttribute("categorias", categoriaService.listarTodas());
            return "trips/formTrip";
        }
        

        if (trip.getCategoria() != null && trip.getCategoria().getId() != null) {
            Categoria categoriaSeleccionada = categoriaService.buscarPorId(trip.getCategoria().getId());
            trip.setCategoria(categoriaSeleccionada);
        }
        

        if (imagenFile != null && !imagenFile.isEmpty()) {
            String nombreImagen = imagenFile.getOriginalFilename();
            trip.setImagen(nombreImagen);
            System.out.println("Imagen recibida: " + nombreImagen);
        } else if (trip.getImagen() == null || trip.getImagen().isEmpty()) {
            trip.setImagen("no-image.png");
        }
        

        try {
            tripService.guardar(trip);
            System.out.println("Trip guardado exitosamente - ID: " + trip.getId());
        } catch (Exception e) {
            System.out.println("ERROR al guardar: " + e.getMessage());
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("mensajeError", "Error al guardar el viaje: " + e.getMessage());
            return "redirect:/trips/create";
        }
        

        if (trip.getId() != null) {
            redirectAttributes.addFlashAttribute("mensajeExito", "Trip '" + trip.getNombre() + "' actualizado correctamente");
        } else {
            redirectAttributes.addFlashAttribute("mensajeExito", "Trip '" + trip.getNombre() + "' guardado correctamente");
        }
        
        return "redirect:/trips/list";
    }

    @GetMapping("/delete/{id}")
    public String eliminarTrip(@PathVariable("id") Long idTrip, 
                               RedirectAttributes redirectAttributes) {
        try {
            Trip trip = tripService.buscarPorId(idTrip);
            if (trip != null) {
                tripService.eliminar(idTrip);
                redirectAttributes.addFlashAttribute("mensajeExito", "Trip '" + trip.getNombre() + "' eliminado correctamente");
            } else {
                redirectAttributes.addFlashAttribute("mensajeError", "Trip no encontrado");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensajeError", "Error al eliminar el viaje");
        }
        return "redirect:/trips/list";
    }

    @GetMapping("/list")
    public String listarTrips(Model model) {
        model.addAttribute("trips", tripService.buscarTodos());
        return "trips/listTrips";
    }
}