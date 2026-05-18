package com.sv.appDemo.service;

import com.sv.appDemo.model.Categoria;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

    private List<Categoria> categorias = new ArrayList<>();
    private Long nextId = 1L;

    public CategoriaServiceImpl() {

        categorias.add(new Categoria(1L, "Aventura"));
        categorias.add(new Categoria(2L, "Gastronomía"));
        categorias.add(new Categoria(3L, "Naturaleza"));
        categorias.add(new Categoria(4L, "Playa"));
        categorias.add(new Categoria(5L, "Cultura"));
        nextId = 6L;
    }

    @Override
    public List<Categoria> listarTodas() {
        return categorias;
    }

    @Override
    public Categoria buscarPorId(Long id) {
        for (Categoria c : categorias) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void guardar(Categoria categoria) {
        if (categoria.getId() == null) {
            categoria.setId(nextId++);
            categorias.add(categoria);
        } else {

            for (int i = 0; i < categorias.size(); i++) {
                if (categorias.get(i).getId().equals(categoria.getId())) {
                    categorias.set(i, categoria);
                    break;
                }
            }
        }
    }

    @Override
    public void eliminar(Long id) {
        categorias.removeIf(c -> c.getId().equals(id));
    }
}