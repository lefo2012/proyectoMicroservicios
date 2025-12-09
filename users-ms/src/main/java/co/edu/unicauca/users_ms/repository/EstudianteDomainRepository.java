package co.edu.unicauca.users_ms.repository;

import co.edu.unicauca.users_ms.entity.Estudiante;

import java.util.List;
import java.util.Optional;

public interface EstudianteDomainRepository {

    List<Estudiante> findAll();

    Optional<Estudiante> findById(long id);

    Optional<Estudiante> findByCorreo(String correo);

    Estudiante save(Estudiante estudiante);

    boolean existsByCorreo(String correo);
}
