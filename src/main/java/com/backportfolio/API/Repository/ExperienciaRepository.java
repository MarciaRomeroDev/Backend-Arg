
package com.backportfolio.API.Repository;

import com.backportfolio.API.Entity.Experiencia;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienciaRepository extends JpaRepository<Experiencia, Integer> {
    Optional<Experiencia> findByTitulo(String titulo);
    boolean existsByTitulo(String titulo);
    
}
