package com.sv.appDemo.service;

import com.sv.appDemo.model.Categoria;
import java.util.List;

public interface ICategoriaService {
    List<Categoria> listarTodas();
    Categoria buscarPorId(Long id);
    void guardar(Categoria categoria);
    void eliminar(Long id);
}