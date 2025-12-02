package co.edu.unicauca.administracionDocumental_ms.infra.adapters.repos;

import co.edu.unicauca.administracionDocumental_ms.entities.Programa;
import co.edu.unicauca.administracionDocumental_ms.infra.adapters.mappers.ProgramaMapper;
import co.edu.unicauca.administracionDocumental_ms.infra.jpa.ProgramaJpa;
import co.edu.unicauca.administracionDocumental_ms.infra.repositoryJpa.ProgramaJpaRepository;
import co.edu.unicauca.administracionDocumental_ms.repository.ProgramaDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProgramaRepositoryAdapter implements ProgramaDomainRepository {

    @Autowired
    private ProgramaJpaRepository repository;

    @Autowired
    private ProgramaMapper mapper;

    @Override
    public List<Programa> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::jpaToDomain)
                    .toList();
    }

    @Override
    public Optional<Programa> findById(Integer id) {
        return repository.findById(id)
                .map(mapper::jpaToDomain);
    }

    @Override
    public Programa save(Programa programa) {
        ProgramaJpa saved = repository.save(mapper.domainToJpa(programa));
        return mapper.jpaToDomain(saved);
    }

    @Override
    public boolean deleteById(Integer id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }

    @Override
    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }
}

