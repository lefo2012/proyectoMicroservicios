package co.edu.unicauca.users_ms.infra.adapters.repos;

import co.edu.unicauca.users_ms.entity.Estudiante;
import co.edu.unicauca.users_ms.infra.adapters.mappers.EstudianteMapper;
import co.edu.unicauca.users_ms.infra.jpa.EstudianteJpa;
import co.edu.unicauca.users_ms.infra.repositoryJpa.EstudianteRepository;
import co.edu.unicauca.users_ms.repository.EstudianteDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EstudianteRepositoryAdapter implements EstudianteDomainRepository {

    @Autowired
    private EstudianteRepository jpa;

    @Autowired
    private EstudianteMapper mapper;

    @Override
    public List<Estudiante> findAll() {
        return jpa.findAll().stream()
                .map(mapper::jpaToDomain)
                .toList();
    }

    @Override
    public Optional<Estudiante> findById(long id) {
        return jpa.findById(id)
                .map(mapper::jpaToDomain);
    }

    @Override
    public Optional<Estudiante> findByCorreo(String correo) {
        return jpa.findByCorreoElectronico(correo)
                .map(mapper::jpaToDomain);
    }

    @Override
    public Estudiante save(Estudiante estudiante) {
        EstudianteJpa saved = jpa.save(mapper.domainToJpa(estudiante));
        return mapper.jpaToDomain(saved);
    }
    @Override
    public boolean existsByCorreo(String correo) {
        return jpa.findByCorreoElectronico(correo).isPresent();
    }
}