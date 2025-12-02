package co.edu.unicauca.administracionDocumental_ms.infra.adapters.repos;

import co.edu.unicauca.administracionDocumental_ms.entities.Estudiante;
import co.edu.unicauca.administracionDocumental_ms.infra.adapters.mappers.EstudianteMapper;
import co.edu.unicauca.administracionDocumental_ms.infra.jpa.EstudianteJpa;
import co.edu.unicauca.administracionDocumental_ms.infra.repositoryJpa.EstudianteJpaRepository;
import co.edu.unicauca.administracionDocumental_ms.repository.EstudianteDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EstudianteRepositoryAdapter implements EstudianteDomainRepository {

    @Autowired
    private EstudianteJpaRepository jpa;

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

    public Optional<Estudiante> findByCorreoLista(String correo)
    {
        return jpa.findByCorreoLista(correo)
                .map(mapper::jpaToDomain);
    }
}