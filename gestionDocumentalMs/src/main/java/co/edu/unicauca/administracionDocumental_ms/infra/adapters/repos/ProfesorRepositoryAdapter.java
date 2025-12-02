package co.edu.unicauca.administracionDocumental_ms.infra.adapters.repos;

import co.edu.unicauca.administracionDocumental_ms.entities.Profesor;
import co.edu.unicauca.administracionDocumental_ms.infra.adapters.mappers.ProfesorMapper;
import co.edu.unicauca.administracionDocumental_ms.infra.jpa.ProfesorJpa;
import co.edu.unicauca.administracionDocumental_ms.infra.repositoryJpa.ProfesorJpaRepository;
import co.edu.unicauca.administracionDocumental_ms.repository.ProfesorDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProfesorRepositoryAdapter implements ProfesorDomainRepository {

    @Autowired
    private ProfesorJpaRepository repository;

    @Autowired
    private ProfesorMapper mapper;

    @Override
    public List<Profesor> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::jpaToDomain)
                .toList();
    }

    @Override
    public Optional<Profesor> findByCorreo(String correo) {
        return repository.findByCorreoElectronico(correo)
                .map(mapper::jpaToDomain);
    }

    @Override
    public Profesor save(Profesor profesor) {
        ProfesorJpa saved = repository.save(mapper.domainToJpa(profesor));
        return mapper.jpaToDomain(saved);
    }

    @Override
    public boolean existsByCorreo(String correo) {
        return repository.findByCorreoElectronico(correo).isPresent();
    }
}

