
package com.backportfolio.API.Controller;

import com.backportfolio.API.DTO.EstudiosDTO;
import com.backportfolio.API.DTO.Mensaje;
import com.backportfolio.API.Entity.Estudios;
import com.backportfolio.API.Service.EstudiosService;
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
@RequestMapping("/estudios")
@CrossOrigin(origins = ("https://frontendarg-7956c.web.app"))
public class EstudiosController {
     @Autowired
    EstudiosService estudiosService;

    @GetMapping("/lista")
    public ResponseEntity<List<Estudios>> list(){
        return  ResponseEntity.ok(estudiosService.list());
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<Estudios> getById(@PathVariable("id") int id){
        return ResponseEntity.ok(estudiosService.getOne(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crear")
    public ResponseEntity<Mensaje> create(@Valid @RequestBody EstudiosDTO estudiosDTO) {
        return ResponseEntity.ok(estudiosService.save(estudiosDTO));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Mensaje> update(@PathVariable ("id") int id,@Valid @RequestBody EstudiosDTO estudiosDTO) {
        return ResponseEntity.ok(estudiosService.update(id, estudiosDTO));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Mensaje> delete(@PathVariable ("id") int id ){
        return ResponseEntity.ok(estudiosService.delete(id));
    }
    
}
