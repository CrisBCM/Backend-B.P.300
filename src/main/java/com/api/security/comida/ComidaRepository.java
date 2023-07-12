package com.api.security.comida;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ComidaRepository extends JpaRepository<Comida, Integer>{
    
}
