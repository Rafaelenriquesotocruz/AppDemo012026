package com.sv.parcial3.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sv.parcial3.model.Alumno;

public interface IAlumnoRepository extends JpaRepository<Alumno, Long> {
    
    Optional<Alumno> findByCarnet(String carnet);
    
    void deleteByCarnet(String carnet);
}