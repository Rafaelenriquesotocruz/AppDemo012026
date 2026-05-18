package com.sv.appDemo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }


    @GetMapping("/view/{id}")
    public String verDetalle(@PathVariable("id") Long idTrip, Model model) {
        Trip trip = tripService.buscarPorId(idTrip);
        if (trip != null) {
            model.addAttribute("trip", trip);
        } else {
            return "redirect:/tabla";
        }
        return "trips/detalle";
    }


    @GetMapping("/delete")
    public String eliminarTrip(@RequestParam("id") Long idTrip, Model model) {
        System.out.println("ID a eliminar: " + idTrip);
        model.addAttribute("id", idTrip);
        return "mensaje";
    }


    @GetMapping("/create")
    public String crear(Model model) {
        model.addAttribute("trip", new Trip());
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "trips/formTrip";
    }


    @PostMapping("/save")
    public String guardarTrip(@ModelAttribute Trip trip,
                              BindingResult result,
                              @RequestParam(value = "imagenFile", required = false) MultipartFile imagenFile,
                              RedirectAttributes redirectAttributes,
                              Model model) {


        if (trip.getCategoria() == null || trip.getCategoria().getId() == null) {
            result.rejectValue("categoria", "error.categoria", "Debe seleccionar una categoría");
        }


        if (trip.getFecha() == null) {
            result.rejectValue("fecha", "error.fecha", "Formato de fecha inválido. Use dd-MM-yyyy");
        }


        if (trip.getCosto() == null || trip.getCosto() <= 0) {
            result.rejectValue("costo", "error.costo", "El costo debe ser un número positivo");
        }


        if (trip.getDescripcion() == null || trip.getDescripcion().trim().isEmpty()) {
            result.rejectValue("descripcion", "error.descripcion", "La descripción es obligatoria");
        }


        if (trip.getNombre() == null || trip.getNombre().trim().isEmpty()) {
            result.rejectValue("nombre", "error.nombre", "El nombre es obligatorio");
        }


        if (result.hasErrors()) {
            System.out.println(" ERRORES DE VALIDACIÓN DETECTADOS:");
            result.getAllErrors().forEach(error -> 
                System.out.println("   - " + error.getDefaultMessage()));
            

            model.addAttribute("categorias", categoriaService.listarTodas());
            return "trips/formTrip";
        }


        if (trip.getCategoria() != null && trip.getCategoria().getId() != null) {
            Categoria categoriaSeleccionada = categoriaService.buscarPorId(trip.getCategoria().getId());
            trip.setCategoria(categoriaSeleccionada);
        }


        if (imagenFile != null && !imagenFile.isEmpty()) {
            trip.setImagen(imagenFile.getOriginalFilename());
        } else {
            trip.setImagen("no-image.png");
        }


        if (trip.getDestacado() == null) {
            trip.setDestacado(0);
        }


        System.out.println(" Datos válidos recibidos:");
        System.out.println("   - Nombre: " + trip.getNombre());
        System.out.println("   - Descripción: " + trip.getDescripcion());
        System.out.println("   - Fecha: " + trip.getFecha());
        System.out.println("   - Costo: " + trip.getCosto());
        System.out.println("   - Categoría: " + (trip.getCategoria() != null ? trip.getCategoria().getNombre() : "ninguna"));
        System.out.println("   - Destacado: " + trip.getDestacado());
        System.out.println("   - Imagen: " + trip.getImagen());


        tripService.guardar(trip);


        redirectAttributes.addFlashAttribute("mensajeExito", "Trip '" + trip.getNombre() + "' guardado correctamente");
        return "redirect:/trips/list";
    }


    @GetMapping("/list")
    public String listarTrips(Model model) {
        model.addAttribute("trips", tripService.buscarTodos());
        return "trips/listTrips";
    }
}