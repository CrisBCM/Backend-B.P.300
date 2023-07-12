package com.api.security.estomago;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstomagoRepository extends JpaRepository<Estomago, Integer>{
    
}
