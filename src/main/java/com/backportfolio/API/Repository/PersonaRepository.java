
package com.backportfolio.API.Repository;

import com.backportfolio.API.Entity.Persona;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {

  Optional<Persona> findByNombre(String nombre);
  boolean existsByNombre(String nombre);
    
}
