package co.edu.unicauca.administracionDocumental_ms.repository;

import co.edu.unicauca.administracionDocumental_ms.entities.Departamento;

import java.util.List;
import java.util.Optional;

public interface DepartamentoDomainRepository {
    List<Departamento> findAll();

    Optional<Departamento> findById(Integer id);

    Departamento save(Departamento d);
}
