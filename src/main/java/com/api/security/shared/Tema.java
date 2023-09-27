package com.api.security.shared;

public enum Tema {
    RECETA("Receta"),
    PREGUNTA("Pregunta");

    private String nombre;

    private Tema(String nombre){
        this.nombre = nombre;
    }

    public String getNombre(){
        return nombre;
    }
}
