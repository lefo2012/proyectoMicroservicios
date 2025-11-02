package co.edu.unicauca.administracionDocumental_ms.repository;

import co.edu.unicauca.administracionDocumental_ms.entities.JefeDepartamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JefeDepartamentoRepository extends JpaRepository<JefeDepartamento, Long> {
    Optional<JefeDepartamento> findByCorreoElectronico(String correoElectronico);
}
