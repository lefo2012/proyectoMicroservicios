package co.edu.unicauca.users_ms.service;

import co.edu.unicauca.users_ms.entity.Estudiante;

import co.edu.unicauca.users_ms.repository.EstudianteDomainRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService implements BaseService<Estudiante, Long> {

    @Autowired
    private EstudianteDomainRepository repository;

    @Override
    public List<Estudiante> findAll() {
        return repository.findAll();
    }

    @Override
    public Estudiante findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Estudiante findByCorreo(String correo) {
        return repository.findByCorreo(correo).orElse(null);
    }

    @Override
    public Estudiante save(Estudiante e) {
        return repository.save(e);
    }

    @Override
    public Estudiante updateById(Estudiante e) {
        return repository.save(e); // update = save
    }

    @Override
    public boolean deleteById(Long id) {
        return false; 
    }

    public boolean existsByCorreo(String correo) {
        return repository.existsByCorreo(correo);
    }
}