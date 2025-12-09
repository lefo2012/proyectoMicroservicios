package co.edu.unicauca.administracionDocumental_ms.repository;

import co.edu.unicauca.administracionDocumental_ms.entities.Coordinador;

import java.util.List;
import java.util.Optional;

public interface CoordinadorDomainRepository {
    Optional<Coordinador> findByCorreo(String correo);
    Coordinador save(Coordinador coordinador);
    List<Coordinador> findAll();
}
