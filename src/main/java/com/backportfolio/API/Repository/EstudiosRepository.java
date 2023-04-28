
package com.backportfolio.API.Repository;

import com.backportfolio.API.Entity.Estudios;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudiosRepository  extends JpaRepository<Estudios, Integer>{
    Optional<Estudios> findByTitulo(String titulo);
    boolean existsByTitulo(String titulo);
    
}
