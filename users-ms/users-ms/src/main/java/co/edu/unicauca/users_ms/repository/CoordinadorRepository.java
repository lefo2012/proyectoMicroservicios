package co.edu.unicauca.users_ms.repository;

import co.edu.unicauca.users_ms.entity.Coordinador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoordinadorRepository extends JpaRepository<Coordinador,Long> {
    Optional<Coordinador> findByCorreoElectronico(String correoElectronico);
}
