package com.api.security.categoria;


import com.api.security.dto.CategoriaDTO;
import com.api.security.dto.CategoriaResumenDTO;

import java.util.List;

public interface ICategoriaService{
    public List<CategoriaDTO> obtenerCategorias();
    public List<CategoriaResumenDTO> obtenerResumenCategorias();
    public CategoriaDTO crearCategoria(CategoriaRequest categoriaRequest);
    public void eliminarCategoriaPorId(int id);

    public void editarCategoria(int id, String nuevoNombre, String nuevaDescripcion);
    public boolean existsByNombre(String nombre);
    public Categoria findByNombre(String nombre);
    public void habilitarODeshabilitarCategoria(int id);
}
