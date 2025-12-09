package co.edu.unicauca.administracionDocumental_ms.repository;

import co.edu.unicauca.administracionDocumental_ms.entities.ProyectoDeGrado;

import java.util.List;
import java.util.Optional;

public interface ProyectoDomainReposiroty {

    ProyectoDeGrado save(ProyectoDeGrado proyecto);

    Optional<ProyectoDeGrado> findById(Long id);

    List<ProyectoDeGrado> findAll();

    boolean existsById(Long id);

    void deleteById(Long id);
}
