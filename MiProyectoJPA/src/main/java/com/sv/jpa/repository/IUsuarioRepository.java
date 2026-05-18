package com.sv.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sv.jpa.model.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
}