package co.edu.unicauca.administracionDocumental_ms.infra.adapters.repos;

import co.edu.unicauca.administracionDocumental_ms.entities.ProyectoDeGrado;
import co.edu.unicauca.administracionDocumental_ms.infra.adapters.mappers.ProyectoDeGradoMapper;
import co.edu.unicauca.administracionDocumental_ms.infra.jpa.ProyectoDeGradoJpa;
import co.edu.unicauca.administracionDocumental_ms.infra.repositoryJpa.ProyectoDeGradoJpaRepository;
import co.edu.unicauca.administracionDocumental_ms.repository.ProyectoDomainReposiroty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProyectoDeGradoAdapter implements ProyectoDomainReposiroty {

    @Autowired
    private ProyectoDeGradoJpaRepository repository;

    @Autowired
    private ProyectoDeGradoMapper mapper;

    @Override
    public ProyectoDeGrado save(ProyectoDeGrado proyecto) {
        ProyectoDeGradoJpa saved = repository.save(mapper.domainToJpa(proyecto));
        return mapper.jpaToDomain(saved);
    }

    @Override
    public Optional<ProyectoDeGrado> findById(Long id) {
        return repository.findById(id).map(mapper::jpaToDomain);
    }

    @Override
    public List<ProyectoDeGrado> findAll() {
        return repository.findAll().stream().map(mapper::jpaToDomain).toList();
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
