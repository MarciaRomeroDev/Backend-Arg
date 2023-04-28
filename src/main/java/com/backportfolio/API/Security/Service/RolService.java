
package com.backportfolio.API.Security.Service;

import com.backportfolio.API.Security.Entity.Rol;
import com.backportfolio.API.Security.Enums.RolNombre;
import com.backportfolio.API.Security.Repository.RolRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class RolService {
    @Autowired
    RolRepository rolRepository;


    public Optional<Rol> getByRolNombre(RolNombre rolNombre) {
        return rolRepository.findByRolNombre(rolNombre);
    }

    public void save(Rol rol) {
        rolRepository.save(rol);
    }  
}
