package com.sv.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sv.jpa.model.Trip;

public interface ITripRepository extends JpaRepository<Trip, Long> {
    
    List<Trip> findByEstatus(String estatus);
    
    List<Trip> findByDestacadoAndEstatusOrderByIdDesc(Integer destacado, String estatus);
    
    List<Trip> findByCostoBetween(Double min, Double max);
    
    List<Trip> findByEstatusIn(List<String> estatusList);
}