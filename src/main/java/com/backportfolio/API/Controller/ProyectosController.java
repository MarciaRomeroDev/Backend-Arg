
package com.backportfolio.API.Controller;

import com.backportfolio.API.DTO.Mensaje;
import com.backportfolio.API.DTO.ProyectosDTO;
import com.backportfolio.API.Entity.Proyectos;
import com.backportfolio.API.Service.ProyectosService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/proyectos")
@CrossOrigin ("https://frontendarg-7956c.web.app")
public class ProyectosController {
     @Autowired
    ProyectosService proyectosService;

    @GetMapping("/lista")
    public ResponseEntity<List<Proyectos>> list(){
        return  ResponseEntity.ok(proyectosService.list());
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<Proyectos> getById(@PathVariable int id){
        return ResponseEntity.ok(proyectosService.getOne(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crear")
    public ResponseEntity<Mensaje> create(@Valid @RequestBody ProyectosDTO proyectosDTO) {
        return ResponseEntity.ok(proyectosService.save(proyectosDTO));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Mensaje> update(@PathVariable int id,@Valid @RequestBody ProyectosDTO proyectosDTO) {
        return ResponseEntity.ok(proyectosService.update(id, proyectosDTO));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Mensaje> delete(@PathVariable int id ){
        return ResponseEntity.ok(proyectosService.delete(id));
    }
    
}
