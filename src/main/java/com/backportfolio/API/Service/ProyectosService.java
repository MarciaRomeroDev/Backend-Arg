
package com.backportfolio.API.Service;

import com.backportfolio.API.DTO.Mensaje;
import com.backportfolio.API.DTO.ProyectosDTO;
import com.backportfolio.API.Entity.Proyectos;
import com.backportfolio.API.Repository.ProyectosRepository;
import com.backportfolio.API.exceptions.CustomException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProyectosService {
    @Autowired
    ProyectosRepository proyectosRepository;

    public List<Proyectos> list(){
        return proyectosRepository.findAll();
    }
    public Proyectos getOne (int id){
        return proyectosRepository.findById(id)
                .orElseThrow(()-> new CustomException(HttpStatus.NOT_FOUND, "Ese proyecto no existe"));
    }

    public Mensaje save(ProyectosDTO proyectosDTO){
        if (proyectosRepository.existsByTitulo(proyectosDTO.getTitulo()))
            throw new CustomException(HttpStatus.BAD_REQUEST, "Ese proyecto ya existe");
        Proyectos proyectos = new Proyectos(proyectosDTO.getTitulo(), proyectosDTO.getDescripcion(), proyectosDTO.getUrl_imagen(), proyectosDTO.getUrl_repositorio());
       proyectosRepository.save(proyectos);

        return new Mensaje(proyectos.getTitulo() + " ha sido creado ");
    }


    public Mensaje update (int id, ProyectosDTO proyectosDTO){
        if (!proyectosRepository.existsById(id))
            throw new CustomException(HttpStatus.NOT_FOUND, "Ese proyecto no existe ");

        Optional<Proyectos> proyectosOptional = proyectosRepository.findByTitulo(proyectosDTO.getTitulo());
        if (proyectosOptional.isPresent() && proyectosOptional.get().getId() != id)
            throw new CustomException(HttpStatus.BAD_REQUEST, "Ese proyecto ya existe");

       Proyectos proyectos = getOne(id);
        proyectos.setTitulo(proyectosDTO.getTitulo());
        proyectos.setDescripcion(proyectosDTO.getDescripcion());
        proyectos.setUrl_imagen(proyectosDTO.getUrl_imagen());
        proyectos.setUrl_repositorio(proyectosDTO.getUrl_repositorio());

        proyectosRepository.save(proyectos);

        return new Mensaje(proyectos.getTitulo() + " ha sido actualizado");
    }
    public Mensaje delete(int id) {
        Proyectos proyectos = getOne(id);
        proyectosRepository.delete(proyectos);
        return new Mensaje(proyectos.getTitulo() + " ha sido eliminada");
    }

    public boolean existsById(int id ){
        return proyectosRepository.existsById(id);
    }

    public boolean existsByTitulo(String titulo){
        return proyectosRepository.existsByTitulo(titulo);
    }
    
}
