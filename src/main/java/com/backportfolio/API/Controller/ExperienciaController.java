
package com.backportfolio.API.Controller;

import com.backportfolio.API.DTO.ExperienciaDTO;
import com.backportfolio.API.DTO.Mensaje;
import com.backportfolio.API.Entity.Experiencia;
import com.backportfolio.API.Service.ExperienciaService;
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
@RequestMapping("/experiencia")
@CrossOrigin(origins = ("*"))
public class ExperienciaController {
    @Autowired
    ExperienciaService experienciaService;

    @GetMapping("/lista")
    public ResponseEntity<List<Experiencia>> list(){
        return  ResponseEntity.ok(experienciaService.list());
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable ("id") int id){
        return ResponseEntity.ok(experienciaService.getOne(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crear")
    public ResponseEntity<Mensaje> create( @Valid  @RequestBody ExperienciaDTO experienciaDTO) {
        return ResponseEntity.ok(experienciaService.save(experienciaDTO));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Mensaje> update(@PathVariable ("id") int id,@Valid @RequestBody ExperienciaDTO experienciaDTO) {
        return ResponseEntity.ok(experienciaService.update(id, experienciaDTO));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Mensaje> delete(@PathVariable ("id") int id ){
        return ResponseEntity.ok(experienciaService.delete(id));
    }
}
