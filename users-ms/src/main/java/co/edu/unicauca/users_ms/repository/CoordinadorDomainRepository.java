package co.edu.unicauca.users_ms.repository;

import co.edu.unicauca.users_ms.entity.Coordinador;

import java.util.List;
import java.util.Optional;

public interface CoordinadorDomainRepository {
    Optional<Coordinador> findByCorreo(String correo);
    Coordinador save(Coordinador coordinador);
    List<Coordinador> findAll();
}