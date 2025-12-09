package co.edu.unicauca.administracionDocumental_ms.repository;

import co.edu.unicauca.administracionDocumental_ms.entities.AnteProyecto;

import java.util.List;
import java.util.Optional;

public interface AnteProyectoDomainRepository {
    AnteProyecto save(AnteProyecto anteProyecto);

    Optional<AnteProyecto> findById(Long id);

    List<AnteProyecto> findAll();
}
