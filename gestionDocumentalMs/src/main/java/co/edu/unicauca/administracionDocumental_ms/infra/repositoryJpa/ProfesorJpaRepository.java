package co.edu.unicauca.administracionDocumental_ms.infra.repositoryJpa;

import co.edu.unicauca.administracionDocumental_ms.infra.jpa.ProfesorJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfesorJpaRepository extends JpaRepository<ProfesorJpa, Long> {
    Optional<ProfesorJpa> findByCorreoElectronico(String correoElectronico);
}
