package com.sv.appDemo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sv.appDemo.model.Trip;
import com.sv.appDemo.service.ITripService;
import com.sv.appDemo.service.ICategoriaService;

@Controller
public class HomeController {

    @Autowired
    private ITripService tripService;

    @Autowired
    private ICategoriaService categoriaService;

    @GetMapping("/")
    public String mostrarHome(Model model) {
        model.addAttribute("mensaje1", "¡Bienvenido a AppTrip!");
        List<Trip> viajes = tripService.buscarTodos();
        model.addAttribute("viajes", viajes);
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "home";
    }

    @GetMapping("/listado")
    public String mostrarListado(Model model) {
        List<String> lista = new ArrayList<>();
        lista.add("En la Montaña");
        lista.add("En la Ciudad");
        lista.add("En los pueblos");
        lista.add("En las playas");
        
        model.addAttribute("lista", lista);
        return "listado";
    }

    @GetMapping("/detalle")
    public String mostrarDetalle(Model model) {
        Trip trip = new Trip();
        trip.setId(1L);
        trip.setNombre("Rapel en Volcatenango");
        trip.setDescripcion("Aventura rapel en un circuito conectado en las...");
        trip.setFecha(new Date());
        trip.setCosto(10.0);
        trip.setDestacado(1);

        model.addAttribute("trip", trip);
        return "detalle";
    }

    @GetMapping("/tabla")
    public String mostrarTabla(Model model) {
        model.addAttribute("trips", tripService.buscarTodos());
        return "tabla";
    }

    @GetMapping("/eliminar")
    public String eliminarTrip(@RequestParam("id") int idTrip, Model model) {
        System.out.println("ID a eliminar: " + idTrip);
        model.addAttribute("id", idTrip);
        return "mensaje";
    }


    @GetMapping("/testTrips")
    public String testTrips(Model model) {
        model.addAttribute("mensaje", "Prueba técnica - Rama test-de-repositorio-trips");
        System.out.println("Prueba técnica - Rama test-de-repositorio-trips");
        return "mensaje";
    }
}
    // Metodo agregado para rama test-de-repositorio-trips
    @GetMapping("/testTrips")
    public String testTrips(Model model) {
        model.addAttribute("mensaje", "Prueba tecnica - Rama test-de-repositorio-trips");
        return "mensaje";
    }
