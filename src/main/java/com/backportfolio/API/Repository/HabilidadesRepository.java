
package com.backportfolio.API.Repository;

import com.backportfolio.API.Entity.Habilidades;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabilidadesRepository extends JpaRepository<Habilidades, Integer> {
    Optional<Habilidades> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
    
}
