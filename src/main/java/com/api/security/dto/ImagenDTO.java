package com.api.security.dto;

import com.api.security.imagen.Imagen;

class ImagenDTO {
    private int id; 
    private String nombre;
    private String path;
    
    public ImagenDTO(Imagen imagen){
        id = imagen.getId();
        nombre = imagen.getNombre();
        path = imagen.getPath();
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPath() {
        return path;
    }
    
}
