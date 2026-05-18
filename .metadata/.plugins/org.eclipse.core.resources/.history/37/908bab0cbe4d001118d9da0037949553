package com.sv.appDemo.service.db;

import com.sv.appDemo.model.Categoria;
import com.sv.appDemo.repository.CategoriaRepository;
import com.sv.appDemo.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Primary
public class CategoriasServicesJpa implements ICategoriaService {
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @Override
    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }
    
    @Override
    public Categoria buscarPorId(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }
    
    @Override
    public void guardar(Categoria categoria) {
        categoriaRepository.save(categoria);
    }
    
    @Override
    public void eliminar(Long id) {
        categoriaRepository.deleteById(id);
    }
}