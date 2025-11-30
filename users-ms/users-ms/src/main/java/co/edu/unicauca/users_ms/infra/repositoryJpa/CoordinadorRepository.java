package co.edu.unicauca.users_ms.infra.repositoryJpa;

import co.edu.unicauca.users_ms.infra.jpa.CoordinadorJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoordinadorRepository extends JpaRepository<CoordinadorJpa,Long> {
    Optional<CoordinadorJpa> findByCorreoElectronico(String correoElectronico);
}
