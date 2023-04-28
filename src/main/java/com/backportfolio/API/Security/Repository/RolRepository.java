
package com.backportfolio.API.Security.Repository;

import com.backportfolio.API.Security.Entity.Rol;
import com.backportfolio.API.Security.Enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
        Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
