package com.api.security.categoria;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService implements ICategoriaService{

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> obtenerCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria crearCategoria(CategoriaRequest categoriaRequest) {
        return categoriaRepository.save(
                            Categoria.builder()
                            .nombre(categoriaRequest.getNombre())
                            .descripcion(categoriaRequest.getDescripcion())
                            .build());
    }

    @Override
    public void eliminarCategoriaPorId(int id) {
        categoriaRepository.deleteById(id);
    }

    @Override
    public void editarCategoria(int id, String nuevoNombre, String nuevaDescripcion) {
        if(nuevoNombre != null || nuevaDescripcion != null){

            Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encontró una categoría con el ID proporcionado"));

            if(nuevoNombre != null) categoria.setNombre(nuevoNombre);
            if(nuevaDescripcion != null) categoria.setDescripcion(nuevaDescripcion);

            categoriaRepository.save(categoria);

        }
    }

}
