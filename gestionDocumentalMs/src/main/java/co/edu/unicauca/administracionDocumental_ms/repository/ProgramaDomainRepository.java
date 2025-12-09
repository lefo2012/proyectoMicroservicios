package co.edu.unicauca.administracionDocumental_ms.repository;

import co.edu.unicauca.administracionDocumental_ms.entities.Programa;

import java.util.List;
import java.util.Optional;

public interface ProgramaDomainRepository {
    List<Programa> findAll();

    Optional<Programa> findById(Integer id);

    Programa save(Programa programa);

    boolean deleteById(Integer id);

    boolean existsById(Integer id);
}
