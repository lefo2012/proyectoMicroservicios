package co.edu.unicauca.users_ms.infra.repositoryJpa;

import co.edu.unicauca.users_ms.infra.jpa.JefeDepartamentoJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JefeDepartamentoRepository extends JpaRepository<JefeDepartamentoJpa, Long> {
    Optional<JefeDepartamentoJpa> findByCorreoElectronico(String correoElectronico);
}
