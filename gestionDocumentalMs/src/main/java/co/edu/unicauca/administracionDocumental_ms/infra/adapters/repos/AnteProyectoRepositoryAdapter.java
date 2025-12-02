package co.edu.unicauca.administracionDocumental_ms.infra.adapters.repos;

import co.edu.unicauca.administracionDocumental_ms.entities.AnteProyecto;
import co.edu.unicauca.administracionDocumental_ms.infra.adapters.mappers.AnteProyectoMapper;
import co.edu.unicauca.administracionDocumental_ms.infra.jpa.AnteProyectoJpa;
import co.edu.unicauca.administracionDocumental_ms.infra.repositoryJpa.AnteProyectoJpaRepository;
import co.edu.unicauca.administracionDocumental_ms.repository.AnteProyectoDomainRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AnteProyectoRepositoryAdapter implements AnteProyectoDomainRepository
{
    @Autowired
    private AnteProyectoJpaRepository repository;

    @Autowired
    private AnteProyectoMapper mapper;

    @Override
    public AnteProyecto save(AnteProyecto anteProyecto) {
        AnteProyectoJpa saved = repository.save(mapper.domainToJpa(anteProyecto));
        return mapper.jpaToDomain(saved);
    }

    @Override
    public Optional<AnteProyecto> findById(Long id) {
        return repository.findById(id).map(mapper::jpaToDomain);
    }

    @Override
    public List<AnteProyecto> findAll() {
        return repository.findAll().stream().map(mapper::jpaToDomain).toList();
    }

}
