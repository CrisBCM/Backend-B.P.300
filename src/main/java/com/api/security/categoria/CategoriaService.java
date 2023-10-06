package com.api.security.categoria;

import com.api.security.dto.CategoriaDTO;
import com.api.security.dto.CategoriaResumenDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService implements ICategoriaService{

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<CategoriaDTO> obtenerCategorias() {
        return categoriaRepository.findAll()
                .stream()
                .map(CategoriaDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoriaResumenDTO> obtenerResumenCategorias() {
        return categoriaRepository.findAll()
                .stream()
                .map(CategoriaResumenDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public CategoriaDTO crearCategoria(CategoriaRequest categoriaRequest) {
        return new CategoriaDTO(categoriaRepository.save(
                Categoria.builder()
                        .nombre(categoriaRequest.getNombre())
                        .descripcion(categoriaRequest.getDescripcion())
                        .build()));
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
    @Override
    public boolean existsByNombre(String nombre){
        return categoriaRepository.existsByNombre(nombre);
    }

}
