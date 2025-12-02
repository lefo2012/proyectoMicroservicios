package co.edu.unicauca.administracionDocumental_ms.infra.adapters.repos;

import co.edu.unicauca.administracionDocumental_ms.entities.Coordinador;
import co.edu.unicauca.administracionDocumental_ms.infra.adapters.mappers.CoordinadorMapper;
import co.edu.unicauca.administracionDocumental_ms.infra.jpa.CoordinadorJpa;
import co.edu.unicauca.administracionDocumental_ms.infra.repositoryJpa.CoordinadorJpaRepository;
import co.edu.unicauca.administracionDocumental_ms.repository.CoordinadorDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CoordinadorRepositoryAdapter implements CoordinadorDomainRepository {

    @Autowired
    private CoordinadorJpaRepository repository;

    @Autowired
    private CoordinadorMapper mapper;

    @Override
    public Optional<Coordinador> findByCorreo(String correo) {
        return repository.findByCorreoElectronico(correo).map(mapper::jpaToDomain);
    }

    @Override
    public Coordinador save(Coordinador coordinador) {
        CoordinadorJpa saved = repository.save(mapper.domainToJpa(coordinador));
        return mapper.jpaToDomain(saved);
    }

    @Override
    public List<Coordinador> findAll() {
        return repository.findAll().stream().map(mapper::jpaToDomain).toList();
    }
}
