
package com.backportfolio.API.Service;

import com.backportfolio.API.DTO.ExperienciaDTO;
import com.backportfolio.API.DTO.Mensaje;
import com.backportfolio.API.Entity.Experiencia;
import com.backportfolio.API.Repository.ExperienciaRepository;
import com.backportfolio.API.exceptions.CustomException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ExperienciaService {
     @Autowired
    ExperienciaRepository experienciaRepository;

    public List<Experiencia> list(){
        return experienciaRepository.findAll();
    }
    public Experiencia getOne (int id){
        return experienciaRepository.findById(id)
                .orElseThrow(()-> new CustomException(HttpStatus.NOT_FOUND, "Esa experiencia no existe"));
    }


    public Mensaje save(ExperienciaDTO experienciaDTO){
        if (experienciaRepository.existsByTitulo(experienciaDTO.getTitulo()))
            throw new CustomException(HttpStatus.BAD_REQUEST, "Esa experiencia ya existe");
        Experiencia experiencia = new Experiencia(experienciaDTO.getTitulo(), experienciaDTO.getFecha(), experienciaDTO.getLugar(), experienciaDTO.getDescripcion());
        experienciaRepository.save(experiencia);

        return new Mensaje(experiencia.getTitulo() + " ha sido creada ");
    }


    public Mensaje update (int id, ExperienciaDTO experienciaDTO){
        if (!experienciaRepository.existsById(id))
           throw new CustomException(HttpStatus.NOT_FOUND, "Esa experiencia no existe ");

      Optional<Experiencia> experienciaOptional = experienciaRepository.findByTitulo(experienciaDTO.getTitulo());
        if (experienciaOptional.isPresent() && experienciaOptional.get().getId() != id)
            throw new CustomException(HttpStatus.BAD_REQUEST, "Esa experiencia ya existe");

        Experiencia experiencia = getOne(id);
        experiencia.setTitulo(experienciaDTO.getTitulo());
        experiencia.setFecha(experienciaDTO.getFecha());
        experiencia.setLugar(experienciaDTO.getLugar());
        experiencia.setDescripcion(experienciaDTO.getDescripcion());

        experienciaRepository.save(experiencia);

        return new Mensaje(experiencia.getTitulo() + " ha sido actualizada");
    }
    public Mensaje delete(int id) {
        Experiencia experiencia = getOne(id);
        experienciaRepository.delete(experiencia);
    return new Mensaje(experiencia.getTitulo() + " ha sido eliminada");
    }

    public boolean existsById(int id ){
        return experienciaRepository.existsById(id);
    }

    public boolean existsByTitulo(String titulo){
        return experienciaRepository.existsByTitulo(titulo);
    }
    
}
