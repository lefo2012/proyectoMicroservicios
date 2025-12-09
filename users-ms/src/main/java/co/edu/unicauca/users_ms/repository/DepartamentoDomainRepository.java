package co.edu.unicauca.users_ms.repository;

import co.edu.unicauca.users_ms.entity.Departamento;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartamentoDomainRepository {
    List<Departamento> findAll();

    Optional<Departamento> findById(Integer id);

    Departamento save(Departamento d);
}
