package co.edu.unicauca.users_ms.infra.adapters.repos;

import co.edu.unicauca.users_ms.entity.JefeDepartamento;
import co.edu.unicauca.users_ms.infra.adapters.mappers.JefeDepartamentoMapper;
import co.edu.unicauca.users_ms.infra.jpa.JefeDepartamentoJpa;
import co.edu.unicauca.users_ms.infra.repositoryJpa.JefeDepartamentoRepository;
import co.edu.unicauca.users_ms.repository.JefeDepartamentoDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JefeDepartamentoRepositoryAdapter implements JefeDepartamentoDomainRepository {

    @Autowired
    private JefeDepartamentoRepository jpa;

    @Autowired
    private JefeDepartamentoMapper mapper;

    @Override
    public List<JefeDepartamento> findAll() {
        return jpa.findAll()
                .stream()
                .map(mapper::jpaToDomain)
                .toList();
    }

    @Override
    public Optional<JefeDepartamento> findById(long id) {
        return jpa.findById(id)
                .map(mapper::jpaToDomain);
    }

    @Override
    public Optional<JefeDepartamento> findByCorreo(String correo) {
        return jpa.findByCorreoElectronico(correo)
                .map(mapper::jpaToDomain);
    }

    @Override
    public JefeDepartamento save(JefeDepartamento jefe) {
        JefeDepartamentoJpa saved = jpa.save(mapper.domainToJpa(jefe));
        return mapper.jpaToDomain(saved);
    }

    @Override
    public boolean existsByCorreo(String correo) {
        return jpa.findByCorreoElectronico(correo).isPresent();
    }
}