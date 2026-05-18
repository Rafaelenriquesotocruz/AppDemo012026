package com.sv.jpa;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.sv.jpa.model.Categoria;
import com.sv.jpa.model.Perfil;
import com.sv.jpa.model.Trip;
import com.sv.jpa.model.Usuario;
import com.sv.jpa.repository.ICategoriaRepository;
import com.sv.jpa.repository.IPerfilRepository;
import com.sv.jpa.repository.ITripRepository;
import com.sv.jpa.repository.IUsuarioRepository;

import jakarta.transaction.Transactional;

@SpringBootApplication
public class MiProyectoJPAApplication implements CommandLineRunner {

    @Autowired
    private ICategoriaRepository categoriaRepository;
    
    @Autowired
    private ITripRepository tripRepository;
    
    @Autowired
    private IUsuarioRepository usuarioRepository;
    
    @Autowired
    private IPerfilRepository perfilRepository;

    public static void main(String[] args) {
        SpringApplication.run(MiProyectoJPAApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        System.out.println("=== PROYECTO JPA INICIADO ===\n");
        

        System.out.println("--- LIMPIANDO TABLAS ---");
        tripRepository.deleteAll();
        categoriaRepository.deleteAll();
        usuarioRepository.deleteAll();
        perfilRepository.deleteAll();
        System.out.println("Tablas limpias\n");
        

        System.out.println("--- GUARDAR CATEGORÍAS ---");
        Categoria aventura = categoriaRepository.save(new Categoria("Aventura"));
        categoriaRepository.save(new Categoria("Deportes"));
        Categoria tecnologia = categoriaRepository.save(new Categoria("Tecnología"));
        Categoria cultura = categoriaRepository.save(new Categoria("Cultura"));
        System.out.println("Categorías guardadas: Aventura, Deportes, Tecnología, Cultura");
        

        System.out.println("\n--- GUARDAR TRIPS ---");
        
        Trip trip1 = new Trip("Rapel en Volcán", "Descenso en rapel", LocalDate.parse("2026-05-01"), 
                              50.0, 1, "trip1.jpg", "publicada", aventura);
        Trip trip2 = new Trip("Caminata al cerro", "Senderismo extremo", LocalDate.parse("2026-05-15"), 
                              30.0, 0, "trip2.jpg", "creada", aventura);
        Trip trip3 = new Trip("Tour tecnológico", "Visita a empresas tech", LocalDate.parse("2026-06-10"), 
                              100.0, 1, "trip3.jpg", "publicada", tecnologia);
        Trip trip4 = new Trip("Museos", "Recorrido cultural", LocalDate.parse("2026-07-20"), 
                              40.0, 0, "trip4.jpg", "finalizada", cultura);
        
        tripRepository.saveAll(List.of(trip1, trip2, trip3, trip4));
        System.out.println("Trips guardados correctamente");
        

        System.out.println("\n--- QUERY METHODS ---");
        
        System.out.println("\nTrips con estatus 'publicada':");
        tripRepository.findByEstatus("publicada").forEach(t -> System.out.println("  " + t));
        
        System.out.println("\nTrips destacados y publicados (ordenados por ID desc):");
        tripRepository.findByDestacadoAndEstatusOrderByIdDesc(1, "publicada").forEach(t -> System.out.println("  " + t));
        
        System.out.println("\nTrips con costo entre 30 y 60:");
        tripRepository.findByCostoBetween(30.0, 60.0).forEach(t -> System.out.println("  " + t));
        
        System.out.println("\nTrips con estatus 'creada' o 'publicada':");
        tripRepository.findByEstatusIn(List.of("creada", "publicada")).forEach(t -> System.out.println("  " + t));
        

        System.out.println("\n--- RELACIÓN MANY TO MANY (Usuario - Perfil) ---");
        
        Perfil admin = new Perfil("ADMIN", "Administrador del sistema");
        Perfil usuario = new Perfil("USUARIO", "Usuario normal");
        Perfil invitado = new Perfil("INVITADO", "Usuario sin privilegios");
        
        perfilRepository.save(admin);
        perfilRepository.save(usuario);
        perfilRepository.save(invitado);
        System.out.println("Perfiles creados: ADMIN, USUARIO, INVITADO");
        

        Usuario user1 = new Usuario("Rafael Soto", "sotocruz@example.com");
        user1.agregarPerfil(admin);
        user1.agregarPerfil(usuario);
        
        Usuario user2 = new Usuario("María López", "maria@example.com");
        user2.agregarPerfil(usuario);
        
        Usuario user3 = new Usuario("Carlos Gómez", "carlos@example.com");
        user3.agregarPerfil(invitado);
        
        usuarioRepository.save(user1);
        usuarioRepository.save(user2);
        usuarioRepository.save(user3);
        System.out.println("Usuarios guardados con sus perfiles");
        
        System.out.println("\nUsuarios con sus perfiles asociados:");
        usuarioRepository.findAll().forEach(u -> {
            System.out.println("  " + u.getNombre() + " (" + u.getEmail() + ")");
            u.getPerfiles().forEach(p -> System.out.println("      Perfil: " + p.getNombre()));
        });
        

        System.out.println("\n--- ORDENAMIENTO (Sort) ---");
        System.out.println("Trips ordenados por nombre ascendente:");
        tripRepository.findAll(Sort.by("nombre").ascending()).forEach(t -> System.out.println("  " + t));
        
        System.out.println("\nTrips ordenados por costo descendente:");
        tripRepository.findAll(Sort.by("costo").descending()).forEach(t -> System.out.println("  " + t));
        

        System.out.println("\n--- PAGINACIÓN (Pageable) ---");
        
        Pageable primeraPagina = PageRequest.of(0, 2, Sort.by("id").ascending());
        Page<Trip> pagina1 = tripRepository.findAll(primeraPagina);
        
        System.out.println("Página 1 (elementos 1-2):");
        pagina1.getContent().forEach(t -> System.out.println("  " + t));
        System.out.println("Total de elementos: " + pagina1.getTotalElements());
        System.out.println("Total de páginas: " + pagina1.getTotalPages());
        
        Pageable segundaPagina = PageRequest.of(1, 2, Sort.by("id").ascending());
        Page<Trip> pagina2 = tripRepository.findAll(segundaPagina);
        
        System.out.println("\nPágina 2 (elementos 3-4):");
        pagina2.getContent().forEach(t -> System.out.println("  " + t));
        

        System.out.println("\n--- BUSCAR USUARIO POR ID ---");
        Usuario usuarioEncontrado = usuarioRepository.findById(1L).orElse(null);
        if (usuarioEncontrado != null) {
            System.out.println("Usuario encontrado: " + usuarioEncontrado.getNombre());
            System.out.println("Perfiles asociados:");
            usuarioEncontrado.getPerfiles().forEach(p -> System.out.println("  - " + p.getNombre()));
        }
        
        System.out.println("\n=== PROYECTO JPA FINALIZADO ===");
    }
}