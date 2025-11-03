package co.edu.unicauca.users_ms.service;

import co.edu.unicauca.users_ms.entity.Profesor;
import co.edu.unicauca.users_ms.repository.ProfesorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesorService implements BaseService<Profesor,String>{
    @Autowired
    ProfesorRepository profesorRepository;

    @Override
    @Transactional
    public List<Profesor> findAll() throws Exception {
        try{
            return profesorRepository.findAll();
        }catch (Exception ex)
        {
            throw new Exception("Error al buscar todos los profesores: "+ex.getMessage());
        }
    }

    @Override
    @Transactional
    public Profesor findById(String correo) throws Exception {
        try
        {
            System.out.println("Estoy entrando a findById profesor finById id (id=correo) :"+ correo);
            Optional<Profesor> profesor = profesorRepository.findByCorreoElectronico(correo);
            return profesor.orElse(null);
        }catch(Exception ex)
        {
            throw new Exception("Error al buscar el profesor con id (id=correo) : "+correo+" :"+ex.getMessage());
        }

    }

    @Override
    @Transactional
    public Profesor save(Profesor entity) throws Exception {
        try{
            return profesorRepository.save(entity);
        } catch (Exception ex) {
            throw new RuntimeException("Error al guardar profesor: "+ex.getMessage());
        }
    }

    @Override
    @Transactional
    public Profesor updateById(Profesor entity) throws Exception {
        try {
            return profesorRepository.save(entity);
        }catch (Exception ex){
            throw new Exception("Error al guardar profesor: "+ex.getMessage());
        }

    }

    @Override
    @Transactional
    public boolean deleteById(String id) throws Exception {
        return false;
    }

    public boolean existsByCorreo(String correo) {
        return profesorRepository.findByCorreoElectronico(correo).isPresent();
    }
}
