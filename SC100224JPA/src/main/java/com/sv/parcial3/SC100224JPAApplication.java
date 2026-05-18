package com.sv.parcial3;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sv.parcial3.model.Alumno;
import com.sv.parcial3.model.Carrera;
import com.sv.parcial3.repository.IAlumnoRepository;
import com.sv.parcial3.repository.ICarreraRepository;

import jakarta.transaction.Transactional;

@SpringBootApplication
public class SC100224JPAApplication implements CommandLineRunner {

    @Autowired
    private ICarreraRepository carreraRepository;
    
    @Autowired
    private IAlumnoRepository alumnoRepository;

    public static void main(String[] args) {
        SpringApplication.run(SC100224JPAApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        System.out.println("=== PROYECTO PARCIAL 3 INICIADO ===\n");
        

        System.out.println("--- LIMPIANDO TABLAS ---");
        alumnoRepository.deleteAll();
        carreraRepository.deleteAll();
        System.out.println("Tablas limpias\n");
        
        test();
        guardarCarreras();
        guardarAlumnos();
        recuperarAlumno("SC100224");
        modificarAlumno("SC100224", "Carlos", "Pérez", "INACTIVO", null);
        eliminarAlumno("SC100224");
    }
    

    private void test() {
        System.out.println("=== TEST DE CONEXIÓN ===");
        System.out.println("Repositorio Carrera: " + carreraRepository);
        System.out.println("Repositorio Alumno: " + alumnoRepository);
        System.out.println("Conexión exitosa");
    }
    

    private void guardarCarreras() {
        System.out.println("\n=== GUARDAR CARRERAS ===");
        
        List<Carrera> carreras = List.of(
            new Carrera("Ingeniería en Sistemas"),
            new Carrera("Ingeniería Civil"),
            new Carrera("Administración de Empresas")
        );
        
        carreraRepository.saveAll(carreras);
        System.out.println("Carreras guardadas correctamente");
        
        System.out.println("Lista de carreras guardadas:");
        carreraRepository.findAll().forEach(c -> System.out.println("  " + c));
    }
    

    private void guardarAlumnos() {
        System.out.println("\n=== GUARDAR ALUMNOS ===");
        

        Carrera ingSistemas = carreraRepository.findByNombre("Ingeniería en Sistemas").orElse(null);
        Carrera civil = carreraRepository.findByNombre("Ingeniería Civil").orElse(null);
        Carrera admin = carreraRepository.findByNombre("Administración de Empresas").orElse(null);
        
        if (ingSistemas == null || civil == null || admin == null) {
            System.out.println("Error: Primero debe guardar las carreras (ejercicio 6)");
            return;
        }
        
        List<Alumno> alumnos = List.of(
            new Alumno("SC100224", "Rafael", "Soto", "ACTIVO", ingSistemas),
            new Alumno("SC100225", "Ana", "López", "ACTIVO", civil),
            new Alumno("SC100226", "Carlos", "Gómez", "ACTIVO", admin)
        );
        
        alumnoRepository.saveAll(alumnos);
        System.out.println("Alumnos guardados correctamente");
        
        System.out.println("Lista de alumnos guardados:");
        alumnoRepository.findAll().forEach(a -> System.out.println("  " + a));
    }
    

    private void recuperarAlumno(String carnet) {
        System.out.println("\n=== RECUPERAR ALUMNO POR CARNET ===");
        
        var alumnoOpt = alumnoRepository.findByCarnet(carnet);
        
        if (alumnoOpt.isPresent()) {
            Alumno alumno = alumnoOpt.get();
            System.out.println("Alumno encontrado:");
            System.out.println("  Carnet: " + alumno.getCarnet());
            System.out.println("  Nombre: " + alumno.getNombre());
            System.out.println("  Apellido: " + alumno.getApellido());
            System.out.println("  Estado: " + alumno.getEstado());
            System.out.println("  Carrera: " + (alumno.getCarrera() != null ? alumno.getCarrera().getNombre() : "Sin carrera"));
        } else {
            System.out.println("No se encontró alumno con carnet: " + carnet);
        }
    }
    

    private void modificarAlumno(String carnet, String nuevoNombre, String nuevoApellido, String nuevoEstado, Long nuevaCarreraId) {
        System.out.println("\n=== MODIFICAR ALUMNO ===");
        
        var alumnoOpt = alumnoRepository.findByCarnet(carnet);
        
        if (alumnoOpt.isPresent()) {
            Alumno alumno = alumnoOpt.get();
            System.out.println("Alumno antes de modificar:");
            System.out.println("  " + alumno);
            
            alumno.setNombre(nuevoNombre);
            alumno.setApellido(nuevoApellido);
            alumno.setEstado(nuevoEstado);
            
            if (nuevaCarreraId != null) {
                Carrera nuevaCarrera = carreraRepository.findById(nuevaCarreraId).orElse(null);
                if (nuevaCarrera != null) {
                    alumno.setCarrera(nuevaCarrera);
                }
            }
            
            alumnoRepository.save(alumno);
            System.out.println("Alumno modificado correctamente:");
            System.out.println("  " + alumno);
        } else {
            System.out.println("No se encontró alumno con carnet: " + carnet);
        }
    }
    

    private void eliminarAlumno(String carnet) {
        System.out.println("\n=== ELIMINAR ALUMNO ===");
        
        var alumnoOpt = alumnoRepository.findByCarnet(carnet);
        
        if (alumnoOpt.isPresent()) {
            Alumno alumno = alumnoOpt.get();
            System.out.println("Alumno a eliminar:");
            System.out.println("  " + alumno);
            
            alumnoRepository.deleteByCarnet(carnet);
            System.out.println("Alumno con carnet " + carnet + " eliminado correctamente");
        } else {
            System.out.println("No se encontró alumno con carnet: " + carnet);
        }
    }
}