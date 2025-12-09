package co.edu.unicauca.administracionDocumental_ms.infra.repositoryJpa;

import co.edu.unicauca.administracionDocumental_ms.infra.jpa.PersonaJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaJpaRepository extends JpaRepository<PersonaJpa, Long> {
    Optional<PersonaJpa> findByCorreoElectronico(String correoElectronico);
}
