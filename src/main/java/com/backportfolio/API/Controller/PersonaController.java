
package com.backportfolio.API.Controller;

import com.backportfolio.API.DTO.Mensaje;
import com.backportfolio.API.DTO.PersonaDTO;
import com.backportfolio.API.Entity.Persona;
import com.backportfolio.API.Service.PersonaService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/persona")
@CrossOrigin("*")
public class PersonaController {
    @Autowired
    PersonaService personaService;

    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list(){
        return  ResponseEntity.ok(personaService.list());
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<Persona> getById(@PathVariable int id){
        return ResponseEntity.ok(personaService.getOne(id));
    }
/*
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crear")
    public ResponseEntity<Mensaje> create( @Valid @RequestBody PersonaDTO personaDTO) {
        return ResponseEntity.ok(personaService.save(personaDTO));
    }*/

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Mensaje> update(@PathVariable int id,@Valid @RequestBody PersonaDTO personaDTO) {
        return ResponseEntity.ok(personaService.update(id, personaDTO));
    }

/*
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Mensaje> delete(@PathVariable int id ){
        return ResponseEntity.ok(personaService.delete(id));
    }*/
    
}
