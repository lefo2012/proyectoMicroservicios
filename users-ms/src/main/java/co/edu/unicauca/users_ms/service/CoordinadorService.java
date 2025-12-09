package co.edu.unicauca.users_ms.service;

import co.edu.unicauca.users_ms.entity.Coordinador;
import co.edu.unicauca.users_ms.repository.CoordinadorDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoordinadorService implements BaseService<Coordinador,String> {

    @Autowired
    private CoordinadorDomainRepository repository;

    @Override
    public List<Coordinador> findAll() {
        return repository.findAll();
    }

    @Override
    public Coordinador findById(String correo) {
        return repository.findByCorreo(correo).orElse(null);
    }

    @Override
    public Coordinador save(Coordinador coordinador) {
        return repository.save(coordinador);
    }

    @Override
    public Coordinador updateById(Coordinador entity) {
        return repository.save(entity);
    }

    @Override
    public boolean deleteById(String correo) {
        return false;
    }

    public boolean existsByCorreo(String correo) {
        return repository.findByCorreo(correo).isPresent();
    }
}

