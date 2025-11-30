package co.edu.unicauca.users_ms.service;

import co.edu.unicauca.users_ms.entity.Profesor;
import co.edu.unicauca.users_ms.repository.ProfesorDomainRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorService implements BaseService<Profesor, String> {

    @Autowired
    private ProfesorDomainRepository repository;

    @Override
    public List<Profesor> findAll() {
        return repository.findAll();
    }

    @Override
    public Profesor findById(String correo) {
        return repository.findByCorreo(correo).orElse(null);
    }

    @Override
    public Profesor save(Profesor profesor) {
        return repository.save(profesor);
    }

    @Override
    public Profesor updateById(Profesor profesor) {
        return repository.save(profesor);
    }

    @Override
    public boolean deleteById(String correo) {
        return false; // según tu estructura
    }

    public boolean existsByCorreo(String correo) {
        return repository.existsByCorreo(correo);
    }
}
