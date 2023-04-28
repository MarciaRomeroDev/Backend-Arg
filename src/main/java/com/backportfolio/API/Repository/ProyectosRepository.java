
package com.backportfolio.API.Repository;

import com.backportfolio.API.Entity.Proyectos;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyectosRepository extends JpaRepository<Proyectos, Integer>{
    Optional<Proyectos> findByTitulo (String titulo);
    boolean existsByTitulo (String titulo);
    
}
