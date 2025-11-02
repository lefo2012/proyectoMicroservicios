package co.edu.unicauca.users_ms.service;

import co.edu.unicauca.users_ms.entity.Estudiante;
import co.edu.unicauca.users_ms.repository.EstudianteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService implements BaseService<Estudiante,String>{

    @Autowired
    EstudianteRepository estudianteRepository;

    @Override
    @Transactional
    public List<Estudiante> findAll() throws Exception {

        return List.of();
    }

    @Override
    @Transactional
    public Estudiante findById(String id) throws Exception {
        try{
            System.out.println("buscando estudiante finById id:" + id);
            Optional<Estudiante> estudiante = estudianteRepository.findByCorreoElectronico(id);
            System.out.println(estudiante);
            return estudiante.orElse(null);
        }catch(Exception ex){
            throw new Exception("Error al buscar al estudiante con id: "+id+" "+ ex.getMessage());
        }
    }

    @Override
    @Transactional
    public Estudiante save(Estudiante entity) throws Exception {
        try{
            return estudianteRepository.save(entity);
        } catch (Exception ex) {
            throw new RuntimeException("Error al guardar estudiante: "+ex.getMessage());
        }
    }

    @Override
    @Transactional
    public Estudiante updateById(Estudiante entity) throws Exception {
        return null;
    }

    @Override
    @Transactional
    public boolean deleteById(String id) throws Exception {
        return false;
    }
}
