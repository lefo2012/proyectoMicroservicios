package co.edu.unicauca.administracionDocumental_ms.repository;

import co.edu.unicauca.administracionDocumental_ms.entities.Profesor;

import java.util.List;
import java.util.Optional;

public interface ProfesorDomainRepository {
    List<Profesor> findAll();

    Optional<Profesor> findByCorreo(String correo);

    Profesor save(Profesor profesor);

    boolean existsByCorreo(String correo);
}
