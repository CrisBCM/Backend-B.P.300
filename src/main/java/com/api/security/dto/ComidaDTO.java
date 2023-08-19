package com.api.security.dto;

import com.api.security.comida.Comida;


class ComidaDTO {
    private int id;
    private String nombreComida;
    private int calorias;
    private ImagenDTO imagen;
    
    public ComidaDTO(Comida comida){
        id = comida.getId();
        nombreComida = comida.getNombreComida();
        calorias = comida.getCalorias();
        imagen = new ImagenDTO(comida.getImagen());
    }

    public int getId() {
        return id;
    }

    public String getNombreComida() {
        return nombreComida;
    }

    public int getCalorias() {
        return calorias;
    }

    public ImagenDTO getImagen() {
        return imagen;
    }
    
}
