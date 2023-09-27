package com.api.security.categoria;


import java.util.List;

public interface ICategoriaService{
    public List<Categoria> obtenerCategorias();
    public Categoria crearCategoria(CategoriaRequest categoriaRequest);
    public void eliminarCategoriaPorId(int id);

    public void editarCategoria(int id, String nuevoNombre, String nuevaDescripcion);
}
