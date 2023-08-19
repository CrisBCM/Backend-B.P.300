package com.api.security.dto;

import com.api.security.estomago.Estomago;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


class EstomagoDTO {
    private int id;
    private List<ComidaDTO> comidas = new ArrayList<>();
    
    public EstomagoDTO(Estomago estomago){
        id = estomago.getId();
        comidas = estomago.getComidas()
                .stream()
                .map(comida -> new ComidaDTO(comida))
                .collect(Collectors.toList());
    }

    public int getId() {
        return id;
    }

    public List<ComidaDTO> getComidas() {
        return comidas;
    }
    
}
