package co.edu.unicauca.users_ms.repository;

import co.edu.unicauca.users_ms.entity.JefeDepartamento;

import java.util.List;
import java.util.Optional;

public interface JefeDepartamentoDomainRepository {

    List<JefeDepartamento> findAll();

    Optional<JefeDepartamento> findById(long id);

    Optional<JefeDepartamento> findByCorreo(String correo);

    JefeDepartamento save(JefeDepartamento jefe);

    boolean existsByCorreo(String correo);
}
