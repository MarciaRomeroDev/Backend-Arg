
package com.backportfolio.API.Service;

import com.backportfolio.API.DTO.Mensaje;
import com.backportfolio.API.DTO.PersonaDTO;
import com.backportfolio.API.Entity.Persona;
import com.backportfolio.API.Repository.PersonaRepository;
import com.backportfolio.API.exceptions.CustomException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PersonaService {
    @Autowired
    PersonaRepository personaRepository;

    public List<Persona> list(){
        return personaRepository.findAll();
    }
    public Persona getOne (int id){
        return personaRepository.findById(id)
                .orElseThrow(()-> new CustomException(HttpStatus.NOT_FOUND, "Esa persona no existe"));
    }

/*
    public Mensaje save(PersonaDTO personaDTO){
        if (personaRepository.existsByNombre(personaDTO.getNombre()))
            throw new CustomException(HttpStatus.BAD_REQUEST, "Esa persona ya existe");
        Persona persona = new Persona(personaDTO.getNombre(), personaDTO.getApellido(), personaDTO.getFoto_perfil(), personaDTO.getDescripcion());
        personaRepository.save(persona);

        return new Mensaje(persona.getNombre() + " ha sido creada ");
    }*/


    public Mensaje update (int id,PersonaDTO personaDTO){
        if (!personaRepository.existsById(id))
            throw new CustomException(HttpStatus.NOT_FOUND, "Esa persona no existe ");

        Optional<Persona> personaOptional = personaRepository.findByNombre(personaDTO.getNombre());
        if (personaOptional.isPresent() && personaOptional.get().getId() != id)
            throw new CustomException(HttpStatus.BAD_REQUEST, "Esa persona ya existe");

        Persona persona = getOne(id);
        persona.setNombre(personaDTO.getNombre());
        persona.setApellido(personaDTO.getApellido());
        persona.setFoto_perfil(personaDTO.getFoto_perfil());
        persona.setDescripcion(personaDTO.getDescripcion());


        personaRepository.save(persona);

        return new Mensaje(persona.getNombre() + " ha sido actualizada");
    }
/*
    public Mensaje delete(int id) {
        Persona persona = getOne(id);
        personaRepository.delete(persona);
        return new Mensaje(persona.getNombre() + " ha sido eliminada");
    }
*/
    public boolean existsById(int id ){
        return personaRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return personaRepository.existsByNombre(nombre);
    }
    
}
