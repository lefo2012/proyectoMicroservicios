package co.edu.unicauca.users_ms.infra.adapters.repos;

import co.edu.unicauca.users_ms.entity.Coordinador;
import co.edu.unicauca.users_ms.infra.adapters.mappers.CoordinadorMapper;
import co.edu.unicauca.users_ms.infra.jpa.CoordinadorJpa;
import co.edu.unicauca.users_ms.infra.repositoryJpa.CoordinadorRepository;
import co.edu.unicauca.users_ms.repository.CoordinadorDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CoordinadorRepositoryAdapter implements CoordinadorDomainRepository {

    @Autowired
    private CoordinadorRepository jpaRepository;

    @Autowired
    private CoordinadorMapper mapper;

    @Override
    public Optional<Coordinador> findByCorreo(String correo) {
        return jpaRepository.findByCorreoElectronico(correo)
                .map(mapper::jpaToDomain);
    }

    @Override
    public Coordinador save(Coordinador coordinador) {
        CoordinadorJpa saved = jpaRepository.save(mapper.domainToJpa(coordinador));
        return mapper.jpaToDomain(saved);
    }

    @Override
    public List<Coordinador> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::jpaToDomain)
                .toList();
    }
}
