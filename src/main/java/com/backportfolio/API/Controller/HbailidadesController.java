
package com.backportfolio.API.Controller;

import com.backportfolio.API.DTO.HabilidadesDTO;
import com.backportfolio.API.DTO.Mensaje;
import com.backportfolio.API.Entity.Habilidades;
import com.backportfolio.API.Service.HabilidadesService;
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
@RequestMapping ("/habilidades")
@CrossOrigin (origins = "*")
public class HbailidadesController {
      @Autowired
    HabilidadesService habilidadesService;
    @GetMapping("/lista")
    public ResponseEntity<List<Habilidades>> list(){

        return  ResponseEntity.ok(habilidadesService.list());
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<Habilidades> getById(@PathVariable int id){
        return ResponseEntity.ok(habilidadesService.getOne(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crear")
    public ResponseEntity<Mensaje> create(@Valid @RequestBody HabilidadesDTO habilidadesDTO) {
        return ResponseEntity.ok(habilidadesService.save(habilidadesDTO));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Mensaje> update(@PathVariable int id,@Valid @RequestBody HabilidadesDTO habilidadesDTO) {
        return ResponseEntity.ok(habilidadesService.update(id, habilidadesDTO));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Mensaje> delete(@PathVariable int id ){
        return ResponseEntity.ok(habilidadesService.delete(id));
    }
    
}
