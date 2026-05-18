package com.sv.parcial3.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sv.parcial3.model.Carrera;

public interface ICarreraRepository extends JpaRepository<Carrera, Long> {
    
    Optional<Carrera> findByNombre(String nombre);
}