
package com.backportfolio.API.Service;

import com.backportfolio.API.DTO.EstudiosDTO;
import com.backportfolio.API.DTO.Mensaje;
import com.backportfolio.API.Entity.Estudios;
import com.backportfolio.API.Repository.EstudiosRepository;
import com.backportfolio.API.exceptions.CustomException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EstudiosService {
    @Autowired
    EstudiosRepository estudiosRepository;

    public List<Estudios> list(){
        return estudiosRepository.findAll();
    }
    public Estudios getOne (int id){
        return estudiosRepository.findById(id)
                .orElseThrow(()-> new CustomException(HttpStatus.NOT_FOUND, "Ese estudio no existe"));
    }


    public Mensaje save(EstudiosDTO estudiosDTO){
        if (estudiosRepository.existsByTitulo(estudiosDTO.getTitulo()))
            throw new CustomException(HttpStatus.BAD_REQUEST, "Ese estudio ya existe");
        Estudios estudios = new Estudios(estudiosDTO.getTitulo(), estudiosDTO.getFecha(), estudiosDTO.getLugar(), estudiosDTO.getDescripcion());
        estudiosRepository.save(estudios);

        return new Mensaje(estudios.getTitulo() + " ha sido creada ");
    }


    public Mensaje update (int id, EstudiosDTO estudiosDTO){
        if (!estudiosRepository.existsById(id))
            throw new CustomException(HttpStatus.NOT_FOUND, "Ese estudio no existe ");

        Optional<Estudios> estudiosOptional = estudiosRepository.findByTitulo(estudiosDTO.getTitulo());
        if (estudiosOptional.isPresent() && estudiosOptional.get().getId() != id)
            throw new CustomException(HttpStatus.BAD_REQUEST, "Ese estudio ya existe");

        Estudios estudios = getOne(id);
        estudios.setTitulo(estudiosDTO.getTitulo());
        estudios.setFecha(estudiosDTO.getFecha());
        estudios.setLugar(estudiosDTO.getLugar());
        estudios.setDescripcion(estudiosDTO.getDescripcion());

        estudiosRepository.save(estudios);

        return new Mensaje(estudios.getTitulo() + " ha sido actualizada");
    }
    public Mensaje delete(int id) {
        Estudios estudios = getOne(id);
        estudiosRepository.delete(estudios);
        return new Mensaje(estudios.getTitulo() + " ha sido eliminada");
    }

    public boolean existsById(int id ){
        return estudiosRepository.existsById(id);
    }
    public boolean existsByTitulo(String titulo){
        return estudiosRepository.existsByTitulo(titulo);
    }
    
}
