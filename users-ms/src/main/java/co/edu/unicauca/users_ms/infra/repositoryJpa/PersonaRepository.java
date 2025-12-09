package co.edu.unicauca.users_ms.infra.repositoryJpa;

import co.edu.unicauca.users_ms.infra.jpa.PersonaJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<PersonaJpa, String> {
    Optional<PersonaJpa> findByCorreoElectronico(String correoElectronico);
}
