
package com.backportfolio.API.Service;

import com.backportfolio.API.DTO.HabilidadesDTO;
import com.backportfolio.API.DTO.Mensaje;
import com.backportfolio.API.Entity.Habilidades;
import com.backportfolio.API.Repository.HabilidadesRepository;
import com.backportfolio.API.exceptions.CustomException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class HabilidadesService {
     @Autowired
    HabilidadesRepository habilidadesRepository;

    public List<Habilidades> list(){
        return habilidadesRepository.findAll();
    }

    public Habilidades getOne ( int id){
        return habilidadesRepository.findById(id).
                orElseThrow(()-> new CustomException(HttpStatus.NOT_FOUND, "Ese skill no existe"));
    }

    public Mensaje save(HabilidadesDTO habilidadesDTO){
        if (habilidadesRepository.existsByNombre(habilidadesDTO.getNombre()))
            throw new CustomException(HttpStatus.BAD_REQUEST, "Ese skill ya existe");
        Habilidades habilidades = new Habilidades (habilidadesDTO.getNombre(), habilidadesDTO.getIcono(), habilidadesDTO.getProgreso());
        habilidadesRepository.save(habilidades);

        return new Mensaje(habilidades.getNombre() + " ha sido creada ");
    }
    public Mensaje update (int id, HabilidadesDTO habilidadesDTO){
        if (!habilidadesRepository.existsById(id))
            throw new CustomException(HttpStatus.NOT_FOUND, "Esa skill no existe ");

        Optional<Habilidades> habilidadesOptional = habilidadesRepository.findByNombre(habilidadesDTO.getNombre());
        if (habilidadesOptional.isPresent() && habilidadesOptional.get().getId() != id)
            throw new CustomException(HttpStatus.BAD_REQUEST, "Esa skill ya existe");

        Habilidades habilidades = getOne(id);
        habilidades.setNombre(habilidadesDTO.getNombre());
        habilidades.setIcono(habilidadesDTO.getIcono());
        habilidades.setProgreso(habilidadesDTO.getProgreso());


       habilidadesRepository.save(habilidades);

        return new Mensaje(habilidades.getNombre() + " ha sido actualizada");
    }

    public Mensaje delete(int id) {
        Habilidades habilidades = getOne(id);
        habilidadesRepository.delete(habilidades);
        return new Mensaje(habilidades.getNombre() + " ha sido eliminada");
    }
    public boolean existsById(int id ){
        return habilidadesRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return habilidadesRepository.existsByNombre(nombre);
    }
    
}
