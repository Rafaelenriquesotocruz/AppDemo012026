package com.sv.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sv.jpa.model.Categoria;

public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {
}