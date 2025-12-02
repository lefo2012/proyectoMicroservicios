package co.edu.unicauca.administracionDocumental_ms.infra.adapters.repos;


import co.edu.unicauca.administracionDocumental_ms.entities.Departamento;
import co.edu.unicauca.administracionDocumental_ms.infra.adapters.mappers.DepartamentoMapper;
import co.edu.unicauca.administracionDocumental_ms.infra.jpa.DepartamentoJpa;
import co.edu.unicauca.administracionDocumental_ms.infra.repositoryJpa.DepartamentoJpaRepository;
import co.edu.unicauca.administracionDocumental_ms.repository.DepartamentoDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DepartamentoRepositoryAdapter implements DepartamentoDomainRepository {

    @Autowired
    private DepartamentoJpaRepository jpa;

    @Autowired
    private DepartamentoMapper mapper;

    @Override
    public List<Departamento> findAll() {
        return jpa.findAll().stream()
                .map(mapper::jpaToDomain)
                .toList();
    }

    @Override
    public Optional<Departamento> findById(Integer id) {
        return jpa.findById(id)
                .map(mapper::jpaToDomain);
    }

    @Override
    public Departamento save(Departamento d) {
        DepartamentoJpa saved = jpa.save(mapper.domainToJpa(d));
        return mapper.jpaToDomain(saved);
    }

}
